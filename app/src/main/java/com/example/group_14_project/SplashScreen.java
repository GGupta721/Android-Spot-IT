package com.example.group_14_project;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

//this class is to setup animation in the splash screen
public class SplashScreen extends AppCompatActivity {
    private Button skip;
    private ImageView image;
    long animation = 6000;
    ProgressBar progressBar;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_splash_screen);

        //image animation
        image = (ImageView) findViewById(R.id.imageView);
        ObjectAnimator rotation = ObjectAnimator.ofFloat(image,"rotation",0f,360f);
        rotation.setDuration(animation);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(rotation);
        animatorSet.start();


        //skip button
        skip = (Button) findViewById(R.id.skips);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skipAnimationButton();
            }
        });

        //loading bar animation
        progressBar = findViewById(R.id.progressBar);
        text = findViewById(R.id.percentage);
        progressBar.setMax(100);
        progressBar.setScaleY(3f);
        progressAnimation();
    }


    public void progressAnimation(){
        ProgressBarAnim anim = new ProgressBarAnim(this, progressBar, text, 0f,100f);
        anim.setDuration(animation);
        progressBar.setAnimation(anim);
    }

    public void skipAnimationButton(){
        Intent i = new Intent(this, MainMenu.class);
        startActivity(i);
    }
    /*
    public void animationSkip()
    {
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                Intent mainIntent = new Intent(SplashScreen.this,MainMenu.class);
                SplashScreen.this.startActivity(mainIntent);
                SplashScreen.this.finish();
            }
        }, animation);

    }
     */
}