package com.example.cobeosijek.garageadministrationapp.working_on;

/**
 * Created by cobeosijek on 11/10/2017.
 */

public class Car {

    private String ownerName;
    private String ownerEmail;
    private WorkNeededEnum workNeeded;
    private double workingCost;
    private boolean isFixed;

    public Car(String ownerName, String ownerEmail, WorkNeededEnum workNeeded) {
        this.ownerName = ownerName;
        this.ownerEmail = ownerEmail;
        this.workNeeded = workNeeded;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public WorkNeededEnum getWorkNeeded() {
        return workNeeded;
    }

    public double getWorkingCost() {
        return workingCost;
    }

    public boolean isFixed() {
        return isFixed;
    }


    public void setFixed(boolean fixed) {
        isFixed = fixed;
    }

    public void addWorkCost(double costToBeAdded) {
        this.workingCost += costToBeAdded;
    }
}
