package com.example.a1201509_ahmad_bakri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    // Array of strings that contain the questions
    String[] Ques = {"How many sides does a triangle have?",
            "What is the square root of 25?",
            "What is the capital city of palestine?",
            "How many meters are in a kilometer?",
            "What is the chemical symbol for potassium?"};

    int Index = 0; // indicate  index for array of strings (Question's)
    int ScoreResult = 0; // initialize score by zero

    TextView score, timer, question, NameId; // Three text views in the main activity
    CountDownTimer TimerCount; // For count descending from 10 to zero

    // Array of strings that contain the choices for each question in the above array
    String[][] choices = {
            {"2", "3", "1", "4"},
            {"6", "7", "5", "3"},
            {"Hebron", "Ramallah", "Jerusalem", "Nablus"},
            {"200", "300", "500", "1000"},
            {"k", "p", "u", "o"}

    };

    Button ch1, ch2, ch3, ch4; // 4 choices buttons for each question
    int[] RightAnswer = {1, 2, 2, 3, 0}; // Right answer for each question

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        score = findViewById(R.id.score_id);
        timer = findViewById(R.id.Timer_id);
        question = findViewById(R.id.Question_id);
        NameId = findViewById(R.id.Name_Id);


        ch1 = findViewById(R.id.Option1);
        ch2 = findViewById(R.id.Option2);
        ch3 = findViewById(R.id.Option3);
        ch4 = findViewById(R.id.Option4);


        Insert(); // Function to insert questions

        ch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                examineChoice(choices[Index][0]);
            }
        });

        ch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                examineChoice(choices[Index][1]);
            }
        });

        ch3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                examineChoice(choices[Index][2]);
            }
        });

        ch4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                examineChoice(choices[Index][3]);
            }
        });


    }

    // This function to compare between chioce and the right answer, if true increment score by one
    private void examineChoice(String choice) {
        int rightIndex = RightAnswer[Index];
        String RightAnswer = choices[Index][rightIndex];

        if (choice.equals(RightAnswer)) {
            ScoreResult++;
            score.setText("Score: " + ScoreResult);
        }
        loadNext(); // The following question
    }

    private void loadNext() {
        Index++;
        Insert();
    }

    // This function to insert questions with options if we not exceed the number of questions
    private void Insert() {
        if (Index < Ques.length) {
            question.setText("Q"+ String.valueOf(Index+1) + ": "  +Ques[Index]);
            ch1.setText(choices[Index][0]);
            ch2.setText(choices[Index][1]);
            ch3.setText(choices[Index][2]);
            ch4.setText(choices[Index][3]);
            CountingTimer();
        }
        else {
            TimerCount.cancel();
            Intent intent = new Intent(MainActivity.this, Result_Activity.class);
            intent.putExtra("SCORE", ScoreResult);
            startActivity(intent);
            finish();
        }
    }

    // This function for counting from 10 to zero for each question
    private void CountingTimer() {

        if (TimerCount != null) {
            TimerCount.cancel();
        }

        TimerCount = new CountDownTimer(11000, 1000) {
            public void onTick(long milliTime) {
                timer.setText("Seconds remaining: " + milliTime / 1000);
            }

            public void onFinish() {
                loadNext();
            }
        }.start();
    }



}