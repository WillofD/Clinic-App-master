package com.example.lucy.clincapp.Suggestions;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.lucy.clincapp.R;
import com.example.lucy.clincapp.Suggestions.SuggestionAdapter;

public class SuggestionActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


      //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);


//        recyclerView =
//                (RecyclerView) findViewById(R.id.recycler_view);
//
//        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//
//        adapter = new SuggestionAdapter();
//        recyclerView.setAdapter(adapter);

    }


}


