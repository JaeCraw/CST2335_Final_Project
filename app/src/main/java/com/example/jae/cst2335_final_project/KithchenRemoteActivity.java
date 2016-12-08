package com.example.jae.cst2335_final_project;

import android.content.Context;
import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class KithchenRemoteActivity extends AppCompatActivity {

    Button btnLight;
    Button microwave;
    Button fridgeBTN;
    public ArrayList<String> list;
    public ApplianceAdapter applianceAdapter;
    public EditText applianceTxt;
    public Button addAppliance;
    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kithchen_remote);

         listView = (ListView) findViewById(R.id.listView);

        addAppliance = (Button) findViewById(R.id.AddAppliance);

        applianceTxt = (EditText) findViewById(R.id.appliance_textbox);

        applianceAdapter = new ApplianceAdapter(this);

        list = new ArrayList<String>();

        listView.setAdapter(applianceAdapter);

        addAppliance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.add(applianceTxt.getText().toString());

                applianceAdapter.notifyDataSetChanged();

                applianceTxt.setText("");

            }
        });

    }

    public class ApplianceAdapter extends ArrayAdapter<String>{

        public ApplianceAdapter(Context ctx){
            super(ctx, 0);
        }

        public int getCount() { return list.size(); }

        public String getItem(int position){
            return list.get(position);
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = KithchenRemoteActivity.this.getLayoutInflater();

            String itemType = getItem(position);

            View result = null;
            if (itemType.equals("Fridge")){
                result = inflater.inflate(R.layout.appliance_item_layout, null);
                TextView message = (TextView) result.findViewById(R.id.appliance_name);
                message.setText(getItem(position));

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
                message.setText(getItem(position));

                result.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent microwave = new Intent(v.getContext(), Microwave.class);
                        startActivity(microwave);
                    }
                });

            }else if(itemType.equals("Main Light")){
                result = inflater.inflate(R.layout.appliance_item_layout, null);
                TextView message = (TextView) result.findViewById(R.id.appliance_name);
                message.setText(getItem(position));

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
