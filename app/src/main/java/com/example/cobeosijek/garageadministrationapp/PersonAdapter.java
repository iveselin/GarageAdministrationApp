package com.example.cobeosijek.garageadministrationapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cobeosijek.garageadministrationapp.staff.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cobeosijek on 12/10/2017.
 */

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {

    public interface ItemClickListener {
        void onClick(View view, int position);
    }

    private List<Person> persons = new ArrayList<>();
    private ItemClickListener itemClickListener;

    public void setPersonList(List<Person> personList) {
        persons.clear();
        persons.addAll(personList);
        notifyDataSetChanged();
    }

    public void addPersonList(List<Person> personList) {
        persons.addAll(personList);
        notifyDataSetChanged();
    }

    public void setClickListener(PersonAdapter.ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public PersonAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View personView = inflater.inflate(R.layout.item_person, parent, false);

        return new ViewHolder(personView, itemClickListener);
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


    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ItemClickListener listener;
        TextView personNameTV;
        TextView personFieldOfWorkTV;
        TextView personSalaryTV;

        public ViewHolder(View itemView, ItemClickListener listener) {
            super(itemView);

            this.listener = listener;
            this.personNameTV = itemView.findViewById(R.id.personName);
            this.personFieldOfWorkTV = itemView.findViewById(R.id.personFieldOfWork);
            this.personSalaryTV = itemView.findViewById(R.id.personSalary);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (listener != null) {
                listener.onClick(view, getAdapterPosition());
            }
        }
    }
}
