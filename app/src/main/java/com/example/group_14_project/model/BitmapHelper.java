package com.example.group_14_project.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.group_14_project.R;

import java.util.ArrayList;
//this class help store pictures taken from flickr
public class BitmapHelper {

    Bitmap []bitmaps = new Bitmap[1000];
    private static final BitmapHelper instance = new BitmapHelper();
    private int stored = 0;

    public BitmapHelper(){}


    public static BitmapHelper getInstance(){
        return instance;
    }
    public Bitmap getBitmap(int position){
        return bitmaps[position];
    }

    public void setBitmap(Bitmap bitmap) {
        boolean isEmpty = false;
        int count = 0;
        while(isEmpty == false){
            if(bitmaps[count] == null) {
                bitmaps[count] = bitmap;
                isEmpty = true;
                stored++;
            }
            else
                count++;
        }
    }


    public int getStored(){
        return stored;
    }

    public void removeAnImage(int position){
        for(int i = position-1;i<=getStored();i++){
            bitmaps[i] = bitmaps[i+1];
        }
        stored--;
    }
    public String getString(int position){
        String s = "Flickr"+position;
        return s;
    }

    public boolean isEmpty(){
        if(bitmaps[0] == null){
            return true;
        }
        return false;
    }

    public void clear(){
        bitmaps = new Bitmap[1000];
        stored = 0;
    }
}
