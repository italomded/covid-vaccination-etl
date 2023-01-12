package com.github.italomded.covidvaccinationetl.etl;

import com.github.italomded.covidvaccinationetl.domain.csv.Line;
import com.github.italomded.covidvaccinationetl.domain.dimension.*;
import com.github.italomded.covidvaccinationetl.etl.persist.*;
import com.github.italomded.covidvaccinationetl.utilities.EntityManagerSingleton;
import com.github.italomded.covidvaccinationetl.utilities.lineconverter.LineConverter;
import jakarta.persistence.EntityManager;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Persist {
    private List<Dimension> dimensions;
    private DomainPersist domainPersist;

    private Set<Line> data;

    public Persist(Set<Line> data) {
        this.data = data;
        dimensions = Arrays.asList(
                new BiologicalSex(), new Birthdate(), new Color(), new PatientAdress(), new VaccinationDate(),
                new VaccinationSite(), new Vaccine(), new VaccineDose()
        );
        domainPersist = new BiologicalSexPersist(
                new BirthdatePersist(
                        new ColorPersist(
                                new PatientAdressPersist(
                                        new VaccinationDatePersist(
                                                new VaccineDosePersist(
                                                        new VaccinePersist()))))));
    }

    public void populate() {
        EntityManager entityManager = EntityManagerSingleton.getEntityManager();
        entityManager.getTransaction().begin();
        for (Line line : data) {
            for (Dimension dimension : dimensions) {
                dimension = LineConverter.toDimension(dimension, line);
                domainPersist.run(dimension, entityManager);
            }
        }
        entityManager.getTransaction().commit();
    }
}
