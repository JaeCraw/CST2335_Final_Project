package com.example.jae.cst2335_final_project;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

public class KitchenLight extends AppCompatActivity {

    private Switch kitchenLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen_light);

        final SharedPreferences prefs = getSharedPreferences("cst2335_group_assignment", Context.MODE_PRIVATE);
        kitchenLight = (Switch) findViewById(R.id.Kitchen_LightSwitch);
        kitchenLight.setChecked(prefs.getBoolean("IsChecked", false));
        kitchenLight.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                SharedPreferences.Editor editor = prefs.edit();
                if (kitchenLight.isChecked()){
                    Toast.makeText(KitchenLight.this, "Light is turned on", Toast.LENGTH_SHORT).show();
                    editor.putBoolean("IsChecked", true);
                    editor.commit();
                }else{
                    Toast.makeText(KitchenLight.this, "Light is turned off", Toast.LENGTH_SHORT).show();
                    editor.putBoolean("IsChecked", false);
                    editor.commit();
                }
            }
        });


    }
}
