package com.rentalshop.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.HashMap;
import java.util.Map;

/**
 * Here located a Hashmap with rented units.
 * The second parameter of Hashmap is  number of available item units.
 * --- Setter isn't redundant. It needed for
 * parsing data from *xml with JAXB parser ---
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
        equipment.setQuantity(quantity);
            if (rentedUnits.containsKey(equipment)) {
                int currentQuantity = rentedUnits.get(equipment);
                rentedUnits.remove(equipment);
                equipment.setQuantity(equipment.getQuantity() + currentQuantity);
                rentedUnits.put(equipment, equipment.getQuantity());
                return;
            }
        rentedUnits.put(equipment, quantity);
    }

    public void removeUnit(String returningItem) {
        SportEquipment equipment = getUnitForName(returningItem);
        if (rentedUnits.containsKey(equipment)) {
            int currentQuantity = rentedUnits.get(equipment);
            if (currentQuantity > 0) {
                rentedUnits.remove(equipment);
                equipment.setQuantity(currentQuantity - 1);
                rentedUnits.put(equipment, equipment.getQuantity());
                return;
            }
            if (rentedUnits.get(equipment).equals(0)) {
                rentedUnits.remove(equipment);
            }
        }
        rentedUnits.put(equipment, equipment.getQuantity());
    }

    private SportEquipment getUnitForName(String name) {
        SportEquipment equipment;
        String[] eqName = name.split(" ");
        for (Map.Entry<SportEquipment, Integer> entry : rentedUnits.entrySet()) {
            equipment = entry.getKey();
            if (eqName[0].equals(equipment.getCategory().toString())) {
                if (eqName[1].equals(equipment.getTitle())) {
                    return equipment;
                }
            }
        }
        return null;
    }

}
