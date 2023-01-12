package com.github.italomded.covidvaccinationetl.etl.setter;

import com.github.italomded.covidvaccinationetl.domain.dimension.Dimension;
import com.github.italomded.covidvaccinationetl.domain.fact.Patient;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public abstract class FactRelationSetter {
    protected FactRelationSetter next;

    public Patient run(Patient patient, Dimension dimension) {
        if (when(dimension)) {
            return set(patient, dimension);
        } else {
            if (next != null) return next.run(patient, dimension);
            throw new FactRelationSetterException("No relationship setter class matches the type passed as an argument!", dimension.getClass());
        }
    }

    protected abstract Patient set(Patient patient, Dimension dimension);
    protected abstract boolean when(Dimension dimension);
}
