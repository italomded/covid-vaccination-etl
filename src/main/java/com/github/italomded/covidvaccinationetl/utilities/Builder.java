package com.github.italomded.covidvaccinationetl.utilities;

import com.github.italomded.covidvaccinationetl.domain.dimension.*;
import com.github.italomded.covidvaccinationetl.etl.persist.*;
import com.github.italomded.covidvaccinationetl.etl.setter.*;
import com.github.italomded.covidvaccinationetl.etl.LineConverter;
import com.github.italomded.covidvaccinationetl.etl.converter.*;

import java.util.Arrays;
import java.util.List;

public class Builder {
    public static List<Dimension> buildDimensionList() {
        List<Dimension> dimensions = Arrays.asList(
                new BiologicalSex(), new Birthdate(), new Color(), new PatientAddress(), new VaccinationDate(),
                new VaccinationSite(), new Vaccine(), new VaccineDose());
        return dimensions;
    }

    public static DomainPersist buildDomainPersist() {
        DomainPersist domainPersist =
                new BiologicalSexPersist(
                        new BirthdatePersist(
                                new ColorPersist(
                                        new PatientAddressPersist(
                                                new VaccinationDatePersist(
                                                        new VaccinationSitePersist(
                                                                new VaccineDosePersist(
                                                                        new VaccinePersist())))))));
        return domainPersist;
    }

    public static FactRelationSetter buildFactRelationSetter() {
        FactRelationSetter factRelationsSetter =
                new BiologicalSexSetter(
                        new BirthdateSetter(
                                new ColorSetter(
                                        new PatientAddressSetter(
                                                new VaccinationDateSetter(
                                                        new VaccinationSiteSetter(
                                                                new VaccineDoseSetter(
                                                                        new VaccineSetter())))))));
        return factRelationsSetter;
    }

    public static LineConverter buildLineConverter() {
        Converter converter =
                new BiologicalSexConverter(
                        new BirthdateConverter(
                                new ColorConverter(
                                        new PatientAddressConverter(
                                                new VaccinationDateConverter(
                                                        new VaccinationSiteConverter(
                                                                new VaccineConverter(
                                                                        new VaccineDoseConverter())))))));
        LineConverter lineConverter = new LineConverter(converter);
        return  lineConverter;
    }
}
