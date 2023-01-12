package com.github.italomded.covidvaccinationetl.etl.setter;

import com.github.italomded.covidvaccinationetl.domain.dimension.Dimension;
import com.github.italomded.covidvaccinationetl.domain.dimension.Vaccine;
import com.github.italomded.covidvaccinationetl.domain.dimension.VaccineDose;
import com.github.italomded.covidvaccinationetl.domain.fact.Patient;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class VaccineSetter extends FactRelationSetter {
    public VaccineSetter(FactRelationSetter next) {
        super(next);
    }
    
    @Override
    protected Patient set(Patient patient, Dimension dimension) {
        Vaccine vaccine = (Vaccine) dimension;
        patient.setVaccine(vaccine);
        return patient;
    }

    @Override
    protected boolean when(Dimension dimension) {
        return dimension instanceof Vaccine;
    }
}
