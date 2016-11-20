package com.example.hrisi.solvingskills2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "questions_database.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Tasks";
    private static final String ID = "id";
    private static final String QUESTION = "question";
    private static final String ANSWER = "answer";
    private static final String POINTS = "points";

    private static final String SQL_CREATE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME +
            " (" + ID + " INTEGER  PRIMARY KEY," +
            QUESTION + " VARCHAR(255) NOT NULL UNIQUE," +
            ANSWER + " INTEGER NOT NULL," +
            POINTS + " INTEGER NOT NULL);";

    private static final String SQL_DELETE_TABLE_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("LOG_TAG", "Upgrading DB from version " + oldVersion + " to version " + newVersion);
        db.execSQL(SQL_DELETE_TABLE_QUERY);
        onCreate(db);
    }

}
