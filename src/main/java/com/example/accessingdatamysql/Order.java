package com.example.accessingdatamysql;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<ServicesInOrder> servicesInOrders;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<ServicesInOrder> getServicesInOrders() {
        return servicesInOrders;
    }

    public void setServicesInOrders(List<ServicesInOrder> servicesInOrders) {
        this.servicesInOrders = servicesInOrders;
    }
}