package com.example.user.billardtrainningapplication;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);

        final ImageButton Start_Button = (ImageButton)findViewById(R.id.button_start);
        Start_Button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    Start_Button.setImageResource(R.drawable.trainningstart_pressed);
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    Start_Button.setImageResource(R.drawable.trainningstart);
                }
                return false;
            }
        });
        final ImageButton Course_Button = (ImageButton)findViewById(R.id.button_coursebook);
        Course_Button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    Course_Button.setImageResource(R.drawable.coursebook_pressed);
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    Course_Button.setImageResource(R.drawable.coursebook);
                }
                return false;
            }
        });
        final ImageButton Score_Button = (ImageButton)findViewById(R.id.button_scorecompute);
        Score_Button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    Score_Button.setImageResource(R.drawable.score_compute_pressed);
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    Score_Button.setImageResource(R.drawable.score_compute);
                }
                return false;
            }
        });

    }

    public void onClick(View view){
        switch (view.getId()) {
            case R.id.button_start:
                GlobalVariable.Menu_Switcher=0;
                Intent Difficulty_intent = new Intent(this, Difficulty.class);
                startActivity(Difficulty_intent);
                break;
            case R.id.button_coursebook:
                GlobalVariable.Menu_Switcher=0;
                Intent Coursebook_intent = new Intent(this, CourseBook_Route.class);
                startActivity(Coursebook_intent);
                break;
            case R.id.button_scorecompute:
                Intent Scorecompute_Intent = new Intent(this,ScoreCompute.class);
                startActivity(Scorecompute_Intent);
                break;
            case R.id.login:
                Intent Login_Intent = new Intent(this,Login.class);
                startActivity(Login_Intent);
                break;

        }
    }
}
