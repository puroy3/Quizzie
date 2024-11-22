package com.example.quizzie;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultsActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    TableLayout tableLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_results);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        databaseHelper = new DatabaseHelper(this);
        tableLayout = findViewById(R.id.resultsTable);
        displayResultsTable();
    }
    private void displayResultsTable() {
        // Get the database which is readable.
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        // Define the columns to get.
        String[] projection = {
                DatabaseHelper.COLUMN_QUESTION_NUMBER,
                DatabaseHelper.COLUMN_USER_ANSWER,
                DatabaseHelper.COLUMN_CORRECT_ANSWER
        };
        // Query database.
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, projection, null,
                null, null, null, null);
        // Iterate through quiz results and add them to TableLayout to be displayed.
        while (cursor.moveToNext()) {
            String questionNumber = cursor.getString(cursor.getColumnIndexOrThrow(
                    DatabaseHelper.COLUMN_QUESTION_NUMBER
            ));
            String userAnswer = cursor.getString(cursor.getColumnIndexOrThrow(
                    DatabaseHelper.COLUMN_USER_ANSWER
            ));
            String correctAnswer = cursor.getString(cursor.getColumnIndexOrThrow(
                    DatabaseHelper.COLUMN_CORRECT_ANSWER
            ));
            // Create a TableRow.
            TableRow tableRow = new TableRow(this);
            // Create TextViews per column.
            TextView questionView = new TextView(this);
            questionView.setText(questionNumber);
            TextView userView = new TextView(this);
            userView.setText(userAnswer);
            TextView correctView = new TextView(this);
            correctView.setText(correctAnswer);
            // Add the text views to the table row.
            tableRow.addView(questionView);
            tableRow.addView(userView);
            tableRow.addView(correctView);
            // Add Table Row to Table Layout.
            tableLayout.addView(tableRow);
        }
        cursor.close();
        database.close();
    }
}