package org.example.Display;

import java.util.Scanner;

public class UserInput {
    private Scanner scanner;

    /**
     * Constructor initializes a new Scanner object
     */
    public UserInput() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prompts the user to enter a start station and returns their input as a trimmed string
     * @return a trimmed string representing the user's input for the start station
     */
    public String getStartStation() {
        System.out.print("Bitte geben Sie die Startstation ein: ");
        return scanner.nextLine().trim();
    }

    /**
     * Prompts the user to enter an end station and returns their input as a trimmed string
     * @return the user's input as a trimmed string, representing the end station.
     */
    public String getEndStation() {
        System.out.print("Bitte geben Sie die Endstation ein: ");
        return scanner.nextLine().trim();
    }
}
