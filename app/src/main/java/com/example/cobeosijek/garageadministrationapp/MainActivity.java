package com.example.cobeosijek.garageadministrationapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button inputCarButton, salaryCalculatorButton, refillExpendablesButton, checkBalanceButton;
    ImageView garageLogoImageView;

    Garage myGarage = new Garage();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUI();
        setListeners();
    }

    private void setUI() {

        inputCarButton = (Button) findViewById(R.id.inputCarButton);
        salaryCalculatorButton = (Button) findViewById(R.id.salaryCalculatorButton);
        refillExpendablesButton = (Button) findViewById(R.id.refillExpendablesButton);
        checkBalanceButton = (Button) findViewById(R.id.checkBalanceButton);
        garageLogoImageView = (ImageView) findViewById(R.id.garageLogoImageView);

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
            case R.id.inputCarButton:
                // TODO: 11/10/2017 go to car activity
                break;
            case R.id.salaryCalculatorButton:
                // TODO: 11/10/2017 calculate salary and show results in recycler view
                break;
            case R.id.refillExpendablesButton:
                // TODO: 11/10/2017 show a list of expendables and on click add new items to it
                break;
            case R.id.checkBalanceButton:
                // TODO: 11/10/2017 think how to improve this
                Toast.makeText(this, String.format("Your current bank balance is %.2f$", myGarage.getBankBalance()),Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
