package com.github.italomded.covidvaccinationetl.application;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter file name: ");
        String fileName = sc.nextLine();
        UseCase useCase = new UseCase();
        useCase.go(fileName);
    }
}
