package com.example.user.billardtrainningapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by user on 2017-04-13.
 */

public class Score_Result extends AppCompatActivity {
    Intent Result = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_result);

        ImageView Win_yellow = (ImageView)findViewById(R.id.win_yellow);
        ImageView Win_white = (ImageView)findViewById(R.id.win_white);

        Win_white.setVisibility(View.INVISIBLE);
        Win_yellow.setVisibility(View.INVISIBLE);

        int Result_Score_yellow;
        int Result_Score_white;
        int Result_Inning_yellow;
        int Result_Nohit_yellow;
        int Result_Nohit_white;
        int Result_Highrun_yellow;
        int Result_Highrun_white;
        double Result_Average_yellow;
        double Result_Average_white;
        String Win;

        TextView Score_Text_yellow = (TextView)findViewById(R.id.result_score_yellow);
        TextView Score_Text_white = (TextView)findViewById(R.id.result_score_white);
        TextView Inning_Text_yellow = (TextView)findViewById(R.id.result_inning_yellow);
        TextView Inning_Text_white = (TextView)findViewById(R.id.result_inning_white);
        TextView Nohit_Text_yellow = (TextView)findViewById(R.id.result_nohit_yellow);
        TextView Nohit_Text_white = (TextView)findViewById(R.id.result_nohit_white);
        TextView Highrun_Text_yellow = (TextView)findViewById(R.id.result_highrun_yellow);
        TextView Highrun_Text_white = (TextView)findViewById(R.id.result_highrun_white);
        TextView Average_Text_yellow = (TextView)findViewById(R.id.result_average_yellow);
        TextView Average_Text_white = (TextView)findViewById(R.id.result_average_white);

        Result = getIntent();
        String[] split5 = Result.getStringExtra("Scoreresult").split("_");

        Result_Score_yellow = Integer.parseInt(split5[0]);
        Result_Inning_yellow = Integer.parseInt(split5[1]);
        Result_Nohit_yellow = Integer.parseInt(split5[2]);
        Result_Highrun_yellow = Integer.parseInt(split5[3]);
        Result_Score_white = Integer.parseInt(split5[4]);
        Result_Nohit_white = Integer.parseInt(split5[5]);
        Result_Highrun_white = Integer.parseInt(split5[6]);
        Win = split5[7];

        Result_Average_yellow = (double)Result_Score_yellow/(double)Result_Inning_yellow;
        Result_Average_white = (double)Result_Score_white/(double)Result_Inning_yellow;

        if(Win.equals("Yellow")){
            Win_yellow.setVisibility(View.VISIBLE);
        }
        else if(Win.equals("White")){
            Win_white.setVisibility(View.VISIBLE);
        }
        else if(Win.equals("Null")) {
            if (Result_Score_white > Result_Score_yellow) Win_white.setVisibility(View.VISIBLE);
            else Win_yellow.setVisibility(View.VISIBLE);
        }

        if(Result_Score_white > Result_Score_yellow)Score_Text_white.setTextColor(Color.RED);
        else Score_Text_yellow.setTextColor(Color.RED);
        if(Result_Nohit_white < Result_Nohit_yellow)Nohit_Text_white.setTextColor(Color.RED);
        else if(Result_Nohit_white > Result_Nohit_yellow)Nohit_Text_yellow.setTextColor(Color.RED);
        if(Result_Highrun_white > Result_Highrun_yellow)Highrun_Text_white.setTextColor(Color.RED);
        else Highrun_Text_yellow.setTextColor(Color.RED);
        if(Result_Average_white > Result_Average_yellow)Average_Text_white.setTextColor(Color.RED);
        else Average_Text_yellow.setTextColor(Color.RED);

        Score_Text_white.setText(Integer.toString(Result_Score_white));
        Score_Text_yellow.setText(Integer.toString(Result_Score_yellow));
        Inning_Text_white.setText(Integer.toString(Result_Inning_yellow));
        Inning_Text_yellow.setText(Integer.toString(Result_Inning_yellow));
        Nohit_Text_white.setText(Integer.toString(Result_Nohit_white));
        Nohit_Text_yellow.setText(Integer.toString(Result_Nohit_yellow));
        Highrun_Text_white.setText(Integer.toString(Result_Highrun_white));
        Highrun_Text_yellow.setText(Integer.toString(Result_Highrun_yellow));
        Average_Text_white.setText(Double.toString(Result_Average_white));
        Average_Text_yellow.setText(Double.toString(Result_Average_yellow));
    }

    public void score_result_check(View v){
        finish();
    }
}
