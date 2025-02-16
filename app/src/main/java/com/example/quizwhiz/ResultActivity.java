package com.example.quizwhiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizwhiz.R;

public class ResultActivity extends AppCompatActivity {
    TextView tvScore;
    Button btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvScore = findViewById(R.id.tvScore);
        btnShare = findViewById(R.id.btnShare);

        int score = getIntent().getIntExtra("SCORE", 0);
        String userName = getIntent().getStringExtra("USER_NAME");

        tvScore.setText(userName + ", your score is: " + score);

        btnShare.setOnClickListener(v -> {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "I scored " + score + " in the quiz!");
            startActivity(Intent.createChooser(shareIntent, "Share via"));
        });
    }
}
