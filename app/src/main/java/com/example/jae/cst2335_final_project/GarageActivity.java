package com.example.jae.cst2335_final_project;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

/**
 * Created by pelle on 2016-11-30.
 */
public class GarageActivity extends AppCompatActivity {

    protected Button openButton;
    protected Button closeButton;
    protected ImageView imageView1;
    protected Switch switch1;
    protected Context Context1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.garage_house_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button openButton = (Button) findViewById(R.id.button5);
        Button closeButton = (Button) findViewById(R.id.button4);
        imageView1 = (ImageView) findViewById(R.id.imageView);
        switch1 = (Switch) findViewById(R.id.switch1);


        openButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                imageView1.setVisibility(View.VISIBLE);
                imageView1.setImageResource(R.drawable.garagelight);
                switch1.isActivated();
                Toast toast4 = Toast.makeText(getApplicationContext(), "Garage Door Open" , Toast.LENGTH_SHORT);
                toast4.show();
                Toast toast8 = Toast.makeText(getApplicationContext(), "Garage Light On" , Toast.LENGTH_SHORT);
                toast8.show();
            }
        });

        closeButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                imageView1.setVisibility(View.INVISIBLE);
                imageView1.setImageResource(R.drawable.garagelight);
                switch1.isActivated();
                Toast toast5 = Toast.makeText(getApplicationContext(), "Garage Door Closed" , Toast.LENGTH_SHORT);
                toast5.show();
                Toast toast9 = Toast.makeText(getApplicationContext(), "Garage Light Off" , Toast.LENGTH_SHORT);
                toast9.show();

            }
        });


        switch1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                if (switch1.isChecked()) {
                    imageView1.setVisibility(View.VISIBLE);
                    imageView1.setImageResource(R.drawable.garagelight);
                    Toast toast6 = Toast.makeText(getApplicationContext(), "Garage Light On" , Toast.LENGTH_SHORT);
                    toast6.show();

                } else {
                    imageView1.setVisibility(View.INVISIBLE);
                    Toast toast7 = Toast.makeText(getApplicationContext(),"Garage Light Off" , Toast.LENGTH_SHORT);
                    toast7.show();
                }
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
                Intent intent1 = new Intent(GarageActivity.this, AutomobileRemoteActivity.class);
                startActivity(intent1);
                Toast toast1 = Toast.makeText(this, getString(R.string.toastauto), Toast.LENGTH_SHORT);
                toast1.show();
                break;

            case R.id.action_four:
                Intent intent2 = new Intent(GarageActivity.this, KithchenRemoteActivity.class);
                startActivity(intent2);
                Toast toast2 = Toast.makeText(this, getString(R.string.toastkitchen), Toast.LENGTH_SHORT);
                toast2.show();
                break;

            case R.id.action_one:
                Intent intent3 = new Intent(GarageActivity.this, LivingRoomRemoteActivity.class);
                startActivity(intent3);
                Toast toast3 = Toast.makeText(this, getString(R.string.toastliving), Toast.LENGTH_SHORT);
                toast3.show();
                break;

            case R.id.action_five:
                AlertDialog.Builder builder = new AlertDialog.Builder(Context1);
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
