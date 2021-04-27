package com.nkrumah.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedQuery(name = "Inventory.findAll", query = "SELECT i FROM Inventory i")
@NamedQuery(name = "Inventory.getByName", query = "SELECT i from Inventory i where i.name = :name")
public class Inventory implements Comparable<Inventory>, Serializable{
    @Id
    @GeneratedValue
    private Long id;


    private String name;
    private String sport;

    private int quantity;

    private double pricePerUnit;

    private Date dateUpdated;

    @PrePersist
    void createdAt() {
        this.dateUpdated = new Date();
    }


    @PreUpdate
    void updatedAt() {
        this.dateUpdated = new Date();
    }

    @ManyToOne
    @JoinColumn(name = "fk_store")
    private Store store;

    public Inventory(String name, String sport, int quantity, double price){
        this.name = name;
        this.sport = sport;
        this.quantity = quantity;
        this.pricePerUnit = price;
    }
    public Inventory(String name, String sport){
        this.name = name;
        this.sport = sport;
    }

    @Override
    public int compareTo(Inventory o) {
        return dateUpdated.compareTo(o.dateUpdated);
    }
}
