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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LivingRoomRemoteActivity extends AppCompatActivity {

    public ListView list;
    String[] menuList;

   /* Button l1;
    Button l2;
    Button l3;
    Button tv; */
    LivingRoomDataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living_room_remote);
        menuList = getResources().getStringArray(R.array.living_room_menu);
        list = (ListView) findViewById(R.id.listView);
        list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menuList));
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(menuList[i].equals("Lamp 1")||menuList[i].equals("Lampe 1")){
                    Intent intent = new Intent(LivingRoomRemoteActivity.this, LivingRoomLamp1.class);
                    startActivity(intent);
                }
                if(menuList[i].equals("Lamp 2")||menuList[i].equals("Lampe 2")){
                    Intent intent = new Intent(LivingRoomRemoteActivity.this, LivingRoomLamp2.class);
                    startActivity(intent);
                }
                if(menuList[i].equals("Lamp 3")||menuList[i].equals("Lampe 3")){
                    Intent intent = new Intent(LivingRoomRemoteActivity.this, LivingRoomLamp3.class);
                    startActivity(intent);
                }
                if(menuList[i].equals("Telivision Remote")||menuList[i].equals("Télécommande")){
                    Intent intent = new Intent(LivingRoomRemoteActivity.this, LivingRoomTvRemote.class);
                    startActivity(intent);
                }
                if(menuList[i].equals("Blinds")||menuList[i].equals("Aveugle")){
                    Intent intent = new Intent(LivingRoomRemoteActivity.this, LivingRoomBlinds.class);
                    startActivity(intent);
                }

            }
        });


                //   list  = (ListView) findViewById(R.id.livingRoomList);

                //   ListAdapter listAdapter = new ListAdapter(this);
                //   list.setAdapter(listAdapter);

/*
        l1 = (Button) findViewById(R.id.lamp1);
        l2 = (Button) findViewById(R.id.lamp2);
        l3 = (Button) findViewById(R.id.lamp3);
        tv = (Button) findViewById(R.id.tvRemoteBTN);

        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LivingRoomRemoteActivity.this, LivingRoomLamp1.class);
                startActivity(intent);
            }
        });
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LivingRoomRemoteActivity.this, LivingRoomLamp2.class);
                startActivity(intent);
            }
        });
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LivingRoomRemoteActivity.this, LivingRoomLamp3.class);
                startActivity(intent);
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LivingRoomRemoteActivity.this, LivingRoomTvRemote.class);
                startActivity(intent);
            }
        });*/

    }


}
