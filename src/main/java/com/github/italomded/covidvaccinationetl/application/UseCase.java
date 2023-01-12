package com.github.italomded.covidvaccinationetl.application;

import com.github.italomded.covidvaccinationetl.domain.csv.Line;
import com.github.italomded.covidvaccinationetl.domain.dimension.Dimension;
import com.github.italomded.covidvaccinationetl.etl.LineConverter;
import com.github.italomded.covidvaccinationetl.etl.Persist;
import com.github.italomded.covidvaccinationetl.etl.Reader;
import com.github.italomded.covidvaccinationetl.etl.persist.DomainPersist;
import com.github.italomded.covidvaccinationetl.etl.setter.FactRelationSetter;
import com.github.italomded.covidvaccinationetl.utilities.Builder;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

public class UseCase {
    public void go(String fileName) {
        Path filePath = Paths.get(System.getProperty("user.dir"), "data", fileName + ".csv");
        File file = new File(filePath.toUri());
        Reader reader = new Reader(file);
        reader.read();

        List<Dimension> dimensions = Builder.buildDimensionList();
        DomainPersist domainPersist = Builder.buildDomainPersist();
        FactRelationSetter factRelationSetter = Builder.buildFactRelationSetter();
        LineConverter lineConverter = Builder.buildLineConverter();
        Set<Line> data = reader.getData();

        Persist persist = new Persist(dimensions, domainPersist, factRelationSetter, lineConverter, data);
        persist.populate();
    }
}
