package com.example.jae.cst2335_final_project;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @ Author: Bashir Mohmand
 * Odometer activity of Automobile
 * Display of odometer and a trip counter that can be reset to 0, displays oil change
 * with a button that will change it to textview to the last old change
 */
public class OdometerActivity extends AppCompatActivity {
    /**
     *declaring variables
     */
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    final Context context = this;
    protected TextView mileageText;
    protected TextView tripAText;
    protected TextView tripBText;
    protected TextView oilchangeText;
    protected Button reset1;
    protected Button reset2;
    /**
     * Inflates the layout, main entry to the class
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.odometer_automobile_activity);

        dbHelper = new DatabaseHelper(OdometerActivity.this);
        db = dbHelper.getWritableDatabase();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

         mileageText = (TextView)findViewById(R.id.textView16);
         tripAText = (TextView)findViewById(R.id.textView18);
        tripBText = (TextView)findViewById(R.id.textView20);
        oilchangeText = (TextView)findViewById(R.id.textView36);
        reset1 = (Button)findViewById(R.id.button12);
        reset2 = (Button)findViewById(R.id.button13);

        reset1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newString ="0";
                tripAText.setText(newString);
            }
        });

        reset2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String newString = mileageText.getText().toString();
                tripBText.setText(newString);
            }
        });


    }

    /**
     * Inflates on create menu items
     * @param m
     * @return
     */
    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.menu_main, m);
        return true;
    }


    /**
     * Adding functionality to the toolbar items
     * @param mi
     * @return
     */

    public boolean onOptionsItemSelected(MenuItem mi) {
        int id = mi.getItemId();
        switch (id) {


            case R.id.action_three:
                Intent intent1 = new Intent(OdometerActivity.this, HouseSettingsRemote.class);
                startActivity(intent1);
                Toast toast1 = Toast.makeText(this, getString(R.string.house_settings), Toast.LENGTH_SHORT);
                toast1.show();
                break;

            case R.id.action_four:
                Intent intent2 = new Intent(OdometerActivity.this, KitchenRemote.class);
                startActivity(intent2);
                Toast toast2 = Toast.makeText(this, getString(R.string.kitchen_settings), Toast.LENGTH_SHORT);
                toast2.show();
                break;

            case R.id.action_one:
                Intent intent3 = new Intent(OdometerActivity.this, LivingRoomRemoteActivity.class);
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