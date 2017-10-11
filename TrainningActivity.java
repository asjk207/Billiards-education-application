package com.example.user.billardtrainningapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;


/**
 * Created by user on 2017-01-23.
 */

public class TrainningActivity extends AppCompatActivity implements GestureDetector.OnGestureListener{
    public static final int REQUEST_CODE_THICK = 1001;
    public static final int REQUEST_CODE_POINTNTIP = 1002;
    public static final int REQUEST_CODE_STROKE = 1003;
    public static final int REQUEST_CODE_MENU = 1004;
    public static final int REQUEST_CODE_NEXT = 1005;




    GlobalVariable globalVariable;

    //쓰레드 시작 변수
    public static final int SEND_BALL_START_MESSAGE = 2001;

    String v_thick;
    String v_point;
    String v_tip;
    String v_stroke;
    Intent MainItent;
    String RouteLevel;

    String CorrectBasicThick;
    String CorrectBasicPoint;
    String CorrectBasicTip;
    String CorrectStroke;

    //변수버튼 순서 변수 1=두께 선택시 0=변수 선택 없음
    int Variable_Switcher;

    private TrainnigView trainnigView;

    ImageSwitcher imageSwitcher;
    ImageSwitcher Correct_imageSwitcher;
    int[] Correct_Switcher_Array = {
            R.drawable.correct_text,R.drawable.correct_text2
    };
    TextView Stroke_text;

    int Wrong_count;
    int Wrong_swtich;
    //오답수 카운트 변수
    int Log_wrong_count=0;
    int Log_right_count=0;

    FileINOUT AFile;

    ImageButton Play_Button;

    // 변경된 해상도 측정 변수
    int cv_width;
    int cv_height;

    //제스쳐 터치 이벤트 감지 변수
    private GestureDetectorCompat gestureDetector;
    float X_down;
    float X_up;
    float Y_down;
    float Y_up;
    boolean isSwiped=false;

    //숨겨진 유아이 창 변수수
   RelativeLayout variable_uiselect;

//    //데이터 베이스 접근 클래스
//    DB_Adapter DBA;
//    int caseid=2; // 문제 번호
//
    //DatabaseReference myRef;
   // DatabaseReference myRef2;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        Log.e("OnCreate Call","온크리에이트가 호출되었습니다.");
        super.onCreate(savedInstanceState);
        //Firebase database 연결 함수
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        myRef = database.getReference("Wrongcnt");
//        myRef2 = database.getReference("Rightcnt");

//        DBA=new DB_Adapter();
//        caseid = rand.nextInt(700);
//        if(GlobalVariable.Global_Difficulty=="R") DB_Process(caseid);

        globalVariable=(GlobalVariable)getApplication();
        MainItent = getIntent();
        //문제 경로및 레벨 설정
        RouteLevel=MainItent.getStringExtra("RouteLevel");
        GlobalVariable.casekinds=RouteLevel;


        // 기기의 해상도 측정
        DisplayMetrics metrics= new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        setContentView(R.layout.trainning_activity);

        trainnigView=(TrainnigView) findViewById(R.id.TrainningView);
        trainnigView.setScreenSize((int)metrics.widthPixels,(int)metrics.heightPixels);



//        Log.e("Globalvariable",""+GlobalVariable.Global_Difficulty);


        //variable_uiselect.wait(100);

        Stroke_text = (TextView)findViewById(R.id.stroke_text);

        imageSwitcher = (ImageSwitcher) findViewById(R.id.main_imageswitcher);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                return imageView;
            }
        });
        //Correct_imageSwitcher = (ImageSwitcher) findViewById(R.id.right_wrong_imageswitcher);
        /*Correct_imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                return imageView;
            }
        });
        */

        Play_Button = (ImageButton)findViewById(R.id.play_animation);

        Play_Button.setVisibility(View.INVISIBLE);
        Play_Button.setEnabled(false);
        GlobalVariable.SetPoint=null;
        GlobalVariable.SetThick=null;
        GlobalVariable.SetStrock=null;
        GlobalVariable.SetTip=null;
        GlobalVariable.whiteball=0;
        GlobalVariable.yellowball=0;
        GlobalVariable.redball=0;
        Variable_Switcher=0;
        v_point=null;
        v_tip=null;
        v_thick=null;
        v_stroke =null;
        Wrong_count=0;
        Wrong_swtich=2;
        AFile=null;
        GlobalVariable.linevisible=false;
        GlobalVariable.BallGoDraw=false;

        if(!(GlobalVariable.Global_Difficulty=="R")) {
            AFile = new FileINOUT(trainnigView.context);
            AFile.Casefileread(trainnigView.context, RouteLevel);
            Log.e("****************", "" + RouteLevel);

            switch (AFile.sugu) {
                case " white":
                    GlobalVariable.whiteball = 1;
                    break;
                case " yellow":
                    GlobalVariable.yellowball = 1;
                    break;
            }
            switch (AFile.onejukgu) {
                case " white":
                    GlobalVariable.whiteball = 2;
                    break;
                case " yellow":
                    GlobalVariable.yellowball = 2;
                    break;
                case " red":
                    GlobalVariable.redball = 2;
                    break;
            }
        }

        if(GlobalVariable.whiteball==1 && GlobalVariable.redball==2) {
            imageSwitcher.setImageResource(GlobalVariable.OneValueArray[30]);
            imageSwitcher.invalidate();
        }
        else if(GlobalVariable.whiteball==1 && GlobalVariable.yellowball==2){
            imageSwitcher.setImageResource(GlobalVariable.yOneValueArray[14]);
            imageSwitcher.invalidate();
        }
