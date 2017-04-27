package com.example.user.billardtrainningapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

/**
 * Created by user on 2017-04-12.
 */

public class ScoreCompute extends AppCompatActivity {
    public static final int REQUEST_CODE_YELLOW = 1006;
    public static final int REQUEST_CODE_WHITE = 1007;

    int Inning_white;
    int Inning_yellow;
    int Score_white;
    int Score_yellow;
    int Nohit_white;
    int Nohit_yellow;
    int HighRun_white;
    int HighRun_yellow;
    int Total_yellow;
    int Total_white;
    int i;
    int k;
    int x;
    int y;
    int[] Highrun_old_yellow;
    int[] Highrun_old_white;

    ImageSwitcher ScoreBoard_Yellow1;
    ImageSwitcher ScoreBoard_Yellow2;
    ImageSwitcher ScoreBoard_White1;
    ImageSwitcher ScoreBoard_White2;

    TextView Inning_Yellow_Text;
    TextView Inning_White_Text;

    int[] Number_Array = {
            R.drawable.number_0,R.drawable.number_1,R.drawable.number_2,R.drawable.number_3,R.drawable.number_4,
            R.drawable.number_5,R.drawable.number_6,R.drawable.number_7,R.drawable.number_8,R.drawable.number_9
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scorecompute);

        Inning_white = 0;
        Inning_yellow = 0;
        Nohit_white = 0;
        Nohit_yellow = 0;
        Total_white = 0;
        Total_yellow = 0;
        i = 0;
        k = 0;
        x = 0;
        y = 0;

