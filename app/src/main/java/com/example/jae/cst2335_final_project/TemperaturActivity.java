package com.example.jae.cst2335_final_project;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class TemperaturActivity extends AppCompatActivity {
    TextView textview;
    protected SeekBar seekbar;
    protected SeekBar seekbar1;
    final Context context = this;
    protected SharedPreferences mSharedPrefs;
    protected SharedPreferences.Editor mEditor;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temp_automobile_activity);

        textview = (TextView)findViewById(R.id.textView);
        seekbar = (SeekBar)findViewById(R.id.seekBar);
        seekbar1 = (SeekBar)findViewById(R.id.seekBar2);
        seekbar.setMax(10);
        seekbar.setProgress(0);
        seekbar1.setMax(10);
        seekbar1.setProgress(0);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


//SAVE DATA
         mSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
         mEditor = mSharedPrefs.edit();
        int mProgress = seekbar1.getProgress();
        int mProgress1 = seekbar.getProgress();
        mEditor.putInt("mMySeekBarProgress", mProgress).commit();
        mEditor.putInt("mMySeekBarProgress", mProgress1).commit();

//RETRIEVE DATA
        int mProgresss = mSharedPrefs.getInt("mMySeekBarProgress", 0);
        int mProgresss1 = mSharedPrefs.getInt("mMySeekBarProgress", 0);
        seekbar1.setProgress(mProgresss);
        seekbar.setProgress(mProgresss1);




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

            }
        });



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
                Intent intent1 = new Intent(TemperaturActivity.this, HouseSettingsRemote.class);
                startActivity(intent1);
                Toast toast1 = Toast.makeText(this, getString(R.string.house_settings), Toast.LENGTH_SHORT);
                toast1.show();
                break;

            case R.id.action_four:
                Intent intent2 = new Intent(TemperaturActivity.this, KithchenRemoteActivity.class);
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