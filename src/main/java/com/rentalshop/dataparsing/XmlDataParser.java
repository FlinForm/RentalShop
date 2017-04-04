package com.rentalshop.dataparsing;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Getting and writing data to *.xml files with JAXB parser.
 */
public class XmlDataParser {
    public Object getData(String filename, Object o){
        try {
            JAXBContext context = JAXBContext.newInstance(o.getClass());
            Unmarshaller unmarshaller = context.createUnmarshaller();
            o = unmarshaller.unmarshal(new File(filename));
        } catch (JAXBException ignored) {
        }
        return o;
    }

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
