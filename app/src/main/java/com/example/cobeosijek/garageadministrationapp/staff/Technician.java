package com.example.cobeosijek.garageadministrationapp.staff;

import com.example.cobeosijek.garageadministrationapp.inventory.ExpendableItem;
import com.example.cobeosijek.garageadministrationapp.inventory.ReusableItem;
import com.example.cobeosijek.garageadministrationapp.inventory.ReusableTypeEnum;
import com.example.cobeosijek.garageadministrationapp.working_on.Car;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cobeosijek on 11/10/2017.
 */

public class Technician extends Person implements Serializable {
    private int numOfApprentices;
    private static final int workCost = 120;

    public static int getWorkCost() {
        return workCost;
    }

    public Technician(String employeeName, FieldOfWorkEnum fieldOFWork, int numOfApprentices) {
        super(employeeName, fieldOFWork);
        this.numOfApprentices = numOfApprentices;
    }


    public void doWork(Car carToFix, List<Apprentice> apprentices, List<ReusableItem> reusableItems, List<ExpendableItem> expendableItems) {
        //give a technician a car to be fixed, he will find helpers & tools from lists, if there is enough of everything, he will fix the car
        int currentNumOfApprentices = 0;
        List<Apprentice> workingApprentices = new ArrayList<>();

        //reserve apprentices
        for (Apprentice apprentice : apprentices) {

            if (this.numOfApprentices > currentNumOfApprentices && this.getFieldOFWork() == apprentice.getFieldOFWork() && apprentice.isAvailable()) {
                apprentice.setAvailable(false);
                workingApprentices.add(apprentice);
                currentNumOfApprentices++;
            }
        }
        //if not enough apprentices, abort
        if (currentNumOfApprentices < this.numOfApprentices) {

            for (Apprentice apprentice : apprentices) {
                apprentice.setAvailable(true);
            }

            return;
        }
        //see if there is enough expendables
        if (this.getFieldOFWork() == FieldOfWorkEnum.BODYWORKER) {

            for (ExpendableItem expendableItem : expendableItems) {

                if (expendableItem.getQuantityLeft() < 1) {


                    for (Apprentice apprentice : apprentices) {
                        apprentice.setAvailable(true);
                    }

                    return;
                }
            }
        }
        //if everything ok, do the work
        //make an apprentice do the work and then release them
        for (Apprentice workingApprentice : workingApprentices) {

            workingApprentice.doWork(carToFix);
            workingApprentice.setAvailable(true);
        }
        //output the tools used and cost of it

        if (this.getFieldOFWork() == FieldOfWorkEnum.BODYWORKER) {

            for (ExpendableItem expendableItem : expendableItems) {

                if (!expendableItem.beUsed(carToFix)) {
                    return;
                }

            }
            for (ReusableItem reusableItem : reusableItems) {
                if (reusableItem.getTypeEnum() != ReusableTypeEnum.WRENCH) {
                    reusableItem.beUsed(carToFix);
                }
            }

        } else {

            for (ReusableItem reusableItem : reusableItems) {
                if (reusableItem.getTypeEnum() == ReusableTypeEnum.WRENCH) {
                    reusableItem.beUsed(carToFix);
                }
            }
        }
        //add a work hour for salary calculation, and tell the car that he is fixed, and how much does it cost
        this.addWorkHours(1);
        carToFix.setFixed(true);
        carToFix.addWorkCost(workCost);

    }
}
