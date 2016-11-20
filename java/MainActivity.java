package com.example.hrisi.solvingskills2;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataBase questionsDB = new DataBase(getApplicationContext());
        SQLiteDatabase sqdb = questionsDB.getWritableDatabase();
        

        Button startBtn = (Button) findViewById(R.id.startBtn);
        Button quitBtn = (Button) findViewById(R.id.quitBtn);

        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent gameActivity = new Intent(getApplicationContext(), gameActivity.class);
                startActivity(gameActivity);
            }
        });

        quitBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
    }
}
