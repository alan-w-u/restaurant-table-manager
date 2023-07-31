package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Choose what method to display application
public class ConsoleOrGUI {

    private Scanner scanner;

    // EFFECTS: chooses whether to run the application in console or GUI
    public ConsoleOrGUI() {
        scanner = new Scanner(System.in);

        System.out.println("Choose what mode you want the application to run in:");
        System.out.println("0. Quit\n1. Console\n2. GUI");

        int consoleOrGUI = scanner.nextInt();

        if (consoleOrGUI == 0) {
            System.exit(0);
        } else if (consoleOrGUI == 1) {
            try {
                new RestaurantTableManagerConsole();
            } catch (FileNotFoundException e) {
                System.out.println("Unable to run application: file not found");
            }
        } else if (consoleOrGUI == 2) {
            new RestaurantTableManagerGUI();
        }
    }
}
