package com.example.cobeosijek.garageadministrationapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.cobeosijek.garageadministrationapp.staff.Person;

import java.util.ArrayList;

public class StaffListActivity extends AppCompatActivity {

    RecyclerView personListRV;
    PersonAdapter personAdapter;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.ItemDecoration itemDecoration;

    // TODO: 12/10/2017 implement touch
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
    }

    private ArrayList<Person> getPeople() {

        ArrayList<Person> persons = new ArrayList<>();
        persons.addAll(myGarage.getTechnicians());
        persons.addAll(myGarage.getApprentices());
        return persons;
    }
}
