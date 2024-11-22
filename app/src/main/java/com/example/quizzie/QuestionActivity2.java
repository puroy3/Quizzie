package com.example.quizzie;
import java.util.*;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class QuestionActivity2 extends AppCompatActivity {
    Button homeButton2;
    Button nextButton2;
    RadioGroup radioGroup2;
    DatabaseHelper databaseHelper;
    String correctAnswer = "New Delhi";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question2);
        databaseHelper = new DatabaseHelper(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        homeButton2 = findViewById(R.id.homeButton2);
        homeButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(view.getContext(), MainActivity.class);
                view.getContext().startActivity(intent);
            }
        });
        radioGroup2 = findViewById(R.id.radioGroup2);
        nextButton2 = findViewById(R.id.nextButton2);
        nextButton2.setEnabled(false);
        radioGroup2.setOnCheckedChangeListener((radioGroup2, checkId) -> {
                    if (checkId != -1) {
                        nextButton2.setEnabled(true);
                    }
                }
        );
        nextButton2.setOnClickListener(view -> {
            int selectedOption = radioGroup2.getCheckedRadioButtonId();
            if (selectedOption != -1) {
                RadioButton selectedRadioOption = findViewById(selectedOption);
                String selectedAnswerOption = selectedRadioOption.getText().toString();
                saveAnswer(selectedAnswerOption);
            } else {
                Toast.makeText(this, "Please select an option!", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void saveAnswer (String selectedAnswerOption) {
        // Save answer in SQLite database.
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_QUESTION_NUMBER, "2");
        values.put(DatabaseHelper.COLUMN_USER_ANSWER, selectedAnswerOption);
        values.put(DatabaseHelper.COLUMN_CORRECT_ANSWER, correctAnswer);
        database.insert(DatabaseHelper.TABLE_NAME, null, values);
        database.close();
        Intent intent = new Intent(QuestionActivity2.this, QuestionActivity3.class);
        startActivity(intent);
    }
}