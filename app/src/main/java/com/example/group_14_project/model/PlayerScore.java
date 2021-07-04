package com.example.group_14_project.model;

import java.util.Date;

// PlayerScore: holds time as type int and nameDate as type string
public class PlayerScore {
    private String nameDate;
    private int time;

    public PlayerScore(){
        this.nameDate = "player1";
        this.time = 60;
    }

    public PlayerScore(int time, String nameDate){
        setName(nameDate);
        setTime(time);
    }

    public String getNameDate() {
        return nameDate;
    }

    public void setName(String nameDate) {
        if(nameDate != ""){
            this.nameDate = nameDate;
        }
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        if(time > 0){
            this.time = time;
        }
    }

    public String getFormattedStringDate(){
        return "" + time + " " + nameDate;
    }

    public void createNewPlayerScore(int time, String name){
        setTime(time);
        setName(name + " on " + new Date());
    }
}
