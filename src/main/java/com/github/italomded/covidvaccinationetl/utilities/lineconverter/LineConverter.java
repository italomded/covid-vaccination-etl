package com.github.italomded.covidvaccinationetl.utilities.lineconverter;

import com.github.italomded.covidvaccinationetl.domain.csv.Line;
import com.github.italomded.covidvaccinationetl.domain.dimension.*;
import com.github.italomded.covidvaccinationetl.domain.fact.Patient;
import com.github.italomded.covidvaccinationetl.utilities.lineconverter.converter.*;

public class LineConverter {
    private static Converter converter = new BiologicalSexConverter(
            new BirthdateConverter(
                    new ColorConverter(
                            new PatientAddressConverter(
                                    new VaccinationDateConverter(
                                            new VaccinationSiteConverter(
                                                    new VaccineConverter(new VaccineDoseConverter())
                                            ))))));

    public static Dimension toDimension(Dimension dimension, Line line) {
        return converter.run(dimension, line);
    }

    public static Patient toPatient(Line line) {
        Patient patient = new Patient();
        patient.setPatientIdentifier(line.p_patientIdentifier());
        return patient;
    }
}
