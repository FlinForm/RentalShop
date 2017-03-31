package com.rentalshop.dataparsing;

import com.rentalshop.model.Shop;

/**
 * Created by Pavel Davydenko on 31.03.2017.
 */
public interface DataParser {

    Shop getData(String filename);

    void writeData(String filename, Shop shop);

}
