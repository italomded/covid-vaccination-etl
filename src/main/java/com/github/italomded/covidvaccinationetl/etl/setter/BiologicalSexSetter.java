package com.github.italomded.covidvaccinationetl.etl.setter;

import com.github.italomded.covidvaccinationetl.domain.dimension.BiologicalSex;
import com.github.italomded.covidvaccinationetl.domain.dimension.Dimension;
import com.github.italomded.covidvaccinationetl.domain.fact.Patient;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BiologicalSexSetter extends FactRelationSetter {
    public BiologicalSexSetter(FactRelationSetter next) {
        super(next);
    }

    @Override
    protected Patient set(Patient patient, Dimension dimension) {
        BiologicalSex biologicalSex = (BiologicalSex) dimension;
        patient.setBiologicalSex(biologicalSex);
        return patient;
    }

    @Override
    protected boolean when(Dimension dimension) {
        return dimension instanceof BiologicalSex;
    }
}