//        Correct_imageSwitcher.setImageResource(Correct_Switcher_Array[0]);
//        Correct_imageSwitcher.invalidate();


        // 제스쳐 감지 선언
        gestureDetector = new GestureDetectorCompat(getApplicationContext(),this);

        //숨겨진 유아이
        variable_uiselect=(RelativeLayout) findViewById(R.id.UIselect);

        /*

        Log.i("TAG", "display width : " + metrics.widthPixels + ", height : " + metrics.heightPixels + ", densityDpi : " + metrics.densityDpi);
        // 변경된 해상도로 넓이, 높이 계산
        cv_width=(int)(metrics.widthPixels*0.8);
        cv_height=(int)(metrics.heightPixels*0.9);
        Log.e("TAG", "cv_width : " + cv_width + ", cv_height : " + cv_height );

        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(cv_width,cv_height);
        trainnigView.setLayoutParams(params1);
        */
        final ImageButton Menu_Button = (ImageButton)findViewById(R.id.menu);
        Menu_Button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    Menu_Button.setImageResource(R.drawable.menu_pressed);
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    Menu_Button.setImageResource(R.drawable.menu);
                }
                return false;
            }
        });
        final ImageButton Thick_Button = (ImageButton)findViewById(R.id.thick);
        Thick_Button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    Thick_Button.setImageResource(R.drawable.thick_icon_s_pressed);
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    Thick_Button.setImageResource(R.drawable.thick_icon_s);
                }
                return false;
            }
        });
        final ImageButton PointnTip_Button = (ImageButton)findViewById(R.id.pointntip);
        PointnTip_Button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    PointnTip_Button.setImageResource(R.drawable.pointntip_icon_s_pressed);
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    PointnTip_Button.setImageResource(R.drawable.pointntip_icon_s);
                }
                return false;
            }
        });
        final ImageButton Stroke_Button = (ImageButton)findViewById(R.id.stroke);
        Stroke_Button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    Stroke_Button.setImageResource(R.drawable.strock_icon_s_pressed);
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    Stroke_Button.setImageResource(R.drawable.strock_icon_s);
                }
                return false;
            }
        });
        final ImageButton Play_Button = (ImageButton)findViewById(R.id.play_animation);
        Play_Button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    Play_Button.setImageResource(R.drawable.button_play_pressed);
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    Play_Button.setImageResource(R.drawable.button_play);
                }
                return false;
            }
        });
        final ImageButton Youtube_Button = (ImageButton)findViewById(R.id.pro_button);
        Youtube_Button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    Youtube_Button.setImageResource(R.drawable.youtube_button_pressed);
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    Youtube_Button.setImageResource(R.drawable.youtube_button);
                }
                return false;
            }
        });
        final ImageButton Correct_Button = (ImageButton)findViewById(R.id.right_wrong);
        Correct_Button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    Correct_Button.setImageResource(R.drawable.correct_icon_pressed);
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    Correct_Button.setImageResource(R.drawable.correct_icon);
                }
                return false;
            }
        });
        final ImageButton Next_Button = (ImageButton)findViewById(R.id.next);
        Next_Button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    Next_Button.setImageResource(R.drawable.next_icon_pressed);
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    Next_Button.setImageResource(R.drawable.next_icon);
                }
                return false;
            }
        });
        final ImageButton Prev_Button = (ImageButton)findViewById(R.id.back);
        Prev_Button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    Prev_Button.setImageResource(R.drawable.back_icon_pressed);
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    Prev_Button.setImageResource(R.drawable.back_icon);
                }
                return false;
            }
        });

    }

    public void onClick2(View v){
        switch(v.getId()){
            case R.id.menu:
                Intent menu_intent = new Intent(getApplicationContext(), Menu_dialog.class);
                menu_intent.putExtra("Info",RouteLevel);
                startActivityForResult(menu_intent,REQUEST_CODE_MENU);
                break;
            case R.id.thick:
//                trainnigView.ballGothread.Bloop=true;
//                GlobalVariable.BallGoDraw = true;
                Intent thick_intent = new Intent(getApplicationContext(), Thick_dialog.class);
                startActivityForResult(thick_intent,REQUEST_CODE_THICK);
                break;
            case R.id.pointntip:
                if(Variable_Switcher == 0){
                    Intent No_Thick = new Intent(getApplicationContext(),Small_dialog.class);
                    No_Thick.putExtra("Message","No");
                    startActivity(No_Thick);
                }
                else {
                    Intent point_intent = new Intent(getApplicationContext(), PointNTip_dialog.class);
                    startActivityForResult(point_intent, REQUEST_CODE_POINTNTIP);
                }
                break;
            case R.id.stroke:
                Intent stroke_intent = new Intent(getApplicationContext(), Strock_dialog.class);
                startActivityForResult(stroke_intent, REQUEST_CODE_STROKE);
                break;
            case R.id.next:
                Play_Button.setVisibility(View.INVISIBLE);
                Play_Button.setEnabled(false);
                GlobalVariable.BallGoDraw=false;
                GlobalVariable.linevisible=false;
                GlobalVariable.SetPoint=null;
                GlobalVariable.SetThick=null;
                GlobalVariable.SetStrock=null;
                Stroke_text.setText("스트로크");
               // Correct_imageSwitcher.setImageResource(Correct_Switcher_Array[0]);
               // Correct_imageSwitcher.invalidate();
                GlobalVariable.SetTip=null;
                GlobalVariable.whiteball=0;
                GlobalVariable.yellowball=0;
                GlobalVariable.redball=0;
                Variable_Switcher=0;
                v_point=null;
                v_tip=null;
                v_thick=null;
                v_stroke =null;
                Wrong_count=0;
                Wrong_swtich=2;
                AFile = null;
                AFile = new FileINOUT(trainnigView.context);

                if(!(GlobalVariable.Global_Difficulty=="R")) {
                    if (GlobalVariable.casenum < case_count()) {
                        GlobalVariable.casenum++;
                        AFile.Casefileread(trainnigView.context, RouteLevel);
                        trainnigView.ballGothread.isWait = true;
                        trainnigView.restartThread();
                        trainnigView.ballGothread.isWait = false;
                        switch (AFile.sugu) {
                            case " white":
                                GlobalVariable.whiteball = 1;
                                break;
                            case " yellow":
                                GlobalVariable.yellowball = 1;
                                break;
                        }
                        switch (AFile.onejukgu) {
                            case " white":
                                GlobalVariable.whiteball = 2;
                                break;
                            case " yellow":
                                GlobalVariable.yellowball = 2;
                                break;
                            case " red":
                                GlobalVariable.redball = 2;
                                break;
                        }

                        if (GlobalVariable.whiteball == 1 && GlobalVariable.redball == 2) {
                            imageSwitcher.setImageResource(GlobalVariable.OneValueArray[30]);
                            imageSwitcher.invalidate();
                        } else if (GlobalVariable.whiteball == 1 && GlobalVariable.yellowball == 2) {
                            imageSwitcher.setImageResource(GlobalVariable.yOneValueArray[14]);
                            imageSwitcher.invalidate();
                        }
                    }
                }
                else if (GlobalVariable.casenum >= case_count())
                    Toast.makeText(getApplicationContext(), "다음 문제는 없습니다.", Toast.LENGTH_SHORT).show();

                if(GlobalVariable.Global_Difficulty=="R"){
                        Log.e("next","test next");
                        if(GlobalVariable.DBcasenum<7) GlobalVariable.DBcasenum++;
                        trainnigView.ballGothread.isWait = true;
                        trainnigView.restartThread();
                        trainnigView.ballGothread.isWait = false;
                }
                break;
            case R.id.back:
                Play_Button.setVisibility(View.INVISIBLE);
                Play_Button.setEnabled(false);
                GlobalVariable.BallGoDraw=false;
                GlobalVariable.linevisible=false;
                GlobalVariable.SetPoint=null;
                GlobalVariable.SetThick=null;
                GlobalVariable.SetStrock=null;
                Stroke_text.setText("스트로크");
               // Correct_imageSwitcher.setImageResource(Correct_Switcher_Array[0]);
               // Correct_imageSwitcher.invalidate();
                GlobalVariable.SetTip=null;
                GlobalVariable.whiteball=0;
                GlobalVariable.yellowball=0;
                GlobalVariable.redball=0;
                Variable_Switcher=0;
                v_point=null;
                v_tip=null;
                v_thick=null;
                v_stroke =null;
                Wrong_count=0;
                Wrong_swtich=2;
                AFile = null;
                AFile = new FileINOUT(trainnigView.context);
                if(1<GlobalVariable.casenum){
                    GlobalVariable.casenum--;
                    AFile.Casefileread(trainnigView.context, RouteLevel);
                    trainnigView.ballGothread.isWait=true;
                    trainnigView.restartThread();
                    trainnigView.ballGothread.isWait=false;
                    switch (AFile.sugu){
                        case " white":
                            GlobalVariable.whiteball=1;
                            break;
                        case " yellow":
                            GlobalVariable.yellowball=1;
                            break;
                    }
                    switch (AFile.onejukgu){
                        case " white":
                            GlobalVariable.whiteball = 2;
                            break;
                        case " yellow":
                            GlobalVariable.yellowball=2;
                            break;
                        case " red":
                            GlobalVariable.redball=2;
                            break;
                    }
                    if(GlobalVariable.whiteball==1 && GlobalVariable.redball==2) {
                        imageSwitcher.setImageResource(GlobalVariable.OneValueArray[30]);
                        imageSwitcher.invalidate();
                    }
                    else if(GlobalVariable.whiteball==1 && GlobalVariable.yellowball==2){
                        imageSwitcher.setImageResource(GlobalVariable.yOneValueArray[14]);
                        imageSwitcher.invalidate();
                    }
                }
                if(GlobalVariable.Global_Difficulty=="R"){
                    if(GlobalVariable.DBcasenum>1) GlobalVariable.DBcasenum--;
                    trainnigView.ballGothread.isWait = true;
                    trainnigView.restartThread();
                    trainnigView.ballGothread.isWait = false;
                }
                else if(GlobalVariable.casenum<=1)Toast.makeText(getApplicationContext(), "이전 문제는 없습니다", Toast.LENGTH_SHORT).show(); //Toast.makeText(getApplicationContext(), "이전 문제는 없습니다."+GlobalVariable.casenum, Toast.LENGTH_SHORT).show();
                break;
            case R.id.right_wrong:
                if(!(GlobalVariable.Global_Difficulty=="R")) {
                    if (Wrong_count >= 3 || Wrong_swtich == 1) {
//                    trainnigView.ballGothread.isWait=true;
//                    trainnigView.ballGothread=null;
                        Intent correct_intent = new Intent(getApplicationContext(), Correct_dialog.class);
                        startActivity(correct_intent);
                    }
                    if (v_thick != null && v_tip != null && v_point != null && v_stroke != null && Wrong_swtich == 2) {
                        printcorrect();
                    } else if (v_thick == null || v_tip == null || v_point == null || v_stroke == null) {
                        Intent small_intent = new Intent(getApplicationContext(), Small_dialog.class);
                        small_intent.putExtra("Message", "shortage");
                        startActivity(small_intent);
                    }
                }
                if(GlobalVariable.Global_Difficulty=="R") {
                    trainnigView.restartThread();
                    trainnigView.ballGothread.Bloop=true;
                    GlobalVariable.BallGoDraw = true;
                    Toast.makeText(getApplicationContext(), "공 움직여요~: "+GlobalVariable.BallGoDraw, Toast.LENGTH_SHORT).show();
                }
                break;
        }


    }

    public void onClickyoutube(View view) {
        switch (view.getId()) {
            case R.id.pro_button:
                    GlobalVariable.CourseBook_Youtube_Switcher = 0;
                    trainnigView.ballGothread.isWait = true;
                    trainnigView.ballGothread = null;
                    Intent YouTubeIntent = new Intent(getApplicationContext(), YouTubeActivity.class);
                    YouTubeIntent.addFlags(YouTubeIntent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(YouTubeIntent);
                break;
                }
    }

//    @Override
//    protected void onStop() {
//        trainnigView.ballGothread.isWait=true;
//        trainnigView.ballGothread=null;
//        super.onStop();
//    }
//
//    @Override
//    protected void onResume() {
//        trainnigView.ballGothread.isWait=false;
//        super.onResume();
//    }
//
//    @Override
//    protected void onPostResume() {
//        trainnigView.ballGothread.isWait=false;
//        super.onPostResume();
//    }
//
//    @Override
//    protected void onDestroy() {
//        trainnigView.ballGothread.isWait=true;
//        trainnigView.ballGothread=null;
//        super.onDestroy();
//    }

    @Override
    public void onBackPressed() {
        trainnigView.ballGothread.isWait=true;
        trainnigView.ballGothread=null;
        GlobalVariable.linevisible=false;
        GlobalVariable.casenum=1;
        GlobalVariable.BallGoDraw=false;
        //MainItent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //MainItent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Log.e("","Log_wrong_cnt"+Log_wrong_count);
        Log.e("","Log_right_cnt"+Log_right_count);
//        myRef.setValue(Log_wrong_count);
//        myRef2.setValue(Log_right_count);

        super.onBackPressed();

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            case REQUEST_CODE_THICK:
                if (resultCode == RESULT_OK){
                    v_thick = intent.getStringExtra("thick");
                    Variable_Switcher=1;
                }
                else if (resultCode == RESULT_CANCELED && v_thick == null) GlobalVariable.SetThick = null;
                break;
            case REQUEST_CODE_POINTNTIP:
                if (resultCode == RESULT_OK) {
                    v_point = intent.getStringExtra("point");
                    v_tip = intent.getStringExtra("tip");
                    if(v_tip.equals("c0"))v_point="b0";
                }
                else if (resultCode == RESULT_CANCELED && v_point == null){
                    GlobalVariable.SetPoint = null;
                    GlobalVariable.SetTip = null;
                }
                break;
            case REQUEST_CODE_STROKE:
                if (resultCode == RESULT_OK) {
                    v_stroke = intent.getStringExtra("strock");
                }
                else if (resultCode == RESULT_CANCELED && v_stroke == null) GlobalVariable.SetStrock = null;
                break;
            case REQUEST_CODE_MENU:
                if (resultCode == RESULT_OK){
                    trainnigView.ballGothread.isWait=true;
                    trainnigView.ballGothread=null;
                    GlobalVariable.casenum=1;
                    GlobalVariable.BallGoDraw=false;
                }
                else {
                    trainnigView.ballGothread.isWait=true;
                    trainnigView.ballGothread=null;
                    GlobalVariable.BallGoDraw=false;
                    v_thick=null;
                    v_point=null;
                    v_tip=null;
                    v_stroke =null;
                }
                break;
        }
        checkValue(v_point, v_tip, v_thick, v_stroke);
    }

    public void checkValue(String vpoint, String vtip, String vthick,String vstrock) {
        GlobalVariable.setVariable(vpoint, vtip, vthick);
        if(vthick != null && vtip != null && vpoint != null && vstrock != null){
            Variable_change();
            if (vpoint.equals(CorrectBasicPoint) && vthick.equals(CorrectBasicThick) && vtip.equals(CorrectBasicTip) && vstrock.equals(CorrectStroke)) {
                Intent small2_intent = new Intent(getApplicationContext(), Small_dialog.class);
                small2_intent.putExtra("Message","correct");
                startActivity(small2_intent);
                Wrong_swtich=1;
//                Correct_imageSwitcher.setImageResource(Correct_Switcher_Array[1]);
//                Correct_imageSwitcher.invalidate();
                Play_Button.setVisibility(View.VISIBLE);
                Play_Button.setEnabled(true);
                //Log_right_count++;
                Log.e("","Log_right_count"+Log_right_count);
                if (RouteLevel.equals("ap_dwidol1") || RouteLevel.equals("ap_dwidol2") || RouteLevel.equals("ap_dwidol3")
                        || RouteLevel.equals("ap_dwidol4") || RouteLevel.equals("ap_dwidol5") || RouteLevel.equals("ap_dwidol6")){
                    GlobalVariable.backspin_right_cnt++;
                    GlobalVariable.right_cnt++;
                }
                else if (RouteLevel.equals("ap_apdol1") || RouteLevel.equals("ap_apdol2") || RouteLevel.equals("ap_apdol3")
                        || RouteLevel.equals("ap_apdol6") || RouteLevel.equals("ap_apdol5") || RouteLevel.equals("ap_apdol4") ||
                        RouteLevel.equals("ap_apdol7") || RouteLevel.equals("ap_apdol8")){
                    GlobalVariable.frontspin_right_cnt++;
                    GlobalVariable.right_cnt++;
                }
                else if (RouteLevel.equals("ap_bitgyou1") || RouteLevel.equals("ap_bitgyou2") || RouteLevel.equals("ap_bitgyou3")){
                    GlobalVariable.bitgyou_right_cnt++;
                    GlobalVariable.right_cnt++;
                }
                else if (RouteLevel.equals("ap_doublecushion1") || RouteLevel.equals("ap_doublecushion2") || RouteLevel.equals("ap_doublecushion3")
                            || RouteLevel.equals("ap_doublecushion4") || RouteLevel.equals("ap_doublecushion5")){
                    GlobalVariable.traverse_right_cnt++;
                    GlobalVariable.right_cnt++;
                }
                else if (RouteLevel.equals("ap_ggeoga1") || RouteLevel.equals("ap_ggeoga2") || RouteLevel.equals("ap_ggeoga3")
                        || RouteLevel.equals("ap_ggeoga4") || RouteLevel.equals("ap_ggeoga5")){
                    GlobalVariable.ggeoga_right_cnt++;
                    GlobalVariable.right_cnt++;
                }
                else if (RouteLevel.equals("ap_youpdol1") || RouteLevel.equals("ap_youpdol2") || RouteLevel.equals("ap_youpdol3")
                        || RouteLevel.equals("ap_youpdol4") || RouteLevel.equals("ap_youpdol5") || RouteLevel.equals("ap_youpdol6") ||
                        RouteLevel.equals("ap_youpdol7") || RouteLevel.equals("ap_youpdol8") || RouteLevel.equals("ap_youpdol9")){
                    GlobalVariable.sidespin_right_cnt++;
                    GlobalVariable.right_cnt++;
                }
            }
        }
        if (vthick != null && vtip != null && vpoint != null){
            if(GlobalVariable.whiteball==1 && GlobalVariable.redball==2)
                ThickNTipNPoint(vthick, vpoint, vtip);
            else if(GlobalVariable.whiteball==1 && GlobalVariable.yellowball==2)
                yThickNTipNPoint(vthick, vpoint, vtip);
        }
        else if (vpoint != null && vtip != null && vthick == null) PointNTip(vpoint, vtip);
        else if (vthick != null && vtip == null && vpoint == null) onlyThick(vthick);
        if (vstrock != null) Strock(vstrock);
    }/*chek {}*/
    /*onActivityResult {} */

    public void printcorrect(){
            Wrong_count=Wrong_count+1;
            if(Wrong_count>=3){
 //               Correct_imageSwitcher.setImageResource(Correct_Switcher_Array[1]);
//                Correct_imageSwitcher.invalidate();
                Wrong_swtich=1;

            }
            GlobalVariable.linevisible=false;
            GlobalVariable.BallGoDraw=false;

        Intent small2_intent = new Intent(getApplicationContext(), Small_dialog.class);
        small2_intent.putExtra("Message","not");
        startActivity(small2_intent);
        Log_wrong_count++;
        Log.e("","wrong_cnt"+Log_wrong_count);
        if (RouteLevel.equals("ap_dwidol1") || RouteLevel.equals("ap_dwidol2") || RouteLevel.equals("ap_dwidol3")
                || RouteLevel.equals("ap_dwidol4") || RouteLevel.equals("ap_dwidol5") || RouteLevel.equals("ap_dwidol6")){
            GlobalVariable.backspin_wrong_cnt++;
            GlobalVariable.wrong_cnt++;
            Log_wrong_count++;
            Log.e("","wrong_cnt"+Log_wrong_count);
        }
        else if (RouteLevel.equals("ap_apdol1") || RouteLevel.equals("ap_apdol2") || RouteLevel.equals("ap_apdol3")
                || RouteLevel.equals("ap_apdol6") || RouteLevel.equals("ap_apdol5") || RouteLevel.equals("ap_apdol4") ||
                RouteLevel.equals("ap_apdol7") || RouteLevel.equals("ap_apdol8")){
            GlobalVariable.frontspin_wrong_cnt++;
            GlobalVariable.wrong_cnt++;
            Log_wrong_count++;
            Log.e("","wrong_cnt"+Log_wrong_count);
        }
        else if (RouteLevel.equals("ap_bitgyou1") || RouteLevel.equals("ap_bitgyou2") || RouteLevel.equals("ap_bitgyou3")){
            GlobalVariable.bitgyou_wrong_cnt++;
            GlobalVariable.wrong_cnt++;
            Log_wrong_count++;
            Log.e("","wrong_cnt"+Log_wrong_count);
        }
        else if (RouteLevel.equals("ap_doublecushion1") || RouteLevel.equals("ap_doublecushion2") || RouteLevel.equals("ap_doublecushion3")
                || RouteLevel.equals("ap_doublecushion4") || RouteLevel.equals("ap_doublecushion5")){
            GlobalVariable.traverse_wrong_cnt++;
            GlobalVariable.wrong_cnt++;
            Log_wrong_count++;
            Log.e("","wrong_cnt"+Log_wrong_count);
        }
        else if (RouteLevel.equals("ap_ggeoga1") || RouteLevel.equals("ap_ggeoga2") || RouteLevel.equals("ap_ggeoga3")
                || RouteLevel.equals("ap_ggeoga4") || RouteLevel.equals("ap_ggeoga5")){
            GlobalVariable.ggeoga_wrong_cnt++;
            GlobalVariable.wrong_cnt++;
            Log_wrong_count++;
            Log.e("","wrong_cnt"+Log_wrong_count);
        }
        else if (RouteLevel.equals("ap_youpdol1") || RouteLevel.equals("ap_youpdol2") || RouteLevel.equals("ap_youpdol3")
                || RouteLevel.equals("ap_youpdol4") || RouteLevel.equals("ap_youpdol5") || RouteLevel.equals("ap_youpdol6") ||
                RouteLevel.equals("ap_youpdol7") || RouteLevel.equals("ap_youpdol8") || RouteLevel.equals("ap_youpdol9")){
            GlobalVariable.sidespin_wrong_cnt++;
            GlobalVariable.wrong_cnt++;
            Log.e("","wrong_cnt"+GlobalVariable.wrong_cnt);
        }
    }

    public void Variable_change(){
        switch(AFile.CaseBasicThick){
            case " 1/8":
                CorrectBasicThick="a1";
                break;
            case " 1/4":
                CorrectBasicThick="a2";
                break;
            case " 3/8":
                CorrectBasicThick="a3";
                break;
            case " 1/2":
                CorrectBasicThick="a4";
                break;
            case " 5/8":
                CorrectBasicThick="a5";
                break;
            case " 6/8":
                CorrectBasicThick="a6";
                break;
            case " 7/8":
                CorrectBasicThick="a7";
                break;
            case " 1/3":
                CorrectBasicThick="a3";
                break;

        }

        switch(AFile.CaseBasicPoint){
            case "중간":
                CorrectBasicPoint="b0";
                break;
            case "1시방향":
                CorrectBasicPoint="b1";
                break;
            case "2시방향":
                CorrectBasicPoint="b2";
                break;
            case "3시방향":
                CorrectBasicPoint="b3";
                break;
            case "4시방향":
                CorrectBasicPoint="b4";
                break;
            case "5시방향":
                CorrectBasicPoint="b5";
                break;
            case "6시방향":
                CorrectBasicPoint="b6";
                break;
            case "7시방향":
                CorrectBasicPoint="b7";
                break;
            case "8시방향":
                CorrectBasicPoint="b8";
                break;
            case "9시방향":
                CorrectBasicPoint="b9";
                break;
            case "10시방향":
                CorrectBasicPoint="b10";
                break;
            case "11시방향":
                CorrectBasicPoint="b11";
                break;
            case "12시방향":
                CorrectBasicPoint="b12";
                break;
        }
        switch(AFile.CaseBasicTip){
            case "0팁":
                CorrectBasicTip="c0";
                break;
            case "1팁":
                CorrectBasicTip="c1";
                break;
            case "2팁":
                CorrectBasicTip="c2";
                break;
            case "3팁":
                CorrectBasicTip="c3";
                break;
            case "4팁":
                CorrectBasicTip="c4";
                break;
        }
        switch(AFile.Stroke) {
            case " 부드럽게 밀어치는 스트로크":
                CorrectStroke ="smooth/push";
                break;
            case " 강하게 밀어치는 스트로크":
                CorrectStroke ="power/push";
                break;
            case " 부드러운 스트로크":
                CorrectStroke ="smooth";
                break;
            case " 부드럽게 끊어치는 스트로크":
                CorrectStroke ="smooth/cut";
                break;
            case " 일반 스트로크":
                CorrectStroke ="normal";
                break;
            case " 강하게 끊어치는 스트로크":
                CorrectStroke ="power/cut";
                break;
        }
    }

   public void Strock(String strock){
        switch (strock){
            case "normal":
                Stroke_text.setText("일반");
                break;
            case "smooth":
                Stroke_text.setText("부드럽게");
                break;
            case "smooth/push":
                Stroke_text.setText("부드럽게 밀어치듯이");
                break;
            case "smooth/cut":
                Stroke_text.setText("부드럽게 끊어치듯이");
                break;
            case "power/push":
                Stroke_text.setText("강하고 밀어치듯이");
                break;
            case "power/cut":
                Stroke_text.setText("강하고 끊어치듯이");
                break;
            default:
                Stroke_text.setText("스트로크");
                break;
        }
    }

    public void onlyThick(String Vthick) {
        if(GlobalVariable.whiteball==1 && GlobalVariable.redball==2) {
            switch (Vthick) {
                case "a1":
                    imageSwitcher.setImageResource(GlobalVariable.OneValueArray[0]);
                    imageSwitcher.invalidate();
                    break;
                case "a2":
                    imageSwitcher.setImageResource(GlobalVariable.OneValueArray[1]);
                    imageSwitcher.invalidate();
                    break;
                case "a3":
                    imageSwitcher.setImageResource(GlobalVariable.OneValueArray[2]);
                    imageSwitcher.invalidate();
                    break;
                case "a4":
                    imageSwitcher.setImageResource(GlobalVariable.OneValueArray[3]);
                    imageSwitcher.invalidate();
                    break;
                case "a5":
                    imageSwitcher.setImageResource(GlobalVariable.OneValueArray[4]);
                    imageSwitcher.invalidate();
                    break;
                case "a6":
                    imageSwitcher.setImageResource(GlobalVariable.OneValueArray[5]);
                    imageSwitcher.invalidate();
                    break;
                case "a7":
                    imageSwitcher.setImageResource(GlobalVariable.OneValueArray[6]);
                    imageSwitcher.invalidate();
                    break;
                case "ar1":
                    imageSwitcher.setImageResource(GlobalVariable.OneValueArray[7]);
                    imageSwitcher.invalidate();
                    break;
                case "ar2":
                    imageSwitcher.setImageResource(GlobalVariable.OneValueArray[8]);
                    imageSwitcher.invalidate();
                    break;
                case "ar3":
                    imageSwitcher.setImageResource(GlobalVariable.OneValueArray[9]);
                    imageSwitcher.invalidate();
                    break;
                case "ar4":
                    imageSwitcher.setImageResource(GlobalVariable.OneValueArray[10]);
                    imageSwitcher.invalidate();
                    break;
                case "ar5":
                    imageSwitcher.setImageResource(GlobalVariable.OneValueArray[11]);
                    imageSwitcher.invalidate();
                    break;
                case "ar6":
                    imageSwitcher.setImageResource(GlobalVariable.OneValueArray[12]);
                    imageSwitcher.invalidate();
                    break;
                case "ar7":
                    imageSwitcher.setImageResource(GlobalVariable.OneValueArray[13]);
                    imageSwitcher.invalidate();
                    break;
            }
        }
        //**********************************************************************************************************************//
        else if(GlobalVariable.whiteball==1 && GlobalVariable.yellowball==2) {
            switch (Vthick) {
                case "a1":
                    imageSwitcher.setImageResource(GlobalVariable.yOneValueArray[0]);
                    imageSwitcher.invalidate();
                    break;
                case "a2":
                    imageSwitcher.setImageResource(GlobalVariable.yOneValueArray[1]);
                    imageSwitcher.invalidate();
                    break;
                case "a3":
                    imageSwitcher.setImageResource(GlobalVariable.yOneValueArray[2]);
                    imageSwitcher.invalidate();
                    break;
                case "a4":
                    imageSwitcher.setImageResource(GlobalVariable.yOneValueArray[3]);
                    imageSwitcher.invalidate();
                    break;
                case "a5":
                    imageSwitcher.setImageResource(GlobalVariable.yOneValueArray[4]);
                    imageSwitcher.invalidate();
                    break;
                case "a6":
                    imageSwitcher.setImageResource(GlobalVariable.yOneValueArray[5]);
                    imageSwitcher.invalidate();
                    break;
                case "a7":
                    imageSwitcher.setImageResource(GlobalVariable.yOneValueArray[6]);
                    imageSwitcher.invalidate();
                    break;
                case "ar1":
                    imageSwitcher.setImageResource(GlobalVariable.yOneValueArray[7]);
                    imageSwitcher.invalidate();
                    break;
                case "ar2":
                    imageSwitcher.setImageResource(GlobalVariable.yOneValueArray[8]);
                    imageSwitcher.invalidate();
                    break;
                case "ar3":
                    imageSwitcher.setImageResource(GlobalVariable.yOneValueArray[9]);
                    imageSwitcher.invalidate();
                    break;
                case "ar4":
                    imageSwitcher.setImageResource(GlobalVariable.yOneValueArray[10]);
                    imageSwitcher.invalidate();
                    break;
                case "ar5":
                    imageSwitcher.setImageResource(GlobalVariable.yOneValueArray[11]);
                    imageSwitcher.invalidate();
                    break;
                case "ar6":
                    imageSwitcher.setImageResource(GlobalVariable.yOneValueArray[12]);
                    imageSwitcher.invalidate();
                    break;
                case "ar7":
                    imageSwitcher.setImageResource(GlobalVariable.yOneValueArray[13]);
                    imageSwitcher.invalidate();
                    break;
            }
        }
    }

    public void PointNTip(String Vpoint,String Vtip){
        if(GlobalVariable.whiteball==1) {
            if (Vtip.equals("c0")) {
                imageSwitcher.setImageResource(GlobalVariable.NoTipArray[14]);
                imageSwitcher.invalidate();
            }
            switch (Vpoint) {
                case "b1":
                    switch (Vtip) {
                        case "c1":
                            imageSwitcher.setImageResource(GlobalVariable.TwoValueArray[0]);
                            imageSwitcher.invalidate();
                            break;
                        case "c2":
                            imageSwitcher.setImageResource(GlobalVariable.TwoValueArray[1]);
                            imageSwitcher.invalidate();
                            break;
                        case "c3":
                            imageSwitcher.setImageResource(GlobalVariable.TwoValueArray[2]);
                            imageSwitcher.invalidate();
                            break;
                        case "c4":
                            imageSwitcher.setImageResource(GlobalVariable.TwoValueArray[3]);
                            imageSwitcher.invalidate();
                            break;
                    }
                    break;
                case "b2":
                    switch (Vtip) {
                        case "c1":
                            imageSwitcher.setImageResource(GlobalVariable.TwoValueArray[4]);
                            imageSwitcher.invalidate();
                            break;
                        case "c2":
                            imageSwitcher.setImageResource(GlobalVariable.TwoValueArray[5]);
                            imageSwitcher.invalidate();
                            break;
                        case "c3":
                            imageSwitcher.setImageResource(GlobalVariable.TwoValueArray[6]);
                            imageSwitcher.invalidate();
                            break;
                        case "c4":
                            imageSwitcher.setImageResource(GlobalVariable.TwoValueArray[7]);
                            imageSwitcher.invalidate();
                            break;
                    }
                    break;
                case "b3":
                    switch (Vtip) {
                        case "c1":
                            imageSwitcher.setImageResource(GlobalVariable.TwoValueArray[8]);
                            imageSwitcher.invalidate();
                            break;
                        case "c2":
                            imageSwitcher.setImageResource(GlobalVariable.TwoValueArray[9]);
                            imageSwitcher.invalidate();
                            break;
                        case "c3":
                            imageSwitcher.setImageResource(GlobalVariable.TwoValueArray[10]);
                            imageSwitcher.invalidate();
                            break;
                        case "c4":
                            imageSwitcher.setImageResource(GlobalVariable.TwoValueArray[11]);
                            imageSwitcher.invalidate();
                            break;
                    }
                    break;
                case "b4":
                    switch (Vtip) {
                        case "c1":
                            imageSwitcher.setImageResource(GlobalVariable.TwoValueArray[12]);
                            imageSwitcher.invalidate();
                            break;
                        case "c2":
                            imageSwitcher.setImageResource(GlobalVariable.TwoValueArray[13]);
                            imageSwitcher.invalidate();
                            break;
                        case "c3":
                            imageSwitcher.setImageResource(GlobalVariable.TwoValueArray[14]);
                            imageSwitcher.invalidate();
                            break;
                        case "c4":
                            imageSwitcher.setImageResource(GlobalVariable.TwoValueArray[15]);
                            imageSwitcher.invalidate();
                            break;
                    }
                    break;
                case "b5":
                    switch (Vtip) {
                        case "c1":
                            imageSwitcher.setImageResource(GlobalVariable.TwoValueArray[16]);
                            imageSwitcher.invalidate();
                            break;
                        case "c2":
                            imageSwitcher.setImageResource(GlobalVariable.TwoValueArray[17]);
                            imageSwitcher.invalidate();
                            break;
                        case "c3":
                            imageSwitcher.setImageResource(GlobalVariable.TwoValueArray[18]);
                            imageSwitcher.invalidate();
                            break;
                        case "c4":
                            imageSwitcher.setImageResource(GlobalVariable.TwoValueArray[19]);
                            imageSwitcher.invalidate();
                            break;
                    }
                    break;
                case "b6":
                    switch (Vtip) {
                        case "c1":
                            imageSwitcher.setImageResource(GlobalVariable.TwoValueArray[20]);
                            imageSwitcher.invalidate();
                            break;
                        case "c2":
                            imageSwitcher.setImageResource(GlobalVariable.TwoValueArray[21]);
                            imageSwitcher.invalidate();
                            break;
                        case "c3":
                            imageSwitcher.setImageResource(GlobalVariable.TwoValueArray[22]);
                            imageSwitcher.invalidate();
                            break;
                        case "c4":
                            imageSwitcher.setImageResource(GlobalVariable.TwoValueArray[23]);
                            imageSwitcher.invalidate();
                            break;
                    }
                    break;
                case "b7":
                    switch (Vtip) {
                        case "c1":
                            imageSwitcher.setImageResource(GlobalVariable.TwoValueArray[24]);
                            imageSwitcher.invalidate();
                            break;
                        case "c2":
                            imageSwitcher.setImageResource(GlobalVariable.TwoValueArray[25]);
                            imageSwitcher.invalidate();
                            break;
                        case "c3":
                            imageSwitcher.setImageResource(GlobalVariable.TwoValueArray[26]);
                            imageSwitcher.invalidate();
                            break;
                        case "c4":
                            imageSwitcher.setImageResource(GlobalVariable.TwoValueArray[27]);
                            imageSwitcher.invalidate();
                            break;
                    }
                    break;
                case "b8":
                    switch (Vtip) {
                        case "c1":
                            imageSwitcher.setImageResource(GlobalVariable.TwoValueArray[28]);
                            imageSwitcher.invalidate();
                            break;
                        case "c2":
                            imageSwitcher.setImageResource(GlobalVariable.TwoValueArray[29]);
                            imageSwitcher.invalidate();
                            break;
                        case "c3":
                            imageSwitcher.setImageResource(GlobalVariable.TwoValueArray[30]);
                            imageSwitcher.invalidate();
                            break;
                        case "c4":
                            imageSwitcher.setImageResource(GlobalVariable.TwoValueArray[31]);
                            imageSwitcher.invalidate();
                            break;
                    }
                    break;
                case "b9":
                    switch (Vtip) {
                        case "c1":
                            imageSwitcher.setImageResource(GlobalVariable.TwoValueArray[32]);
                            imageSwitcher.invalidate();
                            break;
                        case "c2":
                            imageSwitcher.setImageResource(GlobalVariable.TwoValueArray[33]);
                            imageSwitcher.invalidate();
                            break;
                        case "c3":
                            imageSwitcher.setImageResource(GlobalVariable.TwoValueArray[34]);
                            imageSwitcher.invalidate();
                            break;
                        case "c4":
                            imageSwitcher.setImageResource(GlobalVariable.TwoValueArray[35]);
                            imageSwitcher.invalidate();
                            break;
                    }
                    break;
                case "b10":
                    switch (Vtip) {
                        case "c1":
                            imageSwitcher.setImageResource(GlobalVariable.TwoValueArray[36]);
                            imageSwitcher.invalidate();
                            break;
                        case "c2":
                            imageSwitcher.setImageResource(GlobalVariable.TwoValueArray[37]);
                            imageSwitcher.invalidate();
                            break;
                        case "c3":
                            imageSwitcher.setImageResource(GlobalVariable.TwoValueArray[38]);
                            imageSwitcher.invalidate();
                            break;
                        case "c4":
                            imageSwitcher.setImageResource(GlobalVariable.TwoValueArray[39]);
                            imageSwitcher.invalidate();
                            break;
                    }
                    break;
                case "b11":
                    switch (Vtip) {
                        case "c1":
                            imageSwitcher.setImageResource(GlobalVariable.TwoValueArray[40]);
                            imageSwitcher.invalidate();
                            break;
                        case "c2":
                            imageSwitcher.setImageResource(GlobalVariable.TwoValueArray[41]);
                            imageSwitcher.invalidate();
                            break;
                        case "c3":
                            imageSwitcher.setImageResource(GlobalVariable.TwoValueArray[42]);
                            imageSwitcher.invalidate();
                            break;
                        case "c4":
                            imageSwitcher.setImageResource(GlobalVariable.TwoValueArray[43]);
                            imageSwitcher.invalidate();
                            break;
                    }
                    break;
                case "b12":
                    switch (Vtip) {
                        case "c1":
                            imageSwitcher.setImageResource(GlobalVariable.TwoValueArray[44]);
                            imageSwitcher.invalidate();
                            break;
                        case "c2":
                            imageSwitcher.setImageResource(GlobalVariable.TwoValueArray[45]);
                            imageSwitcher.invalidate();
                            break;
                        case "c3":
                            imageSwitcher.setImageResource(GlobalVariable.TwoValueArray[46]);
                            imageSwitcher.invalidate();
                            break;
                        case "c4":
                            imageSwitcher.setImageResource(GlobalVariable.TwoValueArray[47]);
                            imageSwitcher.invalidate();
                            break;
                    }
                    break;
            }
        }

    }

    public void ThickNTipNPoint(String Vthick,String Vpoint,String Vtip){
        switch (Vthick) {
            case "a1":
                if (Vtip.equals("c0")) {
                    imageSwitcher.setImageResource(GlobalVariable.NoTipArray[0]);
                    imageSwitcher.invalidate();
                }
                switch (Vpoint) {
                    case "b1":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[0]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[1]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[2]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[3]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b2":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[4]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[5]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[6]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[7]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b3":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[8]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[9]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[10]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[11]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b4":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[12]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[13]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[14]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[15]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b5":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[16]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[17]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[18]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[19]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b6":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[20]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[21]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[22]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[23]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b7":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[24]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[25]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[26]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[27]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b8":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[28]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[29]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[30]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[31]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b9":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[32]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[33]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[34]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[35]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b10":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[36]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[37]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[38]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[39]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b11":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[40]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[41]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[42]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[43]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b12":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[44]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[45]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[46]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[47]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                }
                break;
            case "a2":
                if (Vtip.equals("c0")) {
                    imageSwitcher.setImageResource(GlobalVariable.NoTipArray[1]);
                    imageSwitcher.invalidate();
                }
                switch (Vpoint) {
                    case "b1":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[48]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[49]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[50]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[51]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b2":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[52]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[53]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[54]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[55]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b3":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[56]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[57]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[58]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[59]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b4":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[60]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[61]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[62]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[63]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b5":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[64]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[65]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[66]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[67]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b6":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[68]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[69]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[70]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[71]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b7":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[72]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[73]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[74]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[75]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b8":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[76]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[77]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[78]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[79]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b9":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[80]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[81]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[82]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[83]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b10":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[84]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[85]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[86]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[87]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b11":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[88]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[89]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[90]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[91]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b12":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[92]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[93]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[94]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[95]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                }
                break;
            case "a3":
                if (Vtip.equals("c0")) {
                    imageSwitcher.setImageResource(GlobalVariable.NoTipArray[2]);
                    imageSwitcher.invalidate();
                }
                switch (Vpoint) {
                    case "b1":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[96]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[97]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[98]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[99]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b2":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[100]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[101]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[102]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[103]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b3":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[104]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[105]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[106]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[107]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b4":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[108]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[109]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[110]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[111]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b5":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[112]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[113]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[114]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[115]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b6":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[116]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[117]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[118]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[119]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b7":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[120]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[121]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[122]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[123]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b8":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[124]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[125]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[126]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[127]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b9":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[128]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[129]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[130]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[131]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b10":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[132]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[133]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[134]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[135]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b11":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[136]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[137]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[138]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[139]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b12":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[140]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[141]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[142]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[143]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                }
                break;
            case "a4":
                if (Vtip.equals("c0")) {
                    imageSwitcher.setImageResource(GlobalVariable.NoTipArray[3]);
                    imageSwitcher.invalidate();
                }
                switch (Vpoint) {
                    case "b1":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[144]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[145]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[146]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[147]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b2":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[148]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[149]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[150]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[151]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b3":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[152]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[153]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[154]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[155]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b4":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[156]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[157]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[158]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[159]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b5":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[160]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[161]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[162]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[163]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b6":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[164]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[165]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[166]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[167]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b7":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[168]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[169]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[170]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[171]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b8":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[172]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[173]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[174]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[175]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b9":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[176]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[177]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[178]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[179]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b10":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[180]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[181]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[182]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[183]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b11":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[184]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[185]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[186]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[187]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b12":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[188]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[189]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[190]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[191]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                }
                break;
            case "a5":
                if (Vtip.equals("c0")) {
                    imageSwitcher.setImageResource(GlobalVariable.NoTipArray[4]);
                    imageSwitcher.invalidate();
                }
                switch (Vpoint) {
                    case "b1":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[192]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[193]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[194]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[195]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b2":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[196]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[197]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[198]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[199]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b3":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[200]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[201]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[202]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[203]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b4":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[204]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[205]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[206]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[207]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b5":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[208]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[209]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[210]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[211]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b6":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[212]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[213]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[214]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[215]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b7":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[216]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[217]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[218]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[219]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b8":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[220]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[221]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[222]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[223]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b9":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[224]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[225]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[226]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[227]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b10":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[228]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[229]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[230]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[231]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b11":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[232]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[233]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[234]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[235]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b12":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[236]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[237]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[238]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[239]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                }
                break;
            case "a6":
                if (Vtip.equals("c0")) {
                    imageSwitcher.setImageResource(GlobalVariable.NoTipArray[5]);
                    imageSwitcher.invalidate();
                }
                switch (Vpoint) {
                    case "b1":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[240]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[241]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[242]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[243]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b2":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[244]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[245]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[246]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[247]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b3":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[248]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[249]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[250]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[251]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b4":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[252]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[253]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[254]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[255]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b5":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[256]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[257]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[258]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[259]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b6":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[260]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[261]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[262]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[263]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b7":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[264]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[265]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[266]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[267]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b8":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[268]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[269]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[270]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[271]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b9":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[272]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[273]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[274]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[275]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b10":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[276]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[277]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[278]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[279]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b11":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[280]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[281]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[282]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[283]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b12":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[284]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[285]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[286]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[287]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                }
                break;
            case "a7":
                if (Vtip.equals("c0")) {
                    imageSwitcher.setImageResource(GlobalVariable.NoTipArray[6]);
                    imageSwitcher.invalidate();
                }
                switch (Vpoint) {
                    case "b1":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[288]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[289]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[290]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[291]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b2":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[292]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[293]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[294]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[295]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b3":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[296]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[297]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[298]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[299]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b4":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[300]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[301]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[302]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[303]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b5":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[304]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[305]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[306]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[307]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b6":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[308]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[309]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[310]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[311]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b7":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[312]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[313]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[314]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[315]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b8":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[316]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[317]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[318]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[319]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b9":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[320]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[321]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[322]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[323]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b10":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[324]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[325]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[326]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[327]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b11":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[328]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[329]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[330]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[331]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b12":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[332]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[333]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[334]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[335]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                }
                break;
            case "ar1":
                if (Vtip.equals("c0")) {
                    imageSwitcher.setImageResource(GlobalVariable.NoTipArray[7]);
                    imageSwitcher.invalidate();
                }
                switch (Vpoint) {
                    case "b1":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[336]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[337]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[338]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[339]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b2":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[340]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[341]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[342]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[343]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b3":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[344]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[345]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[346]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[347]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b4":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[348]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[349]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[350]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[351]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b5":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[352]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[353]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[354]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[355]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b6":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[356]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[357]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[358]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[359]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b7":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[360]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[361]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[362]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[363]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b8":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[364]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[365]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[366]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[367]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b9":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[368]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[369]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[370]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[371]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b10":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[372]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[373]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[374]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[375]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b11":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[376]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[377]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[378]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[379]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b12":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[380]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[381]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[382]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[383]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                }
                break;
            case "ar2":
                if (Vtip.equals("c0")) {
                    imageSwitcher.setImageResource(GlobalVariable.NoTipArray[8]);
                    imageSwitcher.invalidate();
                }
                switch (Vpoint) {
                    case "b1":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[384]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[385]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[386]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[387]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b2":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[388]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[389]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[390]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[391]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b3":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[392]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[393]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[394]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[395]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b4":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[396]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[397]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[398]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[399]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b5":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[400]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[401]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[402]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[403]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b6":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[404]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[405]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[406]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[407]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b7":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[408]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[409]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[410]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[411]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b8":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[412]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[413]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[414]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[415]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b9":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[416]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[417]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[418]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[419]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b10":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[420]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[421]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[422]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[423]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b11":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[424]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[425]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[426]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[427]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b12":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[428]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[429]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[430]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[431]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                }
                break;
            case "ar3":
                if (Vtip.equals("c0")) {
                    imageSwitcher.setImageResource(GlobalVariable.NoTipArray[9]);
                    imageSwitcher.invalidate();
                }
                switch (Vpoint) {
                    case "b1":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[432]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[433]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[434]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[435]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b2":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[436]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[437]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[438]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[439]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b3":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[440]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[441]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[442]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[443]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b4":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[444]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[445]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[446]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[447]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b5":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[448]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[449]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[450]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[451]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b6":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[452]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[453]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[454]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[455]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b7":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[456]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[457]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[458]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[459]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b8":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[460]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[461]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[462]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[463]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b9":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[464]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[465]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[466]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[467]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b10":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[468]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[469]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[470]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[471]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b11":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[472]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[473]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[474]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[475]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b12":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[476]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[477]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[478]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[479]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                }
                break;
            case "ar4":
                if (Vtip.equals("c0")) {
                    imageSwitcher.setImageResource(GlobalVariable.NoTipArray[10]);
                    imageSwitcher.invalidate();
                }
                switch (Vpoint) {
                    case "b1":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[480]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[481]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[482]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[483]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b2":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[484]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[485]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[486]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[487]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b3":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[488]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[489]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[490]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[491]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b4":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[492]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[493]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[494]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[495]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b5":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[496]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[497]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[498]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[499]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b6":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[500]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[501]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[502]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[503]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b7":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[504]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[505]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[506]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[507]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b8":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[508]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[509]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[510]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[511]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b9":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[512]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[513]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[514]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[515]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b10":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[516]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[517]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[518]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[519]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b11":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[520]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[521]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[522]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[523]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b12":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[524]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[525]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[526]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[527]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                }
                break;
            case "ar5":
                if (Vtip.equals("c0")) {
                    imageSwitcher.setImageResource(GlobalVariable.NoTipArray[11]);
                    imageSwitcher.invalidate();
                }
                switch (Vpoint) {
                    case "b1":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[528]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[529]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[530]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[531]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b2":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[532]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[533]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[534]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[535]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b3":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[536]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[537]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[538]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[539]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b4":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[540]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[541]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[542]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[543]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b5":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[544]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[545]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[546]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[547]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b6":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[548]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[549]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[550]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[551]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b7":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[552]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[553]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[554]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[555]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b8":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[556]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[557]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[558]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[559]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b9":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[560]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[561]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[562]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[563]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b10":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[564]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[565]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[566]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[567]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b11":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[568]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[569]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[570]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[571]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b12":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[572]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[573]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[574]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[575]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                }
                break;
            case "ar6":
                if (Vtip.equals("c0")) {
                    imageSwitcher.setImageResource(GlobalVariable.NoTipArray[12]);
                    imageSwitcher.invalidate();
                }
                switch (Vpoint) {
                    case "b1":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[576]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[577]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[578]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[579]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b2":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[580]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[581]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[582]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[583]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b3":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[584]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[585]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[586]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[587]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b4":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[588]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[589]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[590]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[591]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b5":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[592]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[593]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[594]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[595]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b6":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[596]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[597]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[598]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[599]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b7":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[600]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[601]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[602]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[603]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b8":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[604]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[605]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[606]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[607]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b9":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[608]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[609]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[610]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[611]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b10":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[612]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[613]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[614]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[615]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b11":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[616]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[617]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[618]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[619]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b12":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[620]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[621]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[622]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[623]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                }
                break;
            case "ar7":
                if (Vtip.equals("c0")) {
                    imageSwitcher.setImageResource(GlobalVariable.NoTipArray[13]);
                    imageSwitcher.invalidate();
                }
                switch (Vpoint) {
                    case "b1":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[624]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[625]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[626]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[627]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b2":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[628]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[629]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[630]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[631]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b3":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[632]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[633]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[634]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[635]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b4":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[636]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[637]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[638]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[639]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b5":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[640]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[641]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[642]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[643]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b6":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[644]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[645]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[646]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[647]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b7":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[648]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[649]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[650]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[651]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b8":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[652]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[653]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[654]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[655]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b9":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[656]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[657]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[658]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[659]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b10":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[660]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[661]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[662]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[663]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b11":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[664]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[665]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[666]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[667]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b12":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[668]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[669]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[670]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.ThreeValueArray[671]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                }
                break;
        }
    }

    public void yThickNTipNPoint(String Vthick, String Vpoint, String Vtip){
        switch (Vthick) {
            case "a1":
                if (Vtip.equals("c0")) {
                    imageSwitcher.setImageResource(GlobalVariable.yNoTipArray[0]);
                    imageSwitcher.invalidate();
                }
                switch (Vpoint) {
                    case "b1":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[0]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[1]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[2]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[3]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b2":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[4]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[5]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[6]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[7]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b3":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[8]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[9]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[10]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[11]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b4":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[12]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[13]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[14]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[15]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b5":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[16]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[17]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[18]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[19]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b6":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[20]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[21]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[22]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[23]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b7":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[24]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[25]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[26]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[27]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b8":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[28]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[29]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[30]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[31]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b9":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[32]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[33]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[34]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[35]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b10":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[36]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[37]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[38]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[39]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b11":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[40]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[41]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[42]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[43]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b12":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[44]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[45]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[46]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[47]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                }
                break;
            case "a2":
                if (Vtip.equals("c0")) {
                    imageSwitcher.setImageResource(GlobalVariable.yNoTipArray[1]);
                    imageSwitcher.invalidate();
                }
                switch (Vpoint) {
                    case "b1":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[48]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[49]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[50]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[51]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b2":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[52]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[53]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[54]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[55]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b3":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[56]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[57]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[58]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[59]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b4":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[60]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[61]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[62]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[63]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b5":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[64]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[65]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[66]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[67]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b6":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[68]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[69]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[70]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[71]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b7":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[72]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[73]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[74]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[75]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b8":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[76]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[77]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[78]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[79]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b9":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[80]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[81]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[82]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[83]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b10":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[84]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[85]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[86]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[87]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b11":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[88]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[89]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[90]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[91]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b12":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[92]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[93]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[94]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[95]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                }
                break;
            case "a3":
                if (Vtip.equals("c0")) {
                    imageSwitcher.setImageResource(GlobalVariable.yNoTipArray[2]);
                    imageSwitcher.invalidate();
                }
                switch (Vpoint) {
                    case "b1":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[96]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[97]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[98]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[99]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b2":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[100]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[101]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[102]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[103]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b3":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[104]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[105]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[106]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[107]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b4":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[108]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[109]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[110]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[111]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b5":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[112]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[113]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[114]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[115]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b6":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[116]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[117]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[118]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[119]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b7":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[120]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[121]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[122]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[123]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b8":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[124]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[125]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[126]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[127]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b9":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[128]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[129]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[130]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[131]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b10":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[132]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[133]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[134]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[135]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b11":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[136]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[137]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[138]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[139]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b12":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[140]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[141]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[142]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[143]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                }
                break;
            case "a4":
                if (Vtip.equals("c0")) {
                    imageSwitcher.setImageResource(GlobalVariable.yNoTipArray[3]);
                    imageSwitcher.invalidate();
                }
                switch (Vpoint) {
                    case "b1":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[144]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[145]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[146]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[147]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b2":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[148]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[149]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[150]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[151]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b3":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[152]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[153]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[154]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[155]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b4":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[156]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[157]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[158]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[159]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b5":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[160]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[161]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[162]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[163]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b6":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[164]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[165]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[166]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[167]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b7":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[168]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[169]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[170]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[171]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b8":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[172]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[173]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[174]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[175]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b9":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[176]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[177]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[178]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[179]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b10":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[180]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[181]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[182]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[183]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b11":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[184]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[185]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[186]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[187]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b12":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[188]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[189]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[190]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[191]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                }
                break;
            case "a5":
                if (Vtip.equals("c0")) {
                    imageSwitcher.setImageResource(GlobalVariable.yNoTipArray[4]);
                    imageSwitcher.invalidate();
                }
                switch (Vpoint) {
                    case "b1":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[192]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[193]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[194]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[195]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b2":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[196]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[197]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[198]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[199]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b3":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[200]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[201]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[202]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[203]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b4":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[204]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[205]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[206]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[207]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b5":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[208]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[209]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[210]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[211]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b6":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[212]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[213]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[214]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[215]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b7":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[216]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[217]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[218]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[219]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b8":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[220]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[221]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[222]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[223]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b9":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[224]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[225]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[226]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[227]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b10":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[228]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[229]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[230]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[231]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b11":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[232]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[233]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[234]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[235]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b12":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[236]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[237]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[238]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[239]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                }
                break;
            case "a6":
                if (Vtip.equals("c0")) {
                    imageSwitcher.setImageResource(GlobalVariable.yNoTipArray[5]);
                    imageSwitcher.invalidate();
                }
                switch (Vpoint) {
                    case "b1":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[240]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[241]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[242]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[243]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b2":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[244]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[245]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[246]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[247]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b3":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[248]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[249]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[250]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[251]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b4":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[252]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[253]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[254]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[255]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b5":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[256]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[257]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[258]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[259]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b6":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[260]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[261]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[262]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[263]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b7":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[264]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[265]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[266]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[267]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b8":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[268]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[269]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[270]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[271]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b9":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[272]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[273]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[274]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[275]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b10":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[276]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[277]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[278]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[279]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b11":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[280]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[281]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[282]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[283]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b12":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[284]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[285]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[286]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[287]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                }
                break;
            case "a7":
                if (Vtip.equals("c0")) {
                    imageSwitcher.setImageResource(GlobalVariable.yNoTipArray[6]);
                    imageSwitcher.invalidate();
                }
                switch (Vpoint) {
                    case "b1":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[288]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[289]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[290]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[291]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b2":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[292]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[293]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[294]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[295]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b3":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[296]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[297]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[298]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[299]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b4":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[300]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[301]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[302]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[303]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b5":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[304]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[305]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[306]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[307]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b6":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[308]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[309]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[310]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[311]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b7":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[312]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[313]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[314]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[315]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b8":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[316]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[317]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[318]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[319]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b9":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[320]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[321]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[322]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[323]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b10":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[324]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[325]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[326]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[327]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b11":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[328]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[329]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[330]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[331]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b12":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[332]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[333]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[334]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[335]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                }
                break;
            case "ar1":
                if (Vtip.equals("c0")) {
                    imageSwitcher.setImageResource(GlobalVariable.yNoTipArray[7]);
                    imageSwitcher.invalidate();
                }
                switch (Vpoint) {
                    case "b1":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[336]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[337]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[338]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[339]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b2":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[340]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[341]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[342]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[343]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b3":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[344]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[345]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[346]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[347]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b4":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[348]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[349]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[350]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[351]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b5":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[352]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[353]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[354]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[355]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b6":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[356]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[357]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[358]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[359]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b7":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[360]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[361]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[362]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[363]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b8":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[364]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[365]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[366]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[367]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b9":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[368]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[369]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[370]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[371]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b10":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[372]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[373]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[374]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[375]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b11":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[376]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[377]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[378]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[379]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b12":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[380]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[381]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[382]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[383]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                }
                break;
            case "ar2":
                if (Vtip.equals("c0")) {
                    imageSwitcher.setImageResource(GlobalVariable.yNoTipArray[8]);
                    imageSwitcher.invalidate();
                }
                switch (Vpoint) {
                    case "b1":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[384]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[385]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[386]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[387]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b2":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[388]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[389]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[390]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[391]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b3":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[392]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[393]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[394]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[395]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b4":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[396]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[397]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[398]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[399]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b5":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[400]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[401]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[402]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[403]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b6":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[404]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[405]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[406]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[407]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b7":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[408]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[409]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[410]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[411]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b8":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[412]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[413]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[414]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[415]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b9":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[416]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[417]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[418]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[419]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b10":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[420]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[421]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[422]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[423]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b11":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[424]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[425]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[426]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[427]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b12":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[428]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[429]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[430]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[431]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                }
                break;
            case "ar3":
                if (Vtip.equals("c0")) {
                    imageSwitcher.setImageResource(GlobalVariable.yNoTipArray[9]);
                    imageSwitcher.invalidate();
                }
                switch (Vpoint) {
                    case "b1":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[432]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[433]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[434]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[435]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b2":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[436]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[437]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[438]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[439]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b3":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[440]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[441]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[442]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[443]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b4":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[444]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[445]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[446]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[447]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b5":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[448]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[449]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[450]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[451]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b6":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[452]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[453]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[454]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[455]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b7":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[456]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[457]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[458]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[459]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b8":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[460]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[461]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[462]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[463]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b9":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[464]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[465]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[466]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[467]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b10":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[468]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[469]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[470]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[471]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b11":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[472]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[473]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[474]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[475]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b12":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[476]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[477]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[478]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[479]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                }
                break;
            case "ar4":
                if (Vtip.equals("c0")) {
                    imageSwitcher.setImageResource(GlobalVariable.yNoTipArray[10]);
                    imageSwitcher.invalidate();
                }
                switch (Vpoint) {
                    case "b1":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[480]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[481]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[482]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[483]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b2":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[484]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[485]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[486]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[487]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b3":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[488]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[489]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[490]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[491]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b4":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[492]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[493]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[494]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[495]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b5":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[496]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[497]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[498]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[499]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b6":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[500]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[501]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[502]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[503]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b7":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[504]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[505]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[506]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[507]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b8":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[508]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[509]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[510]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[511]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b9":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[512]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[513]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[514]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[515]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b10":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[516]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[517]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[518]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[519]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b11":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[520]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[521]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[522]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[523]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b12":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[524]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[525]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[526]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[527]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                }
                break;
            case "ar5":
                if (Vtip.equals("c0")) {
                    imageSwitcher.setImageResource(GlobalVariable.yNoTipArray[11]);
                    imageSwitcher.invalidate();
                }
                switch (Vpoint) {
                    case "b1":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[528]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[529]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[530]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[531]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b2":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[532]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[533]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[534]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[535]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b3":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[536]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[537]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[538]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[539]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b4":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[540]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[541]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[542]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[543]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b5":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[544]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[545]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[546]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[547]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b6":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[548]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[549]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[550]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[551]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b7":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[552]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[553]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[554]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[555]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b8":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[556]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[557]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[558]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[559]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b9":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[560]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[561]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[562]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[563]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b10":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[564]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[565]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[566]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[567]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b11":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[568]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[569]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[570]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[571]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b12":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[572]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[573]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[574]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[575]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                }
                break;
            case "ar6":
                if (Vtip.equals("c0")) {
                    imageSwitcher.setImageResource(GlobalVariable.yNoTipArray[12]);
                    imageSwitcher.invalidate();
                }
                switch (Vpoint) {
                    case "b1":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[576]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[577]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[578]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[579]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b2":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[580]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[581]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[582]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[583]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b3":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[584]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[585]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[586]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[587]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b4":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[588]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[589]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[590]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[591]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b5":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[592]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[593]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[594]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[595]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b6":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[596]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[597]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[598]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[599]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b7":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[600]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[601]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[602]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[603]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b8":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[604]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[605]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[606]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[607]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b9":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[608]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[609]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[610]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[611]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b10":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[612]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[613]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[614]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[615]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b11":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[616]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[617]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[618]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[619]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b12":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[620]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[621]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[622]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[623]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                }
                break;
            case "ar7":
                if (Vtip.equals("c0")) {
                    imageSwitcher.setImageResource(GlobalVariable.yNoTipArray[13]);
                    imageSwitcher.invalidate();
                }
                switch (Vpoint) {
                    case "b1":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[624]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[625]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[626]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[627]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b2":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[628]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[629]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[630]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[631]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b3":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[632]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[633]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[634]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[635]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b4":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[636]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[637]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[638]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[639]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b5":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[640]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[641]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[642]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[643]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b6":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[644]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[645]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[646]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[647]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b7":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[648]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[649]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[650]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[651]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b8":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[652]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[653]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[654]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[655]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b9":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[656]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[657]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[658]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[659]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b10":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[660]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[661]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[662]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[663]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b11":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[664]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[665]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[666]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[667]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                    case "b12":
                        switch (Vtip) {
                            case "c1":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[668]);
                                imageSwitcher.invalidate();
                                break;
                            case "c2":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[669]);
                                imageSwitcher.invalidate();
                                break;
                            case "c3":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[670]);
                                imageSwitcher.invalidate();
                                break;
                            case "c4":
                                imageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[671]);
                                imageSwitcher.invalidate();
                                break;
                        }
                        break;
                }
                break;
        }
    }


    public int case_count(){
        switch (GlobalVariable.Global_Difficulty) {
            case "B":
                if (RouteLevel.equals("bp_dwidol1")) return 4;
                else if (RouteLevel.equals("bp_dwidol2")) return 3;
                else if (RouteLevel.equals("bp_apdol1")) return 2;
                else if (RouteLevel.equals("bp_apdol2")) return 3;
                else if (RouteLevel.equals("bp_apdol3")) return 4;
                else if (RouteLevel.equals("bp_bitgyou1")) return 3;
                else if (RouteLevel.equals("bp_bitgyou2")) return 2;
                else if (RouteLevel.equals("bp_ggeoga1")) return 2;
                else if (RouteLevel.equals("bp_ggeoga2")) return 2;
                else if (RouteLevel.equals("bp_ggeoga3")) return 2;
                else if (RouteLevel.equals("bp_youpdol1")) return 4;
                else if (RouteLevel.equals("bp_youpdol2")) return 4;
                else if (RouteLevel.equals("bp_youpdol3")) return 4;
                else if (RouteLevel.equals("bp_doublecushion1")) return 3;
                else if (RouteLevel.equals("bp_doublecushion2")) return 3;
                break;

            case "A":
                if (RouteLevel.equals("ap_dwidol1")) return 3;
                else if (RouteLevel.equals("ap_dwidol2")) return 3;
                else if (RouteLevel.equals("ap_dwidol3")) return 2;
                else if (RouteLevel.equals("ap_dwidol4")) return 3;
                else if (RouteLevel.equals("ap_dwidol5")) return 4;
                else if (RouteLevel.equals("ap_dwidol6")) return 2;
                else if (RouteLevel.equals("ap_apdol1")) return 3;
                else if (RouteLevel.equals("ap_apdol2")) return 2;
                else if (RouteLevel.equals("ap_apdol3")) return 3;
                else if (RouteLevel.equals("ap_apdol4")) return 3;
                else if (RouteLevel.equals("ap_apdol5")) return 2;
                else if (RouteLevel.equals("ap_apdol6")) return 2;
                else if (RouteLevel.equals("ap_apdol7")) return 2;
                else if (RouteLevel.equals("ap_apdol8")) return 2;
                else if (RouteLevel.equals("ap_bitgyou1")) return 4;
                else if (RouteLevel.equals("ap_bitgyou2")) return 3;
                else if (RouteLevel.equals("ap_bitgyou3")) return 2;
                else if (RouteLevel.equals("ap_doublecushion1")) return 2;
                else if (RouteLevel.equals("ap_doublecushion2")) return 3;
                else if (RouteLevel.equals("ap_doublecushion3")) return 3;
                else if (RouteLevel.equals("ap_doublecushion4")) return 2;
                else if (RouteLevel.equals("ap_doublecushion5")) return 2;
                else if (RouteLevel.equals("ap_ggeoga1")) return 4;
                else if (RouteLevel.equals("ap_ggeoga2")) return 3;
                else if (RouteLevel.equals("ap_ggeoga3")) return 3;
                else if (RouteLevel.equals("ap_ggeoga4")) return 3;
                else if (RouteLevel.equals("ap_ggeoga5")) return 2;
                else if (RouteLevel.equals("ap_youpdol1")) return 2;
                else if (RouteLevel.equals("ap_youpdol2")) return 3;
                else if (RouteLevel.equals("ap_youpdol3")) return 3;
                else if (RouteLevel.equals("ap_youpdol4")) return 3;
                else if (RouteLevel.equals("ap_youpdol5")) return 4;
                else if (RouteLevel.equals("ap_youpdol6")) return 4;
                else if (RouteLevel.equals("ap_youpdol7")) return 2;
                else if (RouteLevel.equals("ap_youpdol8")) return 4;
                else if (RouteLevel.equals("ap_youpdol9")) return 4;
                break;
        }
            return 0;
    }

    public void Play(View view){
        GlobalVariable.linevisible=true;
        GlobalVariable.BallGoDraw=true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        Log.e("**************","온 콘피규어 체인지가 호출되었습니다.");
    }

    //Activity 파괴시 데이터 저장
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    public boolean onTouchEvent(MotionEvent event) {
//        Log.e("좌표", " x의값 : " + event.getX() + "Y의 값 : " + event.getY());
        /*if(GlobalVariable.Global_Difficulty=="R") {
            trainnigView.ball_cnt++;
            if(trainnigView.ball_cnt==1) {
                trainnigView.WballX = event.getX();
                trainnigView.WballY = event.getY();
                trainnigView.ballGothread.Wball.setSx(trainnigView.WballX);
                trainnigView.ballGothread.Wball.setSy(trainnigView.WballY);
            }
            if(trainnigView.ball_cnt==2) {
                trainnigView.YballX = event.getX();
                trainnigView.YballY = event.getY();
                trainnigView.ballGothread.Yball.setSx(trainnigView.YballX);
                trainnigView.ballGothread.Yball.setSy(trainnigView.YballY);
            }
            if(trainnigView.ball_cnt==3) {
                trainnigView.RballX = event.getX();
                trainnigView.RballY = event.getY();
                trainnigView.ballGothread.Rball.setSx(trainnigView.RballX);
                trainnigView.ballGothread.Rball.setSy(trainnigView.RballY);
                trainnigView.ballGothread.isWait=true;
                trainnigView.ballGothread.DB_Process(3);
                trainnigView.ballGothread.isWait=false;
            }
//            Log.e("좌표", " x의값 : " + event.getX() + "Y의 값 : " + event.getY());
            trainnigView.invalidate();
            return true;
        }*/
        return gestureDetector.onTouchEvent(event);
    }
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        X_down = e1.getX();
        X_up = e2.getX();
        Log.e("X_down","X_down"+e1.getX());
        Log.e("X_up","X_up"+e2.getX());

        if (!isSwiped) {
            if ((X_down - X_up) < 30) {
                Log.e("제스쳐 작동","1");
                trainnigView.ballGothread.isWait=false;
                variable_uiselect.setVisibility(View.INVISIBLE);
                isSwiped = !isSwiped;
            }
        } else {
            if ((X_down - X_up) > 30) {
                Log.e("제스쳐 작동","2");
                trainnigView.ballGothread.isWait=true;
                variable_uiselect.setVisibility(View.VISIBLE);
                isSwiped = !isSwiped;
            }
        }
        return true;
    }
    @Override
    public boolean onDown(MotionEvent e) {

        return false;
    }
    @Override
    public void onShowPress(MotionEvent e) {

    }
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }
    @Override
    public void onLongPress(MotionEvent e) {

    }
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }


}





