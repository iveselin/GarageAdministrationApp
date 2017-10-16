package com.example.cobeosijek.garageadministrationapp.inventory;

import java.io.Serializable;

/**
 * Created by cobeosijek on 11/10/2017.
 */

public class Item implements Serializable {

    private String itemName;

    Item(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }
}
