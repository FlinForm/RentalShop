package com.rentalshop.model;

import java.io.Serializable;

/**
 * Created by Pavel Davydenko on 30.03.2017.
 */
public class Renter implements Serializable {
    private String name;
    private String surname;
    private String passport;
    private int age;
    private String firstItem;
    private String secondItem;
    private String thirdItem;

    public Renter(String name, String surname, String passport, int age) {
        this.name = name;
        this.surname = surname;
        this.passport = passport;
        this.age = age;
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
}
