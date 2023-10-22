package com.tomas.mental_rps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) throws IOException {
        int ownScore = 0;
        int systemScore = 0;
        String instruction = "1";
        while (true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            if (instruction.equals("0")) {
                exit(0);
            } else {
                System.out.println("Choose what you want to throw");
                String throwChoice = reader.readLine();
                String mentalChoice = getChoice(throwChoice);
                if (!mentalChoice.equals("No choice")) {
                    System.out.println("You have selected " + mentalChoice);
                    Random random = new Random();
                    Integer systemThrowChoice = random.nextInt(3) + 1;
                    String systemMentalChoice = getChoice(systemThrowChoice);
                    System.out.println("The system has selected " + systemMentalChoice);

                    System.out.println("Choose what to throw");
                    String throwSelection = reader.readLine();
                    String choice = getChoice(throwSelection);

                    if (!choice.equals("No choice")) {
                        System.out.println("You have thrown " + choice);
                        int systemChangeSelection = random.nextInt(2) + 1;
                        String systemChoice = systemMentalChoice;
                        if (systemChangeSelection == 1) {
                            Integer systemThrowSelection = random.nextInt(3) + 1;
                            systemChoice = getChoice(systemThrowSelection);
                        }
                        System.out.println("The system has thrown " + systemChoice);

                        System.out.println("Mental battle: " + mentalChoice + " vs " + systemMentalChoice);
                        System.out.println("Thrown battle: " + choice + " vs " + systemChoice);

                        ownScore += gameWon(mentalChoice, systemMentalChoice);
                        ownScore += gameWon(choice, systemChoice);
                        systemScore += gameWon(systemMentalChoice, mentalChoice);
                        systemScore += gameWon(systemChoice, choice);
                    }
                }
            }
            System.out.println("Current score: " + ownScore + "-" + systemScore);
            System.out.println("Enter 0 if you want to exit, anything else to continue");
            instruction = reader.readLine();
        }
    }

    private static String getChoice(String choice) {
        return switch (choice) {
            case "1" -> "Rock";
            case "2" -> "Paper";
            case "3" -> "Scissors";
            default -> "No choice";
        };
    }

    private static String getChoice(Integer choice) {
        return switch (choice) {
            case 1 -> "Rock";
            case 2 -> "Paper";
            case 3 -> "Scissors";
            default -> "No choice";
        };
    }

    private static int gameWon(String ownChoice, String systemChoice) {
        return switch (ownChoice) {
            case "Rock" -> systemChoice.equals("Scissors") ? 1 : 0;
            case "Paper" -> systemChoice.equals("Rock") ? 1 : 0;
            case "Scissors" -> systemChoice.equals("Paper") ? 1 : 0;
            default -> 0;
        };
    }
}