package com.example.task31c;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2_Quiz extends AppCompatActivity {

    String userName;
    TextView welcomeText;
    TextView progressText;
    ProgressBar progressBar;
    TextView questionText;
    RadioGroup radioGroup;
    RadioButton option1;
    RadioButton option2;
    RadioButton option3;
    Button submit;
    TextView scoreDisplay;
    int totalQuestions;
    int currentQuestion = 0;
    int score = 0;
    String nextAction = "Submit";
    Intent result;

    List<Question> quiz = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_activity2_quiz);

        // Create quiz
        createQuiz();

        // Link variables to UI elements
        welcomeText = findViewById(R.id.welcomeText);
        progressText = findViewById(R.id.progressText);
        progressBar = findViewById(R.id.progressBar);
        questionText = findViewById(R.id.questionText);
        radioGroup = findViewById(R.id.radioGroup);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        submit = findViewById(R.id.submitButton);
        scoreDisplay = findViewById(R.id.scoreDisplayText);

        // Set User name
        userName = getIntent().getStringExtra("userName");
        welcomeText.setText("Welcome " + userName);

        // Initialise variables
        totalQuestions = quiz.size();

        updateQuestion();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void createQuiz(){
        // Set up quiz questions
        Question question1 = new Question("Who wrote Pride and Prejudice?","Jane Austen","Charlotte Bronte","Anne Bronte",0);
        Question question2 = new Question("Who wrote To Kill a Mockingbird?","Virginia Woolf","Harper Lee","Sylvia Plath",1);
        Question question3 = new Question("Who wrote Bleak House?","Charles Dickens","Thomas Hardy","Alexandre Dumas",0);
        Question question4 = new Question("Who wrote Rebecca?","Margery Allingham","Elizabeth Taylor","Daphne du Maurier",2);
        Question question5 = new Question("Who wrote War of the Worlds?","Jules Verne","H.G. Wells","John Wyndham",1);

        quiz.add(question1);
        quiz.add(question2);
        quiz.add(question3);
        quiz.add(question4);
        quiz.add(question5);
    }

    public void updateQuestion(){
        // Update display to show next question
        progressBar.setProgress((currentQuestion +1) * 100 / totalQuestions);
        questionText.setText(quiz.get(currentQuestion).getQuestion());
        option1.setText(quiz.get(currentQuestion).getOption1());
        option2.setText(quiz.get(currentQuestion).getOption2());
        option3.setText(quiz.get(currentQuestion).getOption3());

        // Reset background colours
        option1.setBackgroundColor(Color.rgb(255,255,255));
        option2.setBackgroundColor(Color.rgb(255,255,255));
        option3.setBackgroundColor(Color.rgb(255,255,255));
        // Remove previously selected answer
        radioGroup.clearCheck();

        // Update progress
        progressText.setText("Question " + (currentQuestion+1) + "/" + totalQuestions);

        // Re set submit button
        submit.setText("Submit");
        nextAction = "Submit";
    }

    public void progressQuiz(View view){
        // Proceed to next question
        if (radioGroup.getCheckedRadioButtonId() == -1)
        {
            // If no answer has been entered, prompt the user
            Toast.makeText(getApplicationContext(), "Please select an answer", Toast.LENGTH_SHORT).show();
        } else {
            if (nextAction == "Submit") {
                // If on submit screen, submit answer
                submitAnswer(view);
            } else if (nextAction == "Next") {
                // If viewing anwer, move to next question
                currentQuestion = currentQuestion + 1;
                updateQuestion();
            } else {
                // Quiz finished, launch end results
                result = new Intent(this, MainActivity3_Result.class);
                result.putExtra("userName", userName);
                result.putExtra("finalScore", score);
                result.putExtra("numQuestions", totalQuestions);
                startActivity(result);
                finish();
            }
        }
    }

    public void submitAnswer(View view){
        // Submit and check answer
        int selectedAnswerId = radioGroup.getCheckedRadioButtonId();

        // Find selected answer
        RadioButton selectedAnswer = radioGroup.findViewById(selectedAnswerId);
        int index = radioGroup.indexOfChild(selectedAnswer);

        // Retrieve correct answer
        int answer = quiz.get(currentQuestion).getAnswer();

        if (answer == index){
            // Answer correct, highlight in green and update score
            selectedAnswer.setBackgroundColor(Color.rgb(134,240,148));
            score = score + 1;
            scoreDisplay.setText("Score: " + score);
        } else {
            // Answer incorrect, highlight selected answer in red, correct answer in green
            selectedAnswer.setBackgroundColor(Color.rgb(240,134,138));
            RadioButton correctAnswer = (RadioButton) radioGroup.getChildAt(answer);
            correctAnswer.setBackgroundColor(Color.rgb(134,240,148));
        }

        // Set next action
        if (currentQuestion +1 == totalQuestions) {
            // All question answered, next screen go to results
            submit.setText("See Results");
            nextAction = "See Results";
        } else {
            // More questiosn to answer, next screen new question
            submit.setText("Next");
            nextAction = "Next";
        }
    }
}