package com.example.jae.cst2335_final_project;

/**
 * @author Eric Pelley
 * This Class is the Main class. It runs and displays everything
 */

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * HouseSettings Remote that extends the AppCompatActivity
 */
public class HouseSettingsRemote extends AppCompatActivity {

    final Context Context = this;

    @Override
    /**
     * First Activity that gets called
     */
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


    /**
     * Infales the House_menu class which displays the toolbar
     * @param m
     * @return true
     */
    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.house_menu, m);
        return true;
    }


    /**
     * Selects the items
     * @param mi
     * @return the cases and displays the toolbar items
     */
    public boolean onOptionsItemSelected(MenuItem mi) {
        int id = mi.getItemId();
        switch (id) {


            case R.id.action_two:
                Intent intent1 = new Intent(HouseSettingsRemote.this, AutomobileRemoteActivity.class);
                startActivity(intent1);
                Toast toast1 = Toast.makeText(this, getString(R.string.toastauto), Toast.LENGTH_SHORT);
                toast1.show();
                break;

            case R.id.action_four:
                Intent intent2 = new Intent(HouseSettingsRemote.this, KitchenRemote.class);
                startActivity(intent2);
                Toast toast2 = Toast.makeText(this, getString(R.string.toastkitchen), Toast.LENGTH_SHORT);
                toast2.show();
                break;

            case R.id.action_one:
                Intent intent3 = new Intent(HouseSettingsRemote.this, LivingRoomRemoteActivity.class);
                startActivity(intent3);
                Toast toast3 = Toast.makeText(this, getString(R.string.toastliving), Toast.LENGTH_SHORT);
                toast3.show();
                break;

            case R.id.action_five:
                AlertDialog.Builder builder = new AlertDialog.Builder(Context);
                String dialog_title = (String)getString(R.string.Help);
                builder.setTitle(dialog_title);
                builder.setIcon(R.drawable.questionmark);
                String helpMessage = (String)getString(R.string.help_message);
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
