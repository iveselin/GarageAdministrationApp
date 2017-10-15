package com.example.cobeosijek.garageadministrationapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.cobeosijek.garageadministrationapp.inventory.ExpendableItem;

import java.util.ArrayList;

public class ExpendablesListActivity extends AppCompatActivity implements ExpendableItemAdapter.OnItemClickListener {

    RecyclerView itemListRV;
    ExpendableItemAdapter itemAdapter;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.ItemDecoration itemDecoration;

    Garage myGarage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expendables_list);
        myGarage = (Garage) getIntent().getSerializableExtra(MainActivity.KEY_GARAGE_SENT);
        setUI();
    }

    private void setUI() {

        Context context = getApplicationContext();
        itemListRV = findViewById(R.id.expendablesListRV);
        itemAdapter = new ExpendableItemAdapter((ArrayList) myGarage.getExpendableItems());
        layoutManager = new LinearLayoutManager(context);
        itemDecoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);

        itemListRV.addItemDecoration(itemDecoration);
        itemListRV.setLayoutManager(layoutManager);
        itemListRV.setAdapter(itemAdapter);

        itemAdapter.setClickListener(this);
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

        double costOfRefill = myGarage.getExpendableItems().get(position).getUseCost() * 0.7 * 10;

        myGarage.getExpendableItems().get(position).addQuantity(10);
        myGarage.changeBankBalance(-costOfRefill);

        itemAdapter.notifyDataSetChanged();
    }
}
