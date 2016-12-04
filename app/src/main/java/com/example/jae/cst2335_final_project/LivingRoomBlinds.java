package com.example.jae.cst2335_final_project;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;

public class LivingRoomBlinds extends AppCompatActivity {

    SeekBar blinds;
    ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living_room_blinds);

        blinds = (SeekBar) findViewById(R.id.blinds);
        back = (ImageButton) findViewById(R.id.blindsBack);

        SharedPreferences seekPref = getSharedPreferences("Blinds", Context.MODE_PRIVATE);
        blinds.setProgress(seekPref.getInt("Blinds", 0));

        blinds.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                SharedPreferences pref = getSharedPreferences("Blinds", Context.MODE_PRIVATE);
                SharedPreferences.Editor editer = pref.edit();

                editer.putInt("Blinds", i);
                editer.commit();
                Snackbar.make(findViewById(android.R.id.content), getString(R.string.blindsAdj), Snackbar.LENGTH_LONG)
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
