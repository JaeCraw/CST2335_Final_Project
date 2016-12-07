package com.example.jae.cst2335_final_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class KithchenRemoteActivity extends AppCompatActivity {

    Button btnLight;
    Button microwave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kithchen_remote);

        btnLight = (Button) findViewById(R.id.light);

        btnLight .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KithchenRemoteActivity.this, KitchenLight.class);
                startActivity(intent);
            }
        });

        microwave = (Button) findViewById(R.id.microwave);

        microwave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KithchenRemoteActivity.this, Microwave.class);
                startActivity(intent);
            }
        });

    }
}
