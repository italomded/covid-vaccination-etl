package com.github.italomded.covidvaccinationetl.etl.replacer;

import com.github.italomded.covidvaccinationetl.domain.csv.Line;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ManufacturerSinovacReplacer extends Replacer {
    public ManufacturerSinovacReplacer(Replacer next) {
        super(next);
    }
    @Override
    protected Line replace(Line line) {
        line = new Line(line.bs_representation(), line.bd_fullDate(), line.c_representation(), line.pa_county(), line.pa_state(), line.pa_country(),
                line.vd_fullDate(), line.vs_corporateName(), line.vs_county(), line.vs_state(), line.v_name(), line.v_category(), line.v_batch(),
                "SINOVAC", line.vd_dose(), line.p_patientIdentifier());
        return line;
    }

    @Override
    protected boolean when(Line line) {
        boolean manufacturerNameWrong = line.v_manufacturer().toLowerCase().contains("pendente");
        boolean isSinovac = line.v_name().toLowerCase().contains("sinovac");
        return manufacturerNameWrong && isSinovac;
    }
}
