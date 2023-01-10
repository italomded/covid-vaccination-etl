package com.github.italomded.covidvaccinationetl.utilities;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerSingleton {
    private static EntityManager entityManager;

    public static EntityManager getEntityManager() {
        if (entityManager == null) {
            EntityManagerFactory database = Persistence.createEntityManagerFactory("covid-vaccination-etl");
            entityManager = database.createEntityManager();
        }
        return entityManager;
    }
}
