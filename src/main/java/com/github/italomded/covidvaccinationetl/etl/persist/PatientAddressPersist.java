package com.github.italomded.covidvaccinationetl.etl.persist;

import com.github.italomded.covidvaccinationetl.domain.dimension.Dimension;
import com.github.italomded.covidvaccinationetl.domain.dimension.PatientAddress;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PatientAddressPersist extends DomainPersist {
    private String queryText = "from PatientAddress where county = :county";

    public PatientAddressPersist(DomainPersist next) {
        super(next);
    }

    @Override
    protected Dimension verify(Dimension dimension, EntityManager entityManager) {
        PatientAddress patientAddress = (PatientAddress) dimension;
        Query query = entityManager.createQuery(queryText);
        query.setParameter("county", patientAddress.getCounty());

        return super.persist(dimension, entityManager, query);
    }

    @Override
    protected boolean when(Dimension dimension) {
        return dimension instanceof PatientAddress;
    }
}
