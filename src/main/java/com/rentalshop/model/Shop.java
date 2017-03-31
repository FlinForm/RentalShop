package com.rentalshop.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Map;

/**
 * Created by Pavel Davydenko on 30.03.2017.
 */
@XmlType(name = "shop")
@XmlRootElement
public class Shop {

    private Map<SportEquipment, Integer> goods;

    public Shop() {
    }


    public Map<SportEquipment, Integer> getGoods() {
        return goods;
    }

    public void addEquipment(SportEquipment equipment, Integer numberOfGoods) {
        if (goods.containsKey(equipment)) {
            int value = goods.get(equipment);
            value += numberOfGoods;
            goods.put(equipment, value);
            return;
        }
        goods.put(equipment, numberOfGoods);
    }

    public void setGoods(Map<SportEquipment, Integer> goods) {
        this.goods = goods;
    }

    public void addItem() {

    }

    public void removeItem() {

    }


}
