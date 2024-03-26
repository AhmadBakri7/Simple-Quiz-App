package com.example.a1201509_ahmad_bakri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result_Activity extends AppCompatActivity {

    TextView FeedBack, ResultScore; // Two text view, one for tou lose or win, one for the result
    Button Reset; // Button for reset the quiz again


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        FeedBack = findViewById(R.id.feedback_id);
        ResultScore = findViewById(R.id.score_result);
        Reset = findViewById(R.id.reset_button);


        int Score = getIntent().getIntExtra("SCORE", 0);

        if (Score >= 4) {
            FeedBack.setText("You Won!");
        } else {
            FeedBack.setText("You Lost!");
        }

        ResultScore.setText(Score + "/5");

        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Result_Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}