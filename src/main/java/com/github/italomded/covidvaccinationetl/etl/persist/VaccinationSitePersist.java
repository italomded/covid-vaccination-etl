package com.github.italomded.covidvaccinationetl.etl.persist;

import com.github.italomded.covidvaccinationetl.domain.dimension.Dimension;
import com.github.italomded.covidvaccinationetl.domain.dimension.VaccinationSite;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class VaccinationSitePersist extends DomainPersist {
    private String queryText = "from VaccinationSite where corporateName = :corporateName";

    public VaccinationSitePersist(DomainPersist next) {
        super(next);
    }

    @Override
    protected boolean verify(Dimension dimension, EntityManager entityManager) {
        VaccinationSite vaccinationSite = (VaccinationSite) dimension;
        Query query = entityManager.createQuery(queryText);
        query.setParameter("corporateName", vaccinationSite.getCorporateName());

        return super.persist(dimension, entityManager, query);
    }

    @Override
    protected boolean when(Dimension dimension) {
        return dimension instanceof VaccinationSite;
    }
}
