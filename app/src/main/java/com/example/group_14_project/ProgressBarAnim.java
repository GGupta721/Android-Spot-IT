package com.example.group_14_project;

import android.content.Context;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;
import android.widget.TextView;
//this class is used to setup the loading bar animation in the splash screen
public class ProgressBarAnim extends Animation {
    private Context c;
    private ProgressBar progressBar;
    private TextView text;
    private float from;
    private float to;


    public ProgressBarAnim(Context c, ProgressBar progressBar, TextView text, float from, float to){
        this.c = c;
        this.progressBar = progressBar;
        this.text = text;
        this.from = from;
        this.to = to;
    }


    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        float value = from + (to - from) * interpolatedTime;
        progressBar.setProgress((int) value);
        text.setText((int) value+" %");

        if(value == to){
            c.startActivity(new Intent(c, MainMenu.class));
        }
    }
}
