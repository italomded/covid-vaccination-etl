package com.github.italomded.covidvaccinationetl.etl.converter;

import com.github.italomded.covidvaccinationetl.domain.csv.Line;
import com.github.italomded.covidvaccinationetl.domain.dimension.BiologicalSex;
import com.github.italomded.covidvaccinationetl.domain.dimension.Dimension;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BiologicalSexConverter extends Converter {
    public BiologicalSexConverter(Converter next) {
        super(next);
    }

    @Override
    protected Dimension convert(Line line) {
        BiologicalSex biologicalSex = new BiologicalSex();
        biologicalSex.setRepresentation(line.bs_representation().charAt(0));
        return biologicalSex;
    }

    @Override
    protected boolean when(Dimension dimension) {
        return dimension instanceof BiologicalSex;
    }
}
