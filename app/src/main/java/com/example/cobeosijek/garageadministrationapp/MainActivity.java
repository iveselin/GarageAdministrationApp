package com.example.cobeosijek.garageadministrationapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button inputCarButton, salaryCalculatorButton, refillExpendablesButton, checkBalanceButton;

    ImageView garageLogoImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUI();
    }

    private void setUI() {

        inputCarButton = (Button) findViewById(R.id.inputCarButton);
        salaryCalculatorButton = (Button) findViewById(R.id.salaryCalculatorButton);
        refillExpendablesButton = (Button) findViewById(R.id.refillExpendablesButton);
        checkBalanceButton = (Button) findViewById(R.id.checkBalanceButton);
        garageLogoImageView = (ImageView) findViewById(R.id.garageLogoImageView);

        inputCarButton.setOnClickListener(this);
        salaryCalculatorButton.setOnClickListener(this);
        refillExpendablesButton.setOnClickListener(this);
        checkBalanceButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.inputCarButton:

                break;
            case R.id.salaryCalculatorButton:

                break;
            case R.id.refillExpendablesButton:

                break;
            case R.id.checkBalanceButton:

                break;
        }
    }
}
