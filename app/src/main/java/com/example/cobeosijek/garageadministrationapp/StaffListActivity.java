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
import java.util.List;
import java.util.Locale;

public class StaffListActivity extends AppCompatActivity implements PersonAdapter.ItemClickListener {

    private static final String KEY_GARAGE_SENT = "garage";

    RecyclerView personListRV;
    PersonAdapter personAdapter;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.ItemDecoration itemDecoration;

    Garage myGarage;

    public static Intent getLaunchIntent(Context context, Garage myGarage) {
        Intent startStaffList = new Intent(context, StaffListActivity.class);
        startStaffList.putExtra(KEY_GARAGE_SENT, myGarage);
        return startStaffList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_list);

        myGarage = (Garage) getIntent().getSerializableExtra(KEY_GARAGE_SENT);
        setUI();
    }

    private void setUI() {
        personListRV = findViewById(R.id.staffListRV);
        personAdapter = new PersonAdapter();
        personAdapter.setPersonList(getPeople());
        layoutManager = new LinearLayoutManager(this);
        itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);

        personListRV.addItemDecoration(itemDecoration);
        personListRV.setLayoutManager(layoutManager);
        personListRV.setAdapter(personAdapter);

        personAdapter.setClickListener(this);


    }

    private List<Person> getPeople() {
        List<Person> persons = new ArrayList<>();
        persons.addAll(myGarage.getTechnicians());
        persons.addAll(myGarage.getApprentices());
        return persons;
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK, MainActivity.getResultGarageIntent(myGarage));
        finish();
    }

    @Override
    public void onClick(View view, int position) {

        int workCost;
        Person clickedPerson = getPeople().get(position);

        if (clickedPerson instanceof Technician) {
            workCost = 120;
        } else {
            workCost = 50;
        }

        String outputString = String.format(Locale.getDefault(), getString(R.string.person_salary_format_text),
                clickedPerson.getEmployeeName(), clickedPerson.getWorkHours(), clickedPerson.getWorkHours() * workCost * 0.7);
        Toast.makeText(getApplicationContext(), outputString, Toast.LENGTH_LONG).show();

        myGarage.changeBankBalance(-(clickedPerson.getWorkHours() * workCost * 0.7));

        getPeople().get(position).resetWorkHours();
        personAdapter.notifyDataSetChanged();
    }
}
