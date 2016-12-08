package com.example.jae.cst2335_final_project;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



/*  @ Author: Bashir Mohmand & Salman Saghir
       Main Automobile Activity
 */
public class AutomobileRemoteActivity extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "AutomobileRemoteActivity";
    final Context context = this;


    // Activity Begins
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automobile_remote);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button temperatureButton = (Button) findViewById(R.id.temperatureButton);
        Button fuelButton = (Button) findViewById(R.id.fuelButton);
        Button radioButton = (Button) findViewById(R.id.radioButton);
        Button gpsButton = (Button) findViewById(R.id.gpsButton);
        Button lightsButton = (Button) findViewById(R.id.lightsButton);
        Button driveButton = (Button) findViewById(R.id.driveButton);
        Button odometerButton = (Button) findViewById(R.id.odometerButton);

// Button Handlers
        temperatureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AutomobileRemoteActivity.this, TemperaturActivity.class);
                startActivity(intent);
            }
        });


        fuelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AutomobileRemoteActivity.this, FuelActivity.class);
                startActivity(intent);
            }
        });


        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AutomobileRemoteActivity.this, RadioActivity.class);
                startActivity(intent);
            }
        });


        gpsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AutomobileRemoteActivity.this, ToDoListActivity.class);
                startActivity(intent);
            }
        });


        lightsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AutomobileRemoteActivity.this, LightsActivity.class);
                startActivity(intent);
            }
        });

        driveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AutomobileRemoteActivity.this, CruiseActivity.class);
                startActivity(intent);
            }
        });

        odometerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AutomobileRemoteActivity.this, OdometerActivity.class);
                startActivity(intent);
            }
        });


    }

// Toolbar Menu
    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.menu_main, m);
        return true;
    }

// What to do when each toolbar item is selected
    public boolean onOptionsItemSelected(MenuItem mi) {
        int id = mi.getItemId();
        switch (id) {


            case R.id.action_three:
                Intent intent1 = new Intent(AutomobileRemoteActivity.this, HouseSettingsRemote.class);
                startActivity(intent1);
                Toast toast1 = Toast.makeText(this, getString(R.string.house_settings), Toast.LENGTH_SHORT);
                toast1.show();
                break;

            case R.id.action_four:
                Intent intent2 = new Intent(AutomobileRemoteActivity.this, KitchenRemote.class);
                startActivity(intent2);
                Toast toast2 = Toast.makeText(this, getString(R.string.kitchen_settings), Toast.LENGTH_SHORT);
                toast2.show();
                break;

            case R.id.action_one:
                Intent intent3 = new Intent(AutomobileRemoteActivity.this, LivingRoomRemoteActivity.class);
                startActivity(intent3);
                Toast toast3 = Toast.makeText(this, getString(R.string.living_settings), Toast.LENGTH_SHORT);
                toast3.show();
                break;

            case R.id.action_five:
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                String dialog_title = (String)getString(R.string.dialog_Title);
            builder.setTitle(dialog_title);
                builder.setIcon(R.drawable.help);
            String helpMessage = (String)getString(R.string.help_Message);
            builder.setMessage(helpMessage);
            builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.show();
            break;
        }
        return true;
    }

}
