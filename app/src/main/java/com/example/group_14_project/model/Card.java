package com.example.group_14_project.model;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;

// Card: holds 3 objects of type Image in an ArrayList
public class Card {


    private int TOTAL_NUM_OF_IMG;
    private int currentNumOfImg = 0;
    private ArrayList<Image> images = new ArrayList<>();

    public Card(int numOfImages){
        TOTAL_NUM_OF_IMG = numOfImages;
    }



    public Card(Card tobeCopied){
        for (Image i:tobeCopied.getImages()) {
            this.TOTAL_NUM_OF_IMG = tobeCopied.getTOTAL_NUM_OF_IMG();
            Image tobeAdded = new Image(i);
            this.addImage(tobeAdded);
        }

    }


    public ArrayList<Image> getImages() {
        return images;
    }

    public void setImages(ArrayList<Image> tobeCopied) {
        images = new ArrayList<>();
        currentNumOfImg = 0;
        for (Image i:tobeCopied
             ) {
            Image j = new Image(i);
            this.addImage(j);
        }
    }


    public int getTOTAL_NUM_OF_IMG() {
        return TOTAL_NUM_OF_IMG;
    }

    public int getCurrentNumOfImg() {
        return currentNumOfImg;
    }

    public void addImage(Image img) {

        if(currentNumOfImg < TOTAL_NUM_OF_IMG) {
            images.add(img);
            currentNumOfImg++;
        }
    }


    public Image getImageAt(int index) {
        if(index < TOTAL_NUM_OF_IMG) {
            return images.get(index);
        }
        return null;
    }

}
