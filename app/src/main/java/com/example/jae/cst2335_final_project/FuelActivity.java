package com.example.jae.cst2335_final_project;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @ Author: Salman Saghir
 * Fuel Activity of Automobile
 */
public class FuelActivity extends AppCompatActivity {


    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    private boolean mTwoPane;
    final Context context = this;
    protected CruiseActivity newCruiseActivity;
    protected ProgressBar progressBar;
    protected TextView textDistance;
    protected TextView lowgasText;
    protected TextView fillingText;
    protected TextView fuelText;
    protected Button fillgasButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fuel_automobile_activity);

        dbHelper = new DatabaseHelper(FuelActivity.this);
        db = dbHelper.getWritableDatabase();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



 // initializing variables
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
         textDistance = (TextView)findViewById(R.id.textView34);
         lowgasText = (TextView)findViewById(R.id.textView38);
         fillingText = (TextView)findViewById(R.id.textView37);
         fillgasButton = (Button)findViewById(R.id.button14);
        fuelText = (TextView)findViewById(R.id.textView23);

        fillGas();

        fillgasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FillUpTank().execute(null, null, null);
                calculateDistanceGas();
            }
        });

    }

 // what to do when gas is low
    public void fillGas(){
        String fuelString = fuelText.getText().toString();
        int fuelAmount = Integer.parseInt(fuelString);
        if(fuelAmount<7){
            lowgasText.setVisibility(View.VISIBLE);
            Animation anim = new AlphaAnimation(0.0f, 1.0f);
            anim.setDuration(50); //You can manage the time of the blink with this parameter
            anim.setStartOffset(20);
            anim.setRepeatMode(Animation.REVERSE);
            anim.setRepeatCount(Animation.INFINITE);
            lowgasText.startAnimation(anim);
        }else{
            lowgasText.setVisibility(View.INVISIBLE);
        }

    }


// calculating  fuel distance amount
    public void calculateDistanceGas(){
        String fuelString = fuelText.getText().toString();
        int fuelAmount = Integer.parseInt(fuelString);
        String fuelcalcString = textDistance.getText().toString();
        int fuelDistancAmount = Integer.parseInt(fuelcalcString);
        int distanceAmount = (fuelAmount/50)*fuelAmount;
        textDistance.setText(distanceAmount);


    }

// toolbar item inflater
    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.menu_main, m);
        return true;
    }

// toolbar item selection functionality
    public boolean onOptionsItemSelected(MenuItem mi) {
        int id = mi.getItemId();
        switch (id) {



            case R.id.action_three:
                Intent intent1 = new Intent(FuelActivity.this, HouseSettingsRemote.class);
                startActivity(intent1);
                Toast toast1 = Toast.makeText(this, getString(R.string.house_settings), Toast.LENGTH_SHORT);
                toast1.show();
                break;

            case R.id.action_four:
                Intent intent2 = new Intent(FuelActivity.this, KitchenRemote.class);
                startActivity(intent2);
                Toast toast2 = Toast.makeText(this, getString(R.string.kitchen_settings), Toast.LENGTH_SHORT);
                toast2.show();
                break;

            case R.id.action_one:
                Intent intent3 = new Intent(FuelActivity.this, LivingRoomRemoteActivity.class);
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



// using AsyncTask to update the progressbar, upto filling the tank
private class FillUpTank extends AsyncTask<String, Integer, String> {



    @Override
    protected String doInBackground(String... params) {

        publishProgress(100);
        return "";

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        ObjectAnimator animation = ObjectAnimator.ofInt(progressBar, "progress", 0, 100);
        animation.setDuration(5000);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) { }

            @Override
            public void onAnimationEnd(Animator animator) {
                //do something when the countdown is complete
            }

            @Override
            public void onAnimationCancel(Animator animator) { }

            @Override
            public void onAnimationRepeat(Animator animator) { }
        });
        animation.start();
        fillingText.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        String fuelString = fuelText.getText().toString();
        int fuelAmount = Integer.parseInt(fuelString);
        String newtext = "100";
        fuelText.setText(newtext);
        progressBar.setVisibility(View.INVISIBLE);
        fillingText.setVisibility(View.INVISIBLE);

    }
}
}