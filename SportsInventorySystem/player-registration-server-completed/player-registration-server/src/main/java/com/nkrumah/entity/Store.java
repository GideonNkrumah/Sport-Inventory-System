package com.nkrumah.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedQuery(name = "Store.getByName", query = "SELECT s from Store s where s.name = :name")
public class Store implements  Serializable{
    //id, name, location and list of inventory
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;
    private String location;

    @OneToMany(mappedBy = "store", fetch = FetchType.EAGER)
    private List<Inventory> inventoryList;

    public Store(String name,String location){
        this.name = name;
        this.location = location;
    }


}
