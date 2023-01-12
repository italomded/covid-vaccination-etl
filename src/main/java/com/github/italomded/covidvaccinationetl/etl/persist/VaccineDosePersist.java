package com.github.italomded.covidvaccinationetl.etl.persist;

import com.github.italomded.covidvaccinationetl.domain.dimension.Dimension;
import com.github.italomded.covidvaccinationetl.domain.dimension.VaccineDose;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class VaccineDosePersist extends DomainPersist {
    private String queryText = "from VaccineDose where dose = :dose";

    public VaccineDosePersist(DomainPersist next) {
        super(next);
    }

    @Override
    protected boolean verify(Dimension dimension, EntityManager entityManager) {
        VaccineDose vaccineDose = (VaccineDose) dimension;
        Query query = entityManager.createQuery(queryText);
        query.setParameter("dose", vaccineDose.getDose());

        return super.persist(dimension, entityManager, query);
    }

    @Override
    protected boolean when(Dimension dimension) {
        return dimension instanceof VaccineDose;
    }
}
