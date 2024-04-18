package com.example.task31c;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // Declare variables
    Button startButton;
    EditText userName;
    Intent quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Link variables to UI elements
        startButton = findViewById(R.id.startButton);
        userName = findViewById(R.id.nameEditText);

        // Set username if main activity is launched again from results page
        userName.setText(getIntent().getStringExtra("userName"));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void launchQuiz(View view){

        String name = userName.getText().toString();

        if (name.isEmpty()){
            // If no answer has been entered, prompt the user
            Toast.makeText(getApplicationContext(), "Please enter your name", Toast.LENGTH_LONG).show();

        } else {
            // Launch quiz
            quiz = new Intent(this, MainActivity2_Quiz.class);
            quiz.putExtra("userName", name);
            startActivity(quiz);
            finish();
        }
    }
}