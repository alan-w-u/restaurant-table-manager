package ui;

import model.*;
import java.util.Scanner;

// Restaurant Table Manager application
public class RestaurantTableManagerApp {

    private Scanner scanner = new Scanner(System.in);
    private Restaurant restaurant;
    private Table currentTable;
    private int whichTable;

    Menu menu = new Menu();

    // EFFECTS: runs the restaurant table manager application
    public RestaurantTableManagerApp() {
        restaurantSetup();

        while (true) {
            chooseTable();
            askChangeAvailability();
            order();
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a restaurant with a number of tables
    private void restaurantSetup() {
        System.out.println("How many tables do you have in your restaurant?");
        int numberOfTables = scanner.nextInt();
        restaurant = new Restaurant(numberOfTables);
    }

    // REQUIRES: whichTable <= restaurant.getNumberOfTables()
    // MODIFIES: this
    // EFFECTS: asks and chooses which table to edit
    private void chooseTable() {
        System.out.println("Which table would you like to choose to edit?");
        whichTable = scanner.nextInt();
        currentTable = restaurant.getSpecificTable(whichTable);
        System.out.println("Currently editing table: " + whichTable);
    }

    // REQUIRES: whichTable = "Y" || "y" || "N" || "n"
    // MODIFIES: this
    // EFFECTS: ask whether to change the availability of the current table
    private void askChangeAvailability() {
        System.out.println("Table " + whichTable + " is currently: " + currentTable.getAvailability());
        System.out.println("Do you want to change the table's availability? (Y/N)");
        String shouldChangeAvailability = scanner.next();

        if (shouldChangeAvailability.equals("Y") || shouldChangeAvailability.equals("y")) {
            currentTable.changeAvailability();
            System.out.println("The availability of Table " + whichTable
                    + " is now: " + currentTable.getAvailability());
        } else if ((shouldChangeAvailability.equals("N") || shouldChangeAvailability.equals("n"))
                && !currentTable.getAvailability().equals("occupied")) {
            chooseTable();
        }
    }

    // REQUIRES: orderItem <= menu.getNumberOfItemsOnMenu()
    // MODIFIES: this
    // EFFECTS: assigns ordered menu items to a table
    private void order() {
        if (currentTable.getAvailability().equals("occupied")) {
            System.out.println("The menu items are:");
            System.out.println(menu.getItemsOnMenu());
            System.out.println("What would you like to order? (\"0\" to exit)");
            int orderItem = scanner.nextInt();

            currentTable.addMenuItem(menu.getSpecificItem(orderItem));
            System.out.println("Added: " + menu.getNameOfSpecificItem(orderItem) + " to your order!");
            System.out.println("Would you like to order anything else? (Y/N)");
            String shouldOrderMore = scanner.next();

            if (shouldOrderMore.equals("Y") || shouldOrderMore.equals("y")) {
                order();
            } else {
                System.out.println("Sounds great! Your current order is: " + currentTable.getNameAllItemsOrdered());
            }
        }
    }

}
