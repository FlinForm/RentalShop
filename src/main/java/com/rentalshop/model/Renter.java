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
    private SportEquipment[] equipment;

    public Renter(String name, String surname, String passport, int age) {
        this.name = name;
        this.surname = surname;
        this.passport = passport;
        this.age = age;
        equipment = new SportEquipment[3];
    }
}
