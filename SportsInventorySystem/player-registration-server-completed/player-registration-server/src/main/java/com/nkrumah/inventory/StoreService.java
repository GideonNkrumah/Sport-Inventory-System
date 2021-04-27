package com.nkrumah.inventory;

import com.nkrumah.entity.Inventory;
import com.nkrumah.entity.Store;

import java.util.List;

public interface StoreService {
    List<Inventory> getInventoryList();
    void addToInventoryList(String name, Inventory i);
    void removeFromInventoryList(Inventory i);
    void addToStoreList(Store s);
}
