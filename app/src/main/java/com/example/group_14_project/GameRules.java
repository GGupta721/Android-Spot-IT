package com.example.group_14_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
//this class is used for the game rules screen
public class GameRules extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_rules);

        //toolbar back button
        getSupportActionBar().setTitle("Rules");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}