package com.github.italomded.covidvaccinationetl.utilities.lineconverter.converter;

import com.github.italomded.covidvaccinationetl.domain.csv.Line;
import com.github.italomded.covidvaccinationetl.domain.dimension.Dimension;
import com.github.italomded.covidvaccinationetl.domain.dimension.PatientAdress;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PatientAdressConverter extends Converter {
    public PatientAdressConverter(Converter next) {
        super(next);
    }
    @Override
    protected Dimension convert(Line line) {
        PatientAdress patientAdress = new PatientAdress();
        patientAdress.setCountry(line.pa_country());
        patientAdress.setState(line.pa_state());
        patientAdress.setCounty(line.pa_county());
        return patientAdress;
    }

    @Override
    protected boolean when(Dimension dimension) {
        return dimension instanceof PatientAdress;
    }
}
