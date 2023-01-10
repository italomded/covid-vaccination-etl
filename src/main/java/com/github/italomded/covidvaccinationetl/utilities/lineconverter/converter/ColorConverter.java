package com.github.italomded.covidvaccinationetl.utilities.lineconverter.converter;

import com.github.italomded.covidvaccinationetl.domain.csv.Line;
import com.github.italomded.covidvaccinationetl.domain.dimension.Color;
import com.github.italomded.covidvaccinationetl.domain.dimension.Dimension;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ColorConverter extends Converter {
    public ColorConverter(Converter next) {
        super(next);
    }

    @Override
    protected Dimension convert(Line line) {
        Color color = new Color();
        color.setRepresentation(line.c_representation().charAt(0));
        return color;
    }

    @Override
    protected boolean when(Dimension dimension) {
        return dimension instanceof Color;
    }
}
