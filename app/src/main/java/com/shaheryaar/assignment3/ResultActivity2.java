package com.shaheryaar.assignment3;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity2 extends AppCompatActivity {

    TextView resultView;
    Button btnRestart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result2);
        resultView=findViewById(R.id.resultView);

        btnRestart = findViewById(R.id.btnRestart);

        String s=getIntent().getStringExtra("Result");
        resultView.setText(s);

        btnRestart.setOnClickListener(v -> {
            Intent in = new Intent(getApplicationContext(),MainActivity2.class);
            startActivity(in);
        });
    }
}