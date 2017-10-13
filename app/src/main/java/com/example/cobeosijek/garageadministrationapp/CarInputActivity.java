package com.example.cobeosijek.garageadministrationapp;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_input);

        setUI();
        setListeners();
    }

    private void setUI() {

        ownerEmailET = (EditText) findViewById(R.id.ownerEmailET);
        ownerNameET = (EditText) findViewById(R.id.ownerNameET);
        workNeededRG = (RadioGroup) findViewById(R.id.workNeededRG);
        submitBTN = (Button) findViewById(R.id.submitCarBTN);
        workNeededRBTN = findViewById(R.id.mechanicRB);

    }

    private void setListeners() {
        workNeededRG.setOnCheckedChangeListener(this);
        submitBTN.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        if (TextUtils.isEmpty(ownerNameET.getText().toString())) {

            ownerNameET.setError("You can't leave this empty");
            return;

        } else if (TextUtils.isEmpty(ownerEmailET.getText().toString())) {

            ownerEmailET.setError("You can't leave this empty");
            return;
        }

        String ownerName = ownerNameET.getText().toString();
        String ownerEmail = ownerEmailET.getText().toString();
        int workNeededFlag = workNeededRG.indexOfChild(workNeededRBTN);


        Intent resultIntent = new Intent();
        resultIntent.putExtra(MainActivity.KEY_OWNER_NAME, ownerName);
        resultIntent.putExtra(MainActivity.KEY_OWNER_EMAIL, ownerEmail);
        resultIntent.putExtra(MainActivity.KEY_WORK_NEEDED, workNeededFlag);

        this.setResult(RESULT_OK, resultIntent);

        this.finish();

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

        workNeededRBTN = (RadioButton) findViewById(i);
    }
}
