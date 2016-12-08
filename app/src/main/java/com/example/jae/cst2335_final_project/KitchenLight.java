package com.example.jae.cst2335_final_project;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class KitchenLight extends AppCompatActivity {

    private TextView lightName;
    private Switch kitchenLight;
    private SeekBar kitchenDimmer;
    private String name;
    public KitchenDataBaseHelper kDH;
    public SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen_light);

        Bundle b = getIntent().getExtras();

        kDH = new KitchenDataBaseHelper(this);
        db = kDH.getWritableDatabase();

        lightName = (TextView) findViewById(R.id.LightName);
        kitchenLight = (Switch) findViewById(R.id.Kitchen_LightSwitch);
        kitchenDimmer = (SeekBar) findViewById(R.id.KitchenLigthDimmer);


        if(b != null){
            name = b.getString("name");
            lightName.setText(name);
            kitchenDimmer.setProgress(b.getInt("setting"));
        }


        kitchenLight.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (kitchenLight.isChecked()){
                    Toast.makeText(KitchenLight.this, getString(R.string.light_text_on), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(KitchenLight.this, getString(R.string.light_text_off), Toast.LENGTH_SHORT).show();
                }
            }
        });




        kitchenDimmer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                Snackbar.make(findViewById(android.R.id.content), "Light set to: " + kitchenDimmer.getProgress(), Snackbar.LENGTH_LONG)
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

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ContentValues newSetting = new ContentValues();
        newSetting.put(KitchenDataBaseHelper.KEY_SETTING, kitchenDimmer.getProgress());
        db.update(KitchenDataBaseHelper.TABLE_NAME, newSetting,
                KitchenDataBaseHelper.KEY_NAME + "='"+ name + "'", null);

        db.close();
    }
}
