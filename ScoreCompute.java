package com.example.user.billardtrainningapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017-04-12.
 */

public class ScoreCompute extends AppCompatActivity {
    public static final int REQUEST_CODE_YELLOW = 1006;
    public static final int REQUEST_CODE_WHITE = 1007;
    public static final int REQUEST_CODE_FIRST = 1008;
    public static final int REQUEST_CODE_WHITE_LOGIN = 1009;
    public static final int REQUEST_CODE_YELLOW_LOGIN = 1010;

    int Inning;
    boolean Inning_Switcher_yellow;
    boolean Inning_Switcher_white;
    int Inning_Switcher;
    boolean First_Switcher;
    boolean Start_Switcher;
    int Start_Pause_Switcher;
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
    int cnt;

    ImageSwitcher ScoreBoard_Yellow1;
    ImageSwitcher ScoreBoard_Yellow2;
    ImageSwitcher ScoreBoard_White1;
    ImageSwitcher ScoreBoard_White2;
    ImageSwitcher Score_Start_Button;

    TextView Inning_Yellow_Text;

    int[] Number_Array = {
            R.drawable.number_0,R.drawable.number_1,R.drawable.number_2,R.drawable.number_3,R.drawable.number_4,
            R.drawable.number_5,R.drawable.number_6,R.drawable.number_7,R.drawable.number_8,R.drawable.number_9
    };

    int[] Start_Button_Array = {
            R.drawable.score_start,R.drawable.score_pause,R.drawable.score_restart
    };

    int sec1 = 0;
    int sec2 = 0;
    int min2 = 0;
    int min1 = 0;
    int hour2 = 0;
    int hour1 = 0;
    TextView sec1_text;
    TextView sec2_text;
    TextView hour1_text;
    TextView hour2_text;
    TextView min1_text;
    TextView min2_text;
    Handler handler;
    Message message;

    ImageButton Score_First_Button;

    int Score_tmp_yellow;
    int Score_tmp_white;
    int Highrun_tmp_yellow;
    int Highrun_tmp_white;
    int Nohit_tmp_yellow;
    int Nohit_tmp_white;

    TextView White_Name;
    TextView White_Handy;
    TextView Yellow_Name;
    TextView Yellow_Handy;
    String White_Name2;
    String White_Handy2;
    String White_Di;
    String Yellow_Name2;
    String Yellow_Handy2;
    String Yellow_Di;
    String Win;

    int Score_Set_White;
    int Score_Set_Yellow;

