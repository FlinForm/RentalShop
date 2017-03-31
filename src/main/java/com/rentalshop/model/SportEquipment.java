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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SportEquipment equipment = (SportEquipment) o;

        if (price != equipment.price) return false;
        if (category != equipment.category) return false;
        return title != null ? title.equals(equipment.title) : equipment.title == null;
    }

    @Override
    public int hashCode() {
        int result = category != null ? category.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + price;
        return result;
    }
}
