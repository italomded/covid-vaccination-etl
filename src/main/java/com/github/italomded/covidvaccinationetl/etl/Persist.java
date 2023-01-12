package com.github.italomded.covidvaccinationetl.etl;

import com.github.italomded.covidvaccinationetl.domain.History;
import com.github.italomded.covidvaccinationetl.domain.csv.Line;
import com.github.italomded.covidvaccinationetl.domain.dimension.*;
import com.github.italomded.covidvaccinationetl.domain.fact.Patient;
import com.github.italomded.covidvaccinationetl.etl.persist.*;
import com.github.italomded.covidvaccinationetl.etl.setter.*;
import com.github.italomded.covidvaccinationetl.utilities.EntityManagerSingleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
public class Persist {
    private List<Dimension> dimensions;
    private DomainPersist domainPersist;
    private FactRelationSetter factRelationsSetter;
    private LineConverter lineConverter;
    private Set<Line> data;

    public void populate() {
        EntityManager entityManager = EntityManagerSingleton.getEntityManager();
        entityManager.getTransaction().begin();
        Set<Dimension> factDimensions = new HashSet<>();

        long createdFacts = 0;

        for (Line line : data) {
            factDimensions.clear();
            for (Dimension dimension : dimensions) {
                dimension = lineConverter.toDimension(dimension, line);
                dimension = domainPersist.run(dimension, entityManager);
                factDimensions.add(dimension);
            }
            Patient patient = lineConverter.toPatient(line);
            if (!patientExists(patient, entityManager)) {
                for (Dimension dimension : factDimensions) {
                    patient = factRelationsSetter.run(patient, dimension);
                }
                entityManager.persist(patient);
                createdFacts++;
            }
        }
        makeHistory(createdFacts, entityManager);
        entityManager.getTransaction().commit();
    }

    private boolean patientExists(Patient patient, EntityManager entityManager) {
        String queryText = "from Patient where patientIdentifier = :patientIdentifier";
        Query query = entityManager.createQuery(queryText);
        query.setParameter("patientIdentifier", patient.getPatientIdentifier());

        List resultList = query.getResultList();
        if (resultList.isEmpty()) return false;
        return true;
    }

    private void makeHistory(long createdFacts, EntityManager entityManager) {
        History history = new History(createdFacts, data.size(), LocalDateTime.now());
        entityManager.persist(history);
    }
}
