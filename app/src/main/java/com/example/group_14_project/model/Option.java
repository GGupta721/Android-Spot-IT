package com.example.group_14_project.model;

import android.content.Context;
//this class is use to support the features in the option screen so the features can be displayed when playing the game
import android.content.ContextWrapper;
import android.graphics.Bitmap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Option {

    // Singleton code
    private static Option instance;
    private Option(){}
    public static Option getInstance(){
        if(instance == null){
            instance = new Option();
        }
        return instance;
    }

    enum Order{
        TWO,
        THREE,
        FIVE
    }

    enum DrawPile{
        FIVE,
        TEN,
        FIFTEEN,
        TWENTY,
        ALL
    }

    enum ImageSet{
        ANIMALS,
        FOOD,
        FLICKR
    }

    enum Difficulty{
        EASY,
        NORMAL,
        HARD
    }

    // Normal class code
    public int NUM_OF_HIGH_SCORES = 5;
    private boolean isFlickrSetCompatible = false;
    private boolean wordMode = false;
    private boolean flickrSetEnabled = false;
    private int flickrSetNumber = 0;
    private int numOfImageSets = 0;
    private String selectedImageSet = "";
    private PlayerScore[] highScores = new PlayerScore[NUM_OF_HIGH_SCORES];
    private static final int[] defaultTimes = {8,12,20,25,30};
    private static final String[] defaultNames = {"1337h4x0r","Pro","casual","filthy casual","noob"};
    private static final int ORDER_TWO = 3;
    private static final int ORDER_THREE = 4;
    private static final int ORDER_FIVE = 6;
    private static final int DRAW_PILE_FIVE = 5;
    private static final int DRAW_PILE_TEN = 10;
    private static final int DRAW_PILE_FIFTEEN = 15;
    private static final int DRAW_PILE_TWENTY = 20;
    private static final int DRAW_PILE_ALL_ORDER_TWO = 7;
    private static final int DRAW_PILE_ALL_ORDER_THREE = 13;
    private static final int DRAW_PILE_ALL_ORDER_FIVE = 31;
    private static final String ORDER_5_DRAW_5 = "com.example.group_14_project.order5draw5";
    private static final String ORDER_5_DRAW_10 = "com.example.group_14_project.order5draw10";
    private static final String ORDER_5_DRAW_15 = "com.example.group_14_project.order5draw15";
    private static final String ORDER_5_DRAW_20 = "com.example.group_14_project.order5draw20";
    private static final String ORDER_5_DRAW_31 = "com.example.group_14_project.order5draw31";
    private static final String ORDER_3_DRAW_5 = "com.example.group_14_project.order3draw5";
    private static final String ORDER_3_DRAW_10 = "com.example.group_14_project.order3draw10";
    private static final String ORDER_3_DRAW_13 = "com.example.group_14_project.order3draw13";
    private static final String ORDER_2_DRAW_5 = "com.example.group_14_project.order2draw5";
    private static final String ORDER_2_DRAW_7 = "com.example.group_14_project.order2draw7";
    private Order myOrder = Order.TWO;
    private DrawPile myDrawPile = DrawPile.FIVE;
    private ImageSet imageSet = ImageSet.ANIMALS;
    private Difficulty difficultyMode = Difficulty.EASY;
    private ArrayList<BitmapHelper> myImageSets = new ArrayList<BitmapHelper>(); // TODO
    private HashMap<String,BitmapHelper> myImageSets_ = new HashMap<String,BitmapHelper>();




    public void addHighScore(PlayerScore score){
        for(int i = 0; i < NUM_OF_HIGH_SCORES; ++i){
            if(score.getTime() < highScores[i].getTime()){
                for(int j = NUM_OF_HIGH_SCORES-1 ; j > i; --j){
                    highScores[j] = highScores[j-1];
                }
                highScores[i] = score;
                i = NUM_OF_HIGH_SCORES;
            }
        }
    }// end func addHighScore

    public void setHighScores(PlayerScore[] scores){
        highScores = scores;
    }

    public PlayerScore getScoreAt(int i){
        if(i >= 0 && i <= 4){
            return highScores[i];
        }
        return null;
    }

    public void initializeDefaultScores(){
        for(int i = 0; i < NUM_OF_HIGH_SCORES;++i){
            instance.highScores[i] = new PlayerScore();
            instance.highScores[i].createNewPlayerScore(defaultTimes[i],defaultNames[i]);
        }
    }

    public void setAsAnimal(){
        imageSet = ImageSet.ANIMALS;
    }

    public boolean isAnimals(){
        if(imageSet == ImageSet.ANIMALS){
            return true;
        }
        return false;
    }

    public void setAsFood(){
        imageSet = ImageSet.FOOD;
    }

    public boolean isFood(){
        if(imageSet == ImageSet.FOOD){
            return true;
        }
        return false;
    }

    public void setAsFlickr(){
        imageSet = ImageSet.FLICKR;
    }

    public boolean isFlickr(){
        if(imageSet == ImageSet.FLICKR){
            return true;
        }
        return false;
    }


    public String getThemeChoiceAsString(){
        String themeChoice = "";
        switch(imageSet){
            case ANIMALS:
                themeChoice = "Animals";
                break;
            case FOOD:
                themeChoice = "Food";
                break;
            case FLICKR:
                themeChoice = "Flickr";
                break;
        }
        return themeChoice;
    }

    public int getFlickrSetNumber(){
        return flickrSetNumber;
    }

    public void setFlickerSetNumber(int flickrSetNumber){
        this.flickrSetNumber = flickrSetNumber;
    }

    public int getMaxOrderCardNum(){
        int maxCardNum = 0;
        switch(myOrder){
            case TWO:
                maxCardNum = DRAW_PILE_ALL_ORDER_TWO;
                break;
            case THREE:
                maxCardNum = DRAW_PILE_ALL_ORDER_THREE;
                break;
            case FIVE:
                maxCardNum = DRAW_PILE_ALL_ORDER_FIVE;
                break;
        }
        return maxCardNum;
    }

    public void setWordModeOn(){
        wordMode = true;
    }

    public void setWordModeOff(){
        wordMode = false;
    }

    public Order getOrder(){
        return myOrder;
    }
    public DrawPile getDrawPile(){
        return myDrawPile;
    }

    public void setOrderToTwo(){
        myOrder = Order.TWO;
    }
    public void setOrderToThree(){
        myOrder = Order.THREE;
    }
    public void setOrderToFive(){
        myOrder = Order.FIVE;
    }

    public boolean getFlickrSetEnabled(){
        return flickrSetEnabled;
    }
    public void setFlickrSetDisabled(){
        flickrSetEnabled = false;
    }
    public void setFlickrSetEnabled(){
        flickrSetEnabled = true;
    }

    public void setDrawPileToFive(){
        myDrawPile = DrawPile.FIVE;
    }
    public void setDrawPileToTen(){
        if(myOrder != Order.TWO){
            myDrawPile = DrawPile.TEN;
        }else{
            myDrawPile = DrawPile.ALL;
        }
    }
    public void setDrawPileToFifteen(){
        if(myOrder == Order.FIVE){
            myDrawPile = DrawPile.FIFTEEN;
        }else{
            myDrawPile = DrawPile.ALL;
        }
    }
    public void setDrawPileToTwenty(){
        if(myOrder == Order.FIVE){
            myDrawPile = DrawPile.TWENTY;
        }else{
            myDrawPile = DrawPile.ALL;
        }
    }
    public void setDrawPileToAll(){
        myDrawPile = DrawPile.ALL;
    }

    public int getOrderAsIntCode(){
        int orderNumber = -1;
        switch(myOrder){
            case TWO:
                orderNumber = 0;
                break;
            case THREE:
                orderNumber = 1;
                break;
            case FIVE:
                orderNumber = 2;
                break;
        }
        return orderNumber;
    }

    public int getDrawPileAsIntCode(){
        int orderNumber = -1;
        switch(myDrawPile){
            case FIVE:
                orderNumber = 0;
                break;
            case TEN:
                orderNumber = 1;
                break;
            case FIFTEEN:
                orderNumber = 2;
                break;
            case TWENTY:
                orderNumber = 3;
                break;
            case ALL:
                orderNumber = 4;
                break;
        }
        return orderNumber;
    }

    public int getOrderAsInt(){
        int order = 0;
        switch(myOrder){
            case TWO:
                order = ORDER_TWO;
                break;
            case THREE:
                order = ORDER_THREE;
                break;
            case FIVE:
                order = ORDER_FIVE;
                break;
        }
        return order;
    }

    public int getDrawPileAsInt(){
        int drawPile = 0;
        switch(myDrawPile){
            case FIVE:
                drawPile = DRAW_PILE_FIVE;
                break;
            case TEN:
                drawPile = DRAW_PILE_TEN;
                break;
            case FIFTEEN:
                drawPile = DRAW_PILE_FIFTEEN;
                break;
            case TWENTY:
                drawPile = DRAW_PILE_TWENTY;
                break;
            case ALL:
                if(myOrder == Order.TWO){
                    drawPile = DRAW_PILE_ALL_ORDER_TWO;
                }else if(myOrder == Order.THREE){
                    drawPile = DRAW_PILE_ALL_ORDER_THREE;
                }else{
                    drawPile = DRAW_PILE_ALL_ORDER_FIVE;
                }
                break;
        }
        return drawPile;
    }

    public void setDifficultyModeEasy(){
        difficultyMode = Difficulty.EASY;
    }

    public void setDifficultyModeNormal(){
        difficultyMode = Difficulty.NORMAL;
    }

    public void setDifficultyModeHard(){
        difficultyMode = Difficulty.HARD;
    }

   /* public Difficulty getDifficultyMode(){
        return difficultyMode;
    }*/

    public String getDifficultyModeAsString(){
        String mode = "";
        switch(difficultyMode){
            case EASY:
                mode = "EASY";
                break;
            case NORMAL:
                mode = "NORMAL";
                break;
            case HARD:
                mode = "HARD";
                break;
        }
        return mode;
    }


    public boolean getWordMode(){
        return wordMode;
    }

    public boolean hasFlickerSet(){
        return isFlickrSetCompatible;
    }

    public void setAsNoFlickrSet(){
        isFlickrSetCompatible = false;
    }

    public void setAsHasFlickrSet(){
        isFlickrSetCompatible = true;
    }

    public String getSharedPreferenceKey(){
        String currentSharedPreference = "";
        switch(getOrderAsIntCode()){
            case 0:
                switch(getDrawPileAsIntCode()){
                    case 0:
                        currentSharedPreference = ORDER_2_DRAW_5;
                        break;
                    case 4:
                        currentSharedPreference = ORDER_2_DRAW_7;
                        break;
                }
                break;
            case 1:
                switch(getDrawPileAsIntCode()){
                    case 0:
                        currentSharedPreference = ORDER_3_DRAW_5;
                        break;
                    case 1:
                        currentSharedPreference = ORDER_3_DRAW_10;
                        break;
                    case 4:
                        currentSharedPreference = ORDER_3_DRAW_13;
                        break;
                }
                break;
            case 2:
                switch(getDrawPileAsIntCode()){
                    case 0:
                        currentSharedPreference = ORDER_5_DRAW_5;
                        break;
                    case 1:
                        currentSharedPreference = ORDER_5_DRAW_10;
                        break;
                    case 2:
                        currentSharedPreference = ORDER_5_DRAW_15;
                        break;
                    case 3:
                        currentSharedPreference = ORDER_5_DRAW_20;
                        break;
                    case 4:
                        currentSharedPreference = ORDER_5_DRAW_31;
                        break;
                }
                break;
        }
        return currentSharedPreference;
    }

    public void setNumOfImageSets(){
        numOfImageSets = myImageSets_.size();
    }

    public int getNumOfImageSets(){
        setNumOfImageSets();
        return numOfImageSets;
    }

    public ArrayList<BitmapHelper> getMyImageSets(){
        return myImageSets;
    }

    public HashMap<String,BitmapHelper> getMyImageSets_(){
        return myImageSets_;
    }

    public String getSelectedImageSet(){
        return selectedImageSet;
    }

    public void setSelectedImageSet(String key){
        selectedImageSet = key;
    }


}
