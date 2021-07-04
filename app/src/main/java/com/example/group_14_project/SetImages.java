package com.example.group_14_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.group_14_project.model.BitmapHelper;
import com.example.group_14_project.model.Option;
import com.example.group_14_project.model.PlayerScore;

import java.util.Map;
//this class is used to set the image set the user wants to use
public class SetImages extends AppCompatActivity {

    static final int NUM_OF_THEMES = 2;
    static final int ANIMAL_SELECT_INDEX = 0;
    static final int FRUIT_SELECT_INDEX = 1;
    static final int FLICKR_SELECT_INDEX = 2;
    private static final String[] TIME_KEYS = {"t1","t2","t3","t4","t5"};
    private static final String[] NAME_DATE_KEYS = {"nd1","nd2","nd3","nd4","nd5"};
    private static final String MAIN = "com.example.group_14_project.main";



    private RadioGroup radio;
    private ImageView image;
    private int flickrRadioId = 0;
    private Integer []photo = {R.drawable.animalset, R.drawable.fruits, R.drawable.flickr};

    private String name,currentSharedPreference;
    private EditText nameInput;
    private Button viewHighScores, resetHighScoresButton, applyButton, searchImagesOnFlickr;
    private Switch wordOptionsButton;
    private Spinner orderSelectionSpinner, drawPileSelectionSpinner;
    private Option options;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private int themeChoice = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_images);


        //toolbar backbutton
        getSupportActionBar().setTitle("Set of Images");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        options = Option.getInstance();

        //display the theme of images when clicked
        image = findViewById(R.id.photo);
        radio = findViewById(R.id.radiogroup);

        updateHighScoresSharedPreferences();

        // toolbar
        getSupportActionBar().setTitle("Options");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        resetHighScoresButton = findViewById(R.id.resetHighScoresButton);
        viewHighScores = findViewById(R.id.highScoresButton);
        applyButton = findViewById(R.id.setImageButton);
        wordOptionsButton = findViewById(R.id.wordOptionButton);
        wordOptionsButton.setChecked(options.getWordMode());
        orderSelectionSpinner = findViewById(R.id.orderSelectionSpinner);
        ArrayAdapter<String> ordersAdapter = new ArrayAdapter<String>(SetImages.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.orders));
        ordersAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderSelectionSpinner.setAdapter(ordersAdapter);
        orderSelectionSpinner.setSelection(options.getOrderAsIntCode());
        drawPileSelectionSpinner = findViewById(R.id.drawpileSelectionSpinner);
        ArrayAdapter<String> drawPileAdapter = new ArrayAdapter<String>(SetImages.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.drawPiles));
        drawPileAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        drawPileSelectionSpinner.setAdapter(drawPileAdapter);
        drawPileSelectionSpinner.setSelection(options.getDrawPileAsIntCode());
        searchImagesOnFlickr = findViewById(R.id.createImageSetButton);

        setListeners();
    }

    private void pullScoresToSharedPreferences() {
        for(int i = 0; i < options.NUM_OF_HIGH_SCORES;++i){
            editor.putInt(TIME_KEYS[i],options.getScoreAt(i).getTime());
            editor.putString(NAME_DATE_KEYS[i], options.getScoreAt(i).getNameDate());
        }
        editor.apply();
        editor.commit();
    }

    private void pullScoresToOptions() {
        PlayerScore[] scores = new PlayerScore[options.NUM_OF_HIGH_SCORES];
        Map<String,?> myPrefs = pref.getAll();
        for(int i = 0; i < options.NUM_OF_HIGH_SCORES;++i){
            scores[i] = new PlayerScore(Integer.parseInt(myPrefs.get(TIME_KEYS[i]).toString()),
                    myPrefs.get(NAME_DATE_KEYS[i]).toString());
        }
        options.setHighScores(scores);
    }

    private void goToHighScores() {
        updateHighScoresSharedPreferences();
        Intent intent = new Intent(SetImages.this, HighScoresActivity.class);
        startActivity(intent);
    }

    private void goToFlicker(){
        Intent intent = new Intent(SetImages.this,FlickrEdit.class);
        startActivity(intent);
    }

    private void toastChangeError(){
        Toast.makeText(getApplicationContext(),
                "Draw pile amount is incompatible with order!",
                Toast.LENGTH_SHORT).show();
    }

    public boolean hasPreviousSharedPref(){
        if(pref.contains("t1")){
            return true;
        }
        return false;
    }

    public void updateHighScoresSharedPreferences(){
        currentSharedPreference = options.getSharedPreferenceKey();
        pref = getSharedPreferences(currentSharedPreference, Context.MODE_PRIVATE);
        editor = pref.edit();
        if(hasPreviousSharedPref()){
            pullScoresToOptions();
        }else{
            options.initializeDefaultScores();
            pullScoresToSharedPreferences();
        }
    }


    public void updateFlickrSet(){
        Log.d("RUN","Flickr set number: " + options.getFlickrSetNumber() + " max:" + options.getMaxOrderCardNum());
        if(options.hasFlickerSet() && options.getFlickrSetNumber() != options.getMaxOrderCardNum()){
            options.setAsNoFlickrSet();
            if(options.isFlickr()){
                options.setAsAnimal();
            }
            Toast.makeText(getApplicationContext(),"Warning! Flickr set does not match order size.",Toast.LENGTH_SHORT).show();
        }else if(!options.hasFlickerSet() && options.getFlickrSetNumber() == options.getMaxOrderCardNum()){
            options.setAsHasFlickrSet();
        }
    }

    private void storeOptionsToSharedPreferences() {
        pref = getSharedPreferences(MAIN, Context.MODE_PRIVATE);
        editor = pref.edit();
        editor.putInt("order",options.getOrderAsInt()-1);
        editor.putInt("drawPile",options.getDrawPileAsInt());
        editor.apply();
        editor.commit();
    }

    private void setListeners(){

        searchImagesOnFlickr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToFlicker();
            }
        });

        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radiobtn = radioGroup.findViewById(i);
                int index = radioGroup.indexOfChild(radiobtn);
                if(index == 2 && !options.hasFlickerSet()){
                    radiobtn.setChecked(false);
                    Toast.makeText(getApplicationContext(),"Flickr image set size is invalid!", Toast.LENGTH_SHORT).show();
                }else{
                    if(options.getWordMode() && index == 2){
                        flickrRadioId = radiobtn.getId();
                        options.setWordModeOff();
                        wordOptionsButton.setChecked(false);
                    }
                    if(index == 2){
                        options.setFlickrSetEnabled();
                        image.setImageBitmap(BitmapHelper.getInstance().getBitmap(0));
                    }else{
                        options.setFlickrSetDisabled();
                        image.setImageResource(photo[index]);
                    }

                    themeChoice = index;
                }
            }
        });
        resetHighScoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                options.initializeDefaultScores();
                pullScoresToSharedPreferences();
                Toast.makeText(getApplicationContext(),"High scores has been reset!",Toast.LENGTH_SHORT).show();
            }
        });
        viewHighScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToHighScores();
            }
        });
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(themeChoice == ANIMAL_SELECT_INDEX){
                    options.setAsAnimal();
                }else if(themeChoice == FRUIT_SELECT_INDEX){
                    options.setAsFood();
                }else if(themeChoice == FLICKR_SELECT_INDEX){
                    if(options.getMaxOrderCardNum() == options.getFlickrSetNumber()){
                        options.setAsFlickr();
                    }else{
                        Toast.makeText(getApplicationContext(),"You must have a valid Flickr image set!", Toast.LENGTH_SHORT).show();
                    }

                }
                Toast.makeText(getApplicationContext(),
                        "Theme set to " + options.getThemeChoiceAsString() + "!",
                        Toast.LENGTH_SHORT).show();
            }
        });
        wordOptionsButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    if(!options.getFlickrSetEnabled()){
                        options.setWordModeOn();
                        Toast.makeText(getApplicationContext(),"Word mode enabled.", Toast.LENGTH_SHORT).show();
                    }else{
                        compoundButton.setChecked(false);
                        Toast.makeText(getApplicationContext(), "Cannot use word mode with Flickr image set.", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    options.setWordModeOff();
                    Toast.makeText(getApplicationContext(),"Word mode disabled.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        orderSelectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i){
                    case 0: // order 2
                        options.setOrderToTwo();
                        storeOptionsToSharedPreferences();
                        if(options.getDrawPileAsIntCode() > 0 && options.getDrawPileAsIntCode() != 4){
                            options.setDrawPileToFive();
                            drawPileSelectionSpinner.setSelection(options.getDrawPileAsIntCode());
                            updateHighScoresSharedPreferences();
                            toastChangeError();
                        }
                        updateFlickrSet();
                        break;
                    case 1: // order 3
                        options.setOrderToThree();
                        storeOptionsToSharedPreferences();
                        if(options.getDrawPileAsIntCode() > 1 && options.getDrawPileAsIntCode() != 4){
                            options.setDrawPileToTen();
                            drawPileSelectionSpinner.setSelection(options.getDrawPileAsIntCode());
                            updateHighScoresSharedPreferences();
                            toastChangeError();
                        }
                        updateFlickrSet();
                        break;
                    case 2: // order 5
                        options.setOrderToFive();
                        storeOptionsToSharedPreferences();
                        updateHighScoresSharedPreferences();
                        updateFlickrSet();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        drawPileSelectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i){
                    case 0: // 5
                        options.setDrawPileToFive();
                        storeOptionsToSharedPreferences();
                        updateHighScoresSharedPreferences();
                        break;
                    case 1: // 10
                        System.out.println(options.getOrderAsIntCode());
                        if(options.getOrderAsIntCode() >= i){
                            options.setDrawPileToTen();
                            storeOptionsToSharedPreferences();
                            updateHighScoresSharedPreferences();
                        }else{
                            drawPileSelectionSpinner.setSelection(options.getDrawPileAsIntCode());
                            toastChangeError();
                        }
                        break;
                    case 2: // 15
                        if(options.getOrderAsIntCode() >= i){
                            options.setDrawPileToFifteen();
                            storeOptionsToSharedPreferences();
                            updateHighScoresSharedPreferences();
                        }else{
                            drawPileSelectionSpinner.setSelection(options.getDrawPileAsIntCode());
                            toastChangeError();
                        }
                        break;
                    case 3: // 20
                        if(options.getOrderAsIntCode() == i-1){
                            options.setDrawPileToTwenty();
                            storeOptionsToSharedPreferences();
                            updateHighScoresSharedPreferences();
                        }else{
                            drawPileSelectionSpinner.setSelection(options.getDrawPileAsIntCode());
                            toastChangeError();
                        }
                        break;
                    case 4: // all
                        options.setDrawPileToAll();
                        storeOptionsToSharedPreferences();
                        updateHighScoresSharedPreferences();
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}