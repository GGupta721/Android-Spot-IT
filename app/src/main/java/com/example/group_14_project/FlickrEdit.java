package com.example.group_14_project;

import android.Manifest;
import android.content.ClipData;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.group_14_project.model.BitmapHelper;
import com.example.group_14_project.model.Option;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Map;


//this activity lets you add/remove images taken from flickr
public class FlickrEdit extends AppCompatActivity {
    private static final int DRAW_PILE_ALL_ORDER_TWO = 7;
    private static final int DRAW_PILE_ALL_ORDER_THREE = 13;
    private static final int DRAW_PILE_ALL_ORDER_FIVE = 31;
    private static final String KEYS = "com.example.group_14_project.keys";

    private int numOfImageSets = 0;

    private LinearLayout layout1;
    private Button load,applyImageSet, save, viewImageSets;
    private Button addLocalImg, addFlickrImg;
    private TextView numImgNumber,orderNumber;
    private EditText imgNumToDelete, setSaveName;
    private Button remove;
    private Map<String,?> myKeys;
    private Option options;
    private SharedPreferences keys;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flickr_edit);
        getSupportActionBar().setTitle("Flickr Image Set");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        options = Option.getInstance();
        load = findViewById(R.id.load);
        save = findViewById(R.id.saveImageSet);
        viewImageSets = findViewById(R.id.viewImageSets);
        layout1 = findViewById(R.id.layout123);
        addLocalImg = findViewById(R.id.addimg);
        addFlickrImg = findViewById(R.id.addFlickrImg);
        numImgNumber = findViewById(R.id.numb);
        orderNumber = findViewById(R.id.orderNumber);
        imgNumToDelete = findViewById(R.id.removeimgnumb);
        setSaveName = findViewById(R.id.savedSetNameInput);
        remove = findViewById(R.id.remove);
        applyImageSet = findViewById(R.id.applyImageSet);
        numImgNumber.setText(""+BitmapHelper.getInstance().getStored());
        orderNumber.setText(""+(options.getMaxOrderCardNum()));
        keys = getSharedPreferences(KEYS,MODE_PRIVATE);
        myKeys = keys.getAll();
        editor = keys.edit();
        try {
            getSavedImageSets();
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadImageLayout();
        setDefaultSaveName();

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadImageLayout();
                numImgNumber.setText(""+BitmapHelper.getInstance().getStored());
                //load.setEnabled(false);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(verifyImageSetSize()){
                    try {
                        saveImageSet();
                        Toast.makeText(getApplicationContext(),
                                "Image set saved!",
                                Toast.LENGTH_SHORT).show();
                        setSaveName.setText("Image_Set_"+(options.getNumOfImageSets()+1));
                        finish();
                        startActivity(getIntent());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        viewImageSets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setClass(FlickrEdit.this, SavedImageSets.class);
                startActivity(i);
            }
        });

        addLocalImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                i.setType("image/*");
                //i.setClass(FlickrEdit.this, PhotoGallery.class);
                startActivityForResult(i,1);
            }
        });

        addFlickrImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setClass(FlickrEdit.this, PhotoGallery.class);
                startActivity(i);
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String edittext = imgNumToDelete.getText().toString();
                checkandremove(edittext);
                numImgNumber.setText(""+BitmapHelper.getInstance().getStored());
                options.setFlickerSetNumber(options.getFlickrSetNumber()-1);
            }
        });
        applyImageSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numImages = BitmapHelper.getInstance().getStored();
                if(verifyImageSetSize()){
                    try {
                        createImageSet(numImages);
                        options.setAsHasFlickrSet();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    // Source: https://www.youtube.com/watch?v=AmOmA6Ih3bE
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK){
            ClipData clipData = data.getClipData();
            if(clipData != null){
                for(int i = 0; i < clipData.getItemCount();++i){
                    Uri imageUri = clipData.getItemAt(i).getUri();
                    try {
                        InputStream is = getContentResolver().openInputStream(imageUri);
                        Bitmap bMap = BitmapFactory.decodeStream(is);
                        BitmapHelper.getInstance().setBitmap(bMap);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }else{
                Uri imgUri = data.getData();
                try {
                    InputStream is = getContentResolver().openInputStream(imgUri);
                    Bitmap bMap = BitmapFactory.decodeStream(is);
                    BitmapHelper.getInstance().setBitmap(bMap);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        loadImageLayout();
        numImgNumber.setText(""+BitmapHelper.getInstance().getStored());
    }

    public boolean verifyImageSetSize(){
        int numImages = BitmapHelper.getInstance().getStored();
        switch(options.getOrderAsIntCode()){
            case 0:
                if(numImages != DRAW_PILE_ALL_ORDER_TWO){
                    Toast.makeText(getApplicationContext(),"You must have " + DRAW_PILE_ALL_ORDER_TWO + " images in image set!", Toast.LENGTH_SHORT).show();
                }else{
                    return true;
                }
                break;
            case 1:
                if(numImages != DRAW_PILE_ALL_ORDER_THREE){
                    Toast.makeText(getApplicationContext(),"You must have " + DRAW_PILE_ALL_ORDER_THREE + " images in image set!", Toast.LENGTH_SHORT).show();
                }else{
                    return true;
                }
                break;
            case 2:
                if(numImages != DRAW_PILE_ALL_ORDER_FIVE){
                    Toast.makeText(getApplicationContext(),"You must have " + DRAW_PILE_ALL_ORDER_FIVE + " images in image set!", Toast.LENGTH_SHORT).show();
                }else{
                    return true;
                }
                break;
        }
        return false;
    }

    public void createImageSet(int numImages) throws IOException {
        options.setAsFlickr();
        options.setFlickerSetNumber(numImages);
        for(int i = 0;i<BitmapHelper.getInstance().getStored();i++) { // TODO
            //SaveImage(BitmapHelper.getInstance().getBitmap(i),i);
        }
        applyImageSet.setEnabled(false);
        Toast.makeText(getApplicationContext(),"Image set created!",Toast.LENGTH_SHORT).show();
    }

    private void checkandremove(String edittext) {
        Boolean isdigit1;
        try {
            BigDecimal n = new BigDecimal(edittext);
            isdigit1 = true;
        } catch (Exception e) {
            isdigit1 = false;
        }
        if (isdigit1 == false) {
            Toast.makeText(this, "ERROR!!! VALUE IS INVALID", Toast.LENGTH_SHORT).show();
        } else {
            int imagenumber = Integer.parseInt(edittext);
            if (imagenumber <= 0 || imagenumber > BitmapHelper.getInstance().getStored()) {
                Toast.makeText(this, "ERROR!!! IMAGE NUMBER IS NOT AVAILABLE", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "IMAGE SUCCESSFULLY REMOVED", Toast.LENGTH_SHORT).show();
                layout1.removeAllViews();
                BitmapHelper.getInstance().removeAnImage(imagenumber);
                loadImageLayout();
                numImgNumber.setText(""+BitmapHelper.getInstance().getStored());
            }
        }
    }

    public void loadImageLayout() {
        layout1.removeAllViews();
        for (int i = 0; i < BitmapHelper.getInstance().getStored(); i++) {
            ImageView img = new ImageView(FlickrEdit.this);
            img.setImageBitmap(BitmapHelper.getInstance().getBitmap(i));


            int x = i + 1;
            TextView txt = new TextView(FlickrEdit.this);
            txt.setText("" + x);
            txt.setTextSize(20);
            txt.setTextColor(Color.WHITE);

            addView(img, 400, 400);
            addView2(txt, 100, 100);
        }
    }


    private void addView2(TextView txt, int width, int height) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
        layoutParams.setMargins(0, 0, 0, 10);
        txt.setLayoutParams(layoutParams);
        layout1.addView(txt);
    }

    public void addView(ImageView img, int width, int height) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
        layoutParams.setMargins(0, 10, 0, 10);
        img.setLayoutParams(layoutParams);
        layout1.addView(img);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, SetImages.class);
        startActivity(intent);
    }


    // Source: https://www.tutorialspoint.com/how-to-write-an-image-file-in-internal-storage-in-android
    private void saveImageSet() throws IOException {
        if(options.getMyImageSets_().containsKey(setSaveName.getText().toString())){
            Toast.makeText(this, "Image set name already exists! Please enter a new name.", Toast.LENGTH_SHORT).show();
        }else{
            options.getMyImageSets_().put(setSaveName.getText().toString(),BitmapHelper.getInstance());// TODO
            saveSetToInternalStorage();
            updateKeyLists();
        }

    }



    // Source: https://www.tutorialspoint.com/how-to-write-an-image-file-in-internal-storage-in-android
    private void getSavedImageSets() throws IOException {
        myKeys = keys.getAll();
        if(keys.contains("key_1")){
            int numOfSets = 0;
            int imgCount = 0;
            ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
            for(int i = 0; i < keys.getAll().size();++i){
                File dir = contextWrapper.getDir(myKeys.get("key_"+(i+1)).toString(), Context.MODE_PRIVATE);
                File file = new File(dir, "image_" + Integer.toString(++imgCount) + ".jpg");
                String dirName = myKeys.get("key_"+(i+1)).toString();
                options.getMyImageSets_().put(dirName, new BitmapHelper()); // **
                while(file.exists()){
                    Bitmap newBitmap;
                    // Source: https://stackoverflow.com/questions/16804404/create-a-bitmap-drawable-from-file-path
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    newBitmap = BitmapFactory.decodeFile(file.getAbsolutePath(),bitmapOptions);
                    options.getMyImageSets_().get(dirName).setBitmap(newBitmap); // **
                    file = new File(dir, "image_" + Integer.toString(++imgCount) + ".jpg");
                }
                if(!file.exists() && imgCount == 0){
                    options.getMyImageSets_().remove(dirName);
                }
                numOfSets++;
                imgCount = 0;
            }
            numOfImageSets = numOfSets;
        }
    }

    private void saveSetToInternalStorage() throws IOException {
        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File directory = contextWrapper.getDir(setSaveName.getText().toString(), Context.MODE_PRIVATE);
        for(int i = 0; i < BitmapHelper.getInstance().getStored();++i){
            File file = new File(directory, "image_" + Integer.toString(i+1) + ".jpg");
                FileOutputStream outputStream = null;
                outputStream = new FileOutputStream(file);
                BitmapHelper.getInstance().getBitmap(i).compress(Bitmap.CompressFormat.JPEG, 100, outputStream); // TODO
                outputStream.flush();
                outputStream.close();
        }
        //getSavedImageSets();
    }

    private void updateKeyLists(){
        int count = 0;
        keys = getSharedPreferences(KEYS,MODE_PRIVATE);
        editor = keys.edit();
        editor.clear();
        myKeys.clear();
        for(String key : options.getMyImageSets_().keySet()){
            editor.putString("key_"+(++count),key);
        }
        editor.apply();
        editor.commit();
        myKeys = keys.getAll();
    }

    private void setDefaultSaveName(){
        setSaveName.setText("Image_Set_" + (keys.getAll().size()+1));
    }

}