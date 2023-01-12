package com.github.italomded.covidvaccinationetl.etl.setter;

import com.github.italomded.covidvaccinationetl.domain.dimension.Birthdate;
import com.github.italomded.covidvaccinationetl.domain.dimension.Dimension;
import com.github.italomded.covidvaccinationetl.domain.fact.Patient;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BirthdateSetter extends FactRelationSetter {
    public BirthdateSetter(FactRelationSetter next) {
        super(next);
    }

    @Override
    protected Patient set(Patient patient, Dimension dimension) {
        Birthdate birthdate = (Birthdate) dimension;
        patient.setBirthdate(birthdate);
        return patient;
    }

    @Override
    protected boolean when(Dimension dimension) {
        return dimension instanceof Birthdate;
    }
}
