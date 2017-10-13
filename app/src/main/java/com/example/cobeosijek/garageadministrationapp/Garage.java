package com.example.cobeosijek.garageadministrationapp;

import com.example.cobeosijek.garageadministrationapp.inventory.ExpendableItem;
import com.example.cobeosijek.garageadministrationapp.inventory.ReusableItem;
import com.example.cobeosijek.garageadministrationapp.staff.Apprentice;
import com.example.cobeosijek.garageadministrationapp.staff.FieldOfWorkEnum;
import com.example.cobeosijek.garageadministrationapp.staff.Technician;
import com.example.cobeosijek.garageadministrationapp.utilities.GarageCreationUtil;
import com.example.cobeosijek.garageadministrationapp.working_on.Car;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cobeosijek on 11/10/2017.
 */

public class Garage implements Serializable {

    private List<Apprentice> apprentices = new ArrayList<>();
    private List<Technician> technicians = new ArrayList<>();
    private List<ReusableItem> reusableItems = new ArrayList<>();
    private List<ExpendableItem> expendableItems = new ArrayList<>();
    private double bankBalance;

    public Garage() {

        apprentices = GarageCreationUtil.createApprentices();
        technicians = GarageCreationUtil.createTehnicians();
        reusableItems = GarageCreationUtil.createReusableItems();
        expendableItems = GarageCreationUtil.createExpendableItems();
        bankBalance = 10000;

    }

    public void fixCar(Car inputCar) {

        switch (inputCar.getWorkNeeded()) {

            case MECHANIC:
                for (Technician technician : technicians) {

                    if (technician.getFieldOFWork() == FieldOfWorkEnum.MECHANIC) {
                        technician.doWork(inputCar, apprentices, reusableItems, expendableItems);
                        break;
                    }
                }
                break;

            case PAINTJOB:
                for (Technician technician : technicians) {

                    if (technician.getFieldOFWork() == FieldOfWorkEnum.BODYWORKER) {
                        technician.doWork(inputCar, apprentices, reusableItems, expendableItems);
                        break;
                    }
                }
                break;

            case BOTH:
                for (Technician technician : technicians) {

                    if (technician.getFieldOFWork() == FieldOfWorkEnum.BODYWORKER) {
                        technician.doWork(inputCar, apprentices, reusableItems, expendableItems);
                        break;
                    }
                }
                for (Technician technician : technicians) {

                    if (technician.getFieldOFWork() == FieldOfWorkEnum.MECHANIC) {
                        technician.doWork(inputCar, apprentices, reusableItems, expendableItems);
                        break;
                    }
                }
                break;

            default:
                System.out.println("That is a mistake, the car is empty");
        }
        if (inputCar.isFixed()) {

            this.changeBankBalance(inputCar.getWorkingCost());


        } else {
            //alert with mistake
            //System.out.println("We don't have enough technicians, try later");
        }
    }

    public List<Apprentice> getApprentices() {
        return apprentices;
    }

    public List<Technician> getTechnicians() {
        return technicians;
    }

    public double getBankBalance() {
        return bankBalance;
    }

    public void changeBankBalance(double bankBalance) {
        this.bankBalance += bankBalance;
    }

    public List<ExpendableItem> getExpendableItems() {
        return expendableItems;
    }

}
