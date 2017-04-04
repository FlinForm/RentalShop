package com.rentalshop.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is a storage of units available in rental shop.
 * The second parameter of Hashmap is  number of available item units.
 * --- Setter isn't redundant. It needed for
 * parsing data from *xml with JAXB parser ---
 */
@XmlType(name = "shop")
@XmlRootElement
public class Shop {

    private Map<SportEquipment, Integer> goods = new HashMap<>();

    public Shop() {
    }

    public Map<SportEquipment, Integer> getGoods() {
        return goods;
    }

    public void setGoods(Map<SportEquipment, Integer> goods) {
        this.goods = goods;
    }

    public void addNewItem(SportEquipment equipment) {
        if ("".equals(equipment.getTitle()) || "".equals(equipment.getPrice()) || "".equals(equipment.getQuantity())) {
            return;
        }
        if (goods.containsKey(equipment)) {
            int quantity = goods.get(equipment);
            goods.remove(equipment);
            equipment.setQuantity(equipment.getQuantity() + quantity);
            goods.put(equipment, equipment.getQuantity());
            return;
        }
        goods.put(equipment, equipment.getQuantity());
    }

    public void returnRentedItem(SportEquipment equipment) {
        if (goods.containsKey(equipment)) {
            goods.remove(equipment);
            equipment.incQuantity();
            goods.put(equipment, equipment.getQuantity());
            return;
        }
        goods.put(equipment, equipment.getQuantity());
    }

    public void refreshItem(SportEquipment equipment) {
        goods.remove(equipment);
        goods.put(equipment, equipment.getQuantity());
    }

    public void removeItem(SportEquipment equipment) {
        goods.remove(equipment);
    }

    public SportEquipment getUnitForName(String name) {
        SportEquipment equipment;
        String[] eqName = name.split(" ");
        for (Map.Entry<SportEquipment, Integer> entry : goods.entrySet()) {
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
