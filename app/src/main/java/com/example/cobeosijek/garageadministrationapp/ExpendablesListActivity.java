package com.example.cobeosijek.garageadministrationapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ExpendablesListActivity extends AppCompatActivity implements ExpendableItemAdapter.OnItemClickListener {

    private static final String KEY_GARAGE_SENT = "garage";

    RecyclerView itemListRV;
    ExpendableItemAdapter itemAdapter;

    Garage myGarage;

    public static Intent getLaunchIntent(Context context, Garage myGarage) {
        Intent startExpendablesList = new Intent(context, ExpendablesListActivity.class);
        startExpendablesList.putExtra(KEY_GARAGE_SENT, myGarage);
        return startExpendablesList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expendables_list);
        myGarage = (Garage) getIntent().getSerializableExtra(KEY_GARAGE_SENT);
        setUI();
    }

    private void setUI() {

        itemListRV = findViewById(R.id.expendablesListRV);
        itemAdapter = new ExpendableItemAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);

        itemListRV.addItemDecoration(itemDecoration);
        itemListRV.setLayoutManager(layoutManager);
        itemListRV.setAdapter(itemAdapter);

        itemAdapter.setClickListener(this);

        itemAdapter.setExpendableList(myGarage.getExpendableItems());
    }

    @Override
    public void onBackPressed() {
        this.setResult(RESULT_OK, MainActivity.getResultGarageIntent(myGarage));
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
