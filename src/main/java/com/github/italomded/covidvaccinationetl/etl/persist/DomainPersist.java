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

    public boolean run(Dimension dimension, EntityManager entityManager) {
        if (when(dimension)) {
            return verify(dimension, entityManager);
        } else {
            if (next != null) return next.run(dimension, entityManager);
            return false;
        }
    }

    public boolean persist(Dimension dimension, EntityManager entityManager, Query query) {
        List<Dimension> dimensionList = query.getResultList();
        if (dimensionList.isEmpty()) {
            entityManager.persist(dimension);
            return true;
        }
        return false;
    }

    protected abstract boolean verify(Dimension dimension, EntityManager entityManager);
    protected abstract boolean when(Dimension dimension);
}
