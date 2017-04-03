package com.rentalshop.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * Here located a list with clients.
 * --- Setter isn't redundant. It needed for
 * parsing data from *xml with JAXB parser ---
 */

@XmlType(name = "units")
@XmlRootElement
public class Clients {
    private List<Renter> clients = new ArrayList<>();

    public Clients() {
    }

    public List<Renter> getClients() {
        return clients;
    }

    public void AddClient(Renter renter) {
        clients.add(renter);
    }

    public void setClients(List<Renter> clients) {
        this.clients = clients;
    }
}
