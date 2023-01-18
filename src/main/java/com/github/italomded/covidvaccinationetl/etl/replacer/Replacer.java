package com.github.italomded.covidvaccinationetl.etl.replacer;

import com.github.italomded.covidvaccinationetl.domain.csv.Line;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public abstract class Replacer {
    protected Replacer next;

    public Line run(Line line) {
        if (when(line)) line = replace(line);
        if (next != null) line = next.run(line);
        return line;
    }
    protected abstract Line replace(Line line);
    protected abstract boolean when(Line line);
}
