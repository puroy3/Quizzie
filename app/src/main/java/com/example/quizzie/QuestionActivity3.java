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

public class QuestionActivity3 extends AppCompatActivity {
    Button homeButton3;
    Button nextButton3;
    RadioGroup radioGroup3;
    DatabaseHelper databaseHelper;
    String correctAnswer = "Option 3: Ottawa";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        homeButton3 = findViewById(R.id.homeButton3);
        homeButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(view.getContext(), MainActivity.class);
                view.getContext().startActivity(intent);
            }
        });
        radioGroup3 = findViewById(R.id.radioGroup3);
        nextButton3.setEnabled(false);
        radioGroup3.setOnCheckedChangeListener((radioGroup3, checkId) -> {
            if (checkId != -1) {
                nextButton3.setEnabled(true);
            }
        });
        nextButton3 = findViewById(R.id.nextButton3);
        nextButton3.setOnClickListener(view -> {
            int selectedOption = radioGroup3.getCheckedRadioButtonId();
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
        values.put(DatabaseHelper.COLUMN_QUESTION_NUMBER, "3");
        values.put(DatabaseHelper.COLUMN_USER_ANSWER, selectedAnswerOption);
        values.put(DatabaseHelper.COLUMN_CORRECT_ANSWER, correctAnswer);
        database.insert(DatabaseHelper.TABLE_NAME, null, values);
        database.close();
        nextButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(view.getContext(), ResultsActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }
}