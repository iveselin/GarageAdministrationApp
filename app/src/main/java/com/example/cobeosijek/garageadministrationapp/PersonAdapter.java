package com.example.cobeosijek.garageadministrationapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cobeosijek.garageadministrationapp.staff.Person;

import java.util.ArrayList;

/**
 * Created by cobeosijek on 12/10/2017.
 */

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {

    ArrayList<Person> persons;

    public PersonAdapter(ArrayList<Person> persons) {
        this.persons = persons;
    }

    @Override
    public PersonAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View personView = inflater.inflate(R.layout.item_person, parent, false);
        ViewHolder personViewHolder = new ViewHolder(personView);
        return personViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Person person = persons.get(position);
        holder.personNameTV.setText(person.getEmployeeName());
        holder.personFieldOfWorkTV.setText(person.getFieldOFWork().toString());
        holder.personSalaryTV.setText(String.valueOf(person.getWorkHours()));

    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView personNameTV;
        TextView personFieldOfWorkTV;
        TextView personSalaryTV;


        public ViewHolder(View itemView) {

            super(itemView);
            this.personNameTV = itemView.findViewById(R.id.personNameTV);
            this.personFieldOfWorkTV = itemView.findViewById(R.id.personFieldOfWorkTV);
            this.personSalaryTV = itemView.findViewById(R.id.personSalaryTV);
        }
    }
}
