package com.github.italomded.covidvaccinationetl.etl.persist;

import com.github.italomded.covidvaccinationetl.domain.dimension.Color;
import com.github.italomded.covidvaccinationetl.domain.dimension.Dimension;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ColorPersist extends DomainPersist {
    private String queryText = "from Color where representation = :representation";

    public ColorPersist(DomainPersist next) {
        super(next);
    }

    @Override
    protected Dimension verify(Dimension dimension, EntityManager entityManager) {
        Color color = (Color) dimension;
        Query query = entityManager.createQuery(queryText);
        query.setParameter("representation", color.getRepresentation());

        return super.persist(dimension, entityManager, query);
    }

    @Override
    protected boolean when(Dimension dimension) {
        return dimension instanceof Color;
    }
}
