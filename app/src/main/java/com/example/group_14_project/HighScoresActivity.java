package com.example.group_14_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.group_14_project.model.Option;
import com.example.group_14_project.model.PlayerScore;

// HighScoresActivity: displays top 5 high scores in a listview
public class HighScoresActivity extends AppCompatActivity {

    static final int NUM_OF_SCORES = 5;
    private String [] scores;
    private Option options;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);
        getSupportActionBar().setTitle("ScoreBoard");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        scores = new String[NUM_OF_SCORES+1];
        options = Option.getInstance();
        initializeScores();
        populateListView();
    }

    private void initializeScores() {
        scores[0] = "T: Name/Date:\t\tOrder: " + (options.getOrderAsInt()-1) + " Draw Pile: " + options.getDrawPileAsInt();
        for(int i = 1; i <= NUM_OF_SCORES; ++i){
            PlayerScore score = options.getScoreAt(i-1);
            scores[i] = "" + score.getFormattedStringDate();
        }
    }

    private void populateListView() {
        // build adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.high_scores, scores);
        // configure list view
        ListView list = findViewById(R.id.highScoresList);
        list.setAdapter(adapter);
    }
}