        ScoreBoard_Yellow1 = (ImageSwitcher)findViewById(R.id.yellow_score_imageswitcher1) ;
        ScoreBoard_Yellow1.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                return imageView;
            }
        });
        ScoreBoard_Yellow2 = (ImageSwitcher)findViewById(R.id.yellow_score_imageswitcher2) ;
        ScoreBoard_Yellow2.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                return imageView;
            }
        });
        ScoreBoard_White1 = (ImageSwitcher)findViewById(R.id.white_score_imageswitcher1) ;
        ScoreBoard_White1.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                return imageView;
            }
        });
        ScoreBoard_White2 = (ImageSwitcher)findViewById(R.id.white_score_imageswitcher2) ;
        ScoreBoard_White2.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                return imageView;
            }
        });
        Inning_Yellow_Text = (TextView)findViewById(R.id.yellow_inning);
        Inning_White_Text = (TextView)findViewById(R.id.white_inning);

        ScoreBoard_White1.setImageResource(Number_Array[0]);
        ScoreBoard_White1.invalidate();
        ScoreBoard_White2.setImageResource(Number_Array[0]);
        ScoreBoard_White2.invalidate();
        ScoreBoard_Yellow1.setImageResource(Number_Array[0]);
        ScoreBoard_Yellow1.invalidate();
        ScoreBoard_Yellow2.setImageResource(Number_Array[0]);
        ScoreBoard_Yellow2.invalidate();
    }
    public void put_score(View v){
        Intent Score_Input_Intent = new Intent(this,Score_Put.class);
        switch (v.getId()){
            case R.id.yellow_score:
                startActivityForResult(Score_Input_Intent,REQUEST_CODE_YELLOW);
                break;
            case R.id.white_score:
                startActivityForResult(Score_Input_Intent,REQUEST_CODE_WHITE);
                break;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode){
            case REQUEST_CODE_YELLOW:
                if(resultCode == RESULT_OK){
                    Inning_yellow++;
                    Score_yellow = Integer.parseInt(intent.getStringExtra("Score"));

                    if(Score_yellow == 0){
                        Nohit_yellow++;
                    }
                    if (Score_yellow > HighRun_yellow) {
                        HighRun_yellow = Score_yellow;
                    }
                    Total_yellow = Total_yellow + Score_yellow;
                    Inning_Yellow_Text.setText(Integer.toString(Inning_yellow));

                    if(0 <= Total_yellow && Total_yellow < 10){
                        for(i=0;i<10;i++){
                            if(Total_yellow == i){
                                ScoreBoard_Yellow2.setImageResource(Number_Array[i]);
                                ScoreBoard_Yellow2.invalidate();
                            }
                        }
                    }
                    if(10 <= Total_yellow && Total_yellow < 20){
                        for(i=10;i<20;i++){
                            if(Total_yellow == i){
                                ScoreBoard_Yellow1.setImageResource(Number_Array[1]);
                                ScoreBoard_Yellow1.invalidate();
                                ScoreBoard_Yellow2.setImageResource(Number_Array[i-10]);
                                ScoreBoard_Yellow2.invalidate();
                            }
                        }
                    }
                    if(20 <= Total_yellow && Total_yellow < 30){
                        for(i=20;i<30;i++){
                            if(Total_yellow == i){
                                ScoreBoard_Yellow1.setImageResource(Number_Array[2]);
                                ScoreBoard_Yellow1.invalidate();
                                ScoreBoard_Yellow2.setImageResource(Number_Array[i-20]);
                                ScoreBoard_Yellow2.invalidate();
                            }
                        }
                    }
                    if(30 <= Total_yellow && Total_yellow < 40){
                        for(i=30;i<40;i++){
                            if(Total_yellow == i){
                                ScoreBoard_Yellow1.setImageResource(Number_Array[3]);
                                ScoreBoard_Yellow1.invalidate();
                                ScoreBoard_Yellow2.setImageResource(Number_Array[i-30]);
                                ScoreBoard_Yellow2.invalidate();
                            }
                        }
                    }
                    if(40 <= Total_yellow && Total_yellow < 50){
                        for(i=40;i<50;i++){
                            if(Total_yellow == i){
                                ScoreBoard_Yellow1.setImageResource(Number_Array[4]);
                                ScoreBoard_Yellow1.invalidate();
                                ScoreBoard_Yellow2.setImageResource(Number_Array[i-40]);
                                ScoreBoard_Yellow2.invalidate();
                            }
                        }
                    }
                    if(50 <= Total_yellow && Total_yellow < 60){
                        for(i=50;i<60;i++){
                            if(Total_yellow == i){
                                ScoreBoard_Yellow1.setImageResource(Number_Array[5]);
                                ScoreBoard_Yellow1.invalidate();
                                ScoreBoard_Yellow2.setImageResource(Number_Array[i-50]);
                                ScoreBoard_Yellow2.invalidate();
                            }
                        }
                    }
                    if(60 <= Total_yellow && Total_yellow < 70){
                        for(i=60;i<70;i++){
                            if(Total_yellow == i){
                                ScoreBoard_Yellow1.setImageResource(Number_Array[6]);
                                ScoreBoard_Yellow1.invalidate();
                                ScoreBoard_Yellow2.setImageResource(Number_Array[i-60]);
                                ScoreBoard_Yellow2.invalidate();
                            }
                        }
                    }
                    if(70 <= Total_yellow && Total_yellow < 80){
                        for(i=70;i<80;i++){
                            if(Total_yellow == i){
                                ScoreBoard_Yellow1.setImageResource(Number_Array[7]);
                                ScoreBoard_Yellow1.invalidate();
                                ScoreBoard_Yellow2.setImageResource(Number_Array[i-70]);
                                ScoreBoard_Yellow2.invalidate();
                            }
                        }
                    }
                    if(80 <= Total_yellow && Total_yellow < 90){
                        for(i=80;i<90;i++){
                            if(Total_yellow == i){
                                ScoreBoard_Yellow1.setImageResource(Number_Array[8]);
                                ScoreBoard_Yellow1.invalidate();
                                ScoreBoard_Yellow2.setImageResource(Number_Array[i-80]);
                                ScoreBoard_Yellow2.invalidate();
                            }
                        }
                    }
                    if(90 <= Total_yellow && Total_yellow < 100){
                        for(i=90;i<100;i++){
                            if(Total_yellow == i){
                                ScoreBoard_Yellow1.setImageResource(Number_Array[9]);
                                ScoreBoard_Yellow1.invalidate();
                                ScoreBoard_Yellow2.setImageResource(Number_Array[i-90]);
                                ScoreBoard_Yellow2.invalidate();
                            }
                        }
                    }
                }
                break;
            case REQUEST_CODE_WHITE:
                if(resultCode == RESULT_OK){
                    Inning_white++;
                    Score_white = Integer.parseInt(intent.getStringExtra("Score"));
                    if(Score_white == 0){
                        Nohit_white++;
                    }
                    if (Score_white > HighRun_white) {
                        HighRun_white = Score_white;
                    }
                }
                Total_white = Total_white + Score_white;
                Inning_White_Text.setText(Integer.toString(Inning_white));

                if(0 <= Total_white && Total_white < 10){
                    for(i=0;i<10;i++){
                        if(Total_white == i){
                            ScoreBoard_White2.setImageResource(Number_Array[i]);
                            ScoreBoard_White2.invalidate();
                        }
                    }
                }
                if(10 <= Total_white && Total_white < 20){
                    for(i=10;i<20;i++){
                        if(Total_white == i){
                            ScoreBoard_White1.setImageResource(Number_Array[1]);
                            ScoreBoard_White1.invalidate();
                            ScoreBoard_White2.setImageResource(Number_Array[i-10]);
                            ScoreBoard_White2.invalidate();
                        }
                    }
                }
                if(20 <= Total_white && Total_white < 30){
                    for(i=20;i<30;i++){
                        if(Total_white == i){
                            ScoreBoard_White1.setImageResource(Number_Array[2]);
                            ScoreBoard_White1.invalidate();
                            ScoreBoard_White2.setImageResource(Number_Array[i-20]);
                            ScoreBoard_White2.invalidate();
                        }
                    }
                }
                if(30 <= Total_white && Total_white < 40){
                    for(i=30;i<40;i++){
                        if(Total_white == i){
                            ScoreBoard_White1.setImageResource(Number_Array[3]);
                            ScoreBoard_White1.invalidate();
                            ScoreBoard_White2.setImageResource(Number_Array[i-30]);
                            ScoreBoard_White2.invalidate();
                        }
                    }
                }
                if(40 <= Total_white && Total_white < 50){
                    for(i=40;i<50;i++){
                        if(Total_white == i){
                            ScoreBoard_White1.setImageResource(Number_Array[4]);
                            ScoreBoard_White1.invalidate();
                            ScoreBoard_White2.setImageResource(Number_Array[i-40]);
                            ScoreBoard_White2.invalidate();
                        }
                    }
                }
                if(50 <= Total_white && Total_white < 60){
                    for(i=50;i<60;i++){
                        if(Total_white == i){
                            ScoreBoard_White1.setImageResource(Number_Array[5]);
                            ScoreBoard_White1.invalidate();
                            ScoreBoard_White2.setImageResource(Number_Array[i-50]);
                            ScoreBoard_White2.invalidate();
                        }
                    }
                }
                if(60 <= Total_white && Total_white < 70){
                    for(i=60;i<70;i++){
                        if(Total_white == i){
                            ScoreBoard_White1.setImageResource(Number_Array[6]);
                            ScoreBoard_White1.invalidate();
                            ScoreBoard_White2.setImageResource(Number_Array[i-60]);
                            ScoreBoard_White2.invalidate();
                        }
                    }
                }
                if(70 <= Total_white && Total_white < 80){
                    for(i=70;i<80;i++){
                        if(Total_white == i){
                            ScoreBoard_White1.setImageResource(Number_Array[7]);
                            ScoreBoard_White1.invalidate();
                            ScoreBoard_White2.setImageResource(Number_Array[i-70]);
                            ScoreBoard_White2.invalidate();
                        }
                    }
                }
                if(80 <= Total_white && Total_white < 90){
                    for(i=80;i<90;i++){
                        if(Total_white == i){
                            ScoreBoard_White1.setImageResource(Number_Array[8]);
                            ScoreBoard_White1.invalidate();
                            ScoreBoard_White2.setImageResource(Number_Array[i-80]);
                            ScoreBoard_White2.invalidate();
                        }
                    }
                }
                if(90 <= Total_white && Total_white < 100){
                    for(i=90;i<100;i++){
                        if(Total_white == i){
                            ScoreBoard_White1.setImageResource(Number_Array[9]);
                            ScoreBoard_White1.invalidate();
                            ScoreBoard_White2.setImageResource(Number_Array[i-90]);
                            ScoreBoard_White2.invalidate();
                        }
                    }
                }
                break;
        }
    }

    public void result_check(View v){
        Intent Score_Result_Intent = new Intent(this,Score_Result.class);
        Score_Result_Intent.putExtra("Scoreresult",Integer.toString(Total_yellow)+"_"+Integer.toString(Inning_yellow)+"_"+Integer.toString(Nohit_yellow)+"_"+Integer.toString(HighRun_yellow)+"_"+Integer.toString(Total_white)+"_"+Integer.toString(Inning_white)+"_"+Integer.toString(Nohit_white)+"_"+Integer.toString(HighRun_white));
        startActivity(Score_Result_Intent);
    }

    public void score_back_yellow(View v){

    }
    public void score_back_white(View v){

    }
}