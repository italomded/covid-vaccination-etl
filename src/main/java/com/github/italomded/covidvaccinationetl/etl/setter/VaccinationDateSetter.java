package com.github.italomded.covidvaccinationetl.etl.setter;

import com.github.italomded.covidvaccinationetl.domain.dimension.Dimension;
import com.github.italomded.covidvaccinationetl.domain.dimension.VaccinationDate;
import com.github.italomded.covidvaccinationetl.domain.fact.Patient;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class VaccinationDateSetter extends FactRelationSetter {
    public VaccinationDateSetter(FactRelationSetter next) {
        super(next);
    }

    @Override
    protected Patient set(Patient patient, Dimension dimension) {
        VaccinationDate vaccinationDate = (VaccinationDate) dimension;
        patient.setVaccinationDate(vaccinationDate);
        return patient;
    }

    @Override
    protected boolean when(Dimension dimension) {
        return dimension instanceof VaccinationDate;
    }
}
