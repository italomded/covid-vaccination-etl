package com.github.italomded.covidvaccinationetl.utilities.lineconverter.converter;

import com.github.italomded.covidvaccinationetl.domain.csv.Line;
import com.github.italomded.covidvaccinationetl.domain.dimension.Birthdate;
import com.github.italomded.covidvaccinationetl.domain.dimension.Dimension;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;

@NoArgsConstructor
public class BirthdateConverter extends Converter {
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-d");

    public BirthdateConverter(Converter next) {
        super(next);
    }

    @Override
    protected Dimension convert(Line line) {
        String dateText = line.bd_fullDate();
        LocalDate date = LocalDate.parse(dateText, dateTimeFormatter);
        Birthdate birthdate = new Birthdate(date);
        return birthdate;
    }

    @Override
    protected boolean when(Dimension dimension) {
        return dimension instanceof Birthdate;
    }
}
