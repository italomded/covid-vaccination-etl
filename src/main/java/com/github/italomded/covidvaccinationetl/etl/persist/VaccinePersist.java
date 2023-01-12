package com.github.italomded.covidvaccinationetl.etl.persist;

import com.github.italomded.covidvaccinationetl.domain.dimension.Dimension;
import com.github.italomded.covidvaccinationetl.domain.dimension.Vaccine;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class VaccinePersist extends DomainPersist {
    private String queryText = "from Vaccine where name = :name";

    public VaccinePersist(DomainPersist next) {
        super(next);
    }

    @Override
    protected boolean verify(Dimension dimension, EntityManager entityManager) {
        Vaccine vaccine = (Vaccine) dimension;
        Query query = entityManager.createQuery(queryText);
        query.setParameter("name", vaccine.getName());

        return super.persist(dimension, entityManager, query);
    }

    @Override
    protected boolean when(Dimension dimension) {
        return dimension instanceof Vaccine;
    }
}
