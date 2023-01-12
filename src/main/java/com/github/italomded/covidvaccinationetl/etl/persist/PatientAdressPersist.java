package com.github.italomded.covidvaccinationetl.etl.persist;

import com.github.italomded.covidvaccinationetl.domain.dimension.Dimension;
import com.github.italomded.covidvaccinationetl.domain.dimension.PatientAdress;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PatientAdressPersist extends DomainPersist {
    private String queryText = "from PatientAdress where county = :county";

    public PatientAdressPersist(DomainPersist next) {
        super(next);
    }

    @Override
    protected boolean verify(Dimension dimension, EntityManager entityManager) {
        PatientAdress patientAdress = (PatientAdress) dimension;
        Query query = entityManager.createQuery(queryText);
        query.setParameter("county", patientAdress.getCounty());

        return super.persist(dimension, entityManager, query);
    }

    @Override
    protected boolean when(Dimension dimension) {
        return dimension instanceof PatientAdress;
    }
}
