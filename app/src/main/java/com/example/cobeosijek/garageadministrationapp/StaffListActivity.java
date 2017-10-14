package com.example.cobeosijek.garageadministrationapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.cobeosijek.garageadministrationapp.staff.Person;
import com.example.cobeosijek.garageadministrationapp.staff.Technician;

import java.util.ArrayList;
import java.util.Locale;

public class StaffListActivity extends AppCompatActivity implements PersonAdapter.ItemClickListener {

    RecyclerView personListRV;
    PersonAdapter personAdapter;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.ItemDecoration itemDecoration;

    Garage myGarage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_list);
        myGarage = (Garage) getIntent().getSerializableExtra(MainActivity.KEY_GARAGE_SENT);
        setUI();

    }

    private void setUI() {

        Context context = getApplicationContext();
        personListRV = findViewById(R.id.staffListRV);
        personAdapter = new PersonAdapter(getPeople());
        layoutManager = new LinearLayoutManager(context);
        itemDecoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);

        personListRV.addItemDecoration(itemDecoration);
        personListRV.setLayoutManager(layoutManager);
        personListRV.setAdapter(personAdapter);

        personAdapter.setClickListener(this);
    }

    private ArrayList<Person> getPeople() {

        ArrayList<Person> persons = new ArrayList<>();
        persons.addAll(myGarage.getTechnicians());
        persons.addAll(myGarage.getApprentices());
        return persons;
    }

    @Override
    public void onBackPressed() {

        Intent resultIntent = new Intent();

        resultIntent.putExtra(MainActivity.KEY_GARAGE_SENT, myGarage);
        this.setResult(RESULT_OK, resultIntent);

        this.finish();
    }

    @Override
    public void onClick(View view, int position) {

        int workCost;
        Person clickedPerson = getPeople().get(position);
        if (clickedPerson.getClass() == Technician.class) {
            workCost = 120;
        } else {
            workCost = 50;
        }
        String outputString = String.format(Locale.getDefault(), "%s worked for %d hour(s) and his salary is: %.2f",
                clickedPerson.getEmployeeName(), clickedPerson.getWorkHours(), clickedPerson.getWorkHours() * workCost * 0.7);

        myGarage.changeBankBalance(-(clickedPerson.getWorkHours() * workCost * 0.7));

        getPeople().get(position).resetWorkHours();
        personAdapter.notifyDataSetChanged();


        Toast.makeText(getApplicationContext(), outputString, Toast.LENGTH_LONG).show();

    }
}
