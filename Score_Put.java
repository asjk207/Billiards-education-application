package com.example.user.billardtrainningapplication;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

/**
 * Created by user on 2017-04-12.
 */

public class Score_Put extends AppCompatActivity {

    TextView Score_Text;
    int Score;
    String Score_string;

    Button Score_Check_Button;
    Button Score_Cancel_Button;

    Intent Score_Result_Intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_put);

        Score = 0;

        Score_Text = (TextView)findViewById(R.id.score_input_total);
        Score_Text.setText(Integer.toString(Score));

        Score_Check_Button = (Button)findViewById(R.id.score_input_check);
        Score_Cancel_Button = (Button)findViewById(R.id.score_input_cancel);

        Score_Check_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Score_Result_Intent.getStringExtra("Score") == null){
                    setResult(RESULT_CANCELED,Score_Result_Intent);
                    finish();
                }
                else {
                    setResult(RESULT_OK, Score_Result_Intent);
                    finish();
                }
            }
        });
        Score_Cancel_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED,Score_Result_Intent);
                finish();
            }
        });
    }

    public void inputScore(View v){
        switch (v.getId()){
            case R.id.number_0:
                Score = Score + 0;
                break;
            case R.id.number_1:
                Score = Score + 1;
                break;
            case R.id.number_2:
                Score = Score + 2;
                break;
            case R.id.number_3:
                Score = Score + 3;
                break;
            case R.id.number_4:
                Score = Score + 4;
                break;
            case R.id.number_5:
                Score = Score + 5;
                break;
            case R.id.number_6:
                Score = Score + 6;
                break;
            case R.id.number_7:
                Score = Score + 7;
                break;
            case R.id.number_8:
                Score = Score + 8;
                break;
            case R.id.number_9:
                Score = Score + 9;
                break;
        }
        Score_Text.setText(Integer.toString(Score));
        Score_string = Integer.toString(Score);
        Score_Result_Intent.putExtra("Score",Score_string);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev){
        Rect dialogBounds = new Rect();
        getWindow().getDecorView().getHitRect(dialogBounds);
        if(!dialogBounds.contains((int)ev.getX(),(int)ev.getY())){
            return false;
        }
        return super.dispatchTouchEvent(ev);
    }

    public void score_return(View v){
        Score = 0;
        Score_Text.setText(Integer.toString(Score));
        Score_string = null;
        Score_Result_Intent.putExtra("Score",Score_string);
    }
}