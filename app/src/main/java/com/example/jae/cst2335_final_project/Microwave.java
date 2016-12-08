package com.example.jae.cst2335_final_project;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Activity for the microwave. vibrates when the timer goes up. the user can stop or reset the timer
 */
public class Microwave extends AppCompatActivity {

    /**
     * displays appliance name
     */
    private TextView microName;

    /**
     * on buttom
     */
    private Button on;

    /**
     * stop button
     */
    private Button stop;

    /**
     * reset button
     */
    private Button reset;

    /**
     * text box to get the time from the user
     */
    private EditText inputTime;

    /**
     * progress bar to mark cook time
     */
    private ProgressBar cookingTimer;

    /**
     * checks if the microwave is running
     */
    private static boolean turnOn = true;

    /**
     * cook time
     */
    private int cookTime=0;

    /**
     * progress of the timer
     */
    private int progress=0;

    /**
     * counts down the timer
     */
    private CountDownTimer timer;

    /**
     * holds appliance name
     */
    private String name;

    /**
     * oncreate is called when the activity is started. loads in user settings and instantiates program variables
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_microwave);

        //bundle to recieve arguments
        Bundle b = getIntent().getExtras();

        //instatiate activity components
        microName = (TextView) findViewById(R.id.microwave_name);

        //sets the program components if the is a bundle
        if(b != null){
            name = b.getString("name");
            microName.setText(name);;
        }

        //instatiates all the items in the program
        on = (Button) findViewById(R.id.microwave_startbutton);

        stop = (Button) findViewById(R.id.microwave_stop_button);

        inputTime = (EditText)findViewById(R.id.microwavetimetxt);

        cookingTimer = (ProgressBar) findViewById(R.id.microwave_timer);
        cookingTimer.setProgress(0);

        //onclick listener for the start button
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

                                //vibrate the phone when the cooking is finished
                                Vibrator mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                mVibrator.vibrate(500);
                                Toast.makeText(getApplicationContext(), getString(R.string.timer_done), Toast.LENGTH_SHORT).show();

                                //automatically turn off the microwave
                                turnOff();
                            }
                        };
                        timer.start();

                    }catch(NumberFormatException e ){
                        //if the user does not put in a number, warn them
                        Toast.makeText(getApplicationContext(), getString(R.string.no_number_error), Toast.LENGTH_LONG).show();
                    }

                }
                else{

                    turnOff();
                }
            }
        });

        //onclick listener for the stop button
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.cancel();
                turnOff();
                Toast.makeText(getApplicationContext(), getString(R.string.timer_stopped), Toast.LENGTH_SHORT).show();
            }
        });

        reset = (Button) findViewById(R.id.microwaveResetButton);

        //on click listener for the reset button
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                resetClock();
                Toast.makeText(getApplicationContext(), getString(R.string.timer_reset), Toast.LENGTH_SHORT).show();
            }
        });

    }

    //turn on the microwave
    public void turnOn(){
        // microwave is off
        stop.setEnabled(true);
        reset.setEnabled(true);
        cookingTimer.setVisibility(View.VISIBLE);
        turnOn = false;
    }

    /**
     * turns off the microwave
     */
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

    /**
     * resets the program button
     */
    public void resetClock(){
        stop.setEnabled(false);
        reset.setEnabled(false);
        progress = 0;
        cookingTimer.setVisibility(View.INVISIBLE);

        //reset microwave and allow microwave to be turned on
        turnOn = true;

    }

}
