package com.shaheryaar.assignment3;;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toast.makeText(getApplicationContext(),"About is Clicked", Toast.LENGTH_LONG).show();

    }
}