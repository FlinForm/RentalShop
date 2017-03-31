package com.rentalshop.model;

/**
 * Created by Pavel Davydenko on 30.03.2017.
 */



public class SportEquipment {
    private Category category;
    private String title;
    private int price;
    private int quantity;

    public SportEquipment() {
    }

    public SportEquipment(Category category, String title, int price, int quantity) {
        this.category = category;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
