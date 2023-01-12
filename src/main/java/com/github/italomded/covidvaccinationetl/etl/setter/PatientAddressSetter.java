package com.github.italomded.covidvaccinationetl.etl.setter;

import com.github.italomded.covidvaccinationetl.domain.dimension.Dimension;
import com.github.italomded.covidvaccinationetl.domain.dimension.PatientAddress;
import com.github.italomded.covidvaccinationetl.domain.fact.Patient;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PatientAddressSetter extends FactRelationSetter {
    public PatientAddressSetter(FactRelationSetter next) {
        super(next);
    }

    @Override
    protected Patient set(Patient patient, Dimension dimension) {
        PatientAddress patientAddress = (PatientAddress) dimension;
        patient.setAdress(patientAddress);
        return patient;
    }

    @Override
    protected boolean when(Dimension dimension) {
        return dimension instanceof PatientAddress;
    }
}
