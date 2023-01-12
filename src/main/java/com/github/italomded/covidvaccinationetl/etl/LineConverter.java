package com.github.italomded.covidvaccinationetl.etl;

import com.github.italomded.covidvaccinationetl.domain.csv.Line;
import com.github.italomded.covidvaccinationetl.domain.dimension.*;
import com.github.italomded.covidvaccinationetl.domain.fact.Patient;
import com.github.italomded.covidvaccinationetl.etl.converter.*;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LineConverter {
    private Converter converter;

    public Dimension toDimension(Dimension dimension, Line line) {
        return converter.run(dimension, line);
    }

    public Patient toPatient(Line line) {
        Patient patient = new Patient();
        patient.setPatientIdentifier(line.p_patientIdentifier());
        return patient;
    }
}
