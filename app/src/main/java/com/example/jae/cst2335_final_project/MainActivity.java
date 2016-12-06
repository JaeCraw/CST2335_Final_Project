package com.example.jae.cst2335_final_project;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button kitchenButton = (Button) findViewById(R.id.kitchen_remote);
        Button lRButton = (Button) findViewById(R.id.smart_remote);
        Button houseButton = (Button) findViewById(R.id.house_settings);
        Button autoButton = (Button) findViewById(R.id.automobile_remote);

        kitchenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, KithchenRemoteActivity.class);
                startActivity(intent);
            }
        });

        lRButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LivingRoomRemoteActivity.class);
                startActivity(intent);
            }
        });

        houseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HouseSettingsRemote.class);
                startActivity(intent);
            }
        });

        autoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AutomobileRemoteActivity.class);
                startActivity(intent);
            }
        });
    }
}
