package com.rentalshop.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pavel Davydenko on 01.04.2017.
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
