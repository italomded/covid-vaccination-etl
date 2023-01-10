package com.github.italomded.covidvaccinationetl.utilities.lineconverter.converter;

import com.github.italomded.covidvaccinationetl.domain.csv.Line;
import com.github.italomded.covidvaccinationetl.domain.dimension.Dimension;
import com.github.italomded.covidvaccinationetl.domain.dimension.VaccineDose;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class VaccineDoseConverter extends Converter {
    public VaccineDoseConverter(Converter next) {
        super(next);
    }

    @Override
    protected Dimension convert(Line line) {
        VaccineDose vaccineDose = new VaccineDose();
        vaccineDose.setDose(line.vd_dose());
        return vaccineDose;
    }

    @Override
    protected boolean when(Dimension dimension) {
        return dimension instanceof VaccineDose;
    }
}
