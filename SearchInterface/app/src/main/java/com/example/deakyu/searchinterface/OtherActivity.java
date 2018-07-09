package com.example.deakyu.searchinterface;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class OtherActivity extends AppCompatActivity {

    private Button activateSearchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        activateSearchButton = findViewById(R.id.activate_search_button);
        activateSearchButton.setOnClickListener(v -> {
            if (onSearchRequested()) Toast.makeText(getApplicationContext(), "Please hold...", Toast.LENGTH_SHORT).show();
            else Toast.makeText(getApplicationContext(), "Something went wrong...", Toast.LENGTH_SHORT).show();
        });
    }
}
