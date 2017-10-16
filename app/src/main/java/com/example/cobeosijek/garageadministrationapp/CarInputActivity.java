package com.example.cobeosijek.garageadministrationapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class CarInputActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    EditText ownerNameET;
    EditText ownerEmailET;

    RadioGroup workNeededRG;
    RadioButton workNeededRBTN;

    Button submitBTN;

    public static Intent getLaunchIntent(Context context) {
        return new Intent(context, CarInputActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_input);

        setUI();
        setListeners();
    }

    private void setUI() {
        ownerEmailET = findViewById(R.id.ownerEmailET);
        ownerNameET = findViewById(R.id.ownerNameET);
        workNeededRG = findViewById(R.id.workNeededRG);
        submitBTN = findViewById(R.id.submitCarBTN);
        workNeededRBTN = findViewById(R.id.mechanicRB);
    }

    private void setListeners() {
        workNeededRG.setOnCheckedChangeListener(this);
        submitBTN.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (TextUtils.isEmpty(ownerNameET.getText().toString())) {
            ownerNameET.setError(getString(R.string.empty_TV_error_text));
            return;
        } else if (TextUtils.isEmpty(ownerEmailET.getText().toString())) {
            ownerEmailET.setError(getString(R.string.empty_TV_error_text));
            return;
        }

        String ownerName = ownerNameET.getText().toString().trim();
        String ownerEmail = ownerEmailET.getText().toString().trim();
        int workNeededFlag = workNeededRG.indexOfChild(workNeededRBTN);

        setResult(RESULT_OK, MainActivity.getResultCarIntent(ownerName, ownerEmail, workNeededFlag));
        finish();
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        workNeededRBTN = findViewById(i);
    }
}
