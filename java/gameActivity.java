package com.example.hrisi.solvingskills2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class gameActivity extends AppCompatActivity {
    StringBuilder strAnswer = new StringBuilder();
    DataBase questionsDB;
    TasksPOJO task;
    Random rn;
    MediaPlayer music = new MediaPlayer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        final SharedPreferences preferences = getSharedPreferences("HighestScore", getApplicationContext().MODE_PRIVATE);
        final Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        final Button btn1 = (Button) findViewById(R.id.btn1);
        final Button btn2 = (Button) findViewById(R.id.btn2);
        final Button btn3 = (Button) findViewById(R.id.btn3);
        final Button btn4 = (Button) findViewById(R.id.btn4);
        final Button btn5 = (Button) findViewById(R.id.btn5);
        final Button btn6 = (Button) findViewById(R.id.btn6);
        final Button btn7 = (Button) findViewById(R.id.btn7);
        final Button btn8 = (Button) findViewById(R.id.btn8);
        final Button btn9 = (Button) findViewById(R.id.btn9);
        final Button btn0 = (Button) findViewById(R.id.btn0);

        final TextView taskTxt = (TextView) findViewById(R.id.textViewTask);
        final TextView scoreTxt = (TextView) findViewById(R.id.textViewScore);
        final TextView time = (TextView) findViewById(R.id.textViewTime);
        final TextView bestScoreTxt = (TextView) findViewById(R.id.textViewBestScore);

        music = MediaPlayer.create(getApplicationContext(), R.raw.buttonclick);

        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                strAnswer.append("1");
                music.seekTo(0);
                music.start();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                strAnswer.append("2");
                music.seekTo(0);
                music.start();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                strAnswer.append("3");
                music.seekTo(0);
                music.start();
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                strAnswer.append("4");
                music.seekTo(0);
                music.start();
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                strAnswer.append("5");
                music.seekTo(0);
                music.start();
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                strAnswer.append("6");
                music.seekTo(0);
                music.start();
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                strAnswer.append("7");
                music.seekTo(0);
                music.start();
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                strAnswer.append("8");
                music.seekTo(0);
                music.start();
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                strAnswer.append("9");
                music.seekTo(0);
                music.start();
            }
        });

        btn0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                strAnswer.append("0");
                music.seekTo(0);
                music.start();
            }
        });

        questionsDB = new DataBase(getApplicationContext());

        rn = new Random();
        int randomInt = rn.nextInt(4) + 1;
        task = getData(questionsDB, randomInt);
        final int highestScore = preferences.getInt("highScore", 0);

        bestScoreTxt.setText("Best: " + Integer.toString(highestScore));
        taskTxt.setText(task.getQuestion());
        scoreTxt.setText("Score: 0");

        new CountDownTimer(60000, 1000) {
            int scoreCounter = 0;

            @Override
            public void onTick(long millisUntilFinished) {
                time.setText("seconds remaining: " + millisUntilFinished / 1000);
                String correctAnswer = String.valueOf(task.getAnswer());
                if ((strAnswer.toString()).length() == correctAnswer.length()) {
                    if (String.valueOf(task.getAnswer()).equals(strAnswer.toString())) {
                        scoreCounter++;
                        scoreTxt.setText("Score: " + scoreCounter);
                        strAnswer.delete(0, strAnswer.length());
                        int randomInt = rn.nextInt(4) + 1;
                        task = getData(questionsDB, randomInt);
                        taskTxt.setText(task.getQuestion());
                    } else {
                        vibrator.vibrate(700);
                        strAnswer.delete(0, strAnswer.length());
                    }
                } else if (strAnswer.toString().length() > correctAnswer.length()) {
                    strAnswer.delete(0, strAnswer.length());
                }
            }

            @Override
            public void onFinish() {
                time.setText("Seconds remaining: 0");
                if (highestScore < scoreCounter) {
                    SharedPreferences.Editor prefEditor = preferences.edit();
                    prefEditor.putInt("highScore", scoreCounter);
                    prefEditor.commit();
                }
                music.release();
                btn0.setClickable(false);
                btn1.setClickable(false);
                btn2.setClickable(false);
                btn3.setClickable(false);
                btn4.setClickable(false);
                btn5.setClickable(false);
                btn6.setClickable(false);
                btn7.setClickable(false);
                btn8.setClickable(false);
                btn9.setClickable(false);
            }
        }.start();

    }


    //This method gets task from database

    public TasksPOJO getData(DataBase questionsDB, int randomID) {

        TasksPOJO task = new TasksPOJO();
        SQLiteDatabase sqdb = questionsDB.getWritableDatabase();
        Cursor cursor = sqdb.rawQuery("SELECT * FROM Tasks WHERE id=" + randomID, null);
        String question = null;
        int answer = 0;
        int points = 0;
        int id = 0;

        while (cursor.moveToNext()) {
            id = cursor.getInt(cursor.getColumnIndex("id"));
            question = cursor.getString(cursor.getColumnIndex("question"));
            answer = cursor.getInt(cursor.getColumnIndex("answer"));
            points = cursor.getInt(cursor.getColumnIndex("points"));
        }

        task.setId(id);
        task.setQuestion(question);
        task.setAnswer(answer);
        task.setPoints(points);

        return task;
    }


}
