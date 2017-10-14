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

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int KEY_CAR_REQUEST = 2;
    public static final int KEY_GARAGE_REQUEST = 1111;
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

                Intent startStaffList = new Intent(getApplicationContext(), StaffListActivity.class);
                startStaffList.putExtra(KEY_GARAGE_SENT, myGarage);
                startActivityForResult(startStaffList, KEY_GARAGE_REQUEST);
                break;

            case R.id.refillExpendablesBTN:
                // TODO: 13/10/2017 fix so its on long click and reflect changes onto main garage
                Intent startExpendablesList = new Intent(getApplicationContext(), ExpendablesListActivity.class);
                startExpendablesList.putExtra(KEY_GARAGE_SENT, myGarage);
                startActivity(startExpendablesList);
                break;

            case R.id.checkBalanceBTN:

                Toast.makeText(this, String.format(Locale.getDefault(), "Your current bank balance is %.2f$",
                        myGarage.getBankBalance()), Toast.LENGTH_SHORT).show();
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
        } else if (requestCode == KEY_GARAGE_REQUEST) {
            if (resultCode == RESULT_OK) {
                updateGarage(data.getExtras());
            }
        }
    }

    private void updateGarage(Bundle extras) {
        if (extras.containsKey(KEY_GARAGE_SENT)){
            myGarage=(Garage) extras.getSerializable(KEY_GARAGE_SENT);
        }
    }

    private void createCar(Bundle extras) {
        String ownerName = null;
        String ownerEmail = null;
        WorkNeededEnum workNeeded = null;

        if (extras.containsKey(KEY_OWNER_NAME) && extras.containsKey(KEY_OWNER_EMAIL) && extras.containsKey(KEY_WORK_NEEDED)) {

            ownerName = extras.getString(KEY_OWNER_NAME);

            ownerEmail = extras.getString(KEY_OWNER_EMAIL);

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
        } else {
            return;
        }

        Car carToFix = new Car(ownerName, ownerEmail, workNeeded);

        myGarage.fixCar(carToFix);

        // TODO: 13/10/2017 send car and display its info
    }
}
