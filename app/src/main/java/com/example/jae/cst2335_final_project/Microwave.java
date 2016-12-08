package com.example.jae.cst2335_final_project;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Microwave extends AppCompatActivity {

    private Button on;
    private Button stop;
    private Button reset;
    private EditText inputTime;
    private ProgressBar cookingTimer;
    private static boolean turnOn = true;
    private int cookTime=0;
    private int progress=0;
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_microwave);

        //link to all view items
        on = (Button) findViewById(R.id.onOffButton);
        stop = (Button) findViewById(R.id.microwave_stop);
        inputTime = (EditText)findViewById(R.id.inputTime);
        cookingTimer = (ProgressBar) findViewById(R.id.microwave_progress_bar);
        cookingTimer.setProgress(0);

        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(turnOn){
                    try{
                        cookTime = Integer.parseInt( inputTime.getText().toString() );
                        turnOn();

                        timer = new CountDownTimer(cookTime * 1000, 1000){
                            public void onTick(long millisUntilFinished) {
                                int timeCooked = cookTime - (int)(millisUntilFinished / 1000);
                                progress = (timeCooked * 100) / cookTime;
                                cookingTimer.setProgress(progress);
                            }

                            public void onFinish() {
                                cookingTimer.setProgress(100);

                                // vibrate and show text when done cooking
//                                Vibrator mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
//                                mVibrator.vibrate(500);
                                Toast.makeText(getApplicationContext(), getString(R.string.timer_done), Toast.LENGTH_SHORT).show();

                                //automatically turn off the microwave
                                turnOff();
                            }
                        };
                        timer.start();

                    }catch(NumberFormatException e ){
                        Toast.makeText(getApplicationContext(), getString(R.string.no_number_error), Toast.LENGTH_SHORT).show();
                    }

                }
                else{

                    turnOff();
                }
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.cancel();
                turnOff();
                Toast.makeText(getApplicationContext(), getString(R.string.timer_stopped), Toast.LENGTH_SHORT).show();
            }
        });

        reset = (Button) findViewById(R.id.microwaveResetButton);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                resetClock();
                Toast.makeText(getApplicationContext(), getString(R.string.timer_reset), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void turnOn(){
        // microwave is off
        stop.setEnabled(true);
        reset.setEnabled(true);
        cookingTimer.setVisibility(View.VISIBLE);

        //turn on microwave and allow microwave to be turned off
        turnOn = false;
    }

    public void turnOff(){
        // microwave is on
        stop.setEnabled(false);
        reset.setEnabled(false);
        cookTime = 0;
        progress = 0;
        cookingTimer.setVisibility(View.INVISIBLE);
        inputTime.setText("");
        //turn off microwave and allow microwave to be turned on
        turnOn = true;
    }

    public void resetClock(){
        stop.setEnabled(false);
        reset.setEnabled(false);
        progress = 0;
        cookingTimer.setVisibility(View.INVISIBLE);

        //reset microwave and allow microwave to be turned on
        turnOn = true;

    }

}
