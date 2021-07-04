package com.example.group_14_project.model;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;

// Image: placeholder class to hold the int ID of images
public class Image {

    private int img;

    public Image(int img){
        this.img = img;
    }

    public Image(Image newImage){
        img = newImage.getImg();
    }

    public int getImg(){
        return img;
    }



    @NonNull
    @Override
    public String toString() {
        return ""+img;
    }
}
