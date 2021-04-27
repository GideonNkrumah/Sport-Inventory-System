package com.nkrumah.inventory;

import com.nkrumah.entity.Inventory;
import com.nkrumah.entity.Store;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Remote(StoreService.class)
public class StoreServiceImpl implements StoreService {



    @PersistenceContext
    private EntityManager em;

    //Adding Data to empty inventory lists
    private void PopulateInventory(String name){
        addToInventoryList(name,new Inventory("Soccer Ball", "Soccer", 42, 32.34));
        addToInventoryList(name,new Inventory("Hockey Jersey", "Hockey", 13, 69.99));
        addToInventoryList(name,new Inventory("Basketball Jersey", "Basketball", 7, 111.11));
    }


    //Used to get all Inventory Items
    @Override
    public List<Inventory> getInventoryList() {

        List<Inventory> inventoryList = em.createNamedQuery("Inventory.findAll", Inventory.class)
                .getResultList();


        return inventoryList.stream()
                .sorted()
                .collect(Collectors.toList());

    }

    //Used to add item to inventory
    @Override
    public void addToInventoryList(String name, Inventory i) {
        Store correspondingStore = em.createNamedQuery("Store.getByName", Store.class)
                .setParameter("name", name)
                .getSingleResult();
        i.setStore(correspondingStore);
        em.persist(i);
    }

    //Used to Add new Store to Store list
    @Override
    public void addToStoreList(Store s) {

        em.persist(s);
        PopulateInventory(s.getName());
    }

    //Used to remove product from inventory list
    @Override
    public void removeFromInventoryList(Inventory i) {
        Inventory correspondingItem = em.createNamedQuery("Inventory.getByName", Inventory.class)
                .setParameter("name", i.getName())
                .getSingleResult();
        em.remove(correspondingItem);
    }


}
