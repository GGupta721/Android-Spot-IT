package com.example.group_14_project;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group_14_project.model.BitmapHelper;
import com.example.group_14_project.model.Card;
import com.example.group_14_project.model.Deck;
import com.example.group_14_project.model.Image;
import com.example.group_14_project.model.Option;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Random;

/**
* This class represents the game screen activity. It uses linear-layouts to represent cards and assigns images to the imageView they contain.
* This class also keeps track of the common image in the two cards and uses onClick listeners for the user input.
*/
// comment
public class GameScreen extends AppCompatActivity {

    Deck gameDeck;

    Chronometer timer;
    TextView cardCount;
    int drawPileSize = 5;
    int imgPerCard = 3;
    String gameDifficulty = "EASY";

    int currentCard = 1;
    Image commonImg = null;
    LinearLayout drawPile;
    LinearLayout discardPile;
    Button startEasy;
    Button startNormal;
    Button startHard;
    Option options;
    boolean discard = false;
    boolean draw = false;

    ImageView V1, V2, V3, V4, V5, V6, V7, V8, V9, V10, V11, V12;
    Image I1, I2, I3, I4, I5, I6, I7, I8, I9, I10, I11, I12;       //to keep track of images associated with the ImageView

    //Sound effects
    MediaPlayer correctChoice;
    MediaPlayer wrongChoice;
    MediaPlayer gameWon;
    MediaPlayer gameStart;

    ArrayList<Bitmap> bitmapArray = new ArrayList<Bitmap>();

    Random random = new Random();

    private int randomrotateV1 = new Random().nextInt(361);
    private int randomrotateV2 = new Random().nextInt(361);
    private int randomrotateV3 = new Random().nextInt(361);
    private int randomrotateV4 = new Random().nextInt(361);
    private int randomrotateV5 = new Random().nextInt(361);
    private int randomrotateV6 = new Random().nextInt(361);

    private int randomresizeV1 = new Random().nextInt(11);
    private int randomresizeV2 = new Random().nextInt(11);
    private int randomresizeV3 = new Random().nextInt(11);
    private int randomresizeV4 = new Random().nextInt(11);
    private int randomresizeV5 = new Random().nextInt(11);
    private int randomresizeV6 = new Random().nextInt(11);

    public static Intent makeIntent(Context C) {
        Intent intent = new Intent(C, GameScreen.class);
        return intent;
    }

