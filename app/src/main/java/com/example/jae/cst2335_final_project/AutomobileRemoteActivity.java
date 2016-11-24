package com.example.jae.cst2335_final_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class AutomobileRemoteActivity extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "AutomobileRemoteActivity";
    ListView automobileListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automobile_remote);
    }
}
