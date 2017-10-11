package com.example.cobeosijek.garageadministrationapp.inventory;

import com.example.cobeosijek.garageadministrationapp.working_on.Car;

/**
 * Created by cobeosijek on 11/10/2017.
 */

public class ExpendableItem extends Item implements WorkingItem {
    private ExpendableTypeEnum expendableType;
    private int quantityLeft;

    public ExpendableItem(String itemName, ExpendableTypeEnum expendableType) {

        super(itemName);
        this.expendableType = expendableType;
// TODO: 10/10/2017 dont use this in constr.
        switch (expendableType) {
            case SPRAYPAINT:
                quantityLeft = 5;
                break;
            case SANDPAPER:
                quantityLeft = 10;
                break;
            default:
                quantityLeft = 0;
                break;
        }
    }

    public int getQuantityLeft() {
        return quantityLeft;
    }

    @Override
    public boolean beUsed(Car carToBeFixed) {

        if (this.quantityLeft > 0) {

            System.out.println(String.format("\t%s was used and it costs %.2f$", this.getItemName(), this.getUseCost()));
            carToBeFixed.addWorkCost(this.getUseCost());
            quantityLeft--;
            return true;

        } else {

            System.out.println("Oh no, there is not enough " + expendableType + ", you should refill.");
            return false;
        }

    }

    private double getUseCost() {

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
