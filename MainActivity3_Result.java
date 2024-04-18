package com.example.task31c;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.widget.Button;

public class MainActivity3_Result extends AppCompatActivity {

    String userName;
    TextView congratulations;
    TextView score;
    int finalScore;
    int totalQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_activity3_result);

        // Link variables to UI elements
        congratulations = findViewById(R.id.congratulationsText);
        score = findViewById(R.id.scoreText);

        // Set variable values
        userName = getIntent().getStringExtra("userName");
        finalScore = getIntent().getIntExtra("finalScore",0);
        totalQuestions = getIntent().getIntExtra("numQuestions",0);
        congratulations.setText("Congratulations " + userName + "!");
        score.setText("YOUR SCORE: " + finalScore + "/" + totalQuestions);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void newQuiz (View view){
        // Launch a new instance of the main app page
        Intent restart = new Intent(this, MainActivity.class);
        restart.putExtra("userName", userName);
        startActivity(restart);
        finish();
    }

    public void exit (View view){
        // Close app
        finish();
    }
}