package com.example.jae.cst2335_final_project;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

/**
 * Created by bmohm90 on 2016-12-01.
 */
public class LightsActivity extends AppCompatActivity {

    final Context context = this;
    protected SeekBar seekbar;
    protected Switch switch1;
    protected Switch switch2;
    protected Switch switch3;
    protected SeekBar seekBar;
    protected ImageView imageView;
    protected String kmString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lights_automobile_activity);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

         seekbar = (SeekBar)findViewById(R.id.seekBar3);
         switch1 = (Switch)findViewById(R.id.switch3);
        switch2 = (Switch)findViewById(R.id.switch4);
        switch3 = (Switch)findViewById(R.id.switch5);
        seekBar = (SeekBar)findViewById(R.id.seekBar3);
        imageView = (ImageView)findViewById(R.id.imageView2);

        // save data
        final SharedPreferences mSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor mEditor = mSharedPrefs.edit();
        int mProgress = seekBar.getProgress();
        mEditor.putInt("lightsBarProgress", mProgress).commit();

        //retrieve data
        int mProgresss = mSharedPrefs.getInt("lightsBarProgress", 0);
        seekBar.setProgress(mProgresss);
        switch1.setChecked(mSharedPrefs.getBoolean("mainLights Off", false));
        switch2.setChecked(mSharedPrefs.getBoolean("normalLights Off", false));
        switch3.setChecked(mSharedPrefs.getBoolean("highBeams Off", false));
        mEditor.commit();

        setLightsProperties();
        setnormalLightsProperties();
        sethighBeamsProperties();

    }

    public void setLightsProperties() {

        switch1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                if (switch1.isChecked()) {
                    final SharedPreferences mSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
                    SharedPreferences.Editor editor = mSharedPrefs.edit();
                    editor.putBoolean("mainLights On", true);
                    editor.commit();
                    imageView.setVisibility(View.VISIBLE);
                    imageView.setImageResource(R.drawable.fflights);
                    switch2.setEnabled(true);
                    switch3.setEnabled(true);

                } else {
                    final SharedPreferences mSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
                    SharedPreferences.Editor editor = mSharedPrefs.edit();
                    editor.putBoolean("mainLights Off", false);
                    editor.commit();
                    imageView.setVisibility(View.INVISIBLE);
                    switch2.setEnabled(false);
                    switch3.setEnabled(false);
                }
            }
        });
    }

    public void setnormalLightsProperties(){

        switch2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                if (switch2.isChecked()) {
                    final SharedPreferences mSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
                    SharedPreferences.Editor editor = mSharedPrefs.edit();
                    editor.putBoolean("normalLights On", true);
                    switch3.setEnabled(false);
                    editor.commit();

                } else {
                    final SharedPreferences mSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
                    SharedPreferences.Editor editor = mSharedPrefs.edit();
                    editor.putBoolean("normalLights Off", false);
                    editor.commit();
                    switch3.setEnabled(true);
                }
            }
        });





    }

    public void sethighBeamsProperties() {

        switch3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                if (switch3.isChecked()) {
                    final SharedPreferences mSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
                    SharedPreferences.Editor editor = mSharedPrefs.edit();
                    editor.putBoolean("highBeams On", true);
                    switch2.setEnabled(false);
                    editor.commit();

                } else {
                    final SharedPreferences mSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
                    SharedPreferences.Editor editor = mSharedPrefs.edit();
                    editor.putBoolean("highBeams Off", false);
                    editor.commit();
                    switch2.setEnabled(true);
                }
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.menu_main, m);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem mi) {
        int id = mi.getItemId();
        switch (id) {



            case R.id.action_three:
                Intent intent1 = new Intent(LightsActivity.this, HouseSettingsRemote.class);
                startActivity(intent1);
                Toast toast1 = Toast.makeText(this, "HOUSE SETTINGS", Toast.LENGTH_SHORT);
                toast1.show();
                break;

            case R.id.action_four:
                Intent intent2 = new Intent(LightsActivity.this, KithchenRemoteActivity.class);
                startActivity(intent2);
                Toast toast2 = Toast.makeText(this, "KITCHEN SETTINGS", Toast.LENGTH_SHORT);
                toast2.show();
                break;

            case R.id.action_one:
                Intent intent3 = new Intent(LightsActivity.this, LivingRoomRemoteActivity.class);
                startActivity(intent3);
                Toast toast3 = Toast.makeText(this, "LIVING ROOM SETTINGS", Toast.LENGTH_SHORT);
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
