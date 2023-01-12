package com.github.italomded.covidvaccinationetl.etl.converter;

import com.github.italomded.covidvaccinationetl.domain.csv.Line;
import com.github.italomded.covidvaccinationetl.domain.dimension.Dimension;
import com.github.italomded.covidvaccinationetl.domain.dimension.PatientAddress;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PatientAddressConverter extends Converter {
    public PatientAddressConverter(Converter next) {
        super(next);
    }

    @Override
    protected Dimension convert(Line line) {
        PatientAddress patientAddress = new PatientAddress();
        patientAddress.setCountry(line.pa_country());
        patientAddress.setState(line.pa_state());
        patientAddress.setCounty(line.pa_county());
        return patientAddress;
    }

    @Override
    protected boolean when(Dimension dimension) {
        return dimension instanceof PatientAddress;
    }
}
