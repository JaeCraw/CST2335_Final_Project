package com.example.jae.cst2335_final_project;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class Fridge extends AppCompatActivity {

    private SeekBar fridgeTemp;
    private TextView tempText;
    private TextView fridgeName;
    private int temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge);

        Bundle b = getIntent().getExtras();

        tempText = (TextView) findViewById(R.id.fridgeTemp);

        fridgeName = (TextView) findViewById(R.id.fridgeName);

        fridgeTemp = (SeekBar) findViewById(R.id.fridgeTempSlider);

        fridgeTemp.setMax(50);



        if(b != null){
            fridgeName.setText(b.getString("name"));
            fridgeTemp.setProgress(b.getInt("setting"));
        }


        tempText.setText((fridgeTemp.getProgress() - 25) + "C");

        fridgeTemp.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                tempText.setText((progress - 25) + "C");
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
