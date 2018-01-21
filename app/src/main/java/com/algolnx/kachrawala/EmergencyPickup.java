package com.algolnx.kachrawala;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class EmergencyPickup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_pickup);

        setUpToolbar();

        String[] list = {"Non bio-degradable", "Bio-degradable", "Mixture of both"};

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> myadapter = new
                ArrayAdapter<String>(EmergencyPickup.this, android.R.layout.simple_spinner_item, list);
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(myadapter);

    }
    private void setUpToolbar(){
        Toolbar toolbar = findViewById(R.id.my_awesome_toolbar);
        toolbar.setTitle("Emergency pickup");
        toolbar.setTitleTextColor(0xFFFFFFFF);
        setSupportActionBar(toolbar);
    }
}
