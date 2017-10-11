package com.example.cobeosijek.garageadministrationapp.staff;

import com.example.cobeosijek.garageadministrationapp.inventory.ExpendableItem;
import com.example.cobeosijek.garageadministrationapp.inventory.ReusableItem;
import com.example.cobeosijek.garageadministrationapp.inventory.ReusableTypeEnum;
import com.example.cobeosijek.garageadministrationapp.working_on.Car;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cobeosijek on 11/10/2017.
 */

public class Technician extends Person {
    private int numOfApprentices;
    private static final int workCost = 120;

    public static int getWorkCost() {
        return workCost;
    }

    public Technician(String employeeName, FieldOfWorkEnum fieldOFWork, String accountIBAN, int numOfApprentices) {
        super(employeeName, fieldOFWork, accountIBAN);
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

            System.out.println("Not enough apprentices, try later");

            for (Apprentice apprentice : apprentices) {
                apprentice.setAvailable(true);
            }

            return;
        }
        //see if there is enough expendables
        if (this.getFieldOFWork() == FieldOfWorkEnum.BODYWORKER) {

            for (ExpendableItem expendableItem : expendableItems) {

                if (expendableItem.getQuantityLeft() < 1) {

                    System.out.println("You have to refill on your expendables first");

                    for (Apprentice apprentice : apprentices) {
                        apprentice.setAvailable(true);
                    }

                    return;
                }
            }
        }
        //if everything ok, do the work
        String outputString = String.format("Technician %s worked on the car and his work costs %.2f$. He also needed %d apprentice(s):",
                this.getEmployeeName(), (float) workCost, numOfApprentices);
        System.out.println(outputString);
        //make an apprentice do the work and then release them
        for (Apprentice workingApprentice : workingApprentices) {

            workingApprentice.doWork(carToFix);
            workingApprentice.setAvailable(true);
        }
        //output the tools used and cost of it
        System.out.println("They used: ");
        if (this.getFieldOFWork() == FieldOfWorkEnum.BODYWORKER) {

            for (ExpendableItem expendableItem : expendableItems) {

                if (!expendableItem.beUsed(carToFix)) {
                    System.out.println("\nYou have to refill on expendables, then try to fix the car\n");
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
