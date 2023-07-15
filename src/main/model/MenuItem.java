package model;

public class MenuItem {

    private String name;
    private Double price;

    public MenuItem(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getMenuItemName() {
        return name;
    }

    public Double getMenuItemPrice() {
        return price;
    }
}
