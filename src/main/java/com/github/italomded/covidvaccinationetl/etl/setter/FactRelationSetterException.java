package com.github.italomded.covidvaccinationetl.etl.setter;

import lombok.Getter;

public class FactRelationSetterException extends RuntimeException {
    @Getter
    private Class<?> problemClass;

    public FactRelationSetterException(String message, Class<?> problemClass) {
        super(message);
        this.problemClass = problemClass;
    }
}
