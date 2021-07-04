package com.example.group_14_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.group_14_project.model.BitmapHelper;
import com.example.group_14_project.model.Option;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

// This class displays all saved image sets in a list view. It allows the user to load and delete different image sets.
public class SavedImageSets extends AppCompatActivity {

    private static final String KEYS = "com.example.group_14_project.keys";

    private Option options = Option.getInstance();
    private List<BitmapHelper> myHelpers = new ArrayList<BitmapHelper>();
    private Map<String,?> myKeys;
    private int numOfSets = 0;
    private int selectedBitmapHelper = -1;
    private Button delImgSetBut,selImgSetBut;
    private SharedPreferences keys;
    private SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_image_sets);
        getSupportActionBar().setTitle("Image Sets");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        keys = getSharedPreferences(KEYS,MODE_PRIVATE);
        editor = keys.edit();
        myKeys = keys.getAll();
        numOfSets = options.getNumOfImageSets();
        delImgSetBut = findViewById(R.id.deleteImageSetButton);
        selImgSetBut = findViewById(R.id.selectImgSetButton);
        populateHelpers();
        populateListView();
        registerClickCallback();

        delImgSetBut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(selectedBitmapHelper != -1){
                    int numOfPicturesToDelete = options.getMyImageSets_().get(myKeys.get("key_"+(selectedBitmapHelper+1))).getStored();
                    options.getMyImageSets_().remove(myKeys.get("key_"+(selectedBitmapHelper+1)));
                    deleteImageDirectory(numOfPicturesToDelete);
                    updateKeyLists();
                }
            }
        });

        selImgSetBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedImageSet = options.getSelectedImageSet();
                if(selectedImageSet != null && !options.getMyImageSets_().isEmpty()) {
                    BitmapHelper.getInstance().clear();
                    for(int i = 0; i < options.getMyImageSets_().get(selectedImageSet).getStored();++i){
                        BitmapHelper.getInstance().setBitmap(options.getMyImageSets_().get(selectedImageSet).getBitmap(i));
                    }
                    finish();
                }
            }
        });
    }


    private void populateListView() {

        ArrayAdapter<BitmapHelper> adapter = new MyListAdapter();
        ListView list = findViewById(R.id.imageSetsList);
        list.setAdapter(adapter);
    }

    private class MyListAdapter extends ArrayAdapter<BitmapHelper>{
        public MyListAdapter(){
            super(SavedImageSets.this, R.layout.saved_image_sets, myHelpers);
        }

        @Override
        public View getView(int position,  View convertView, ViewGroup parent) {
            View itemView = convertView;
            if(itemView == null){
                itemView = getLayoutInflater().inflate(R.layout.saved_image_sets, parent, false);
            }
            BitmapHelper newHelper = myHelpers.get(position);
            ImageView imageView = itemView.findViewById(R.id.imageSetView);
            //imageView.setImageBitmap(options.getMyImageSets_().get(myKeys.get("key_"+position).toString()).getBitmap(0));
            imageView.setImageBitmap(newHelper.getBitmap(position));

            TextView textView = itemView.findViewById(R.id.imageSetText);
            textView.setText(myKeys.get("key_"+(position+1))+"\t\tImage Count: " + newHelper.getStored());
            return itemView;
        }
    }


    private void registerClickCallback() {
        ListView list = findViewById(R.id.imageSetsList);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                TextView textView = findViewById(R.id.selectedSetLabel);
                textView.setText("Selected Image Set: " + Integer.toString(position+1));
                selectedBitmapHelper = position;
                options.setSelectedImageSet(myKeys.get("key_"+(position+1)).toString());
            }
        });
    }

    private void deleteImageDirectory(int numOfPicturesToDelete){
        String key = myKeys.get("key_"+(selectedBitmapHelper+1)).toString();
        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File dir = contextWrapper.getDir(key, Context.MODE_PRIVATE);
        for(int i = 0; i < numOfPicturesToDelete;++i){
            File file = new File(dir, "image_" + (i+1) + ".jpg");
            if(file.exists()){
                file.delete();
            }
        }
        dir.deleteOnExit();
        finish();
        startActivity(getIntent());
    }

    private void populateHelpers(){
        for(int i = 0; i < Option.getInstance().getMyImageSets_().size();++i){
            String key = myKeys.get("key_"+(i+1)).toString();
            BitmapHelper newHelper = Option.getInstance().getMyImageSets_().get(key);
            myHelpers.add(newHelper);
        }
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
}