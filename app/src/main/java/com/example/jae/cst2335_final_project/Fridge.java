package com.example.jae.cst2335_final_project;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * @author Jacob Crawford
 * this class holds the controls for a fridge
 */
public class Fridge extends AppCompatActivity {

    /**
     * Seekbar to change fridge temp
     */
    private SeekBar fridgeTemp;

    /**
     * holds the value of tempurature
     */
    private TextView tempText;

    /**
     * TextView saves appliance name
     */
    private TextView fridgeName;

    /**
     * string to hold the name
     */
    private String name;

    /**
     * object of KitchenDatabaseHelper
     */
    public KitchenDataBaseHelper kDH;

    /**
     * object of SQLite to modify the database
     */
    public SQLiteDatabase db;

    /**
     * On create. called when the class is created. Loads all the components of the activity and sets the listeners
     * @param savedInstanceState arguments passed to the class
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge);

        //retrieves passed data
        Bundle b = getIntent().getExtras();

        //instatiates database components

        kDH = new KitchenDataBaseHelper(this);
        db = kDH.getWritableDatabase();

        //instatiates program variables

        tempText = (TextView) findViewById(R.id.fridgeTemp);

        fridgeName = (TextView) findViewById(R.id.fridgeName);

        fridgeTemp = (SeekBar) findViewById(R.id.fridgeTempSlider);

        fridgeTemp.setMax(50);


        //if there are passed arguments, load it into the activity
        if(b != null){
            name = b.getString("name");
            fridgeName.setText(name);
            fridgeTemp.setProgress(b.getInt("setting"));
        }


        tempText.setText((fridgeTemp.getProgress() - 25) + "C");


        //on click listener for the slider
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

    /**
     * called when the project is closed. this is used to write to the databse
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.out.println("onDestroy");

        //save last settings to the database
        ContentValues newSetting = new ContentValues();
        newSetting.put(KitchenDataBaseHelper.KEY_SETTING, fridgeTemp.getProgress());
        db.update(KitchenDataBaseHelper.TABLE_NAME, newSetting,
                KitchenDataBaseHelper.KEY_NAME + "='"+ name + "'", null);

        //close the database
        db.close();



    }

}
