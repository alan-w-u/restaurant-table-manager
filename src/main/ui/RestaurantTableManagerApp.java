package ui;

import model.Restaurant;
import model.Table;

import java.util.Scanner;

// Restaurant Table Manager application
public class RestaurantTableManagerApp {

    private Scanner scanner = new Scanner(System.in);
    private Restaurant restaurant;
    private Table currentTable;
    private int whichTable;

    // EFFECTS: runs the restaurant table manager application
    public RestaurantTableManagerApp() {
        restaurantSetup();
        chooseTable();
        editTable();
    }

    // MODIFIES: this
    // EFFECTS: creates a restaurant with a number of tables
    private void restaurantSetup() {
        System.out.println("How many tables do you have in your restaurant?");
        int numberOfTables = scanner.nextInt();
        restaurant = new Restaurant(numberOfTables);
    }

    // MODIFIES: this
    // EFFECTS: asks and chooses which table to edit
    private void chooseTable() {
        System.out.println("Which table would you like to edit?");
        whichTable = scanner.nextInt();
        currentTable = restaurant.getSpecificTable(whichTable);
        System.out.println("Currently editing table: " + whichTable);
    }

    // MODIFIES: this
    // EFFECTS: changes the properties of the table
    private void editTable() {
        System.out.println("Table " + whichTable + " is currently: " + currentTable.getAvailability());
        System.out.println("Do you want to change the table's availability? (Y/N)");
        String askChangeAvailability = scanner.next();

        if (askChangeAvailability.equals("Y")) {
            currentTable.changeAvailability();
        } else if (askChangeAvailability.equals("N")) {
            chooseTable();
        }
    }

}
