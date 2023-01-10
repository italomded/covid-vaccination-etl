package com.github.italomded.covidvaccinationetl.application;

import com.github.italomded.covidvaccinationetl.etl.Persist;
import com.github.italomded.covidvaccinationetl.etl.Reader;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main (String[] args) {
        Path filePath = Paths.get(System.getProperty("user.dir"), "data", "teste.csv");
        Reader reader = new Reader(new File(filePath.toUri()));
        reader.read();
        reader.getData().forEach(System.out::println);

        Persist persist = new Persist(reader.getData());
        persist.populate();
    }
}
