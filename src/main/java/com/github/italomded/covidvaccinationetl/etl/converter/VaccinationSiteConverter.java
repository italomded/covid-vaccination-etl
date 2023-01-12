package com.github.italomded.covidvaccinationetl.etl.converter;

import com.github.italomded.covidvaccinationetl.domain.csv.Line;
import com.github.italomded.covidvaccinationetl.domain.dimension.Dimension;
import com.github.italomded.covidvaccinationetl.domain.dimension.VaccinationSite;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class VaccinationSiteConverter extends Converter {
    public VaccinationSiteConverter(Converter next) {
        super(next);
    }

    @Override
    protected Dimension convert(Line line) {
        VaccinationSite vaccinationSite = new VaccinationSite();
        vaccinationSite.setCounty(line.vs_county());
        vaccinationSite.setState(line.vs_state());
        vaccinationSite.setCorporateName(line.vs_corporateName());
        return vaccinationSite;
    }

    @Override
    protected boolean when(Dimension dimension) {
        return dimension instanceof VaccinationSite;
    }
}
