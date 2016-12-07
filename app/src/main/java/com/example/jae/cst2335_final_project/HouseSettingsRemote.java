package com.example.jae.cst2335_final_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HouseSettingsRemote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_settings_remote);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button garagebutton = (Button) findViewById(R.id.button);
        Button housetemp = (Button) findViewById(R.id.button2);
        Button outsideweather = (Button) findViewById(R.id.button3);

        garagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HouseSettingsRemote.this, GarageActivity.class);
                startActivity(intent);
            }
        });


       housetemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HouseSettingsRemote.this, HouseTempActivity.class);
                startActivity(intent);
            }
        });


        outsideweather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HouseSettingsRemote.this, WeatherActivity.class);
                startActivity(intent);
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.house_menu, m);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem mi) {
        int id = mi.getItemId();
        switch (id) {


            case R.id.action_two:
                Intent intent1 = new Intent(HouseSettingsRemote.this, AutomobileRemoteActivity.class);
                startActivity(intent1);
                Toast toast1 = Toast.makeText(this, "AUTOMOBILE SETTINGS", Toast.LENGTH_SHORT);
                toast1.show();
                break;

            case R.id.action_four:
                Intent intent2 = new Intent(HouseSettingsRemote.this, KithchenRemoteActivity.class);
                startActivity(intent2);
                Toast toast2 = Toast.makeText(this, "KITCHEN SETTINGS", Toast.LENGTH_SHORT);
                toast2.show();
                break;

            case R.id.action_one:
                Intent intent3 = new Intent(HouseSettingsRemote.this, LivingRoomRemoteActivity.class);
                startActivity(intent3);
                Toast toast3 = Toast.makeText(this, "LIVING ROOM SETTINGS", Toast.LENGTH_SHORT);
                toast3.show();
                break;
        }
        return true;
    }

}
