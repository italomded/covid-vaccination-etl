package com.github.italomded.covidvaccinationetl.etl;

import com.github.italomded.covidvaccinationetl.domain.csv.Line;
import com.github.italomded.covidvaccinationetl.domain.dimension.*;
import com.github.italomded.covidvaccinationetl.domain.fact.Patient;
import com.github.italomded.covidvaccinationetl.etl.persist.*;
import com.github.italomded.covidvaccinationetl.etl.setter.*;
import com.github.italomded.covidvaccinationetl.utilities.EntityManagerSingleton;
import com.github.italomded.covidvaccinationetl.utilities.lineconverter.LineConverter;
import jakarta.persistence.EntityManager;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Persist {
    private List<Dimension> dimensions;
    private DomainPersist domainPersist;
    private FactRelationSetter factRelationsSetter;

    private Set<Line> data;

    public Persist(Set<Line> data) {
        this.data = data;
        dimensions = Arrays.asList(
                new BiologicalSex(), new Birthdate(), new Color(), new PatientAddress(), new VaccinationDate(),
                new VaccinationSite(), new Vaccine(), new VaccineDose()
        );
        domainPersist = new BiologicalSexPersist(
                new BirthdatePersist(
                        new ColorPersist(
                                new PatientAddressPersist(
                                        new VaccinationDatePersist(
                                                new VaccinationSitePersist(
                                                        new VaccineDosePersist(
                                                            new VaccinePersist())))))));
        factRelationsSetter = new BiologicalSexSetter(
                new BirthdateSetter(
                        new ColorSetter(
                                new PatientAddressSetter(
                                        new VaccinationDateSetter(
                                                new VaccinationSiteSetter(
                                                        new VaccineDoseSetter(
                                                                new VaccineSetter())))))));
    }

    public void populate() {
        EntityManager entityManager = EntityManagerSingleton.getEntityManager();
        entityManager.getTransaction().begin();
        Set<Dimension> factDimensions = new HashSet<>();
        for (Line line : data) {
            factDimensions.clear();
            for (Dimension dimension : dimensions) {
                dimension = LineConverter.toDimension(dimension, line);
                dimension = domainPersist.run(dimension, entityManager);
                factDimensions.add(dimension);
            }
            Patient patient = LineConverter.toPatient(line);
            for (Dimension dimension : factDimensions) {
                patient = factRelationsSetter.run(patient, dimension);
            }
            entityManager.persist(patient);
        }
        entityManager.getTransaction().commit();
    }
}
