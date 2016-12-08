package com.example.jae.cst2335_final_project;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class KitchenRemote extends AppCompatActivity {

    public ArrayList<KitchenDataObject> list;
    public ApplianceAdapter applianceAdapter;
    public Button addAppliance;
    public ListView listView;
    public KitchenDataBaseHelper kDH;
    public SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen_remote);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        listView = (ListView) findViewById(R.id.listView);

        addAppliance = (Button) findViewById(R.id.AddAppliance);

        applianceAdapter = new ApplianceAdapter(this);

        list = new ArrayList<KitchenDataObject>();

        listView.setAdapter(applianceAdapter);

        kDH = new KitchenDataBaseHelper(this);
        db = kDH.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + KitchenDataBaseHelper.TABLE_NAME, null);

//        if (c!=null) {
//            c.moveToFirst();
//            //Sorts through each row of the db adding it to the list
//            while(!c.isAfterLast()){
//
//
//
//                list.add(new KitchenDataObject(
//                        c.getString(c.getColumnIndex(KitchenDataBaseHelper.KEY_TYPE)),
//                        c.getString(c.getColumnIndex(KitchenDataBaseHelper.KEY_NAME)),
//                        c.getInt(c.getColumnIndex(KitchenDataBaseHelper.KEY_SETTING))
//                        ));
//                applianceAdapter.notifyDataSetChanged();
//                c.moveToNext();
//            }
//        }

        new databaseLoader().execute();

        addAppliance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewAppliance();
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.menu_main, m);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem mi) {
        int id = mi.getItemId();
        switch (id) {


            case R.id.action_three:
                Intent intent1 = new Intent(KitchenRemote.this, HouseSettingsRemote.class);
                startActivity(intent1);
                break;

            case R.id.action_four:

                Toast toast2 = Toast.makeText(this, getString(R.string.current_activity), Toast.LENGTH_SHORT);
                toast2.show();
                break;

            case R.id.action_one:
                Intent intent3 = new Intent(KitchenRemote.this, LivingRoomRemoteActivity.class);
                startActivity(intent3);

                break;

            case R.id.action_five:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                String dialog_title = "Help";
                builder.setTitle(dialog_title);
                builder.setIcon(R.drawable.help);
                String helpMessage = "insert witty message";
                builder.setMessage(helpMessage);
                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
                break;
        }
        return true;
    }

    public void onDestroy(){
        super.onDestroy();
        db.close();
    }

    public void addNewAppliance() {
        AlertDialog.Builder custBuilder = new AlertDialog.Builder(this);



        //get a layout inflater
        LayoutInflater inflater = this.getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.add_appliance_alert_layout, null);

        final EditText nameBox = (EditText) dialogView.findViewById(R.id.newApplianceName);
        final RadioButton fridgeOption = (RadioButton) dialogView.findViewById(R.id.radioFridge);
        final RadioButton microOption = (RadioButton) dialogView.findViewById(R.id.radioMicrowave);
        final RadioButton lightOption = (RadioButton) dialogView.findViewById(R.id.radioLight);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        custBuilder.setView(dialogView)
                // Add action buttons
                .setPositiveButton("Set", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        String objectType = null;

                        if (fridgeOption.isChecked()){
                            objectType = fridgeOption.getText().toString();
                        }else if(microOption.isChecked()){
                            objectType = microOption.getText().toString();
                        }else{
                            objectType = lightOption.getText().toString();
                        }


                        KitchenDataObject kitchenDO = new KitchenDataObject(objectType, nameBox.getText().toString(), 0);

                        ContentValues cValues = new ContentValues();
                        cValues.put(KitchenDataBaseHelper.KEY_NAME, kitchenDO.getName());
                        cValues.put(KitchenDataBaseHelper.KEY_TYPE, kitchenDO.getType());
                        db.insert(KitchenDataBaseHelper.TABLE_NAME, null, cValues);

                        list.add(kitchenDO);

                        applianceAdapter.notifyDataSetChanged();

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        AlertDialog custAlert = custBuilder.create();

        custAlert.show();



    }

    public class databaseLoader extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {

            Cursor c = db.rawQuery("SELECT * FROM " + KitchenDataBaseHelper.TABLE_NAME, null);

            if (c!=null) {
                c.moveToFirst();
                //Sorts through each row of the db adding it to the list
                while(!c.isAfterLast()){



                    list.add(new KitchenDataObject(
                            c.getString(c.getColumnIndex(KitchenDataBaseHelper.KEY_TYPE)),
                            c.getString(c.getColumnIndex(KitchenDataBaseHelper.KEY_NAME)),
                            c.getInt(c.getColumnIndex(KitchenDataBaseHelper.KEY_SETTING))
                    ));
                    applianceAdapter.notifyDataSetChanged();
                    c.moveToNext();
                }
            }

            return null;
        }
    }

    public class ApplianceAdapter extends ArrayAdapter<KitchenDataObject> {

        public ApplianceAdapter(Context ctx){
            super(ctx, 0);
        }

        public int getCount() { return list.size(); }

        public KitchenDataObject getItem(int position){
            return list.get(position);
        }

        public View getView(final int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = KitchenRemote.this.getLayoutInflater();

            String itemType = list.get(position).getType();

            View result = null;
            if (itemType.equals("Fridge")){
                result = inflater.inflate(R.layout.appliance_item_layout, null);
                TextView message = (TextView) result.findViewById(R.id.appliance_name);
                message.setText(getItem(position).getName());

                result.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent fridge = new Intent(v.getContext(), Fridge.class);

                        Bundle b = new Bundle();
                        b.putString("name", getItem(position).getName()); //Your id
                        b.putInt("setting", getItem(position).getSetting());
                        fridge.putExtras(b);

                        startActivity(fridge);



                    }
                });

            }else if(itemType.equals("Microwave")) {
                result = inflater.inflate(R.layout.appliance_item_layout, null);
                TextView message = (TextView) result.findViewById(R.id.appliance_name);
                message.setText(getItem(position).getName());

                result.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent microwave = new Intent(v.getContext(), Microwave.class);

                        Bundle b = new Bundle();
                        b.putString("name", getItem(position).getName());
                        microwave.putExtras(b);

                        startActivity(microwave);
                    }
                });

            }else if(itemType.equals("Light")){
                result = inflater.inflate(R.layout.appliance_item_layout, null);
                TextView message = (TextView) result.findViewById(R.id.appliance_name);
                message.setText(getItem(position).getName());

                result.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent light = new Intent(v.getContext(), KitchenLight.class);

                        Bundle b = new Bundle();
                        b.putString("name", getItem(position).getName()); //Your id
                        b.putInt("setting", getItem(position).getSetting());
                        light.putExtras(b);

                        startActivity(light);

                    }
                });
            }

            return result;
        }



    }

}