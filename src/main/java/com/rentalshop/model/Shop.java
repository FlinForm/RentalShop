package com.rentalshop.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pavel Davydenko on 30.03.2017.
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


    public void addItem(SportEquipment equipment) {
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

    public void refreshItem(SportEquipment equipment) {
        goods.remove(equipment);
        goods.put(equipment, equipment.getQuantity());
    }

    public void removeItem(SportEquipment equipment) {
        goods.remove(equipment);
    }


}
