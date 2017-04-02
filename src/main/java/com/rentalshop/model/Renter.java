package com.rentalshop.model;

import java.io.Serializable;

/**
 * Describes a renter.
 */
public class Renter implements Serializable {
    private String name;
    private String surname;
    private String passport;
    private String firstItem;
    private String secondItem;
    private String thirdItem;
    private int availableItems;

    public Renter() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getFirstItem() {
        return firstItem;
    }

    public void setFirstItem(String firstItem) {
        this.firstItem = firstItem;
    }

    public String getSecondItem() {
        return secondItem;
    }

    public void setSecondItem(String secondItem) {
        this.secondItem = secondItem;
    }

    public String getThirdItem() {
        return thirdItem;
    }

    public void setThirdItem(String thirdItem) {
        this.thirdItem = thirdItem;
    }

    public int getAvailableItems() {
        return availableItems;
    }

    public void setAvailableItems(int availableItems) {
        this.availableItems = availableItems;
    }

    public void setItem(SportEquipment equipment) {
        if (firstItem == null) {
            firstItem = equipment.toString();
            availableItems--;
            return;
        }
        if (secondItem == null) {
            secondItem = equipment.toString();
            availableItems--;
            return;
        }
        if (thirdItem == null) {
            thirdItem = equipment.toString();
            availableItems--;
            return;
        }

    }

    public boolean hasRentedItems() {
        if (firstItem == null && secondItem == null && thirdItem == null) {
            return false;
        }
            else return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Renter renter = (Renter) o;

        if (name != null ? !name.equals(renter.name) : renter.name != null) return false;
        if (surname != null ? !surname.equals(renter.surname) : renter.surname != null) return false;
        return passport != null ? passport.equals(renter.passport) : renter.passport == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (passport != null ? passport.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}
