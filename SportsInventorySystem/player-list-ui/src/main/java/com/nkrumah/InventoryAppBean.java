package com.nkrumah;
import com.nkrumah.entity.Inventory;
import com.nkrumah.entity.Store;

import com.nkrumah.interceptor.Logged;
import com.nkrumah.inventory.StoreService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.*;

@SessionScoped
@Named
public class InventoryAppBean implements Serializable{

    //Variables used for Creating the Store

    @NotEmpty
    private String strName;
    @NotEmpty
    private String strLocation;


    //Variables used for deleting products
    @NotEmpty
    private String delName;
    @NotEmpty
    private String delSport;


    //Variables used for Adding items to Store
    @NotEmpty
    private String name;
    @NotEmpty
    private String sport;
    @NotNull
    private int quantity;
    @NotNull
    private double pricePerUnit;

    //Attach StoreService to Ui
    @EJB
    private StoreService StoreService;

    //Return Inventory Items
    public List<Inventory> getInventoryList() {
        return StoreService.getInventoryList();
    }

    //Add Item to Inventory
    @Logged
    public void addInventory() {


        Inventory inventory = new Inventory(name, sport, quantity, pricePerUnit);

        Optional<Inventory> inventoryExists = StoreService.getInventoryList().stream().filter(i ->
                i.getName().equals(name) && i.getSport().equals(sport)).findFirst();
        if (inventoryExists.isPresent()) {
            StoreService.removeFromInventoryList(inventory);
        }
        StoreService.addToInventoryList(strName, inventory);
        clearFields();

    }

    //Delete Item from Inventory
    @Logged
    public void deleteInventory() {
        Inventory inventory = new Inventory(delName, delSport);
        StoreService.removeFromInventoryList(inventory);
    }

    //Add Store
    @Logged
    public String addStore() {


        Store store = new Store(strName, strLocation);
        StoreService.addToStoreList(store);

        return "inventoryList";

    }
    //Clear All Textboxes
    private void clearFields() {
        setPricePerUnit(0.00);
        setName("");
        setQuantity(0);
        setSport("");

    }

    // Getters and Setters
    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getSport() { return sport; }

    public void setSport(String sport) { this.sport = sport; }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPricePerUnit() { return pricePerUnit; }

    public void setPricePerUnit(double pricePerUnit) { this.pricePerUnit = pricePerUnit; }

    public String getStrName() { return strName; }

    public void setStrName(String strName) { this.strName = strName; }

    public String getStrLocation() { return strLocation; }

    public void setStrLocation(String strLocation) { this.strLocation = strLocation; }

    public String getDelName() { return delName; }

    public void setDelName(String delName) { this.delName = delName; }

    public String getDelSport() { return delSport; }

    public void setDelSport(String delSport) { this.delSport = delSport; }

}
