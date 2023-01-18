package com.github.italomded.covidvaccinationetl.etl.replacer;

import com.github.italomded.covidvaccinationetl.domain.csv.Line;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ManufacturerPfizerReplacer extends Replacer {
    public ManufacturerPfizerReplacer(Replacer next) {
        super(next);
    }
    @Override
    protected Line replace(Line line) {
        line = new Line(line.bs_representation(), line.bd_fullDate(), line.c_representation(), line.pa_county(), line.pa_state(), line.pa_country(),
                line.vd_fullDate(), line.vs_corporateName(), line.vs_county(), line.vs_state(), line.v_name(), line.v_category(), line.v_batch(),
                "PFIZER - PEDIÁTRICA", line.vd_dose(), line.p_patientIdentifier());
        return line;
    }

    @Override
    protected boolean when(Line line) {
        boolean manufacturerNameWrong = line.v_manufacturer().toLowerCase().contains("pendente") ||
                line.v_manufacturer().toLowerCase().contains("pedi?trica") ||
                line.v_manufacturer().toLowerCase().contains("menor de 5 anos");
        boolean isPfizer = line.v_name().toLowerCase().contains("pfizer - comirnaty") ||
                line.v_name().toLowerCase().contains("pfizer comirnaty");
        return manufacturerNameWrong && isPfizer;
    }
}
