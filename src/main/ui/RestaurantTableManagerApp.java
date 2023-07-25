package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Restaurant Table Manager application
public class RestaurantTableManagerApp {

    private Scanner scanner = new Scanner(System.in);
    private Restaurant restaurant;
    private Table currentTable;
    private int currentTableNumber;
    private int numberOfTables;

    private static final String JSON_LOCATION = "./data/restaurant.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    Menu menu = new Menu();

    // EFFECTS: runs the restaurant table manager application
    public RestaurantTableManagerApp() throws  FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_LOCATION);
        jsonReader = new JsonReader(JSON_LOCATION);
        restaurantTableManagerRun();
    }

    // EFFECTS: runs the restaurant table manager application
    public void restaurantTableManagerRun() {
        chooseNewOrLoad();
        chooseAction();

        while (true) {
            chooseTable();
            changeAvailability();
        }
    }

    // REQUIRES:  1 <= newOrLoad <= 2
    // EFFECTS: chooses whether to create a new restaurant or load one in
    private void chooseNewOrLoad() {
        System.out.println("Do you want to:\n1. Create a new restaurant\n2. Load an existing restaurant");
        int newOrLoad = scanner.nextInt();

        if (newOrLoad == 1) {
            createRestaurant();
        } else if (newOrLoad == 2) {
            loadRestaurant();
        }
    }

    // REQUIRES: 0 <= whatAction <= 5
    // EFFECTS: chooses which action the user wants to take
    private void chooseAction() {
        System.out.println("What would you like to do?\n0. Exit application\n1. Edit the restaurant table setup\n"
                + "2. Edit a table\n3. Check availability of all tables\n4. Check orders of all tables\n"
                + "5. Save restaurant");
        int whatAction = scanner.nextInt();

        if (whatAction == 0) {
            System.exit(0);
        } else if (whatAction == 1) {
            restaurantSetup();
        } else if (whatAction == 2) {
            chooseTable();
            editTable();
        } else if (whatAction == 3) {
            System.out.println(restaurant.getAllAvailability() + "\n");
            chooseAction();
        } else if (whatAction == 4) {
            System.out.println(restaurant.getAllOrders() + "\n");
            chooseAction();
        } else if (whatAction == 5) {
            saveRestaurant();
            chooseAction();
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
        System.out.println("There are: " + this.numberOfTables + " tables in the restaurant\n");
        chooseAction();
    }

    // REQUIRES: 0 <= restaurantAction <= 3
    //           amountAdd > 0
    //           amountRemove <= restaurant.getNumberOfTables()
    // MODIFIES: this
    // EFFECTS: asks to add or remove a given amount of tables
    private void editRestaurant() {
        System.out.println("How do you want to edit the restaurant?");
        System.out.println("There are: " + restaurant.getNumberOfTables() + " tables");
        System.out.println("0. Exit\n1. Add tables\n2. Remove tables\n3. Load an existing restaurant");
        int restaurantAction = scanner.nextInt();

        if (restaurantAction == 0) {
            chooseAction();
        } else if (restaurantAction == 1) {
            System.out.println("How many tables would you like to add?");
            int amountAdd = scanner.nextInt();
            restaurant.addAmountOfTables(amountAdd);
            chooseAction();
        } else if (restaurantAction == 2) {
            System.out.println("How many tables would you like to remove?");
            int amountRemove = scanner.nextInt();
            restaurant.removeAmountOfTables(amountRemove);
            chooseAction();
        } else if (restaurantAction == 3) {
            loadRestaurant();
            chooseAction();
        }
    }

    // MODIFIES: this
    // EFFECTS: asks to change availability or order for a table
    private void editTable() {
        System.out.println("Table " + currentTableNumber + ": " + currentTable.getAvailability());
        System.out.println("Current order\n" + currentTable.getTableOrder());
        System.out.println("\nHow do you want to edit the table?");
        System.out.println("0. Exit\n1. Change availability\n2. Order");
        int tableAction = scanner.nextInt();

        if (tableAction == 0) {
            chooseAction();
        } else if (tableAction == 1) {
            changeAvailability();
            chooseAction();
        } else if (tableAction == 2) {
            orderBoundaries();
            chooseAction();
        }
    }

    // REQUIRES: whichTable <= restaurant.getNumberOfTables()
    // MODIFIES: this
    // EFFECTS: asks and chooses which table to edit
    private void chooseTable() {
        System.out.println("Which table would you like to choose to edit?");
        currentTableNumber = scanner.nextInt();
        currentTable = restaurant.getSpecificTable(currentTableNumber);
        System.out.println("Currently editing table: " + currentTableNumber + "\n");
    }

    // MODIFIES: this
    // EFFECTS: changes the availability of the current table,
    //          if table becomes occupied or is already occupied and availability
    //          is unchanged, then order items for the table
    private void changeAvailability() {
        currentTable.changeAvailability();
        System.out.println("Table " + currentTableNumber + " is now: " + currentTable.getAvailability());

        if (currentTable.getAvailability().equals("available")) {
            System.out.println("Since the table is no longer in use the table order has been reset");
            currentTable.resetOrder();
            chooseAction();
        } else if (currentTable.getAvailability().equals("occupied")) {
            System.out.println("Would you like to begin an order for this table? (Y/N)");
            String shouldOrder = scanner.next();

            if (shouldOrder.equals("Y") || shouldOrder.equals("y")) {
                orderBoundaries();
            } else if (shouldOrder.equals("N") || shouldOrder.equals("n")) {
                chooseAction();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: sees if an order can be made and executes the appropriate branch
    private void orderBoundaries() {
        if (currentTable.getAvailability().equals("occupied")) {
            order();
            chooseAction();
        } else if (!currentTable.getAvailability().equals("occupied")) {
            askForceOrder();
            chooseAction();
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

        System.out.println("Sounds great! Your current order is:\n\n" + currentTable.getAllItemsOrdered()
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
            chooseAction();
        }
    }

    // EFFECTS: saves the restaurant to file
    private void saveRestaurant() {
        try {
            jsonWriter.open();
            jsonWriter.write(restaurant);
            jsonWriter.close();
            System.out.println("Saved restaurant to " + JSON_LOCATION);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_LOCATION);
        }
    }

    // EFFECTS: loads the restaurant from file
    private void loadRestaurant() {
        try {
            restaurant = jsonReader.read();
            System.out.println("Loaded restaurant from " + JSON_LOCATION);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_LOCATION);
        }
    }
}
