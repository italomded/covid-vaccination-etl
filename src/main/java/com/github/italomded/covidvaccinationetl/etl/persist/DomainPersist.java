package com.github.italomded.covidvaccinationetl.etl.persist;

import com.github.italomded.covidvaccinationetl.domain.dimension.Dimension;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public abstract class DomainPersist {
    protected DomainPersist next;

    public Dimension run(Dimension dimension, EntityManager entityManager) {
        if (when(dimension)) {
            return verify(dimension, entityManager);
        } else {
            if (next != null) return next.run(dimension, entityManager);
            throw new DomainPersistException("No domain persistence class listens to the type passed as an argument!", dimension.getClass());
        }
    }

    public Dimension persist(Dimension dimension, EntityManager entityManager, Query query) {
        List<Dimension> dimensionList = query.getResultList();
        if (dimensionList.isEmpty()) {
            entityManager.persist(dimension);
            return dimension;
        }
        return dimensionList.get(0);
    }

    protected abstract Dimension verify(Dimension dimension, EntityManager entityManager);
    protected abstract boolean when(Dimension dimension);
}
