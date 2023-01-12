package com.github.italomded.covidvaccinationetl.etl.persist;

import com.github.italomded.covidvaccinationetl.domain.dimension.BiologicalSex;
import com.github.italomded.covidvaccinationetl.domain.dimension.Dimension;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BiologicalSexPersist extends DomainPersist {
    private String queryText = "from BiologicalSex where representation = :nome";

    public BiologicalSexPersist(DomainPersist next) {
        super(next);
    }

    @Override
    protected Dimension verify(Dimension dimension, EntityManager entityManager) {
        BiologicalSex biologicalSex = (BiologicalSex) dimension;
        Query query = entityManager.createQuery(queryText);
        query.setParameter("nome", biologicalSex.getRepresentation());

        return super.persist(dimension, entityManager, query);
    }

    @Override
    protected boolean when(Dimension dimension) {
        return dimension instanceof BiologicalSex;
    }
}
