package com.algolnx.kachrawala;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class RecycleGuide extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_guide);

        RecyclerView recyclerView = findViewById(R.id.recyclingList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String[] list = {"Can", "Battery", "Medicine", "Paint", "Fertilizer", "Glass", "PVC"};
        recyclerView.setAdapter(new guideAdapter(list));

    }
}
