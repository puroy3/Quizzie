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

public class QuestionActivity1 extends AppCompatActivity {
    // Create buttons and radio group.
    Button homeButton;
    Button nextButton;
    RadioGroup radioGroup1;
    // Initialize instance of database helper.
    DatabaseHelper databaseHelper;
    // Set correct answer.
    String correctAnswer = "Option 1: Paris";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question1);
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Initialize buttons and radio group.
        homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(view.getContext(), MainActivity.class);
                view.getContext().startActivity(intent);
            }
        });
        radioGroup1 = findViewById(R.id.radioGroup1);
        // Disable next button until a radio button is pressed.
        nextButton.setEnabled(false);
        radioGroup1.setOnCheckedChangeListener((radioGroup1, checkId) -> {
            if (checkId != -1) {
                nextButton.setEnabled(true);
            }
                }
                );
        nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(view ->  {
            int selectedOption = radioGroup1.getCheckedRadioButtonId();
            if (selectedOption != -1) {
                RadioButton selectedRadioOption = findViewById(selectedOption);
                String selectedAnswerOption = selectedRadioOption.getText().toString();
                saveAnswer(selectedAnswerOption);
            }
            else {
                Toast.makeText(this, "Please select an option!", Toast.LENGTH_LONG).show();
            }
            });
            private void saveAnswer(String selectedAnswerOption) {
                // Save answer in SQLite database.
            SQLiteDatabase database = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.COLUMN_QUESTION_NUMBER, "1");
            values.put(DatabaseHelper.COLUMN_USER_ANSWER, selectedAnswerOption);
            values.put(DatabaseHelper.COLUMN_CORRECT_ANSWER, correctAnswer);
            database.insert(DatabaseHelper.TABLE_NAME, null, values);
            database.close();
                Intent intent;
                intent = new Intent(view.getContext(), QuestionActivity2.class);
                view.getContext().startActivity(intent);
        }
    }