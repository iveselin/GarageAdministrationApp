package com.example.cobeosijek.garageadministrationapp.inventory;

import com.example.cobeosijek.garageadministrationapp.working_on.Car;

/**
 * Created by cobeosijek on 11/10/2017.
 */

public interface WorkingItem {

    boolean beUsed(Car carToBeFixed);

}
