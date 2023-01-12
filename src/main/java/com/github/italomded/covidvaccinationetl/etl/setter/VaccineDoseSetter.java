package com.github.italomded.covidvaccinationetl.etl.setter;

import com.github.italomded.covidvaccinationetl.domain.dimension.Dimension;
import com.github.italomded.covidvaccinationetl.domain.dimension.VaccinationSite;
import com.github.italomded.covidvaccinationetl.domain.dimension.VaccineDose;
import com.github.italomded.covidvaccinationetl.domain.fact.Patient;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class VaccineDoseSetter extends FactRelationSetter {
    public VaccineDoseSetter(FactRelationSetter next) {
        super(next);
    }
    
    @Override
    protected Patient set(Patient patient, Dimension dimension) {
        VaccineDose vaccineDose = (VaccineDose) dimension;
        patient.setVaccineDose(vaccineDose);
        return patient;
    }

    @Override
    protected boolean when(Dimension dimension) {
        return dimension instanceof VaccineDose;
    }
}
