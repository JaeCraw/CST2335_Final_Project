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

/**
 * activity for light
 */
public class KitchenLight extends AppCompatActivity {

    /**
     * displays appliance name
     */
    private TextView lightName;

    /**
     * light switch
     */
    private Switch kitchenLight;

    /**
     * dimmer for the light
     */
    private SeekBar kitchenDimmer;

    /**
     * holds appliance name
     */
    private String name;

    /**
     * database helper to be written to
     */
    public KitchenDataBaseHelper kDH;

    /**
     * object of SQLiteDatabase
     */
    public SQLiteDatabase db;

    /**
     * called when the activity is started
     * instantiates the items to be used and set the listeners
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen_light);

        //bundle to recieve arguments
        Bundle b = getIntent().getExtras();

        //instatiate the database
        kDH = new KitchenDataBaseHelper(this);
        db = kDH.getWritableDatabase();

        //instatiate activity components
        lightName = (TextView) findViewById(R.id.LightName);
        kitchenLight = (Switch) findViewById(R.id.Kitchen_LightSwitch);
        kitchenDimmer = (SeekBar) findViewById(R.id.KitchenLigthDimmer);

        //sets the program components if the is a bundle
        if(b != null){
            name = b.getString("name");
            lightName.setText(name);
            kitchenDimmer.setProgress(b.getInt("setting"));
        }

        //onlick listener for the light switch
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



        //listener for the slider
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

    /**
     * called when the program enters teardown. writes the last settings to the database and closes it
     */
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
