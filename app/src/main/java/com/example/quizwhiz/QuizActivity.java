package com.example.quizwhiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizwhiz.R;

public class QuizActivity extends AppCompatActivity {
    TextView tvQuestion;
    RadioGroup rgOptions;
    Button btnNext, btnPrev;
    String userName;

    int currentQuestionIndex = 0;
    int score = 0;

    // Sample MCQs
    String[] questions = {
            "What is the capital of France?",
            "What is 2 + 2?",
            "Which planet is known as the Red Planet?"
    };

    String[][] options = {
            {"Paris", "London", "Berlin", "Madrid"},
            {"3", "4", "5", "6"},
            {"Earth", "Mars", "Jupiter", "Venus"}
    };

    int[] answers = {0, 1, 1}; // Correct answers index

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        tvQuestion = findViewById(R.id.tvQuestion);
        rgOptions = findViewById(R.id.rgOptions);
        btnNext = findViewById(R.id.btnNext);
        btnPrev = findViewById(R.id.btnPrev);

        // Get user name from intent
        userName = getIntent().getStringExtra("USER_NAME");

        loadQuestion();

        btnNext.setOnClickListener(v -> {
            checkAnswer();
            if (currentQuestionIndex < questions.length - 1) {
                currentQuestionIndex++;
                loadQuestion();
            } else {
                Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                intent.putExtra("SCORE", score);
                intent.putExtra("USER_NAME", userName);
                startActivity(intent);
                finish();
            }
        });

        btnPrev.setOnClickListener(v -> {
            if (currentQuestionIndex > 0) {
                currentQuestionIndex--;
                loadQuestion();
            }
        });
    }

    void loadQuestion() {
        rgOptions.clearCheck();
        tvQuestion.setText(questions[currentQuestionIndex]);
        for (int i = 0; i < 4; i++) {
            ((RadioButton) rgOptions.getChildAt(i)).setText(options[currentQuestionIndex][i]);
        }
        btnPrev.setEnabled(currentQuestionIndex > 0);
    }

    void checkAnswer() {
        int selectedId = rgOptions.getCheckedRadioButtonId();
        if (selectedId != -1) {
            int answerIndex = rgOptions.indexOfChild(findViewById(selectedId));
            if (answerIndex == answers[currentQuestionIndex]) {
                score++;
            }
        }
    }
}
