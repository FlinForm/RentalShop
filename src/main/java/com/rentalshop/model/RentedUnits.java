package com.rentalshop.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pavel Davydenko on 30.03.2017.
 */

@XmlType(name = "units")
@XmlRootElement
public class RentedUnits {
    private Map<SportEquipment, Integer> rentedUnits = new HashMap<>();

    public RentedUnits() {
    }

    public Map<SportEquipment, Integer> getRentedUnits() {
        return rentedUnits;
    }

    public void setRentedUnits(Map<SportEquipment, Integer> rentedUnits) {
        this.rentedUnits = rentedUnits;
    }

    public void  addUnit(SportEquipment equipment, int quantity) {
        if (rentedUnits != null) {
            if (rentedUnits.containsKey(equipment)) {
                int currentQuantity = rentedUnits.get(equipment);
                rentedUnits.remove(equipment);
                equipment.setQuantity(equipment.getQuantity() + currentQuantity);
                rentedUnits.put(equipment, equipment.getQuantity());
                return;
            }
        }
        System.out.println(equipment == null);

        rentedUnits.put(equipment, quantity);
    }
}
