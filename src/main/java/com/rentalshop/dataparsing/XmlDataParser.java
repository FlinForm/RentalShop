package com.rentalshop.dataparsing;

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
    public Object getData(String filename, Object o){

        try {
            JAXBContext context = JAXBContext.newInstance(o.getClass());
            Unmarshaller unmarshaller = context.createUnmarshaller();
            o = unmarshaller.unmarshal(new File(filename));
        } catch (JAXBException ignored) {
        }
        return o;
    }

    @Override
    public void writeData(String filename, Object object){
        try {
            JAXBContext context = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                marshaller.marshal(object, new File(filename));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