    boolean GameOver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scorecompute);

        Inning = 0;
        Inning_Switcher = 0;
        Inning_Switcher_yellow = false;
        Inning_Switcher_white = false;
        GameOver = false;
        First_Switcher = true;
        Start_Switcher = true;
        Start_Pause_Switcher = 0;
        Nohit_white = 0;
        Nohit_yellow = 0;
        Total_white = 0;
        Total_yellow = 0;
        i = 0;
        k = 0;
        x = 0;
        y = 0;
        cnt = 1;
        Score_tmp_yellow=0;
        Score_tmp_white=0;
        Highrun_tmp_yellow=0;
        Highrun_tmp_white=0;
        Nohit_tmp_yellow=0;
        Nohit_tmp_white=0;
        Score_Set_Yellow=99;
        Score_Set_White=99;
        Win = "Null";

        hour1_text = (TextView)findViewById(R.id.score_hour1);
        hour2_text = (TextView)findViewById(R.id.score_hour2);
        min1_text = (TextView)findViewById(R.id.score_min1);
        min2_text = (TextView)findViewById(R.id.score_min2);
        sec1_text = (TextView)findViewById(R.id.score_sec1);
        sec2_text = (TextView)findViewById(R.id.score_sec2);

        White_Name = (TextView)findViewById(R.id.score_white_name);
        White_Handy = (TextView)findViewById(R.id.score_white_handy);
        Yellow_Name = (TextView)findViewById(R.id.score_yellow_name);
        Yellow_Handy = (TextView)findViewById(R.id.score_yellow_handy);


        Score_First_Button = (ImageButton)findViewById(R.id.score_first_button);
        Score_First_Button.setEnabled(true);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                        sec2++;
                        if (sec2 == 10) {
                            sec2 = 0;
                            sec1++;
                        }
                        if (sec1 == 6) {
                            sec1 = 0;
                            min2++;
                        }
                        if (min2 == 10) {
                            min2 = 0;
                            min1++;
                        }
                        if (min1 == 6) {
                            min1 = 0;
                            hour2++;
                        }
                        if (hour2 == 10) {
                            hour2 = 0;
                            hour1++;
                        }
                        sec2_text.setText(Integer.toString(sec2));
                        sec1_text.setText(Integer.toString(sec1));
                        min2_text.setText(Integer.toString(min2));
                        min1_text.setText(Integer.toString(min1));
                        hour2_text.setText(Integer.toString(hour2));
                        hour1_text.setText(Integer.toString(hour1));
                        handler.sendEmptyMessageDelayed(0,1000);
            }
        };


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
        Score_Start_Button = (ImageSwitcher)findViewById(R.id.score_start_imageswitcher) ;
        Score_Start_Button.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                return imageView;
            }
        });
        Inning_Yellow_Text = (TextView)findViewById(R.id.yellow_inning);

        ScoreBoard_White1.setImageResource(Number_Array[0]);
        ScoreBoard_White1.invalidate();
        ScoreBoard_White2.setImageResource(Number_Array[0]);
        ScoreBoard_White2.invalidate();
        ScoreBoard_Yellow1.setImageResource(Number_Array[0]);
        ScoreBoard_Yellow1.invalidate();
        ScoreBoard_Yellow2.setImageResource(Number_Array[0]);
        ScoreBoard_Yellow2.invalidate();
        Score_Start_Button.setImageResource(Start_Button_Array[0]);
        Score_Start_Button.invalidate();
    }
    public void put_score(View v){
        Intent Score_Input_Intent = new Intent(this,Score_Put.class);
        if(First_Switcher) Toast.makeText(getApplicationContext(),"선공을 먼저 선택해주세요.",Toast.LENGTH_SHORT).show();
        else {
            if(Start_Switcher) Toast.makeText(getApplicationContext(),"시작 버튼을 눌러주세요.",Toast.LENGTH_SHORT).show();
            else if(Start_Pause_Switcher == 2){
                Toast.makeText(getApplicationContext(),"재개 버튼을 눌러주세요.",Toast.LENGTH_SHORT).show();
            }
            else {
                switch (v.getId()) {
                    case R.id.yellow_score:
                        if(GameOver)Toast.makeText(ScoreCompute.this,"게임이 끝났습니다.",Toast.LENGTH_SHORT).show();
                        else {
                            startActivityForResult(Score_Input_Intent, REQUEST_CODE_YELLOW);
                        }
                        break;
                    case R.id.white_score:
                        if(GameOver)Toast.makeText(ScoreCompute.this,"게임이 끝났습니다.",Toast.LENGTH_SHORT).show();
                        else {
                            startActivityForResult(Score_Input_Intent, REQUEST_CODE_WHITE);
                        }
                        break;
                }
            }
        }
    }
    public void score_start(View v) throws InterruptedException {
        if(GameOver)Toast.makeText(ScoreCompute.this,"게임이 끝났습니다.",Toast.LENGTH_SHORT).show();
        else if(First_Switcher) Toast.makeText(getApplicationContext(),"선공을 먼저 선택해주세요.",Toast.LENGTH_SHORT).show();
        else {
                    if(Start_Pause_Switcher == 0) {
                        handler.sendEmptyMessage(0);
                        Start_Switcher = false;
                        Start_Pause_Switcher = 1;
                        Score_Start_Button.setImageResource(Start_Button_Array[1]);
                        Score_Start_Button.invalidate();
                    }
                    else if(Start_Pause_Switcher == 1){
                                handler.removeMessages(0);
                                Start_Pause_Switcher = 2;
                        Score_Start_Button.setImageResource(Start_Button_Array[2]);
                        Score_Start_Button.invalidate();
                        }
                else if(Start_Pause_Switcher == 2){
                        handler.sendEmptyMessage(0);
                        Start_Pause_Switcher = 1;
                        Score_Start_Button.setImageResource(Start_Button_Array[1]);
                        Score_Start_Button.invalidate();
                }
        }
    }
    public void score_first(View v){
        Intent Score_First_Intent = new Intent(this,Score_First.class);
        startActivityForResult(Score_First_Intent,REQUEST_CODE_FIRST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            case REQUEST_CODE_FIRST:
                if (resultCode == RESULT_OK) {
                    First_Switcher = false;
                    Score_First_Button.setEnabled(false);
                    if (intent.getStringExtra("First_Attack").equals("yellow")) {
                        Inning_Switcher_yellow = true;
                    } else if (intent.getStringExtra("First_Attack").equals("white")) {
                        Inning_Switcher_white = true;
                    }
                }
                break;
            case REQUEST_CODE_YELLOW:
                if (resultCode == RESULT_OK) {
                    Highrun_tmp_yellow = HighRun_yellow;
                    Score_tmp_yellow = Score_yellow;
                    Nohit_tmp_yellow = Nohit_yellow;
                    Score_yellow = Integer.parseInt(intent.getStringExtra("Score"));
                    if (Inning_Switcher_yellow) {
                        if (intent.getStringExtra("Score") != null && Inning_Switcher == 0) {
                            Inning_Switcher = 1;
                        } else if (intent.getStringExtra("Score") != null && Inning_Switcher == 2) {
                            Inning_Switcher = 1;
                            Inning++;
                            cnt = 1;
                            Inning_Yellow_Text.setText(Integer.toString(Inning));
                        }
                    } else if (Inning_Switcher_white) {
                        if (intent.getStringExtra("Score") != null && Inning_Switcher == 1) {
                            Inning_Switcher = 2;
                        }
                    }

                    if (Score_yellow == 0) {
                        Nohit_yellow++;
                    }
                    if (Score_yellow > HighRun_yellow) {
                        HighRun_yellow = Score_yellow;
                    }
                    Total_yellow = Total_yellow + Score_yellow;

                    if (0 <= Total_yellow && Total_yellow < 10) {
                        for (i = 0; i < 10; i++) {
                            if (Total_yellow == i) {
                                ScoreBoard_Yellow2.setImageResource(Number_Array[i]);
                                ScoreBoard_Yellow2.invalidate();
                            }
                        }
                    }
                    if (10 <= Total_yellow && Total_yellow < 20) {
                        for (i = 10; i < 20; i++) {
                            if (Total_yellow == i) {
                                ScoreBoard_Yellow1.setImageResource(Number_Array[1]);
                                ScoreBoard_Yellow1.invalidate();
                                ScoreBoard_Yellow2.setImageResource(Number_Array[i - 10]);
                                ScoreBoard_Yellow2.invalidate();
                            }
                        }
                    }
                    if (20 <= Total_yellow && Total_yellow < 30) {
                        for (i = 20; i < 30; i++) {
                            if (Total_yellow == i) {
                                ScoreBoard_Yellow1.setImageResource(Number_Array[2]);
                                ScoreBoard_Yellow1.invalidate();
                                ScoreBoard_Yellow2.setImageResource(Number_Array[i - 20]);
                                ScoreBoard_Yellow2.invalidate();
                            }
                        }
                    }
                    if (30 <= Total_yellow && Total_yellow < 40) {
                        for (i = 30; i < 40; i++) {
                            if (Total_yellow == i) {
                                ScoreBoard_Yellow1.setImageResource(Number_Array[3]);
                                ScoreBoard_Yellow1.invalidate();
                                ScoreBoard_Yellow2.setImageResource(Number_Array[i - 30]);
                                ScoreBoard_Yellow2.invalidate();
                            }
                        }
                    }
                    if (40 <= Total_yellow && Total_yellow < 50) {
                        for (i = 40; i < 50; i++) {
                            if (Total_yellow == i) {
                                ScoreBoard_Yellow1.setImageResource(Number_Array[4]);
                                ScoreBoard_Yellow1.invalidate();
                                ScoreBoard_Yellow2.setImageResource(Number_Array[i - 40]);
                                ScoreBoard_Yellow2.invalidate();
                            }
                        }
                    }
                    if (50 <= Total_yellow && Total_yellow < 60) {
                        for (i = 50; i < 60; i++) {
                            if (Total_yellow == i) {
                                ScoreBoard_Yellow1.setImageResource(Number_Array[5]);
                                ScoreBoard_Yellow1.invalidate();
                                ScoreBoard_Yellow2.setImageResource(Number_Array[i - 50]);
                                ScoreBoard_Yellow2.invalidate();
                            }
                        }
                    }
                    if (60 <= Total_yellow && Total_yellow < 70) {
                        for (i = 60; i < 70; i++) {
                            if (Total_yellow == i) {
                                ScoreBoard_Yellow1.setImageResource(Number_Array[6]);
                                ScoreBoard_Yellow1.invalidate();
                                ScoreBoard_Yellow2.setImageResource(Number_Array[i - 60]);
                                ScoreBoard_Yellow2.invalidate();
                            }
                        }
                    }
                    if (70 <= Total_yellow && Total_yellow < 80) {
                        for (i = 70; i < 80; i++) {
                            if (Total_yellow == i) {
                                ScoreBoard_Yellow1.setImageResource(Number_Array[7]);
                                ScoreBoard_Yellow1.invalidate();
                                ScoreBoard_Yellow2.setImageResource(Number_Array[i - 70]);
                                ScoreBoard_Yellow2.invalidate();
                            }
                        }
                    }
                    if (80 <= Total_yellow && Total_yellow < 90) {
                        for (i = 80; i < 90; i++) {
                            if (Total_yellow == i) {
                                ScoreBoard_Yellow1.setImageResource(Number_Array[8]);
                                ScoreBoard_Yellow1.invalidate();
                                ScoreBoard_Yellow2.setImageResource(Number_Array[i - 80]);
                                ScoreBoard_Yellow2.invalidate();
                            }
                        }
                    }
                    if (90 <= Total_yellow && Total_yellow < 100) {
                        for (i = 90; i < 100; i++) {
                            if (Total_yellow == i) {
                                ScoreBoard_Yellow1.setImageResource(Number_Array[9]);
                                ScoreBoard_Yellow1.invalidate();
                                ScoreBoard_Yellow2.setImageResource(Number_Array[i - 90]);
                                ScoreBoard_Yellow2.invalidate();
                            }
                        }
                    }
                }
                if(Score_Set_Yellow <= Total_yellow){
                    GameOver = true;
                    handler.removeMessages(0);
                    Toast.makeText(ScoreCompute.this,"게임이 끝났습니다.",Toast.LENGTH_SHORT).show();
                    Win = "Yellow";
                }
                break;
            case REQUEST_CODE_WHITE:
                if(resultCode == RESULT_OK) {
                    Highrun_tmp_white = HighRun_white;
                    Score_tmp_white = Score_white;
                    Nohit_tmp_white = Nohit_white;
                    Score_white = Integer.parseInt(intent.getStringExtra("Score"));
                    if (Inning_Switcher_yellow) {
                        if (intent.getStringExtra("Score") != null && Inning_Switcher == 1) {
                            Inning_Switcher = 2;
                        }
                    } else if (Inning_Switcher_white) {
                        if (intent.getStringExtra("Score") != null && Inning_Switcher == 0) {
                            Inning_Switcher = 1;
                        } else if (intent.getStringExtra("Score") != null && Inning_Switcher == 2) {
                            Inning_Switcher = 1;
                            Inning++;
                            Inning_Yellow_Text.setText(Integer.toString(Inning));
                        }
                    }

                    if (Score_white == 0) {
                        Nohit_white++;
                    }
                    if (Score_white > HighRun_white) {
                        HighRun_white = Score_white;
                    }

                    Total_white = Total_white + Score_white;

                    if (0 <= Total_white && Total_white < 10) {
                        for (i = 0; i < 10; i++) {
                            if (Total_white == i) {
                                ScoreBoard_White2.setImageResource(Number_Array[i]);
                                ScoreBoard_White2.invalidate();
                            }
                        }
                    }
                    if (10 <= Total_white && Total_white < 20) {
                        for (i = 10; i < 20; i++) {
                            if (Total_white == i) {
                                ScoreBoard_White1.setImageResource(Number_Array[1]);
                                ScoreBoard_White1.invalidate();
                                ScoreBoard_White2.setImageResource(Number_Array[i - 10]);
                                ScoreBoard_White2.invalidate();
                            }
                        }
                    }
                    if (20 <= Total_white && Total_white < 30) {
                        for (i = 20; i < 30; i++) {
                            if (Total_white == i) {
                                ScoreBoard_White1.setImageResource(Number_Array[2]);
                                ScoreBoard_White1.invalidate();
                                ScoreBoard_White2.setImageResource(Number_Array[i - 20]);
                                ScoreBoard_White2.invalidate();
                            }
                        }
                    }
                    if (30 <= Total_white && Total_white < 40) {
                        for (i = 30; i < 40; i++) {
                            if (Total_white == i) {
                                ScoreBoard_White1.setImageResource(Number_Array[3]);
                                ScoreBoard_White1.invalidate();
                                ScoreBoard_White2.setImageResource(Number_Array[i - 30]);
                                ScoreBoard_White2.invalidate();
                            }
                        }
                    }
                    if (40 <= Total_white && Total_white < 50) {
                        for (i = 40; i < 50; i++) {
                            if (Total_white == i) {
                                ScoreBoard_White1.setImageResource(Number_Array[4]);
                                ScoreBoard_White1.invalidate();
                                ScoreBoard_White2.setImageResource(Number_Array[i - 40]);
                                ScoreBoard_White2.invalidate();
                            }
                        }
                    }
                    if (50 <= Total_white && Total_white < 60) {
                        for (i = 50; i < 60; i++) {
                            if (Total_white == i) {
                                ScoreBoard_White1.setImageResource(Number_Array[5]);
                                ScoreBoard_White1.invalidate();
                                ScoreBoard_White2.setImageResource(Number_Array[i - 50]);
                                ScoreBoard_White2.invalidate();
                            }
                        }
                    }
                    if (60 <= Total_white && Total_white < 70) {
                        for (i = 60; i < 70; i++) {
                            if (Total_white == i) {
                                ScoreBoard_White1.setImageResource(Number_Array[6]);
                                ScoreBoard_White1.invalidate();
                                ScoreBoard_White2.setImageResource(Number_Array[i - 60]);
                                ScoreBoard_White2.invalidate();
                            }
                        }
                    }
                    if (70 <= Total_white && Total_white < 80) {
                        for (i = 70; i < 80; i++) {
                            if (Total_white == i) {
                                ScoreBoard_White1.setImageResource(Number_Array[7]);
                                ScoreBoard_White1.invalidate();
                                ScoreBoard_White2.setImageResource(Number_Array[i - 70]);
                                ScoreBoard_White2.invalidate();
                            }
                        }
                    }
                    if (80 <= Total_white && Total_white < 90) {
                        for (i = 80; i < 90; i++) {
                            if (Total_white == i) {
                                ScoreBoard_White1.setImageResource(Number_Array[8]);
                                ScoreBoard_White1.invalidate();
                                ScoreBoard_White2.setImageResource(Number_Array[i - 80]);
                                ScoreBoard_White2.invalidate();
                            }
                        }
                    }
                    if (90 <= Total_white && Total_white < 100) {
                        for (i = 90; i < 100; i++) {
                            if (Total_white == i) {
                                ScoreBoard_White1.setImageResource(Number_Array[9]);
                                ScoreBoard_White1.invalidate();
                                ScoreBoard_White2.setImageResource(Number_Array[i - 90]);
                                ScoreBoard_White2.invalidate();
                            }
                        }
                    }
                }
                if(Score_Set_White <= Total_white){
                    GameOver = true;
                    handler.removeMessages(0);
                    Toast.makeText(ScoreCompute.this,"게임이 끝났습니다.",Toast.LENGTH_SHORT).show();
                    Win = "White";
                }
                break;
            case REQUEST_CODE_WHITE_LOGIN:
                if(resultCode == RESULT_OK){
                    White_Name2=intent.getStringExtra("Name");
                    White_Handy2=intent.getStringExtra("Handy");
                    White_Di=intent.getStringExtra("Di");
                    if(White_Di.equals("Big"))White_Di="대대";
                    else if(White_Di.equals("Mid"))White_Di="중대";
                    White_Name.setText(White_Name2);
                    White_Handy.setText(White_Di+" : "+White_Handy2);
                    Score_Set_White = Integer.parseInt(White_Handy2);
                }
                break;
            case REQUEST_CODE_YELLOW_LOGIN:
                if(resultCode == RESULT_OK){
                    Yellow_Name2=intent.getStringExtra("Name");
                    Yellow_Handy2=intent.getStringExtra("Handy");
                    Yellow_Di=intent.getStringExtra("Di");
                    if(Yellow_Di.equals("Big"))Yellow_Di="대대";
                    else if(Yellow_Di.equals("Mid"))Yellow_Di="중대";
                    Yellow_Name.setText(Yellow_Name2);
                    Yellow_Handy.setText(Yellow_Di+" : "+Yellow_Handy2);
                    Score_Set_Yellow = Integer.parseInt(Yellow_Handy2);
                }
                break;
        }
    }

    public void result_check(View v){
        Intent Score_Result_Intent = new Intent(this,Score_Result.class);
        Score_Result_Intent.putExtra("Scoreresult",Integer.toString(Total_yellow)+"_"+Integer.toString(Inning)+"_"+Integer.toString(Nohit_yellow)+"_"+Integer.toString(HighRun_yellow)+"_"+Integer.toString(Total_white)+"_"+Integer.toString(Nohit_white)+"_"+Integer.toString(HighRun_white)+"_"+Win);
        startActivity(Score_Result_Intent);
    }

    public void score_back_yellow(View v){
        if(cnt == 0)Toast.makeText(ScoreCompute.this,"이닝당 1번만 수정할 수 있습니다.",Toast.LENGTH_SHORT).show();
        else {
            Total_yellow = Total_yellow - Score_yellow;
            Score_yellow = Score_tmp_yellow;
            HighRun_yellow = Highrun_tmp_yellow;
            Nohit_yellow = Nohit_tmp_yellow;

            if (0 <= Total_yellow && Total_yellow < 10) {
                for (i = 0; i < 10; i++) {
                    if (Total_yellow == i) {
                        ScoreBoard_Yellow2.setImageResource(Number_Array[i]);
                        ScoreBoard_Yellow2.invalidate();
                    }
                }
            }
            if (10 <= Total_yellow && Total_yellow < 20) {
                for (i = 10; i < 20; i++) {
                    if (Total_yellow == i) {
                        ScoreBoard_Yellow1.setImageResource(Number_Array[1]);
                        ScoreBoard_Yellow1.invalidate();
                        ScoreBoard_Yellow2.setImageResource(Number_Array[i - 10]);
                        ScoreBoard_Yellow2.invalidate();
                    }
                }
            }
            if (20 <= Total_yellow && Total_yellow < 30) {
                for (i = 20; i < 30; i++) {
                    if (Total_yellow == i) {
                        ScoreBoard_Yellow1.setImageResource(Number_Array[2]);
                        ScoreBoard_Yellow1.invalidate();
                        ScoreBoard_Yellow2.setImageResource(Number_Array[i - 20]);
                        ScoreBoard_Yellow2.invalidate();
                    }
                }
            }
            if (30 <= Total_yellow && Total_yellow < 40) {
                for (i = 30; i < 40; i++) {
                    if (Total_yellow == i) {
                        ScoreBoard_Yellow1.setImageResource(Number_Array[3]);
                        ScoreBoard_Yellow1.invalidate();
                        ScoreBoard_Yellow2.setImageResource(Number_Array[i - 30]);
                        ScoreBoard_Yellow2.invalidate();
                    }
                }
            }
            if (40 <= Total_yellow && Total_yellow < 50) {
                for (i = 40; i < 50; i++) {
                    if (Total_yellow == i) {
                        ScoreBoard_Yellow1.setImageResource(Number_Array[4]);
                        ScoreBoard_Yellow1.invalidate();
                        ScoreBoard_Yellow2.setImageResource(Number_Array[i - 40]);
                        ScoreBoard_Yellow2.invalidate();
                    }
                }
            }
            if (50 <= Total_yellow && Total_yellow < 60) {
                for (i = 50; i < 60; i++) {
                    if (Total_yellow == i) {
                        ScoreBoard_Yellow1.setImageResource(Number_Array[5]);
                        ScoreBoard_Yellow1.invalidate();
                        ScoreBoard_Yellow2.setImageResource(Number_Array[i - 50]);
                        ScoreBoard_Yellow2.invalidate();
                    }
                }
            }
            if (60 <= Total_yellow && Total_yellow < 70) {
                for (i = 60; i < 70; i++) {
                    if (Total_yellow == i) {
                        ScoreBoard_Yellow1.setImageResource(Number_Array[6]);
                        ScoreBoard_Yellow1.invalidate();
                        ScoreBoard_Yellow2.setImageResource(Number_Array[i - 60]);
                        ScoreBoard_Yellow2.invalidate();
                    }
                }
            }
            if (70 <= Total_yellow && Total_yellow < 80) {
                for (i = 70; i < 80; i++) {
                    if (Total_yellow == i) {
                        ScoreBoard_Yellow1.setImageResource(Number_Array[7]);
                        ScoreBoard_Yellow1.invalidate();
                        ScoreBoard_Yellow2.setImageResource(Number_Array[i - 70]);
                        ScoreBoard_Yellow2.invalidate();
                    }
                }
            }
            if (80 <= Total_yellow && Total_yellow < 90) {
                for (i = 80; i < 90; i++) {
                    if (Total_yellow == i) {
                        ScoreBoard_Yellow1.setImageResource(Number_Array[8]);
                        ScoreBoard_Yellow1.invalidate();
                        ScoreBoard_Yellow2.setImageResource(Number_Array[i - 80]);
                        ScoreBoard_Yellow2.invalidate();
                    }
                }
            }
            if (90 <= Total_yellow && Total_yellow < 100) {
                for (i = 90; i < 100; i++) {
                    if (Total_yellow == i) {
                        ScoreBoard_Yellow1.setImageResource(Number_Array[9]);
                        ScoreBoard_Yellow1.invalidate();
                        ScoreBoard_Yellow2.setImageResource(Number_Array[i - 90]);
                        ScoreBoard_Yellow2.invalidate();
                    }
                }
            }
            Intent Back_yellow_Intent = new Intent();
            if (Inning_Switcher_yellow) {
                if (Inning_Switcher == 1 && Back_yellow_Intent.getStringExtra("Score") != null)
                    Inning--;
            }
            cnt = 0;
//            if(Inning_Switcher_yellow)Inning_Switcher=1;
        }
    }
    public void score_back_white(View v) {
        Total_white = Total_white - Score_white;
        Score_white = Score_tmp_white;
        HighRun_white = Highrun_tmp_white;
        Nohit_white = Nohit_tmp_white;

        if (0 <= Total_white && Total_white < 10) {
            for (i = 0; i < 10; i++) {
                if (Total_white == i) {
                    ScoreBoard_White2.setImageResource(Number_Array[i]);
                    ScoreBoard_White2.invalidate();
                }
            }
        }
        if (10 <= Total_white && Total_white < 20) {
            for (i = 10; i < 20; i++) {
                if (Total_white == i) {
                    ScoreBoard_White1.setImageResource(Number_Array[1]);
                    ScoreBoard_White1.invalidate();
                    ScoreBoard_White2.setImageResource(Number_Array[i - 10]);
                    ScoreBoard_White2.invalidate();
                }
            }
        }
        if (20 <= Total_white && Total_white < 30) {
            for (i = 20; i < 30; i++) {
                if (Total_white == i) {
                    ScoreBoard_White1.setImageResource(Number_Array[2]);
                    ScoreBoard_White1.invalidate();
                    ScoreBoard_White2.setImageResource(Number_Array[i - 20]);
                    ScoreBoard_White2.invalidate();
                }
            }
        }
        if (30 <= Total_white && Total_white < 40) {
            for (i = 30; i < 40; i++) {
                if (Total_white == i) {
                    ScoreBoard_White1.setImageResource(Number_Array[3]);
                    ScoreBoard_White1.invalidate();
                    ScoreBoard_White2.setImageResource(Number_Array[i - 30]);
                    ScoreBoard_White2.invalidate();
                }
            }
        }
        if (40 <= Total_white && Total_white < 50) {
            for (i = 40; i < 50; i++) {
                if (Total_white == i) {
                    ScoreBoard_White1.setImageResource(Number_Array[4]);
                    ScoreBoard_White1.invalidate();
                    ScoreBoard_White2.setImageResource(Number_Array[i - 40]);
                    ScoreBoard_White2.invalidate();
                }
            }
        }
        if (50 <= Total_white && Total_white < 60) {
            for (i = 50; i < 60; i++) {
                if (Total_white == i) {
                    ScoreBoard_White1.setImageResource(Number_Array[5]);
                    ScoreBoard_White1.invalidate();
                    ScoreBoard_White2.setImageResource(Number_Array[i - 50]);
                    ScoreBoard_White2.invalidate();
                }
            }
        }
        if (60 <= Total_white && Total_white < 70) {
            for (i = 60; i < 70; i++) {
                if (Total_white == i) {
                    ScoreBoard_White1.setImageResource(Number_Array[6]);
                    ScoreBoard_White1.invalidate();
                    ScoreBoard_White2.setImageResource(Number_Array[i - 60]);
                    ScoreBoard_White2.invalidate();
                }
            }
        }
        if (70 <= Total_white && Total_white < 80) {
            for (i = 70; i < 80; i++) {
                if (Total_white == i) {
                    ScoreBoard_White1.setImageResource(Number_Array[7]);
                    ScoreBoard_White1.invalidate();
                    ScoreBoard_White2.setImageResource(Number_Array[i - 70]);
                    ScoreBoard_White2.invalidate();
                }
            }
        }
        if (80 <= Total_white && Total_white < 90) {
            for (i = 80; i < 90; i++) {
                if (Total_white == i) {
                    ScoreBoard_White1.setImageResource(Number_Array[8]);
                    ScoreBoard_White1.invalidate();
                    ScoreBoard_White2.setImageResource(Number_Array[i - 80]);
                    ScoreBoard_White2.invalidate();
                }
            }
        }
        if (90 <= Total_white && Total_white < 100) {
            for (i = 90; i < 100; i++) {
                if (Total_white == i) {
                    ScoreBoard_White1.setImageResource(Number_Array[9]);
                    ScoreBoard_White1.invalidate();
                    ScoreBoard_White2.setImageResource(Number_Array[i - 90]);
                    ScoreBoard_White2.invalidate();
                }
            }
        }
        Intent Back_white_Intent = new Intent();
        if(Inning_Switcher_white){
            if(Inning_Switcher==1 && Back_white_Intent.getStringExtra("Score") != null) Inning--;
        }
    }

    public void user_white(View v){
        Intent user_white_intent = new Intent(ScoreCompute.this,Usermode.class);
        startActivityForResult(user_white_intent,REQUEST_CODE_WHITE_LOGIN);
    }
    public void user_yellow(View v){
        Intent user_yellow_intent = new Intent(ScoreCompute.this,Usermode.class);
        startActivityForResult(user_yellow_intent,REQUEST_CODE_YELLOW_LOGIN);
    }
}