package com.example.accessingdatamysql;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @Column
    private String name;
//    @OneToMany(mappedBy = "client")
//    private List<Order> order;

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

//    public List<Order> getOrder() {
//        return order;
//    }
//
//    public void setOrder(List<Order> order) {
//        this.order = order;
//    }
}
