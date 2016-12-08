package com.example.jae.cst2335_final_project;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

/**
@ Author: Salman Saghir
Temperature activity of Automobile
This activity displays temperature control settings for an automobile that allows you
to switch it on or off and with a slider to control temperature
from cold to hot for the front vents and the rear vents
 */

public class TemperaturActivity extends AppCompatActivity {
    /**
     * seekbar is the slider to control temperature from cold to hot for the front vents
     * seekbar1 is the second slider to control temperature cold to hot for the rear vents
 */
    protected SeekBar seekbar;
    protected SeekBar seekbar1;
    /**
     * refers to this class
     */
    final Context context = this;
    /**
     * writes it to the file
     */
    protected SharedPreferences mSharedPrefs;
    protected SharedPreferences.Editor mEditor;

    /**
     * Inflates the layout, main entry to the class
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temp_automobile_activity);

/**
 * Initializing the variables and setting a maximum and
 * minimum for the seekbar and the progress starts at 0
 */
        seekbar = (SeekBar)findViewById(R.id.seekBar);
        seekbar1 = (SeekBar)findViewById(R.id.seekBar2);
        seekbar.setMax(10);
        seekbar.setProgress(0);
        seekbar1.setMax(10);
        seekbar1.setProgress(0);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


/**
 * Saves the data into the file
 */
         mSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
         mEditor = mSharedPrefs.edit();


/**
 * Retrieves data from the file
 */
        int mProgresss = mSharedPrefs.getInt("mMySeekBarProgress", 0);
        int mProgresss1 = mSharedPrefs.getInt("mMySeekBarProgress", 0);
        seekbar1.setProgress(mProgresss);
        seekbar.setProgress(mProgresss1);


/**
 * Seekbar listener on change
 */

        seekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //add here your implementation
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //add here your implementation
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                int mProgress = seekbar1.getProgress();
                mEditor.putInt("mMySeekBarProgress", mProgress).commit();
            }
        });


/**
 * seekbar listener on change
 *
 */
        seekbar1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //add here your implementation
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //add here your implementation
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                int mProgress1 = seekbar.getProgress();
                mEditor.putInt("mMySeekBarProgress", mProgress1).commit();
            }
        });
    }

    /**
     * Inflating the layout of the toolbar items
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
                Intent intent1 = new Intent(TemperaturActivity.this, HouseSettingsRemote.class);
                startActivity(intent1);
                Toast toast1 = Toast.makeText(this, getString(R.string.house_settings), Toast.LENGTH_SHORT);
                toast1.show();
                break;

            case R.id.action_four:
                Intent intent2 = new Intent(TemperaturActivity.this, KitchenRemote.class);
                startActivity(intent2);
                Toast toast2 = Toast.makeText(this, getString(R.string.kitchen_settings), Toast.LENGTH_SHORT);
                toast2.show();
                break;

            case R.id.action_one:
                Intent intent3 = new Intent(TemperaturActivity.this, LivingRoomRemoteActivity.class);
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