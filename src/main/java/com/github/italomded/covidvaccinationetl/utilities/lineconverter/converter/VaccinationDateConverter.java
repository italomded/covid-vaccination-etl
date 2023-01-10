package com.github.italomded.covidvaccinationetl.utilities.lineconverter.converter;

import com.github.italomded.covidvaccinationetl.domain.csv.Line;
import com.github.italomded.covidvaccinationetl.domain.dimension.Dimension;
import com.github.italomded.covidvaccinationetl.domain.dimension.VaccinationDate;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
public class VaccinationDateConverter extends Converter {
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-d");

    public VaccinationDateConverter(Converter next) {
        super(next);
    }

    @Override
    protected Dimension convert(Line line) {
        String dateText = line.bd_fullDate();
        LocalDate date = LocalDate.parse(dateText, dateTimeFormatter);
        VaccinationDate vaccinationDate = new VaccinationDate(date);
        return vaccinationDate;
    }

    @Override
    protected boolean when(Dimension dimension) {
        return dimension instanceof VaccinationDate;
    }
}
