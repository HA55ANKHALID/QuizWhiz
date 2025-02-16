package com.example.quizwhiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText etName;
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        btnStart = findViewById(R.id.btnStart);

        btnStart.setOnClickListener(v -> {
            String name = etName.getText().toString();
            if (!name.isEmpty()) {
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                intent.putExtra("USER_NAME", name); // Passing data
                startActivity(intent);
            } else {
                etName.setError("Enter your name");
            }
        });
    }
}
