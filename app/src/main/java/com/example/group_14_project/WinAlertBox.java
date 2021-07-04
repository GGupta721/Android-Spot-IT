package com.example.group_14_project;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.group_14_project.model.Option;
import com.example.group_14_project.model.PlayerScore;
//this class is to setup a dialog box for the user to enter their name when the user finish playing the game
public class WinAlertBox extends AppCompatDialogFragment {

    String nickName,currentSharedPreferences;
    Option options;
    int time;
    PlayerScore newScore;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    private static final String[] TIME_KEYS = {"t1","t2","t3","t4","t5"};
    private static final String[] NAME_DATE_KEYS = {"nd1","nd2","nd3","nd4","nd5"};

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {



    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.win_message_layout, null);
        options = Option.getInstance();
        currentSharedPreferences = options.getSharedPreferenceKey();
        pref = getContext().getSharedPreferences(currentSharedPreferences,Context.MODE_PRIVATE);
        editor = pref.edit();
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                EditText nicknameEntry = view.findViewById(R.id.nickNameInput);
                String name = nicknameEntry.getText().toString();
                addScoreToHighScores(name);
                pullScoresToSharedPreferences();
                getActivity().finish();
            }
        };

        return new AlertDialog.Builder(getActivity()).setTitle("You did it !!!")
                .setView(view)
                .setPositiveButton(android.R.string.ok, listener)
                .create();
    }

    private void addScoreToHighScores(String nickname){
        newScore = new PlayerScore();
        newScore.createNewPlayerScore(time,nickname);
        options.addHighScore(newScore);
    }


    public void setTime(int time){
        this.time = time;
    }

    private void pullScoresToSharedPreferences() {
        for(int i = 0; i < options.NUM_OF_HIGH_SCORES;++i){
            editor.putInt(TIME_KEYS[i],options.getScoreAt(i).getTime());
            editor.putString(NAME_DATE_KEYS[i], options.getScoreAt(i).getNameDate());
        }
        editor.apply();
        editor.commit();
    }

}
