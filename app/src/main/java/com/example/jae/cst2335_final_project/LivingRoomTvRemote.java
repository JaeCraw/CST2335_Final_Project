package com.example.jae.cst2335_final_project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * @author Martin Speelman
 * This activity is the television remote for the living room.
 */
public class LivingRoomTvRemote extends AppCompatActivity {

    /**
     * Back button
     */
    ImageButton back;
    /**
     * Volume up buton
     */
    ImageButton up;
    /**
     * Volume down button
     */
    ImageButton down;
    /**
     * Channel button down
     */
    ImageButton left;
    /**
     * Channel up button
     */
    ImageButton right;
    /**
     * Enter button chooses the channel
     */
    ImageButton enter;
    /**
     * Progress bar to show the volume level
     */
    ProgressBar volBar;
    /**
     * Turn the television on or off
     */
    ImageButton powerBtn;
    /**
     * the button to choose the channel
     */
    Button chooseChannel;
    /**
     * allows you to type the chennel into the textbox
     */
    EditText channelEddit;
    /**
     * displays the channel
     */
    TextView channelTXT;
    /**
     *  holds the value for the volume
     */
    int volume;
    /**
     * holds the value for
     */
    int channel;
    /**
     * hold the value for the power
     */
    boolean power;

    /**
     * first metho called when the activity is launched
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living_room_tv_remote);

        SharedPreferences powerPref = getSharedPreferences("Power", Context.MODE_PRIVATE);
        SharedPreferences chanelPref = getSharedPreferences("Chanel", Context.MODE_PRIVATE);
        SharedPreferences volPref = getSharedPreferences("Volume", Context.MODE_PRIVATE);

        power = powerPref.getBoolean("Power", false);
        channel = chanelPref.getInt("Chanel", 000);
        volume = volPref.getInt("Volume", 0);

        enter = (ImageButton) findViewById(R.id.okButton);
        back = (ImageButton) findViewById(R.id.backButtonTv);
        up = (ImageButton) findViewById(R.id.upArrow);
        down = (ImageButton) findViewById(R.id.downArrow);
        left = (ImageButton) findViewById(R.id.leftArrow);
        right = (ImageButton) findViewById(R.id.rightArrow);
        volBar = (ProgressBar) findViewById(R.id.volume);
        powerBtn = (ImageButton) findViewById(R.id.power);
        chooseChannel = (Button) findViewById(R.id.channelButton);
        channelEddit = (EditText) findViewById(R.id.channelChooser);
        channelTXT = (TextView) findViewById(R.id.channelTXT);

        channelEddit.setText("" + channel);
        channelTXT.setText(getString(R.string.chanel) + " " + channel);
        volBar.setProgress(volume);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volume++;
                Log.d("tv Remote", "Volume " + volume);

                volBar.setProgress(volume);

                SharedPreferences pref = getSharedPreferences("Volume", Context.MODE_PRIVATE);
                SharedPreferences.Editor editer = pref.edit();

                editer.putInt("Volume", volume);
                editer.commit();
            }
        });

        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(volume -1 >= 0) {
                    volume--;
                    volBar.setProgress(volume);

                    SharedPreferences pref = getSharedPreferences("Volume", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editer = pref.edit();

                    editer.putInt("Volume", volume);
                    editer.commit();
                }
            }
        });

        powerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(power == false) {
                    Snackbar.make(findViewById(android.R.id.content), getString(R.string.tvOn), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    power = true;
                    SharedPreferences pref = getSharedPreferences("Power", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editer = pref.edit();

                    editer.putBoolean("Power", power);
                    editer.commit();

                }else{
                    Snackbar.make(findViewById(android.R.id.content), R.string.tvOff, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    power = false;

                    SharedPreferences pref = getSharedPreferences("Power", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editer = pref.edit();

                    editer.putBoolean("Power", power);
                    editer.commit();
                }
            }
        });
        chooseChannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Integer.parseInt(channelEddit.getText().toString()) >=0) {
                    channelTXT.setText(getString(R.string.chanel) + " " + channelEddit.getText());
                    channel = Integer.parseInt(channelEddit.getText().toString());

                    SharedPreferences pref = getSharedPreferences("Chanel", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editer = pref.edit();

                    editer.putInt(getString(R.string.chanel), channel);
                    editer.commit();
                }
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 channel++;
                 channelTXT.setText("Chanel " + channel);
                 channelEddit.setText("" + channel);
                 SharedPreferences pref = getSharedPreferences("Chanel", Context.MODE_PRIVATE);
                 SharedPreferences.Editor editer = pref.edit();
                 editer.putInt("Chanel", channel);
                 editer.commit();
            }
        });
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((channel - 1) >= 0) {
                    channel--;
                    channelTXT.setText(getString(R.string.chanel) + " " + channel);
                    channelEddit.setText("" + channel);

                    SharedPreferences pref = getSharedPreferences("Chanel", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editer = pref.edit();

                    editer.putInt("Chanel", channel);
                    editer.commit();
                }
            }
        });
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Integer.parseInt(channelEddit.getText().toString()) >=0) {
                    channelTXT.setText(getString(R.string.chanel) + " " + channelEddit.getText());
                    channel = Integer.parseInt(channelEddit.getText().toString());

                    SharedPreferences pref = getSharedPreferences("Chanel", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editer = pref.edit();

                    editer.putInt(getString(R.string.chanel), channel);
                    editer.commit();
                }
            }
        });
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.i("TvRemote", "OnStart");

    }
}