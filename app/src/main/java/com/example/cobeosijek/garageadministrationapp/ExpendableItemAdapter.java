package com.example.cobeosijek.garageadministrationapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cobeosijek.garageadministrationapp.inventory.ExpendableItem;

import java.util.ArrayList;

/**
 * Created by cobeosijek on 13/10/2017.
 */

public class ExpendableItemAdapter extends RecyclerView.Adapter<ExpendableItemAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }

    ArrayList<ExpendableItem> expendables;
    private OnItemClickListener itemClickListener;

    public ExpendableItemAdapter(ArrayList<ExpendableItem> expendables) {
        this.expendables = expendables;
    }

    @Override
    public ExpendableItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View expendableItemView = inflater.inflate(R.layout.item_expendable, parent, false);

        return new ViewHolder(expendableItemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ExpendableItem expendableItem = expendables.get(position);

        holder.itemNameTV.setText(expendableItem.getItemName());
        holder.itemTypeTV.setText(expendableItem.getExpendableType().toString());
        holder.itemQuantityTV.setText(expendableItem.getQuantityLeft() + "pcs");

    }

    public void setClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public int getItemCount() {
        return expendables.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView itemNameTV;
        TextView itemTypeTV;
        TextView itemQuantityTV;

        public ViewHolder(View itemView) {

            super(itemView);
            this.itemNameTV = itemView.findViewById(R.id.itemNameTV);
            this.itemTypeTV = itemView.findViewById(R.id.itemTypeTV);
            this.itemQuantityTV = itemView.findViewById(R.id.itemQuantityTV);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {

            itemClickListener.onClick(view, getAdapterPosition());
        }
    }
}
