package com.github.italomded.covidvaccinationetl.etl;

import com.github.italomded.covidvaccinationetl.domain.csv.Line;
import com.github.italomded.covidvaccinationetl.domain.dimension.*;
import com.github.italomded.covidvaccinationetl.utilities.EntityManagerSingleton;
import com.github.italomded.covidvaccinationetl.utilities.lineconverter.LineConverter;
import jakarta.persistence.EntityManager;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Persist {
    private List<Dimension> dimensions;
    private Set<Line> data;

    public Persist(Set<Line> data) {
        this.data = data;
        dimensions = Arrays.asList(
                new BiologicalSex(), new Birthdate(), new Color(), new PatientAdress(), new VaccinationDate(),
                new VaccinationSite(), new Vaccine(), new VaccineDose()
        );
    }

    public void populate() {
        EntityManager entityManager = EntityManagerSingleton.getEntityManager();
        entityManager.getTransaction().begin();
        for (Line line : data) {
            for (Dimension dimension : dimensions) {
                dimension = LineConverter.toDimension(dimension, line);
                entityManager.persist(dimension);
            }
        }
        entityManager.getTransaction().commit();
    }
}
