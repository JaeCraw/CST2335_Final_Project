package com.example.jae.cst2335_final_project;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

public class KitchenLight extends AppCompatActivity {

    private Switch kitchenLight;
    private SeekBar kitchenDimmer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen_light);

         final SharedPreferences lightPrefs = getSharedPreferences("cst2335_group_assignment", Context.MODE_PRIVATE);
        kitchenLight = (Switch) findViewById(R.id.Kitchen_LightSwitch);
        kitchenLight.setChecked(lightPrefs.getBoolean("IsKitchenChecked", false));
        kitchenLight.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                SharedPreferences.Editor editor = lightPrefs.edit();
                if (kitchenLight.isChecked()){
                    Toast.makeText(KitchenLight.this, "Light is turned on", Toast.LENGTH_SHORT).show();
                    editor.putBoolean("IsKitchenChecked", true);
                    editor.commit();
                }else{
                    Toast.makeText(KitchenLight.this, "Light is turned off", Toast.LENGTH_SHORT).show();
                    editor.putBoolean("IsKitchenChecked", false);
                    editor.commit();
                }
            }
        });

        kitchenDimmer = (SeekBar) findViewById(R.id.KitchenLigthDimmer);

        SharedPreferences seekPref = getSharedPreferences("Seek1", Context.MODE_PRIVATE);
        kitchenDimmer.setProgress(seekPref.getInt("Seek1", 0));

        kitchenDimmer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                SharedPreferences pref = getSharedPreferences("Seek1", Context.MODE_PRIVATE);
                SharedPreferences.Editor editer = pref.edit();

                editer.putInt("Seek1", i);
                editer.commit();
                Snackbar.make(findViewById(android.R.id.content), "doopdoop changes the light", Snackbar.LENGTH_LONG)
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
