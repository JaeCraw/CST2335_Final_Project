package com.example.jae.cst2335_final_project;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class KithchenRemoteActivity extends AppCompatActivity {

    Button btnLight;
    Button microwave;
    Button fridgeBTN;
    public ArrayList<KitchenDataObject> list;
    public ApplianceAdapter applianceAdapter;
    public Button addAppliance;
    public ListView listView;
    public KitchenDataBaseHelper kDH;
    public SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kithchen_remote);

         listView = (ListView) findViewById(R.id.listView);

        addAppliance = (Button) findViewById(R.id.AddAppliance);

        applianceAdapter = new ApplianceAdapter(this);

        list = new ArrayList<KitchenDataObject>();

        listView.setAdapter(applianceAdapter);

        addAppliance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewAppliance();
            }
        });

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


                        KitchenDataObject kitchenDO = new KitchenDataObject(objectType, nameBox.getText().toString(), null);

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

    public class ApplianceAdapter extends ArrayAdapter<KitchenDataObject>{

        public ApplianceAdapter(Context ctx){
            super(ctx, 0);
        }

        public int getCount() { return list.size(); }

        public KitchenDataObject getItem(int position){
            return list.get(position);
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = KithchenRemoteActivity.this.getLayoutInflater();

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
                        startActivity(light);
                    }
                });
            }

            return result;
        }



    }

}
