package com.example.cobeosijek.garageadministrationapp.inventory;

import com.example.cobeosijek.garageadministrationapp.working_on.Car;

import java.io.Serializable;

/**
 * Created by cobeosijek on 11/10/2017.
 */

public class ExpendableItem extends Item implements WorkingItem, Serializable {

    private ExpendableTypeEnum expendableType;
    private int quantityLeft;

    public ExpendableItem(String itemName, ExpendableTypeEnum expendableType) {

        super(itemName);
        this.expendableType = expendableType;
        this.quantityLeft = setQuantity();

    }

    private int setQuantity() {

        switch (expendableType) {
            case SPRAYPAINT:
                return 5;
            case SANDPAPER:
                return 10;
            default:
                return 0;
        }
    }

    public int getQuantityLeft() {
        return quantityLeft;
    }

    @Override
    public boolean beUsed(Car carToBeFixed) {

        if (this.quantityLeft > 0) {

            carToBeFixed.addWorkCost(this.getUseCost());
            quantityLeft--;
            return true;

        } else {

            return false;
        }

    }

    public double getUseCost() {

        switch (expendableType) {

            case SANDPAPER:
                return 0.50;

            case SPRAYPAINT:
                return 2.80;

            default:
                return 0.00;

        }
    }

    public ExpendableTypeEnum getExpendableType() {

        return expendableType;
    }

    public void addQuantity(int quantity) {

        this.quantityLeft += quantity;
    }
}
