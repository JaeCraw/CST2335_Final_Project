package com.example.jae.cst2335_final_project;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

/**
 * @author Martin Speelman
 * The first lamp in the livivng room. This lamp is only an on off switch.
 */
public class LivingRoomLamp1 extends AppCompatActivity {

    ImageButton back;
    Switch aswitch;
    boolean on;
    final Context context = this;

    /**
     * First thing to be called when the activity is called
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living_room_lamp1);
        back = (ImageButton) findViewById(R.id.backButton1);
        aswitch = (Switch) findViewById(R.id.switch1);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences switchPref = getSharedPreferences("Switch", Context.MODE_PRIVATE);
        on = switchPref.getBoolean("Switch", false);
        if (on) {
            aswitch.setChecked(true);
        }

        aswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getSharedPreferences("Switch", Context.MODE_PRIVATE);
                SharedPreferences.Editor editer = pref.edit();

                if (on == false) {
                    on = true;
                    editer.putBoolean("Switch", on);
                    editer.commit();
                    Snackbar.make(findViewById(android.R.id.content), getString(R.string.lampOn), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    on = false;
                    editer.putBoolean("Switch", on);
                    editer.commit();
                    Snackbar.make(findViewById(android.R.id.content), getString(R.string.lampOff), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.livingroom_menu, m);
        return true;
    }

    /**
     * sets the toolbar item functionality
     *
     * @param mi
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem mi) {
        int id = mi.getItemId();
        switch (id) {


            case R.id.action_three:
                Intent intent1 = new Intent(LivingRoomLamp1.this, HouseSettingsRemote.class);
                startActivity(intent1);
                Toast toast1 = Toast.makeText(this, "House settings", Toast.LENGTH_SHORT);
                toast1.show();
                break;

            case R.id.action_four:
                Intent intent2 = new Intent(LivingRoomLamp1.this, KitchenRemote.class);
                startActivity(intent2);
                Toast toast2 = Toast.makeText(this, "KitchenRemore", Toast.LENGTH_SHORT);
                toast2.show();
                break;

            case R.id.action_one:
                Intent intent3 = new Intent(LivingRoomLamp1.this, AutomobileRemoteActivity.class);
                startActivity(intent3);
                Toast toast3 = Toast.makeText(this, "Automobile", Toast.LENGTH_SHORT);
                toast3.show();
                break;
            case R.id.action_five:
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                String dialog_title = (String) getString(R.string.dialog_title);
                builder.setTitle(dialog_title);
                // builder.setIcon(R.drawable.help);
                String helpMessage = (String) getString(R.string.author);
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
