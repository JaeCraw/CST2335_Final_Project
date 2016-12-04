package com.example.jae.cst2335_final_project;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;

public class LivingRoomLamp3 extends AppCompatActivity {

    SeekBar seek;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living_room_lamp3);

        seek = (SeekBar) findViewById(R.id.colorSeek);
        back = (ImageButton) findViewById(R.id.backButton3);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        SharedPreferences seekPref = getSharedPreferences("Seek2", Context.MODE_PRIVATE);
        seek.setProgress(seekPref.getInt("Seek2", 0));

        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                float[] hsvColor = {0, 1, 1};
                Log.i("Here", "Here");
                if(i == 0){
                    getWindow().getDecorView().setBackgroundColor(Color.WHITE);
                }else {
                    hsvColor[0] = 360f * i / seek.getMax();
                    //     view.setBackgroundColor(Color.HSVToColor(hsvColor));
                    getWindow().getDecorView().setBackgroundColor(Color.HSVToColor(hsvColor));

                    SharedPreferences pref = getSharedPreferences("Seek2", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editer = pref.edit();
                    editer.putInt("Seek2", i);
                    editer.commit();

                }
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
    }
}