package com.example.jae.cst2335_final_project;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @ Author: Salman Saghir
 * SubActivity of Automobile, Cruise control
 * This activity displays cruise control settings for an automobile
 * you can turn cruise control on/off and enter and set the distance that you will drive during the cruise
 *
 */
public class CruiseActivity extends AppCompatActivity {

    /**
     * declaring variables
     */
    final Context context = this;
    protected Switch switch1;
    protected TextView kmText;
    protected EditText kmEText;
    protected Button onButton;
    protected Button offButton;
    protected Button okButton;
    protected String newString;
    protected SharedPreferences mSharedPrefs;
    protected SharedPreferences.Editor editor;

    /**
     * This method allows the main entry for class and inflates layout of class
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drive_automobile_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
/**
 * Initializing variables
 */
        switch1 = (Switch)findViewById(R.id.switch6);
        kmText = (TextView)findViewById(R.id.textView30);
        kmEText = (EditText)findViewById(R.id.editText3);
        onButton = (Button)findViewById(R.id.button9);
        offButton = (Button)findViewById(R.id.button10);
        okButton = (Button)findViewById(R.id.button11);

/**
 * Retrieving data from a file
 */
        mSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        editor = mSharedPrefs.edit();
        String kmText1 = mSharedPrefs.getString("kmTextChange", newString);
        kmText.setText(kmText1);

        setCruiseProperties();
        setkmProperties();

/**
 * Button handlers, when you press the set button it will set the distance in km and allow to turn the cruise control
 */
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newString =kmEText.getText().toString();
                kmText.setText(newString);
                kmEText.setText("");
                String new1String ="Activate at: " + kmEText.getText().toString()+ "km/h";
                Toast.makeText(CruiseActivity.this, new1String, Toast.LENGTH_SHORT).show();
            }
        });

        onButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CruiseActivity.this, "CRUISE ON", Toast.LENGTH_SHORT).show();
            }
        });

        offButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CruiseActivity.this, "CRUISE OFF", Toast.LENGTH_SHORT).show();
            }
        });


    }

    /**
     * setting cruise properties with writing data into a file
     */
    public void setCruiseProperties() {

        switch1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                if (switch1.isChecked()) {

                    editor.putBoolean("mainCruise On", true);
                    onButton.setEnabled(true);
                    offButton.setEnabled(true);
                    editor.putBoolean("activateCruise ON", true);
                    editor.putBoolean("activatedCruise OFF", true);
                    editor.commit();
                    Toast.makeText(CruiseActivity.this, "MAIN CRUISE ON", Toast.LENGTH_SHORT).show();


                } else {

                    editor.putBoolean("mainCruise Off", false);
                    onButton.setEnabled(false);
                    offButton.setEnabled(false);
                    editor.putBoolean("activateCruise ON", false);
                    editor.putBoolean("activatedCruise OFF", false);
                    editor.commit();
                    Toast.makeText(CruiseActivity.this, "MAIN CRUISE OFF", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    /**
     * Setting the amount of kilometers, inserts data into a file
     */
    public void setkmProperties(){

       newString = kmEText.getText().toString();
        kmText.setText(newString);
        editor.putString("kmTextChange", newString).commit();

    }

    /**
     * Inflates on create menu items
     * @param m
     * @return
     */
    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.menu_main, m);
        return true;
    }

    /**
     * toolbar item selection functionality
     * @param mi
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem mi) {
        int id = mi.getItemId();
        switch (id) {


            case R.id.action_three:
                Intent intent1 = new Intent(CruiseActivity.this, HouseSettingsRemote.class);
                startActivity(intent1);
                Toast toast1 = Toast.makeText(this, getString(R.string.house_settings), Toast.LENGTH_SHORT);
                toast1.show();
                break;

            case R.id.action_four:
                Intent intent2 = new Intent(CruiseActivity.this, KitchenRemote.class);
                startActivity(intent2);
                Toast toast2 = Toast.makeText(this, getString(R.string.kitchen_settings), Toast.LENGTH_SHORT);
                toast2.show();
                break;

            case R.id.action_one:
                Intent intent3 = new Intent(CruiseActivity.this, LivingRoomRemoteActivity.class);
                startActivity(intent3);
                Toast toast3 = Toast.makeText(this, getString(R.string.living_settings), Toast.LENGTH_SHORT);
                toast3.show();
                break;
            case R.id.action_five:
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                String dialog_title = (String)getString(R.string.dialog_Title);
                builder.setTitle(dialog_title);
                builder.setIcon(R.drawable.help);
                String helpMessage = (String)getString(R.string.help_Message);
                builder.setMessage(helpMessage);
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
                break;


        }
        return true;


}
}