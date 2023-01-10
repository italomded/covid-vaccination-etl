package com.github.italomded.covidvaccinationetl.domain.csv;

import com.github.italomded.covidvaccinationetl.domain.dimension.BiologicalSex;

public record Line
        (
        String bs_representation, String bd_fullDate, String c_representation,
        String pa_county, String pa_state, String pa_country,
        String vd_fullDate, String vs_corporateName, String vs_county,
        String vs_state, String v_name, String v_category,
        String v_batch, String v_manufacturer, String vd_dose, String p_patientIdentifier
        )
{
    @Override
    public String toString() {
        return "Line{" +
                "bs_representation='" + bs_representation + '\'' +
                ", bd_fullDate='" + bd_fullDate + '\'' +
                ", c_representation='" + c_representation + '\'' +
                ", pa_county='" + pa_county + '\'' +
                ", pa_state='" + pa_state + '\'' +
                ", pa_country='" + pa_country + '\'' +
                ", vd_fullDate='" + vd_fullDate + '\'' +
                ", vs_corporateName='" + vs_corporateName + '\'' +
                ", vs_county='" + vs_county + '\'' +
                ", vs_state='" + vs_state + '\'' +
                ", v_name='" + v_name + '\'' +
                ", v_category='" + v_category + '\'' +
                ", v_batch='" + v_batch + '\'' +
                ", v_manufacturer='" + v_manufacturer + '\'' +
                ", vd_dose='" + vd_dose + '\'' +
                ", p_patientIdentifier='" + p_patientIdentifier + '\'' +
                '}';
    }
}
