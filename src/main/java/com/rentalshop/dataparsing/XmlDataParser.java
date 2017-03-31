package com.rentalshop.dataparsing;

import com.rentalshop.model.Shop;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by Pavel Davydenko on 31.03.2017.
 */
public class XmlDataParser implements DataParser{
    @Override
    public Shop getData(String filename){
        Shop shop = new Shop();
        try {
            JAXBContext context = JAXBContext.newInstance(Shop.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            shop = (Shop) unmarshaller.unmarshal(new File(filename));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return shop;
    }

    @Override
    public void writeData(String filename, Shop shop){
        try {
            JAXBContext context = JAXBContext.newInstance(Shop.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                marshaller.marshal(shop, new File(filename));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
