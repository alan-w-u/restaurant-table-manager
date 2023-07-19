package ui;

import model.*;
import java.util.Scanner;

// Restaurant Table Manager application
public class RestaurantTableManagerApp {

    private Scanner scanner = new Scanner(System.in);
    private Restaurant restaurant;
    private Table currentTable;
    private int whichTable;
    private int numberOfTables;

    Menu menu = new Menu();

    // EFFECTS: runs the restaurant table manager application
    public RestaurantTableManagerApp() {
        chooseAction();

        while (true) {
            chooseTable();
            askChangeAvailability();
        }
    }

    // REQUIRES: 1 <= whatAction <= 3
    // EFFECTS: chooses which action the user wants to take
    private void chooseAction() {
        System.out.println("What would you like to do?\n1. Edit the restaurant table setup\n"
                + "2. Change availability of table\n3. Order for a table\n4. Check availability of all tables");
        int whatAction = scanner.nextInt();

        if (whatAction == 1) {
            restaurantSetup();
        } else if (whatAction == 2) {
            chooseTable();
            askChangeAvailability();
        } else if (whatAction == 3) {
            orderBoundaries();
        } else if (whatAction == 4) {
            if (restaurant == null || restaurant.getNumberOfTables() == 0) {
                System.out.println("Sorry, there are no tables in the restaurant");
                restaurantSetup();
            } else {
                System.out.println(restaurant.getAllAvailability());
                chooseAction();
            }
        }
    }

    // REQUIRES: 1 <= restaurantAction <= 2
    // MODIFIES: this
    // EFFECTS: creates a restaurant with a number of tables if no tables already present
    //          or add or removes tables if tables are already present
    private void restaurantSetup() {
        if (restaurant == null || restaurant.getNumberOfTables() == 0) {
            createRestaurant();
        } else {
            editRestaurant();
        }
    }

    // REQUIRES: amount > 0
    // MODIFIES: this
    // EFFECTS: creates an instance of table with given amount of tables
    private void createRestaurant() {
        System.out.println("The restaurant has no tables so we will set up tables\n");
        System.out.println("How many tables do you want in the restaurant?");
        int numberOfTables = scanner.nextInt();
        this.numberOfTables = numberOfTables;
        restaurant = new Restaurant(numberOfTables);
        System.out.println("There are: " + this.numberOfTables + " tables in the restaurant");
        chooseAction();
    }

    // REQUIRES: amountAdd > 0
    //           amountRemove <= restaurant.getNumberOfTables()
    // MODIFIES: this
    // EFFECTS: asks to add or remove a given amount of tables
    private void editRestaurant() {
        System.out.println("How do you want to edit the restaurant?");
        System.out.println("There are currently: " + restaurant.getNumberOfTables() + " tables");
        System.out.println("1. Add tables\n2. Remove tables");
        int restaurantAction = scanner.nextInt();

        if (restaurantAction == 1) {
            System.out.println("How many tables would you like to add?");
            int amountAdd = scanner.nextInt();
            restaurant.addAmountOfTables(amountAdd);
            chooseAction();
        } else if (restaurantAction == 2) {
            System.out.println("How many tables would you like to remove?");
            int amountRemove = scanner.nextInt();
            restaurant.removeAmountOfTables(amountRemove);
            chooseAction();
        }
    }

    // REQUIRES: whichTable <= restaurant.getNumberOfTables()
    // MODIFIES: this
    // EFFECTS: asks and chooses which table to edit
    private void chooseTable() {
        if (restaurant == null || restaurant.getNumberOfTables() == 0) {
            System.out.println("The restaurant has no tables, please set up some tables");
            restaurantSetup();
        } else {
            System.out.println("Which table would you like to choose to edit?");
            whichTable = scanner.nextInt();
            currentTable = restaurant.getSpecificTable(whichTable);
            System.out.println("Currently editing table: " + whichTable + "\n");
        }
    }

