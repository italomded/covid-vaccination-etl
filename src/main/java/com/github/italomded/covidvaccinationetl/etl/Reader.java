package com.github.italomded.covidvaccinationetl.etl;

import com.github.italomded.covidvaccinationetl.domain.csv.Line;
import com.github.italomded.covidvaccinationetl.etl.replacer.Replacer;
import lombok.Getter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Reader {
    private static final String TEXT_WITHOUT_INFORMATION_REPLACE = "SEM INFORMACAO";
    private File file;
    private Replacer replacer;

    @Getter
    private Set<Line> data;

    public Reader(File file, Replacer replacer) {
        this.file = file;
        this.replacer = replacer;
        this.data = new HashSet<>();
    }

    public void read() {
        try {
            Scanner readerScan = new Scanner(file);
            readerScan.nextLine();
            while (readerScan.hasNextLine()) {
                String plainLine = readerScan.nextLine();
                if (plainLine.isBlank()) break;

                Line line = toLine(plainLine);
                if (line == null) break;
                if (!line.p_patientIdentifier().equals(TEXT_WITHOUT_INFORMATION_REPLACE)) {
                    line = replacer.run(line);
                    data.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found!", e);
        }
    }

    private Line toLine(String plainLine) {
        String[] lineArray = plainLine.replaceAll("\"", "")
                .replaceAll(";;", ";" + TEXT_WITHOUT_INFORMATION_REPLACE + ";")
                .replaceAll(";;", ";" + TEXT_WITHOUT_INFORMATION_REPLACE + ";")
                .replaceAll("None", TEXT_WITHOUT_INFORMATION_REPLACE)
                .split(";");
        if (lineArray.length < 31) return null;
        Line line = new Line(
                lineArray[4], lineArray[3], lineArray[6], lineArray[9], lineArray[11], lineArray[10],
                lineArray[27], lineArray[15], lineArray[18], lineArray[19], lineArray[30], lineArray[23],
                lineArray[24], lineArray[25], lineArray[28], lineArray[1]
        );
        return line;
    }
}
