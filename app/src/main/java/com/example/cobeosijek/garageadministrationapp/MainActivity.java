package com.example.cobeosijek.garageadministrationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cobeosijek.garageadministrationapp.working_on.Car;
import com.example.cobeosijek.garageadministrationapp.working_on.WorkNeededEnum;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int KEY_CAR_REQUEST = 2;
    public static final String KEY_OWNER_NAME = "owner_name";
    public static final String KEY_OWNER_EMAIL = "owner_email";
    public static final String KEY_WORK_NEEDED = "work_needed";
    public static final String KEY_GARAGE_SENT = "garage";

    Button inputCarButton, salaryCalculatorButton, refillExpendablesButton, checkBalanceButton;
    ImageView garageLogoImageView;

    Garage myGarage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUI();
        setListeners();
        myGarage = new Garage();
    }

    private void setUI() {

        inputCarButton = (Button) findViewById(R.id.inputCarBTN);
        salaryCalculatorButton = (Button) findViewById(R.id.salaryCalculatorBTN);
        refillExpendablesButton = (Button) findViewById(R.id.refillExpendablesBTN);
        checkBalanceButton = (Button) findViewById(R.id.checkBalanceBTN);
        garageLogoImageView = (ImageView) findViewById(R.id.garageLogoIV);

    }

    private void setListeners() {

        inputCarButton.setOnClickListener(this);
        salaryCalculatorButton.setOnClickListener(this);
        refillExpendablesButton.setOnClickListener(this);
        checkBalanceButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.inputCarBTN:

                Intent startCarInput = new Intent(getApplicationContext(), CarInputActivity.class);
                this.startActivityForResult(startCarInput, KEY_CAR_REQUEST);
                break;

            case R.id.salaryCalculatorBTN:
                // TODO: 13/10/2017 calculate salary in staff activity and reset work hours in main
                Intent startStaffList = new Intent(getApplicationContext(), StaffListActivity.class);
                startStaffList.putExtra(KEY_GARAGE_SENT, myGarage);
                startActivity(startStaffList);
                break;

            case R.id.refillExpendablesBTN:
                // TODO: 13/10/2017 fix so its on long click and reflect changes onto main garage
                Intent startExpendablesList = new Intent(getApplicationContext(), ExpendablesListActivity.class);
                startExpendablesList.putExtra(KEY_GARAGE_SENT, myGarage);
                startActivity(startExpendablesList);
                break;

            case R.id.checkBalanceBTN:
                // TODO: 11/10/2017 think how to improve this yeppp
                Toast.makeText(this, String.format("Your current bank balance is %.2f$", myGarage.getBankBalance()), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == KEY_CAR_REQUEST) {
            if (resultCode == RESULT_OK) {
                createCar(data.getExtras());
            }
        }
    }

    private void createCar(Bundle extras) {
        String ownerName = null;
        String ownerEmail = null;
        WorkNeededEnum workNeeded = null;
        // TODO: 12/10/2017 maybe put everything in one if so you are sure that you have everything...
        if (extras.containsKey(KEY_OWNER_NAME)) {
            ownerName = extras.getString(KEY_OWNER_NAME);
        }

        if (extras.containsKey(KEY_OWNER_EMAIL)) {
            ownerEmail = extras.getString(KEY_OWNER_EMAIL);
        }

        if (extras.containsKey(KEY_WORK_NEEDED)) {

            int workNeededFlag = extras.getInt(KEY_WORK_NEEDED);

            switch (workNeededFlag) {
                case 1:
                    workNeeded = WorkNeededEnum.MECHANIC;
                    break;
                case 2:
                    workNeeded = WorkNeededEnum.PAINTJOB;
                    break;
                case 3:
                    workNeeded = WorkNeededEnum.BOTH;
                    break;
            }
        }
        Car carToFix = new Car(ownerName, ownerEmail, workNeeded);

        String carTestOutput = String.format("%s, %s, %s", carToFix.getOwnerName(), carToFix.getOwnerEmail(), carToFix.getWorkNeeded());
        Toast.makeText(this, carTestOutput, Toast.LENGTH_SHORT).show();

        // TODO: 12/10/2017 fix the carToFix and display it somewhere

    }
}
