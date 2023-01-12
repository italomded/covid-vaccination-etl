package com.github.italomded.covidvaccinationetl.application;

import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter file name: ");
        String fileName = sc.nextLine();

        try {
            UseCase useCase = new UseCase();
            useCase.go(fileName);
            System.out.println("Done!");
        } catch (RuntimeException exception) {
            exception.printStackTrace();
            System.out.println("Error: " + exception.getLocalizedMessage());
        }
    }
}
