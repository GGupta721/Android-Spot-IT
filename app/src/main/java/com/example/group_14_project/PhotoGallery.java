package com.example.group_14_project;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;
//class to setup photo search in user interface
public class PhotoGallery extends SingleFragmentActivity {
    public static Intent newIntent(Context context) {
        return new Intent(context, PhotoGallery.class);
    }
    @Override
    protected Fragment createFragment() {
        return PhotoGalleryFragment.newInstance();
    }
}