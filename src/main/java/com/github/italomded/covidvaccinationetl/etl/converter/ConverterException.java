package com.github.italomded.covidvaccinationetl.etl.converter;

import lombok.Getter;

public class ConverterException extends RuntimeException {
    @Getter
    private Class<?> problemClass;

    public ConverterException(String message, Class<?> problemClass) {
        super(message);
        this.problemClass = problemClass;
    }
}
