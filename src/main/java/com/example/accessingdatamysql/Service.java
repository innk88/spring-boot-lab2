package com.example.accessingdatamysql;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Service {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    private List<ServicesInOrder> servicesInOrders;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ServicesInOrder> getServicesInOrders() {
        return servicesInOrders;
    }

    public void setServicesInOrders(List<ServicesInOrder> servicesInOrders) {
        this.servicesInOrders = servicesInOrders;
    }
}
