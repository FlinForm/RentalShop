package com.rentalshop.dataparsing;

/**
 * Created by Pavel Davydenko on 31.03.2017.
 */
public interface DataParser {

    Object getData(String filename, Object o);

    void writeData(String filename, Object o);

}
