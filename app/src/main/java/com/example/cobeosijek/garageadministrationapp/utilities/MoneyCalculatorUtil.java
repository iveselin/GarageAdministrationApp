package com.example.cobeosijek.garageadministrationapp.utilities;

import com.example.cobeosijek.garageadministrationapp.Garage;
import com.example.cobeosijek.garageadministrationapp.inventory.ExpendableItem;
import com.example.cobeosijek.garageadministrationapp.staff.Apprentice;
import com.example.cobeosijek.garageadministrationapp.staff.Technician;

/**
 * Created by cobeosijek on 11/10/2017.
 */

public class MoneyCalculatorUtil {
    private static final double costOfPaint = 1.89;
    private static final double costOfSandpaper = 0.45;

    private MoneyCalculatorUtil() {
    }

    public static void refillExpendables(Garage currentGarage) {

        boolean refilled = false;
        for (ExpendableItem expendable : currentGarage.getExpendableItems()) {

            double costOfRefill = 0;

            if (expendable.getQuantityLeft() < 1) {

                refilled = true;
                switch (expendable.getExpendableType()) {

                    case SANDPAPER:
                        costOfRefill += 10 * costOfSandpaper;
                        expendable.addQuantity(10);
                        currentGarage.changeBankBalance(-costOfRefill);
                        System.out.println("Sandpaper was empty, it was refilled with 10pcs which costs " + costOfRefill + "$");
                        break;

                    case SPRAYPAINT:
                        costOfRefill += 5 * costOfPaint;
                        expendable.addQuantity(5);
                        currentGarage.changeBankBalance(-costOfRefill);
                        System.out.println("Spraypaint was empty, if was refilled with 5pcs which costs " + costOfRefill + "$");
                        break;
                }
            }
        }
        if (!refilled){
            System.out.println("You have enough in stock.");
        }
    }

    public static void calculateSalary(Garage currentGarage) {

        double salary;

        for (Technician technician : currentGarage.getTechnicians()) {

            salary = technician.getWorkHours() * Technician.getWorkCost() * 0.7;

            System.out.println(String.format("%s worked %d hour(s), and his salary sums up to: \t%.2f$",
                    technician.getEmployeeName(), technician.getWorkHours(), salary));

            currentGarage.changeBankBalance(-salary);
            technician.resetWorkHours();
        }
        for (Apprentice apprentice : currentGarage.getApprentices()) {

            salary = apprentice.getWorkHours() * Apprentice.getApprenticeWorkCost() * 0.7;

            System.out.println(String.format("%s worked %d hour(s), and his salary sums up to: \t%.2f$",
                    apprentice.getEmployeeName(), apprentice.getWorkHours(), salary));

            currentGarage.changeBankBalance(-salary);
            apprentice.resetWorkHours();
        }

    }
}
