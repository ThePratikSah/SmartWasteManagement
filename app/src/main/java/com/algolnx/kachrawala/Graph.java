package com.algolnx.kachrawala;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class Graph extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        setUpToolbar();
    }

    private void setUpToolbar(){
        Toolbar toolbar = findViewById(R.id.my_awesome_toolbar);
        toolbar.setTitle("Graphical representation");
        toolbar.setTitleTextColor(0xFFFFFFFF);
        setSupportActionBar(toolbar);
    }
}
