package com.rentalshop.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Map;

/**
 * Created by Pavel Davydenko on 30.03.2017.
 */

@XmlType(name = "units")
@XmlRootElement
public class RentedUnits {
    private Map<SportEquipment, Integer> rentedUnits;

    public RentedUnits() {
    }

    public Map<SportEquipment, Integer> getRentedUnits() {
        return rentedUnits;
    }

    public void setRentedUnits(Map<SportEquipment, Integer> rentedUnits) {
        this.rentedUnits = rentedUnits;
    }
}
