package com.example.jae.cst2335_final_project;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;

/**
 * @author Martin Speelman
 * This is the lamp 2 in the living room. it is a dimmable lamp.
 */
public class LivingRoomLamp2 extends AppCompatActivity {

    /**
     * Babk button
     */
    ImageButton back;
    /**
     * Seek bar to change the brightness of the lamp
     */
    SeekBar seekBar;

    /**
     *Firt method called when the activity is called
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living_room_lamp2);

        back = (ImageButton) findViewById(R.id.backButton2);
        seekBar = (SeekBar) findViewById(R.id.seekBar);

        SharedPreferences seekPref = getSharedPreferences("Seek1", Context.MODE_PRIVATE);
        seekBar.setProgress(seekPref.getInt("Seek1", 0));

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                SharedPreferences pref = getSharedPreferences("Seek1", Context.MODE_PRIVATE);
                SharedPreferences.Editor editer = pref.edit();

                    editer.putInt("Seek1", i);
                    editer.commit();
                    Snackbar.make(findViewById(android.R.id.content), getString(R.string.lampAdj), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
