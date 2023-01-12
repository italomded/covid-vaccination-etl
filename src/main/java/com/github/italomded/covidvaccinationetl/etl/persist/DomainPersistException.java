package com.github.italomded.covidvaccinationetl.etl.persist;

import lombok.Getter;

public class DomainPersistException extends RuntimeException {
    @Getter
    private Class<?> problemClass;

    public DomainPersistException(String message, Class<?> problemClass) {
        super(message);
        this.problemClass = problemClass;
    }
}
