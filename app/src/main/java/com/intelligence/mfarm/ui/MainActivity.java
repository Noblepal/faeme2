package com.intelligence.mfarm.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.intelligence.mfarm.R;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView temp, humidity, rainfall, windspeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSummaryCard();

        findViewById(R.id.fab_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.m_dialog);
                dialog.show();
            }
        });
        findViewById(R.id.lin_buy_farm_input)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this, BuyFarmInputActivity.class));
                    }
                });
    }

    private void initSummaryCard() {
        temp = findViewById(R.id.img_temp);
        temp.setImageResource(R.drawable.thermometer);
    }
}
