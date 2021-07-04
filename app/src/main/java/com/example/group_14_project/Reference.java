package com.example.group_14_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
//this class handle the hyperlink in the reference screen
public class Reference extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reference);

        getSupportActionBar().setTitle("Reference");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView text = (TextView) findViewById(R.id.link1);
        text.setMovementMethod(LinkMovementMethod.getInstance());
        TextView text0 = (TextView) findViewById(R.id.link2);
        text0.setMovementMethod(LinkMovementMethod.getInstance());
        TextView text1 = (TextView) findViewById(R.id.link3);
        text1.setMovementMethod(LinkMovementMethod.getInstance());
        TextView text2 = (TextView) findViewById(R.id.link4);
        text2.setMovementMethod(LinkMovementMethod.getInstance());
    }
}