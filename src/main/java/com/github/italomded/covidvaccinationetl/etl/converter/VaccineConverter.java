package com.github.italomded.covidvaccinationetl.etl.converter;

import com.github.italomded.covidvaccinationetl.domain.csv.Line;
import com.github.italomded.covidvaccinationetl.domain.dimension.Dimension;
import com.github.italomded.covidvaccinationetl.domain.dimension.Vaccine;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class VaccineConverter extends Converter {
    public VaccineConverter(Converter next) {
        super(next);
    }

    @Override
    protected Dimension convert(Line line) {
        Vaccine vaccine = new Vaccine();
        vaccine.setBatch(line.v_batch());
        vaccine.setName(line.v_name());
        vaccine.setCategory(line.v_category());
        vaccine.setManufacturer(line.v_category());
        return vaccine;
    }

    @Override
    protected boolean when(Dimension dimension) {
        return dimension instanceof Vaccine;
    }
}
