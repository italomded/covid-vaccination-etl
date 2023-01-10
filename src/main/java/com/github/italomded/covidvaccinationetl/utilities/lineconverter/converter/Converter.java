package com.github.italomded.covidvaccinationetl.utilities.lineconverter.converter;

import com.github.italomded.covidvaccinationetl.domain.csv.Line;
import com.github.italomded.covidvaccinationetl.domain.dimension.Dimension;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
public abstract class Converter {
    protected Converter next;

    public Dimension run(Dimension dimension, Line line) {
        if (when(dimension)) {
            return convert(line);
        } else {
            if (next != null) return next.run(dimension, line);
            return null;
        }
    }
    protected abstract Dimension convert(Line line);
    protected abstract boolean when(Dimension dimension);
}