    // REQUIRES: whichTable = "Y" || "y" || "N" || "n"
    // MODIFIES: this
    // EFFECTS: ask whether to change the availability of the current table,
    //          if table becomes occupied or is already occupied and availability
    //          is unchanged, then order items for the table
    private void askChangeAvailability() {
        System.out.println("Table " + whichTable + " is currently: " + currentTable.getAvailability());
        System.out.println("Do you want to change the table's availability? (Y/N)");
        String shouldChangeAvailability = scanner.next();

        if ((shouldChangeAvailability.equals("Y") || shouldChangeAvailability.equals("y"))
                && currentTable.getAvailability().equals("ready to pay")) {
            currentTable.changeAvailability();
            currentTable.resetOrder();
            System.out.println("Table " + whichTable + " is now: " + currentTable.getAvailability());
            System.out.println("Since the table is no longer in use the table order has been reset");
        } else if ((shouldChangeAvailability.equals("Y") || shouldChangeAvailability.equals("y"))
                && currentTable.getAvailability().equals("available")) {
            currentTable.changeAvailability();
            System.out.println("Table " + whichTable + " is now: " + currentTable.getAvailability() + "\n");
            orderBoundaries();
        } else if ((shouldChangeAvailability.equals("N") || shouldChangeAvailability.equals("n"))
                && currentTable.getAvailability().equals("occupied")) {
            orderBoundaries();
        } else if ((shouldChangeAvailability.equals("N") || shouldChangeAvailability.equals("n"))
                && !currentTable.getAvailability().equals("occupied")) {
            chooseAction();
        }
    }

    // MODIFIES: this
    // EFFECTS: sees if an order can be made and executes the appropriate branch
    private void orderBoundaries() {
        if (restaurant == null || restaurant.getNumberOfTables() == 0) {
            System.out.println("The restaurant has no tables, please set up some tables");
            restaurantSetup();
        } else if (currentTable == null) {
            chooseTable();
            orderBoundaries();
        } else if (currentTable.getAvailability().equals("occupied")) {
            order();
        } else if (!currentTable.getAvailability().equals("occupied")) {
            askForceOrder();
        }
    }

    // REQUIRES: 0 <= orderItem <= menu.getNumberOfItemsOnMenu()
    // MODIFIES: this
    // EFFECTS: assigns ordered menu items to a table
    private void order() {
        System.out.println("The menu items are:\n");
        System.out.println(menu.getItemsOnMenu() + "\n");
        System.out.println("What would you like to order? "
                + "(type an \"int\" to add item to the order, type \"0\" to exit)");
        int orderItem = scanner.nextInt();

        while (orderItem != 0) {
            currentTable.addMenuItem(menu.getSpecificItem(orderItem));
            System.out.println("Added: " + menu.getNameOfSpecificItem(orderItem) + " to your order!");
            System.out.println("What else would you like to order? (\"0\" to exit)");
            orderItem = scanner.nextInt();

            if (orderItem == 0) {
                break;
            }
        }

        System.out.println("Sounds great! Your current order is:\n" + currentTable.getAllItemsOrdered()
                + "\nYou currently owe: $" + currentTable.getTotalPriceAllItemsOrdered() + "\n");
    }

    // REQUIRES: shouldOrder = "Y" || "y" || "N" || "n"
    // EFFECTS: ask whether to automatically set available to "occupied"
    //          and begin an order
    private void askForceOrder() {
        System.out.println("Sorry, the table is currently not occupied so orders cannot be added to it. "
                + "Would you like to set the table to occupied and begin an order? (Y/N)");
        String shouldOrder = scanner.next();

        if (shouldOrder.equals("Y") || shouldOrder.equals("y")) {
            currentTable.changeAvailabilityTo(1);
            orderBoundaries();
        } else if (shouldOrder.equals("N") || shouldOrder.equals("n")) {
            chooseTable();
        }
    }
}
