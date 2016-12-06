package com.example.jae.cst2335_final_project;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by bmohm90 on 2016-12-01.
 */
public class FuelActivity extends AppCompatActivity {


    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    private boolean mTwoPane;
    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fuel_automobile_activity);

        dbHelper = new DatabaseHelper(FuelActivity.this);
        db = dbHelper.getWritableDatabase();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.menu_main, m);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem mi) {
        int id = mi.getItemId();
        switch (id) {



            case R.id.action_three:
                Intent intent1 = new Intent(FuelActivity.this, HouseSettingsRemote.class);
                startActivity(intent1);
                Toast toast1 = Toast.makeText(this, "HOUSE SETTINGS", Toast.LENGTH_SHORT);
                toast1.show();
                break;

            case R.id.action_four:
                Intent intent2 = new Intent(FuelActivity.this, KithchenRemoteActivity.class);
                startActivity(intent2);
                Toast toast2 = Toast.makeText(this, "KITCHEN SETTINGS", Toast.LENGTH_SHORT);
                toast2.show();
                break;

            case R.id.action_one:
                Intent intent3 = new Intent(FuelActivity.this, LivingRoomRemoteActivity.class);
                startActivity(intent3);
                Toast toast3 = Toast.makeText(this, "LIVING ROOM SETTINGS", Toast.LENGTH_SHORT);
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
