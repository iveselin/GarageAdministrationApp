package com.example.cobeosijek.garageadministrationapp.staff;

import com.example.cobeosijek.garageadministrationapp.working_on.Car;

import java.io.Serializable;

/**
 * Created by cobeosijek on 11/10/2017.
 */

public class Apprentice extends Person implements Serializable{
    private boolean isAvailable = true;
    private static final int workCost = 50;

    public Apprentice(String employeeName, FieldOfWorkEnum fieldOFWork) {
        super(employeeName, fieldOFWork);
    }


    public void doWork(Car carToFix) {

        String outputString = String.format("\tApprentice %s helped the tehnician and his work costs %.2f$",
                this.getEmployeeName(), (float) workCost);
        System.out.println(outputString);

        carToFix.addWorkCost(workCost);
        this.addWorkHours(1);
    }

    public static int getApprenticeWorkCost() {
        return workCost;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
