package com.github.italomded.covidvaccinationetl.etl.setter;

import com.github.italomded.covidvaccinationetl.domain.dimension.Dimension;
import com.github.italomded.covidvaccinationetl.domain.dimension.VaccinationDate;
import com.github.italomded.covidvaccinationetl.domain.dimension.VaccinationSite;
import com.github.italomded.covidvaccinationetl.domain.fact.Patient;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class VaccinationSiteSetter extends FactRelationSetter {
    public VaccinationSiteSetter(FactRelationSetter next) {
        super(next);
    }
    
    @Override
    protected Patient set(Patient patient, Dimension dimension) {
        VaccinationSite vaccinationSite = (VaccinationSite) dimension;
        patient.setVaccinationSite(vaccinationSite);
        return patient;
    }

    @Override
    protected boolean when(Dimension dimension) {
        return dimension instanceof VaccinationSite;
    }
}
