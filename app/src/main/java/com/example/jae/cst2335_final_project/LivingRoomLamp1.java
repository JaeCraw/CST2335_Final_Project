package com.example.jae.cst2335_final_project;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;

/**
 * @author Martin Speelman
 * The first lamp in the livivng room. This lamp is only an on off switch.
 */
public class LivingRoomLamp1 extends AppCompatActivity {

    ImageButton back;
    Switch aswitch;
    boolean on;

    /**
     * First thing to be called when the activity is called
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living_room_lamp1);
        back = (ImageButton) findViewById(R.id.backButton1);
        aswitch = (Switch) findViewById(R.id.switch1);

        SharedPreferences switchPref = getSharedPreferences("Switch", Context.MODE_PRIVATE);
        on = switchPref.getBoolean("Switch", false);
        if(on){
            aswitch.setChecked(true);
        }

        aswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getSharedPreferences("Switch", Context.MODE_PRIVATE);
                SharedPreferences.Editor editer = pref.edit();

                if(on == false) {
                    on = true;
                    editer.putBoolean("Switch", on);
                    editer.commit();
                    Snackbar.make(findViewById(android.R.id.content), getString(R.string.lampOn), Snackbar.LENGTH_LONG)
                             .setAction("Action", null).show();
                }else{
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
}
