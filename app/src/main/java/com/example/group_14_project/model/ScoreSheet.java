package com.example.group_14_project.model;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuBuilder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
//this class is used supports HighScoreActivity
public class ScoreSheet implements Iterable<PlayerScore>{

    final static int NUM_OF_HIGH_SCORES = 5;
    private static List<PlayerScore> highscore = new ArrayList<>();

    public static ScoreSheet instance;
    public static ScoreSheet getInstance(){
        if(instance == null){
            instance = new ScoreSheet();
        }
        return instance;
    }

    public static void addItem(PlayerScore score){

        highscore.add(score);
    }

    public static PlayerScore returnItem(int index){
        return highscore.get(index);
    }

    public static void replaceItem(int index ,PlayerScore playerScore){
        highscore.set(index, playerScore);
    }

    @NonNull
    @Override
    public Iterator<PlayerScore> iterator() {

        return highscore.iterator();
    }
}