    private static final int WRITE_EXTERNAL_STORAGE_CODE = 1;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);



        ActivityCompat.requestPermissions(GameScreen.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        ActivityCompat.requestPermissions(GameScreen.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        ActivityCompat.requestPermissions(GameScreen.this, new String[]{Manifest.permission.MANAGE_EXTERNAL_STORAGE}, 1);

        options = Option.getInstance();

        drawPileSize = options.getDrawPileAsInt();
        imgPerCard = options.getOrderAsInt();
        gameDifficulty = options.getDifficultyModeAsString();

        getSupportActionBar().setTitle("Play Game");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        gameDeck = new Deck(drawPileSize, imgPerCard);

        V1 = (ImageView) findViewById(R.id.discardPIMG1);
        V2 = (ImageView) findViewById(R.id.discardPIMG2);
        V3 = (ImageView) findViewById(R.id.discardPIMG3);
        V4 = (ImageView) findViewById(R.id.discardPIMG4);
        V5 = (ImageView) findViewById(R.id.discardPIMG5);
        V6 = (ImageView) findViewById(R.id.discardPIMG6);

        V7 = (ImageView) findViewById(R.id.drawPIMG1);
        V8 = (ImageView) findViewById(R.id.drawPIMG2);
        V9 = (ImageView) findViewById(R.id.drawPIMG3);
        V10 = (ImageView) findViewById(R.id.drawPIMG4);
        V11 = (ImageView) findViewById(R.id.drawPIMG5);
        V12 = (ImageView) findViewById(R.id.drawPIMG6);


        timer = findViewById(R.id.timerCount);
        timer.setFormat("Timer: %s");
        cardCount = (TextView) findViewById(R.id.cardCount);
        setOnClickListeners();

        drawPile = (LinearLayout) findViewById(R.id.drawPile);
        discardPile = (LinearLayout) findViewById(R.id.discardPile);

        drawPile.setVisibility(View.INVISIBLE);
        discardPile.setVisibility(View.INVISIBLE);

        correctChoice = MediaPlayer.create(this, R.raw.correct_choice);
        wrongChoice = MediaPlayer.create(this, R.raw.wrong_choice);
        gameWon = MediaPlayer.create(this, R.raw.game_won);
        gameStart = MediaPlayer.create(this, R.raw.startbutton1);

        startGame();
        flipCard(currentCard);

    }



    private void resizeDrawRandomly() {
        final int randomV7 = new Random().nextInt(11);
        final int randomV8 = new Random().nextInt(11);
        final int randomV9 = new Random().nextInt(11);
        final int randomV10 = new Random().nextInt(11);
        final int randomV11 = new Random().nextInt(11);
        final int randomV12 = new Random().nextInt(11);
        
        LinearLayout.LayoutParams layoutParamsBig = new LinearLayout.LayoutParams(160, 160);
        layoutParamsBig.weight = 1.0f;
        layoutParamsBig.gravity = Gravity.CENTER;

        LinearLayout.LayoutParams layoutParamsSmall = new LinearLayout.LayoutParams(80, 80);
        layoutParamsSmall.weight = 1.0f;
        layoutParamsSmall.gravity = Gravity.CENTER;


        if(randomV7 <= 5) {
            V7.setLayoutParams(layoutParamsSmall);
        }
        else if(randomV7 > 5){
            V7.setLayoutParams(layoutParamsBig);
        }

        if(randomV8 <= 5) {
            V8.setLayoutParams(layoutParamsSmall);
        }
        else if(randomV8 > 5){
            V8.setLayoutParams(layoutParamsBig);
        }

        if(randomV9 <= 5) {
            V9.setLayoutParams(layoutParamsSmall);
        }
        else if(randomV9 > 5){
            V9.setLayoutParams(layoutParamsBig);
        }

        if(randomV10 <= 5) {
            V10.setLayoutParams(layoutParamsSmall);
        }
        else if(randomV10 > 5){
            V10.setLayoutParams(layoutParamsBig);
        }

        if(randomV11 <= 5) {
            V11.setLayoutParams(layoutParamsSmall);
        }
        else if(randomV11 > 5){
            V11.setLayoutParams(layoutParamsBig);
        }

        if(randomV12 <= 5) {
            V12.setLayoutParams(layoutParamsSmall);
        }
        else if(randomV12 > 5){
            V12.setLayoutParams(layoutParamsBig);
        }


        randomresizeV1 = randomV7;
        randomresizeV2 = randomV8;
        randomresizeV3 = randomV9;
        randomresizeV4 = randomV10;
        randomresizeV5 = randomV11;
        randomresizeV6 = randomV12;
    }

    // rotate the drawpile
    private void rotateDrawRandomly() {
        final int randomV7 = new Random().nextInt(361);
        final int randomV8 = new Random().nextInt(361);
        final int randomV9 = new Random().nextInt(361);
        final int randomV10 = new Random().nextInt(361);
        final int randomV11 = new Random().nextInt(361);
        final int randomV12 = new Random().nextInt(361);
        V7.setRotation(randomV7);
        V8.setRotation(randomV8);
        V9.setRotation(randomV9);
        V10.setRotation(randomV10);
        V11.setRotation(randomV11);
        V12.setRotation(randomV12);
        randomrotateV1 = randomV7;
        randomrotateV2 = randomV8;
        randomrotateV3 = randomV9;
        randomrotateV4 = randomV10;
        randomrotateV5 = randomV11;
        randomrotateV6 = randomV12;
    }

    private void startGame() {
        startEasy = (Button) findViewById(R.id.startGameEasy);
        startNormal = (Button) findViewById(R.id.startGameNormal);
        startHard = (Button) findViewById(R.id.startGameHard);

        startEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                options.setDifficultyModeEasy();
                initialSetup();
            }
        });
        startNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                options.setDifficultyModeNormal();
                initialSetup();
            }
        });
        startHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                options.setDifficultyModeHard();
                initialSetup();
            }
        });
    }

    private void initialSetup() {
        gameStart.start();
        setVisibilitySettings();
        cardCount.setText("Cards Left: "+(drawPileSize-currentCard));
        timer.setBase(SystemClock.elapsedRealtime());
        timer.start();
        if(options.getDifficultyModeAsString() == "NORMAL" || options.getDifficultyModeAsString() == "HARD") {
            rotateDiscard();
            rotateDrawRandomly();
            if(options.getDifficultyModeAsString() == "HARD"){
                resizeDiscard();
                resizeDrawRandomly();
            }
        }
    }

    //Set number of images per card
    private void setVisibilitySettings() {
        startEasy.setVisibility(View.GONE);
        startNormal.setVisibility(View.GONE);
        startHard.setVisibility(View.GONE);

        drawPile.setVisibility(View.VISIBLE);
        discardPile.setVisibility(View.VISIBLE);
        cardCount.setVisibility(View.VISIBLE);
        timer.setVisibility(View.VISIBLE);

        switch(imgPerCard){
            case 3:
                V4.setVisibility(View.GONE);
                V5.setVisibility(View.GONE);
                V6.setVisibility(View.GONE);
                V10.setVisibility(View.GONE);
                V11.setVisibility(View.GONE);
                V12.setVisibility(View.GONE);

                break;
            case 4:

                V4.setVisibility(View.GONE);
                V6.setVisibility(View.GONE);
                V10.setVisibility(View.GONE);
                V12.setVisibility(View.GONE);

                break;
            case 6:


                break;
        }

    }


    private void setOnClickListeners() {

        V7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCommonStandardImage(I7);
            }
        });

        V8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCommonStandardImage(I8);
            }
        });

        V9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCommonStandardImage(I9);
            }
        });

        V10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCommonStandardImage(I10);
            }
        });

        V11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCommonStandardImage(I11);
            }
        });

        V12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCommonStandardImage(I12);
            }
        });
    }

    private void checkCommonStandardImage(Image I){
        if (I == commonImg){
            correctChoice.start();
            flipCard(++currentCard );
            if(options.getDifficultyModeAsString() == "NORMAL" || options.getDifficultyModeAsString() == "HARD") {
                rotateDiscard();
                rotateDrawRandomly();
                if(options.getDifficultyModeAsString() == "HARD"){
                    resizeDiscard();
                    resizeDrawRandomly();
                }
            }
        }
        else {
            wrongChoice.start();
        }
    }

    private void rotateDiscard() {
        V1.setRotation(randomrotateV1);
        V2.setRotation(randomrotateV2);
        V3.setRotation(randomrotateV3);
        V4.setRotation(randomrotateV4);
        V5.setRotation(randomrotateV5);
        V6.setRotation(randomrotateV6);
    }

    private void resizeDiscard() {

        LinearLayout.LayoutParams layoutParamsBig = new LinearLayout.LayoutParams(160, 160);
        layoutParamsBig.weight = 1.0f;
        layoutParamsBig.gravity = Gravity.CENTER;

        LinearLayout.LayoutParams layoutParamsSmall = new LinearLayout.LayoutParams(80, 80);
        layoutParamsSmall.weight = 1.0f;
        layoutParamsSmall.gravity = Gravity.CENTER;

        if(randomresizeV1 <= 5) {
            V1.setLayoutParams(layoutParamsSmall);
        }
        else if(randomresizeV1 > 5) {
            V1.setLayoutParams(layoutParamsBig);
        }
        if(randomresizeV2 <= 5) {
            V2.setLayoutParams(layoutParamsSmall);
        }
        else if(randomresizeV2 > 5) {
            V2.setLayoutParams(layoutParamsBig);
        }
        if(randomresizeV3 <= 5) {
            V3.setLayoutParams(layoutParamsSmall);
        }
        else if(randomresizeV3 > 5) {
            V3.setLayoutParams(layoutParamsBig);
        }
        if(randomresizeV4 <= 5) {
            V4.setLayoutParams(layoutParamsSmall);
        }
        else if(randomresizeV4 > 5) {
            V4.setLayoutParams(layoutParamsBig);
        }
        if(randomresizeV5 <= 5) {
            V5.setLayoutParams(layoutParamsSmall);
        }
        else if(randomresizeV5 > 5) {
            V5.setLayoutParams(layoutParamsBig);
        }
        if(randomresizeV6 <= 5) {
            V6.setLayoutParams(layoutParamsSmall);
        }
        else if(randomresizeV6 > 5) {
            V6.setLayoutParams(layoutParamsBig);
        }
    }

    private void flipCard(int localCurrentCard) {

        cardCount.setText("Cards Left: " + (drawPileSize-currentCard));
        if(!(localCurrentCard < drawPileSize)) {
            setDiscardPile(gameDeck.getCardAt(localCurrentCard-1));
            drawPile.setVisibility(View.INVISIBLE);
            timer.stop();
            finishGame();
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

                if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){

                    String[] permission = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    requestPermissions(permission,WRITE_EXTERNAL_STORAGE_CODE);
                }
                else {
                    saveImages();
                }
            }
            return;
        }

        Card currentDraw = gameDeck.getCardAt(localCurrentCard);
        Card currentDiscard = gameDeck.getCardAt(localCurrentCard-1);
        setDiscardPile(currentDiscard);
        setDrawPile(currentDraw);



        commonImg = findCommonSymbol(currentDraw, currentDiscard);
    }

    private void finishGame() {
        gameWon.start();
        FragmentManager manager = getSupportFragmentManager();
        WinAlertBox dialog = new WinAlertBox();
        int timeInMillis = (int) ((SystemClock.elapsedRealtime() - timer.getBase())/1000);
        dialog.setTime(timeInMillis);
        dialog.show(manager, "Dialog");
    }

    private Image findCommonSymbol(Card currentDraw, Card currentDiscard) {
        Image commonImg = null;
        for (int i = 0 ; i< currentDraw.getImages().size(); i++){
            for (int j = 0 ; j< currentDiscard.getImages().size(); j++) {
                if (!options.getWordMode() && currentDraw.getImageAt(i) == (currentDiscard.getImageAt(j))){
                    commonImg = currentDraw.getImageAt(i);
                    break;
                }else if(options.getWordMode()
                        && (currentDraw.getImageAt(i) == (currentDiscard.getImageAt(j))
                        || (gameDeck.isPairedImage(currentDraw.getImageAt(i),currentDiscard.getImageAt(j))))){
                    commonImg = currentDraw.getImageAt(i);
                    break;
                }
            }
        }
        return commonImg;
    }


    public void setDrawPile(Card card) {
        if (options.isFlickr()) {
            V7.setImageBitmap(BitmapHelper.getInstance().getBitmap(card.getImageAt(0).getImg()));
            V8.setImageBitmap(BitmapHelper.getInstance().getBitmap(card.getImageAt(1).getImg()));
            V9.setImageBitmap(BitmapHelper.getInstance().getBitmap(card.getImageAt(2).getImg()));
        } else {
            V7.setImageResource((card.getImageAt(0).getImg()));
            V8.setImageResource(card.getImageAt(1).getImg());
            V9.setImageResource(card.getImageAt(2).getImg());
        }
        I7 = card.getImageAt(0);
        I8 = card.getImageAt(1);
        I9 = card.getImageAt(2);
        if (imgPerCard > 3) {
            if(options.isFlickr()){
                V11.setImageBitmap(BitmapHelper.getInstance().getBitmap(card.getImageAt(3).getImg()));
            }else{
                V11.setImageResource(card.getImageAt(3).getImg());
            }
            I11 = card.getImageAt(3);
            if (imgPerCard == 6) {
                if(options.isFlickr()){
                    V10.setImageBitmap(BitmapHelper.getInstance().getBitmap(card.getImageAt(3).getImg()));
                    V12.setImageBitmap(BitmapHelper.getInstance().getBitmap(card.getImageAt(5).getImg()));
                }else{
                    V10.setImageResource(card.getImageAt(4).getImg());
                    V12.setImageResource(card.getImageAt(5).getImg());

                }
                I10 = card.getImageAt(4);
                I12 = card.getImageAt(5);
            }
        }

       // getBimapImages(drawPile);

    }


    public void setDiscardPile(Card card){
        if (options.isFlickr()) {
            V1.setImageBitmap(BitmapHelper.getInstance().getBitmap(card.getImageAt(0).getImg()));
            V2.setImageBitmap(BitmapHelper.getInstance().getBitmap(card.getImageAt(1).getImg()));
            V3.setImageBitmap(BitmapHelper.getInstance().getBitmap(card.getImageAt(2).getImg()));
        } else {
            V1.setImageResource((card.getImageAt(0).getImg()));
            V2.setImageResource(card.getImageAt(1).getImg());
            V3.setImageResource(card.getImageAt(2).getImg());
        }
        I1 = card.getImageAt(0);
        I2 = card.getImageAt(1);
        I3 = card.getImageAt(2);
        if (imgPerCard > 3) {
            if(options.isFlickr()){
                V5.setImageBitmap(BitmapHelper.getInstance().getBitmap(card.getImageAt(3).getImg()));
            }else{
                V5.setImageResource(card.getImageAt(3).getImg());
            }
            I5 = card.getImageAt(3);
            if (imgPerCard == 6) {
                if(options.isFlickr()){
                    V4.setImageBitmap(BitmapHelper.getInstance().getBitmap(card.getImageAt(3).getImg()));
                    V6.setImageBitmap(BitmapHelper.getInstance().getBitmap(card.getImageAt(5).getImg()));
                }else{
                    V4.setImageResource(card.getImageAt(4).getImg());
                    V6.setImageResource(card.getImageAt(5).getImg());
                }
                I4 = card.getImageAt(4);
                I6 = card.getImageAt(5);
            }
        }
        getBimapImages(discardPile);
    }

    private void getBimapImages(LinearLayout layout) {

            layout.post(new Runnable() {
                @Override
                public void run() {

                    layout.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                    layout.layout(0, 0, layout.getMeasuredWidth(), layout.getMeasuredHeight());

                    Bitmap bitmap = Bitmap.createBitmap(layout.getMeasuredWidth(),
                            layout.getMeasuredHeight(),
                            Bitmap.Config.ARGB_8888);
                    Canvas c = new Canvas(bitmap);
                    layout.draw(c);
//                    Bitmap bitmap1 = Bitmap.createBitmap(layout.getWidth(),
//                           layout.getHeight(), Bitmap.Config.ARGB_8888);

                    bitmapArray.add(bitmap);
                }
            });

    }

   private void saveImages() {


       File path = Environment.getExternalStorageDirectory();
       File dir = new File(path+"/Download");
       dir.mkdir();
            for(int i = 0 ; i < bitmapArray.size(); i++) {

                String m = "card" + i + ".png";

                File file = new File(dir, m);

                //Output stream
                OutputStream outputStream;

                try {
                    outputStream = new FileOutputStream(file);

                    bitmapArray.get(i).compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                    outputStream.flush();

                    MediaScannerConnection.scanFile(this, new String[]{file.toString()}, null,
                            new MediaScannerConnection.OnScanCompletedListener() {
                                public void onScanCompleted(String path, Uri uri) {
                                    Log.d("appname", "image is saved in gallery and gallery is refreshed.");
                                }
                            });
                    outputStream.close();
                    Toast.makeText(GameScreen.this, "Downloading..", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Did not work!", Toast.LENGTH_SHORT).show();
                }




            }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case WRITE_EXTERNAL_STORAGE_CODE: {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){}

                else {
                }
            }
        }
    }
}