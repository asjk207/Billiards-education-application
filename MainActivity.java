package com.example.user.billardtrainningapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);


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

        }
    }
}
