package com.example.quizzie;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Create the name of the database.
    private static final String DATABASE_NAME = "database1.db";
    // Create database version.
    private static final int DATABASE_VERSION = 1;
    // Create table name.
    public static final String TABLE_NAME = "quizResults";
    // Create table columns.
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_QUESTION_NUMBER = "questionNumber";
    public static final String COLUMN_USER_ANSWER = "userAnswer";
    public static final String COLUMN_CORRECT_ANSWER = "correctAnswer";
    // Create SQL Query.
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_CORRECT_ANSWER + " TEXT,"
            + COLUMN_USER_ANSWER + " TEXT," + COLUMN_CORRECT_ANSWER + " TEXT," + ");";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the table.
        db.execSQL(CREATE_TABLE);
    }
    // Replace table by dropping and creating.
    public void onUpgrade(SQLiteDatabase db, int olderVersion, int newerVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
