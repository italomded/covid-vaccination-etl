package com.github.italomded.covidvaccinationetl.etl.setter;

import com.github.italomded.covidvaccinationetl.domain.dimension.Color;
import com.github.italomded.covidvaccinationetl.domain.dimension.Dimension;
import com.github.italomded.covidvaccinationetl.domain.fact.Patient;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ColorSetter extends FactRelationSetter {
    public ColorSetter(FactRelationSetter next) {
        super(next);
    }

    @Override
    protected Patient set(Patient patient, Dimension dimension) {
        Color color = (Color) dimension;
        patient.setColor(color);
        return patient;
    }

    @Override
    protected boolean when(Dimension dimension) {
        return dimension instanceof Color;
    }
}
