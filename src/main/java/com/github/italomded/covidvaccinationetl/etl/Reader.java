package com.github.italomded.covidvaccinationetl.etl;

import com.github.italomded.covidvaccinationetl.domain.csv.Line;
import lombok.Getter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Reader {
    private File file;
    @Getter
    private Set<Line> data;

    public Reader(File file) {
        this.file = file;
        this.data = new HashSet<>();
    }

    public void read() {
        try {
            Scanner readerScan = new Scanner(file);
            var counter = 1; // delete
            readerScan.nextLine();
            while (readerScan.hasNextLine()) {
                counter++; // delete
                String plainLine = readerScan.nextLine();
                Line line = toLine(plainLine);
                if (line == null) break;
                data.add(line);
                if (counter > 30) break; // delete
            }
        } catch (FileNotFoundException e) {
            System.out.println("[!] FILE NOT FOUND!");
        }
    }

    private Line toLine(String plainLine) {
        String[] lineArray = plainLine.replaceAll("\"", "").split(";");
        if (lineArray.length != 32) return null;
        Line line = new Line(
                lineArray[4], lineArray[3], lineArray[6], lineArray[9], lineArray[11], lineArray[10],
                lineArray[27], lineArray[15], lineArray[18], lineArray[19], lineArray[30], lineArray[23],
                lineArray[24], lineArray[25], lineArray[28], lineArray[1]
        );
        return line;
    }
}
