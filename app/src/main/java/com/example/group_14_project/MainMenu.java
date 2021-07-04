package com.example.group_14_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.group_14_project.model.Option;
import com.example.group_14_project.model.PlayerScore;

import java.util.Map;

// MainMenu: allows user to start game, see options, see rules or see references
public class MainMenu extends AppCompatActivity {
    private Button play;
    private Button option;
    private Button rules;
    private Button reference;
    private Option options     = Option.getInstance();
    private String currentSharedPreference = "";
    private static final String MAIN = "com.example.group_14_project.main";
    private static final String[] TIME_KEYS = {"t1","t2","t3","t4","t5"};
    private static final String[] NAME_DATE_KEYS = {"nd1","nd2","nd3","nd4","nd5"};

    private static final String[] GAME_MODE_KEYS = {"order","drawPile"};


    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        pullOptionPreferencesToOptions();
        currentSharedPreference = options.getSharedPreferenceKey();
        pref = getSharedPreferences(currentSharedPreference,Context.MODE_PRIVATE);
        editor = pref.edit();
        setupSharedPreferences();

        play = (Button) findViewById(R.id.playbtn);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPlay();
            }
        });
        rules = (Button) findViewById(R.id.rulesbtn);
        rules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRules();
            }
        });

        reference = (Button) findViewById(R.id.ref);
        reference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRef();
            }
        });

        option = (Button) findViewById(R.id.optionbtn);
        option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToOption();
            }
        });
    }

    private void goToOption() {
        Intent i = new Intent(this, SetImages.class);
        startActivity(i);
    }

    private void setupSharedPreferences() {
        if(hasPreviousHighScoreSharedPref()){
            pullScoresToOptions();
        }else{
            options.initializeDefaultScores();
            pullScoresToSharedPreferences();
        }
        pullScoresToSharedPreferences();
    }

    private void pullScoresToOptions() {
        PlayerScore[] scores = new PlayerScore[options.NUM_OF_HIGH_SCORES];
        Map<String,?> myPrefs = pref.getAll();
        for(int i = 0; i < options.NUM_OF_HIGH_SCORES;++i){
            scores[i] = new PlayerScore(Integer.parseInt(myPrefs.get(TIME_KEYS[i]).toString()),
                    myPrefs.get(NAME_DATE_KEYS[i]).toString());
        }
        options.setHighScores(scores);
    }

    private void pullScoresToSharedPreferences() {
        for(int i = 0; i < options.NUM_OF_HIGH_SCORES;++i){
            editor.putInt(TIME_KEYS[i],options.getScoreAt(i).getTime());
            editor.putString(NAME_DATE_KEYS[i], options.getScoreAt(i).getNameDate());
        }
        editor.apply();
        editor.commit();
    }

    private void pullOptionPreferencesToOptions() {
        pref = getSharedPreferences(MAIN,MODE_PRIVATE);
        if(hasPreviousMainSharedPref()){
            int order = pref.getInt("order",2);
            int drawPile = pref.getInt("drawPile",5);
            switch(order){
                case 2:
                    options.setOrderToTwo();
                    break;
                case 3:
                    options.setOrderToThree();
                    break;
                case 5:
                    options.setOrderToFive();
                    break;
            }
            switch(drawPile){
                case 5:
                    options.setDrawPileToFive();
                    break;
                case 10:
                    options.setDrawPileToTen();
                    break;
                case 15:
                    options.setDrawPileToFifteen();
                    break;
                case 20:
                    options.setDrawPileToTwenty();
                    break;
                default:
                    options.setDrawPileToAll();
            }
        }
    }

    private void goToRef() {
        Intent i = new Intent(this, Reference.class);
        startActivity(i);
    }

    private void goToRules() {
        Intent i = new Intent(this, GameRules.class);
        startActivity(i);
    }

    private void goToPlay() {
        Intent i = GameScreen.makeIntent(this);
        startActivity(i);
    }


    public boolean hasPreviousHighScoreSharedPref(){
        if(pref.contains("t1")){
            return true;
        }
        return false;
    }

    public boolean hasPreviousMainSharedPref(){
        if(pref.contains("order")){
            return true;
        }
        return false;
    }



}