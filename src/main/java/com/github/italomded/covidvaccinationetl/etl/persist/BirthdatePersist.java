package com.github.italomded.covidvaccinationetl.etl.persist;

import com.github.italomded.covidvaccinationetl.domain.dimension.Birthdate;
import com.github.italomded.covidvaccinationetl.domain.dimension.Dimension;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BirthdatePersist extends DomainPersist {
    private String queryText = "from Birthdate where fullDate = :fullDate";

    public BirthdatePersist(DomainPersist next) {
        super(next);
    }

    @Override
    protected boolean verify(Dimension dimension, EntityManager entityManager) {
        Birthdate birthdate = (Birthdate) dimension;
        Query query = entityManager.createQuery(queryText);
        query.setParameter("fullDate", birthdate.getFullDate());

        return super.persist(dimension, entityManager, query);
    }

    @Override
    protected boolean when(Dimension dimension) {
        return dimension instanceof Birthdate;
    }
}
