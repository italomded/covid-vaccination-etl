package com.github.italomded.covidvaccinationetl.etl.persist;

import com.github.italomded.covidvaccinationetl.domain.dimension.Dimension;
import com.github.italomded.covidvaccinationetl.domain.dimension.VaccinationDate;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class VaccinationDatePersist extends DomainPersist {
    private String queryText = "from VaccinationDate where fullDate = :fullDate";

    public VaccinationDatePersist(DomainPersist next) {
        super(next);
    }

    @Override
    protected boolean verify(Dimension dimension, EntityManager entityManager) {
        VaccinationDate vaccinationDate = (VaccinationDate) dimension;
        Query query = entityManager.createQuery(queryText);
        query.setParameter("fullDate", vaccinationDate.getFullDate());

        return super.persist(dimension, entityManager, query);
    }

    @Override
    protected boolean when(Dimension dimension) {
        return dimension instanceof VaccinationDate;
    }
}
