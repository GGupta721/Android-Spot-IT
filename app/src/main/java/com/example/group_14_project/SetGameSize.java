package com.example.group_14_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
//this class is used to set the size of the game
public class SetGameSize extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_game_size);

        createGameSizeRB();
        int savedGameOrder = getGameOrderSelected(this);
        createGameOrderRB();
        String savedGameSize = getGameSizeSelected(this);
        setConfirmButton();
        
    }

    private void setConfirmButton() {
        Button btn = (Button) findViewById(R.id.confirm);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateResponses();
                finish();
            }
        });
    }

    private void createGameOrderRB() {
        RadioGroup group = (RadioGroup) findViewById(R.id.gameOrder);
        int[] gameOrderList = getResources().getIntArray(R.array.order_options);

        for(int i = 0;i<gameOrderList.length; i++){
            int gameOrder = gameOrderList[i];
            RadioButton btn = new RadioButton(this);
            btn.setText(String.format("%s Order ", gameOrder));

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(SetGameSize.this, "You selected : "+gameOrder, Toast.LENGTH_SHORT ).show();
                    saveGameOrderSelected(gameOrder);
                }
            });

            group.addView(btn);

            if(gameOrder == getGameOrderSelected(this)){
                btn.setChecked(true);
            }
        }
    }

    private void saveGameOrderSelected(int gameOrder) {
        SharedPreferences prefs = this.getSharedPreferences("OrderPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("Game order selected", gameOrder);
        editor.apply();
    }

    static public int getGameOrderSelected(Context context){
        SharedPreferences prefs = context.getSharedPreferences("OrderPrefs", MODE_PRIVATE);
        return prefs.getInt("Game order selected", 3);

    }

    private void createGameSizeRB() {
        RadioGroup group = (RadioGroup) findViewById(R.id.gameSize);

        String[] gameSizeList = getResources().getStringArray(R.array.game_size_options);

        for(int i = 0;i<gameSizeList.length; i++){
            String gameSize = gameSizeList[i];
            RadioButton btn = new RadioButton(this);
            btn.setText(String.format("%s Cards ", gameSize));

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(SetGameSize.this, "You selected : "+gameSize, Toast.LENGTH_SHORT ).show();
                    saveGameSizeSelected(gameSize);


                }
            });

            group.addView(btn);

            if(gameSize.equals(getGameSizeSelected(this))){
                btn.setChecked(true);
            }
        }

    }

    private void saveGameSizeSelected(String gameSize) {
        SharedPreferences prefs = this.getSharedPreferences("SizePrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("Game size selected", gameSize);
        editor.apply();
    }

    static public String getGameSizeSelected(Context context){
        SharedPreferences prefs = context.getSharedPreferences("SizePrefs", MODE_PRIVATE);
        return prefs.getString("Game size selected", "5");

    }


    private void validateResponses() {


    }
}