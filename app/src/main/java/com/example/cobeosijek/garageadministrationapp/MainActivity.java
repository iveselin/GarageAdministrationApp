package com.example.cobeosijek.garageadministrationapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.cobeosijek.garageadministrationapp.working_on.Car;
import com.example.cobeosijek.garageadministrationapp.working_on.WorkNeededEnum;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int KEY_CAR_REQUEST = 2;
    private static final int KEY_GARAGE_REQUEST = 1111;
    private static final String KEY_GARAGE_SENT = "garage_sent";
    private static final String KEY_OWNER_NAME = "owner_name";
    private static final String KEY_OWNER_EMAIL = "owner_email";
    private static final String KEY_WORK_NEEDED = "work_needed";


    Button inputCarButton;
    Button salaryCalculatorButton;
    Button refillExpendablesButton;
    Button checkBalanceButton;

    Garage myGarage;

    public static Intent getResultGarageIntent(Garage myGarage) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(KEY_GARAGE_SENT, myGarage);
        return resultIntent;
    }

    public static Intent getResultCarIntent(String ownerName, String ownerEmail, int workNeededFlag) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(KEY_OWNER_NAME, ownerName);
        resultIntent.putExtra(KEY_OWNER_EMAIL, ownerEmail);
        resultIntent.putExtra(KEY_WORK_NEEDED, workNeededFlag);
        return resultIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setUI();
        setListeners();
        myGarage = new Garage();
    }

    private void setUI() {

        inputCarButton = findViewById(R.id.inputCarBTN);
        salaryCalculatorButton = findViewById(R.id.salaryCalculatorBTN);
        refillExpendablesButton = findViewById(R.id.refillExpendablesBTN);
        checkBalanceButton = findViewById(R.id.checkBalanceBTN);
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
                startActivityForResult(CarInputActivity.getLaunchIntent(this), KEY_CAR_REQUEST);
                break;

            case R.id.salaryCalculatorBTN:
                startActivityForResult(StaffListActivity.getLaunchIntent(this, myGarage), KEY_GARAGE_REQUEST);
                break;

            case R.id.refillExpendablesBTN:
                startActivityForResult(ExpendablesListActivity.getLaunchIntent(this, myGarage), KEY_GARAGE_REQUEST);
                break;

            case R.id.checkBalanceBTN:
                Toast.makeText(getApplicationContext(), String.format(Locale.getDefault(), getString(R.string.bank_balance_format),
                        myGarage.getBankBalance()), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == KEY_CAR_REQUEST) {
                createCar(data.getExtras());
            } else if (requestCode == KEY_GARAGE_REQUEST) {
                updateGarage(data.getExtras());
            }
        }
    }

    private void updateGarage(Bundle extras) {
        if (extras.containsKey(KEY_GARAGE_SENT)) {
            myGarage = (Garage) extras.getSerializable(KEY_GARAGE_SENT);
        }
    }

    private void createCar(Bundle extras) {
        if (extras.containsKey(KEY_OWNER_NAME) && extras.containsKey(KEY_OWNER_EMAIL) && extras.containsKey(KEY_WORK_NEEDED)) {

            String ownerName = extras.getString(KEY_OWNER_NAME);
            String ownerEmail = extras.getString(KEY_OWNER_EMAIL);

            int workNeededFlag = extras.getInt(KEY_WORK_NEEDED);
            WorkNeededEnum workNeeded = null;

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

            Car carToFix = new Car(ownerName, ownerEmail, workNeeded);

            myGarage.fixCar(carToFix);

            String outputString = String.format(getString(R.string.car_fixed_text), carToFix.getWorkingCost());
            Toast.makeText(getApplicationContext(), outputString, Toast.LENGTH_SHORT).show();
        }
    }
}
