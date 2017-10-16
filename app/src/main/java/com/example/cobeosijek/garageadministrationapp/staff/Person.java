package com.example.cobeosijek.garageadministrationapp.staff;

import java.io.Serializable;

/**
 * Created by cobeosijek on 11/10/2017.
 */

public class Person implements Serializable {
    private String employeeName;
    private FieldOfWorkEnum fieldOFWork;
    private byte workHours;

    public Person(String employeeName, FieldOfWorkEnum fieldOFWork) {
        this.employeeName = employeeName;
        this.fieldOFWork = fieldOFWork;
        this.workHours = 0;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public FieldOfWorkEnum getFieldOFWork() {
        return fieldOFWork;
    }

    public byte getWorkHours() {
        return workHours;
    }


    public void addWorkHours(int hoursToAdd) {
        workHours += hoursToAdd;
    }

    public void resetWorkHours() {
        this.workHours = 0;
    }
}
