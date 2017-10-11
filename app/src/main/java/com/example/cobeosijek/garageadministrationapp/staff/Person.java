package com.example.cobeosijek.garageadministrationapp.staff;

/**
 * Created by cobeosijek on 11/10/2017.
 */

public class Person {
    private String employeeName;
    private FieldOfWorkEnum fieldOFWork;
    private String accountIBAN;
    private byte workHours;

    Person(String employeeName, FieldOfWorkEnum fieldOFWork, String accountIBAN) {
        this.employeeName = employeeName;
        this.fieldOFWork = fieldOFWork;
        this.accountIBAN = accountIBAN;
        this.workHours = 0;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public FieldOfWorkEnum getFieldOFWork() {
        return fieldOFWork;
    }

    public String getAccountIBAN() {
        return accountIBAN;
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
