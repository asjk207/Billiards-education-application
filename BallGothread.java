package com.example.user.billardtrainningapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.SurfaceHolder;

import android.os.Handler;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by user on 2017-02-01.
 */

public class BallGothread extends Thread{
    Context context;


    SurfaceHolder sHolder; //핵심개체이며 화면의 구성에 대한 정보를 모두 가지고 있다.

    //쓰레드 변수
    boolean isRun = true;
    boolean isWait = false;

    private static final int SEND_THREAD_INFOMATION = 2001;
    private static final int SEND_THREAD_STOP_MESSAGE = 2002;
    private static final int SEND_DB_COMPLETE_MESSAGE = 2003;


    public Canvas canvas; //화면에 무언가를 그리기 위한 캔바스 객체(sHolder가 가지고 있다)
    private Paint paint= new Paint();
    int width,height;
    int twidth,theight;

    //공에 대한 정보

    CBall Wball;
    CBall Yball;
    CBall Rball;

    //좌표 정보
    int WposX [];
    int WposY [];
    int RposX [];
    int RposY [];
    int YposX [];
    int YposY [];
    int InitialPoint[];

    //좌표 정보
    float FWposX [];
    float FWposY [];
    float FRposX [];
    float FRposY [];
    float FYposX [];
    float FYposY [];
    float FInitialPoint[];



    float DBWposX [];
    float DBWposY [];
    float DBRposX [];
    float DBRposY [];
    float DBYposX [];
    float DBYposY [];
    float DBInitialPoint[];

    String CueBall;
    String TargetBall;

    //상태 변수
    int cnt=0;
    int a=0;
    int c=0;
    int b=0;
    int n=0;
    boolean Bloop=false;

    //좌표 맞추기 위한 변수
    int Cox=0;
    int Coy=0;


    //비트맵 정보
    Bitmap Board;
    Bitmap RedBall;
    Bitmap YellowBall;
    Bitmap WhiteBall;

    int tBoardheight;

    //DB 용 변수
    private static final String TAG_RESULTS="result";
    private static final String TAG_ID = "ID";
    private static final String TAG_INIT = "InitialPoint";
    private static final String TAG_RED = "RedBall";
    private static final String TAG_YELLOW = "YellowBall";
    private static final String TAG_WHITE = "WhiteBall";
    private static final String TAG_CUE = "CueBall";
    private static final String TAG_TARGET = "TargetBall";

    //공 수구 적구 배열
    int CueTarget [] = new int [3];
    private static final int Red=10;
    private static final int Yellow=20;
    private static final int White=30;


    String myJSON;
    JSONArray billards = null;

    // 문자열 분할 변수
    String[] split;
    String[] split2;
    //좌표 정보
    public List WposXYlist = new ArrayList();
    public List RposXYlist = new ArrayList();
    public List YposXYlist = new ArrayList();
    public List InitialPointlist = new ArrayList();

//    //핸들러 선언
//    SendMassgeHandler mMainHandler;

    //랜덤 변수 만들기
    Random rand = new Random();


    //생성자
    public BallGothread(Context context, SurfaceHolder sHolder){
        this.context = context;
        this.sHolder = sHolder;

//        mMainHandler=new SendMassgeHandler();


        Bitmap_process();

    }

    public void File_process(){
        //File 에서 받은 공 좌표정보 초기화
        FileINOUT BFile=new FileINOUT(context);
        BFile.Ballfileread(context);
        BFile.InputArray();

        //ScreenScaleResult 출력하는 화면에 맞추어 좌표 비율 맞춤
        ScreenScaleResult SSR=new ScreenScaleResult(width, height);
        Log.e("***********", "width"+width+" / height: "+ height);
        SSR.CalcCorrectScreenRatio(1150,2300);

        InitialPoint=BFile.getInitialPointArrays();

        WposX=BFile.getListWBallXArrays();
        WposY=BFile.getListWBallYArrays();
        for(int i=0;i<WposX.length;i++){
            int tmp1,tmp2;
            tmp1=SSR.getRatioPosX2(WposX[i]);
            WposX[i]=tmp1;
            tmp2=SSR.getRatioPosY2(WposY[i]);
            WposY[i]=tmp2;
        }


        Rball=new CBall(SSR.getRatioPosX2(InitialPoint[0]),SSR.getRatioPosY2(InitialPoint[1]));
//        Log.e("**************", "RedX좌표 "+SSR.getRatioPosX2(InitialPoint[0])+"RedY좌표 "+SSR.getRatioPosY2(InitialPoint[1]));
        Yball=new CBall(SSR.getRatioPosX2(InitialPoint[2]),SSR.getRatioPosY2(InitialPoint[3]));
//        Log.e("**************", "YellowX좌표 "+SSR.getRatioPosX2(InitialPoint[2])+"YellowY좌표 "+SSR.getRatioPosY2(InitialPoint[3]));
        Wball=new CBall(SSR.getRatioPosX2(InitialPoint[4]),SSR.getRatioPosY2(InitialPoint[5]));
//        Log.e("**************", "WhiteX좌표 "+SSR.getRatioPosX2(InitialPoint[4])+"WhiteY좌표 "+SSR.getRatioPosY2(InitialPoint[5]));

//        Rball.setSx();
//        Rball.setSy();
//        Log.e("**************", "RedX좌표 "+SSR.getRatioPosX2(InitialPoint[0])+"RedY좌표 "+SSR.getRatioPosY2(InitialPoint[1]));
//        Yball.setSx();
//        Yball.setSy();
//        Log.e("**************", "YellowX좌표 "+SSR.getRatioPosX2(InitialPoint[2])+"YellowY좌표 "+SSR.getRatioPosY2(InitialPoint[3]));
//        Wball.setSx();
//        Wball.setSy();
//        Log.e("**************", "WhiteX좌표 "+SSR.getRatioPosX2(InitialPoint[4])+"WhiteY좌표 "+SSR.getRatioPosY2(InitialPoint[5]));



        /*
        Rball=new CBall(SSR.getRatioPosX(InitialPoint[1])+Constant.BoardThick,SSR.getRatioPosY(InitialPoint[0])+Constant.BoardThick);
        //Log.e("**************", "RedX좌표 "+SSR.getRatioPosX(InitialPoint[1])+"RedY좌표 "+SSR.getRatioPosY(InitialPoint[0]));
        Yball=new CBall((SSR.getRatioPosX(InitialPoint[3]))+Constant.BoardThick,SSR.getRatioPosY(InitialPoint[2])+Constant.BoardThick);
        //Log.e("**************", "YellowX좌표 "+(SSR.getRatioPosX(InitialPoint[2]))+"YellowY좌표 "+SSR.getRatioPosY(InitialPoint[3]));
        Wball=new CBall(SSR.getRatioPosX(InitialPoint[5])+Constant.BoardThick,SSR.getRatioPosY(InitialPoint[4])+Constant.BoardThick);

        WposX=BFile.getListWBallXArrays();
        WposY=BFile.getListWBallYArrays();
        for(int i=0;i<WposX.length;i++){
            int tmp1,tmp2;
            tmp1=SSR.getRatioPosX(WposX[i])+Constant.BoardThick;
            WposX[i]=tmp1;
            tmp2=SSR.getRatioPosY(WposY[i])+Constant.BoardThick;
            WposY[i]=tmp2;
        }
        for (int i = 0; i < WposX.length; i++) {
            Log.e("******","WposX["+i+"]:  "+WposX[i]);
        }
        for (int i = 0; i < WposY.length; i++) {
            Log.e("******","WposY["+i+"]:  "+WposY[i]);
        }

        RposX=BFile.getListRBallXArrays();
        RposY=BFile.getListRBallYArrays();
        for(int i=0;i<RposX.length;i++){
            int tmp3,tmp4;
            tmp3=SSR.getRatioPosX(RposX[i])+Constant.BoardThick;
            tmp4=SSR.getRatioPosY(RposY[i])+Constant.BoardThick;
            RposX[i]=tmp3;
            RposY[i]=tmp4;
        }

//        for (int i = 0; i < RposX.length; i++) {
//            Log.e("******","RposX["+i+"]:  "+RposX[i]);
//        }
//        for (int i = 0; i < RposY.length; i++) {
//            Log.e("******","RposY["+i+"]:  "+RposY[i]);
//        }

        YposX=BFile.getListYBallXArrays();
        YposY=BFile.getListYBallYArrays();

        for(int i=0;i<YposX.length;i++){
            int tmp5,tmp6;
            tmp5=SSR.getRatioPosX(YposX[i])+Constant.BoardThick;
            YposX[i]=tmp5;
            tmp6=SSR.getRatioPosY(YposY[i])+Constant.BoardThick;
            YposY[i]=tmp6;
        }
        */
    }

    public void DB_Process(/*float rex, float rey, float yex, float yey, float whx, float why*/) {

        //File 에서 받은 공 좌표정보 초기화
        FileINOUT BFile=new FileINOUT(context);
        BFile.Ballfileread(context);
        BFile.InputArray();

        int [] tmpWposX=BFile.getListWBallYArrays();
        int [] tmpWposY=BFile.getListWBallXArrays();

        int [] tmpRposX=BFile.getListRBallYArrays();
        int [] tmpRposY=BFile.getListRBallXArrays();

        int [] tmpYposX=BFile.getListYBallYArrays();
        int [] tmpYposY=BFile.getListYBallXArrays();


        DBWposX=new float[tmpWposX.length];
        DBWposY=new float[tmpWposY.length];

        DBRposX=new float[tmpRposX.length];
        DBRposY=new float[tmpRposY.length];

        DBYposX=new float[tmpYposX.length];
        DBYposY=new float[tmpYposY.length];

        FWposX=new float[tmpWposX.length];
        FWposY=new float[tmpWposY.length];

        FRposX=new float[tmpRposX.length];
        FRposY=new float[tmpRposY.length];

        FYposX=new float[tmpYposX.length];
        FYposY=new float[tmpYposY.length];

        for(int i=0; i<tmpWposX.length;++i) {
            DBWposX[i] = (float)tmpWposX[i];
            DBWposY[i] = (float)tmpWposY[i];
        }
        for(int i=0; i<tmpRposX.length;++i) {
            DBRposX[i] = (float)tmpRposX[i];
            DBRposY[i] = (float)tmpRposY[i];
        }
        for(int i=0; i<tmpYposX.length;++i) {
            DBYposX[i] = (float)tmpYposX[i];
            DBYposY[i] = (float)tmpYposY[i];
        }

        //DB 에서 받은 공 좌표정보 초기화

        //ScreenScaleResult 출력하는 화면에 맞추어 좌표 비율 맞춤
        ScreenScaleResult SSR = new ScreenScaleResult(width, height);
        SSR.CalcDBScreenRatio(1020,540);
        Log.e("***********", "width" + width + " / height: " + height);
        InitialPoint=BFile.getInitialPointArrays();


        if(GlobalVariable.ReverseX && !GlobalVariable.ReverseY) {
            Log.e("REVERSE X","REVERSE X");
            Rball = new CBall(SSR.getRatioPosX(520-InitialPoint[1]) + Cox + GlobalVariable.BposX, SSR.getRatioPosY(InitialPoint[0]) + Coy + GlobalVariable.BposY);
            Yball = new CBall(SSR.getRatioPosX(520-InitialPoint[3]) + Cox + GlobalVariable.BposX, SSR.getRatioPosY(InitialPoint[2]) + Coy + GlobalVariable.BposY);
            Wball = new CBall(SSR.getRatioPosX(520-InitialPoint[5]) + Cox + GlobalVariable.BposX, SSR.getRatioPosY(InitialPoint[4]) + Coy + GlobalVariable.BposY);
        }
        else if(!GlobalVariable.ReverseX && GlobalVariable.ReverseY) {
            Log.e("REVERSE Y","REVERSE Y");
            Rball = new CBall(SSR.getRatioPosX(InitialPoint[1]) + Cox + GlobalVariable.BposX, SSR.getRatioPosY(1020-InitialPoint[0]) + Coy + GlobalVariable.BposY);
            Yball = new CBall(SSR.getRatioPosX(InitialPoint[3]) + Cox + GlobalVariable.BposX, SSR.getRatioPosY(1020-InitialPoint[2]) + Coy + GlobalVariable.BposY);
            Wball = new CBall(SSR.getRatioPosX(InitialPoint[5]) + Cox + GlobalVariable.BposX, SSR.getRatioPosY(1020-InitialPoint[4]) + Coy + GlobalVariable.BposY);
        }
        else if(GlobalVariable.ReverseX && GlobalVariable.ReverseY) {
            Log.e("REVERSE XY","REVERSE XY");
            Rball = new CBall(SSR.getRatioPosX(520-InitialPoint[1]) + Cox + GlobalVariable.BposX, SSR.getRatioPosY(1020-InitialPoint[0]) + Coy + GlobalVariable.BposY);
            Yball = new CBall(SSR.getRatioPosX(520-InitialPoint[3]) + Cox + GlobalVariable.BposX, SSR.getRatioPosY(1020-InitialPoint[2]) + Coy + GlobalVariable.BposY);
            Wball = new CBall(SSR.getRatioPosX(520-InitialPoint[5]) + Cox + GlobalVariable.BposX, SSR.getRatioPosY(1020-InitialPoint[4]) + Coy + GlobalVariable.BposY);
        }
        else if(!GlobalVariable.ReverseX && !GlobalVariable.ReverseY) {
            Log.e("NOT REVERSE","NOT REVERSE");
            Rball = new CBall(SSR.getRatioPosX(InitialPoint[1]) + Cox + GlobalVariable.BposX, SSR.getRatioPosY(InitialPoint[0]) + Coy + GlobalVariable.BposY);
            Yball = new CBall(SSR.getRatioPosX(InitialPoint[3]) + Cox + GlobalVariable.BposX, SSR.getRatioPosY(InitialPoint[2]) + Coy + GlobalVariable.BposY);
            Wball = new CBall(SSR.getRatioPosX(InitialPoint[5]) + Cox + GlobalVariable.BposX, SSR.getRatioPosY(InitialPoint[4]) + Coy + GlobalVariable.BposY);
        }
        Log.e("RballX",""+Rball.getSx());
        Log.e("RballY",""+Rball.getSy());
        Log.e("YballX",""+Yball.getSx());
        Log.e("YballY",""+Yball.getSy());
        Log.e("WballX",""+Wball.getSx());
        Log.e("WballY",""+Wball.getSy());

        for (int i = 0; i < DBWposX.length; i++) {
            float tmp1=0, tmp2=0;
            if(!GlobalVariable.ReverseX) tmp1 = SSR.getRatioPosX(DBWposX[i]);
            else if(GlobalVariable.ReverseX) tmp1 = SSR.getRatioPosX(520-DBWposX[i]);
            FWposX[i] = tmp1+Cox+GlobalVariable.BposX;
            if(!GlobalVariable.ReverseY)tmp2 = SSR.getRatioPosY(DBWposY[i]);
            else if(GlobalVariable.ReverseY) tmp2 = SSR.getRatioPosY(1020-DBWposY[i]);
            FWposY[i] = tmp2+Coy+GlobalVariable.BposY;
//                Log.e("**************", "WposX좌표 " + WposX[i] + "WposY좌표 " + WposY[i]);
        }
        for (int i = 0; i < DBRposX.length; i++) {
            float tmp3=0, tmp4=0;
            if(!GlobalVariable.ReverseX) tmp3 = SSR.getRatioPosX(DBRposX[i]);
            else if(GlobalVariable.ReverseX)tmp3 = SSR.getRatioPosX(520-DBRposX[i]);
            FRposX[i] = tmp3+Cox+GlobalVariable.BposX;
            if(!GlobalVariable.ReverseY) tmp4 = SSR.getRatioPosY(DBRposY[i]);
            else if(GlobalVariable.ReverseY) tmp4 = SSR.getRatioPosY(1020-DBRposY[i]);
            FRposY[i] = tmp4+Coy+GlobalVariable.BposY;
//                Log.e("**************", "RposX좌표 "+RposX[i]+"RposY좌표 "+RposY[i]);
        }
        for (int i = 0; i < DBYposX.length; i++) {
            float tmp5=0, tmp6=0;
            if(!GlobalVariable.ReverseX) tmp5 = SSR.getRatioPosX(DBYposX[i]);
            else if(GlobalVariable.ReverseX)tmp5 = SSR.getRatioPosX(520-DBYposX[i]);
            FYposX[i] = tmp5+Cox+GlobalVariable.BposX;
            if(!GlobalVariable.ReverseY) tmp6 = SSR.getRatioPosY(DBYposY[i]);
            else if(GlobalVariable.ReverseY)tmp6 = SSR.getRatioPosY(1020-DBYposY[i]);
            FYposY[i] = tmp6+Coy+GlobalVariable.BposY;
//                Log.e("**************", "YposX좌표 "+YposX[i]+"YposY좌표 "+YposY[i]);
        }
    }



    public void Bitmap_process(){
        //Board 비트맵 정보 초기화(사이즈 맞춤)
        Board = BitmapFactory.decodeResource(context.getResources(),R.drawable.board);
        RedBall = BitmapFactory.decodeResource(context.getResources(),R.drawable.rball2);
        YellowBall = BitmapFactory.decodeResource(context.getResources(),R.drawable.yball2);
        WhiteBall = BitmapFactory.decodeResource(context.getResources(),R.drawable.wball2);

        Log.e("************", "보드 높이"+tBoardheight);
        RedBall=Bitmap.createScaledBitmap(RedBall,Constant.BALL_WIDTH,Constant.BALL_HEIGHT,true);
        YellowBall=Bitmap.createScaledBitmap(YellowBall,Constant.BALL_WIDTH,Constant.BALL_HEIGHT,true);
        WhiteBall=Bitmap.createScaledBitmap(WhiteBall,Constant.BALL_WIDTH,Constant.BALL_HEIGHT,true);
    }

    //화면의 폭과 높이를 전달 받는다
    public void setScreenSize(int width, int height){
        this.width = width;
        this.height = height;
        //비트맵 화면에 맞추어 리사이즈
        Board=Bitmap.createScaledBitmap(Board,width+30,height+30,true);
        //Log.e("***********", "width"+width+" / height: "+ height);
        int id= rand.nextInt(500);
        Log.e("","*************************************************************ID : " +id);
        Log.e("","*************************************************************ID : " +GlobalVariable.Global_Difficulty);
        if(GlobalVariable.Global_Difficulty=="R") DB_Process();
        else if(!(GlobalVariable.Global_Difficulty=="R")) File_process();
    }
//    //화면의 폭과 높이를 전달 받는다
//    public void setScreenSizeNDBP(int width, int height){
//        this.width = width;
//        this.height = height;
//        //비트맵 화면에 맞추어 리사이즈
//        Board=Bitmap.createScaledBitmap(Board,width+30,height+30,true);
//        //Log.e("***********", "width"+width+" / height: "+ height);
////        this.isWait=true;
//        DB_Process(3);
////        this.isWait=false;
//    }


    //빠르게 돌아가는 스레드에서 메소드를 실행하거나 멤버필드가 교환되는 작업을 하면
    //스레드 작업이 깨질수있기 때문에 동기화가 필요하다.
    //스레드의 일시정지 혹은 재시작 하는 메소드

    public void pauseNResume(boolean isWait){
        synchronized (this) {
            this.isWait = isWait;
            notify();
        }
    }

    //스레드 완전 정지하는 메소드
    public void stopForever(){
        synchronized (this){
            this.isRun=isRun;
            notify();
        }
    }

    public void InputArray(){
        int c = 0;
        int d = 0;
        //텍스트에서 Object 형을 받아왔으므로
        // List -> Object[]
        //InitialPoint LIst 형변환
        Object[] OArrays = InitialPointlist.toArray(new String[InitialPointlist.size()]);

        DBInitialPoint = new float[OArrays.length];
        for (int i = 0; i < OArrays.length; i++) {
            DBInitialPoint[i] = Float.parseFloat(OArrays[i].toString());
        }
        for(int i=0; i< DBInitialPoint.length;i++)
            Log.e("","DBInitialPoint"+DBInitialPoint[i]);

        //listRBallXY List 형 변환
        Object[] OArrays2 = RposXYlist.toArray(new String[RposXYlist.size()]);
        DBRposX = new float[OArrays2.length/2];
        DBRposY = new float[OArrays2.length/2];
        for (int i = 0; i < OArrays2.length; i++) {
            if(i%2==0) {
                DBRposX[c]  = Float.parseFloat(OArrays2[i].toString());
                c++;
            }
            else if(i%2==1) {
                DBRposY[d]  = Float.parseFloat(OArrays2[i].toString());
                d++;
            }
        }
        for(int i=0; i< DBRposX.length;i++)
            Log.e("","DBRposX"+DBRposX[i]+"  DBRposY"+DBRposY[i]);
        c=0;
        d=0;
        //listYBallXY List 형 변환
        Object[] OArrays3 = YposXYlist.toArray(new String[YposXYlist.size()]);
        DBYposX = new float[OArrays3.length/2];
        DBYposY = new float[OArrays3.length/2];

        for (int i = 0; i < OArrays3.length; i++) {
            if(i%2==0) {
                DBYposX[c]  = Float.parseFloat(OArrays3[i].toString());
                c++;
            }
            else if(i%2==1) {
                DBYposY[d]  = Float.parseFloat(OArrays3[i].toString());
                d++;
            }
        }
        for(int i=0; i< DBYposX.length;i++)
//        Log.e("","DBYposX"+DBYposX[i]+"DBYposY"+DBYposY[i]);
            c=0;
        d=0;
        //listWBallXY List 형 변환
        Object[] OArrays4 = WposXYlist.toArray(new String[WposXYlist.size()]);
        DBWposX = new float[(OArrays4.length+1)/2];
        DBWposY = new float[(OArrays4.length+1)/2];
//        Log.e("DB"," DBWposX"+DBWposX);
        for(int i=0;i<OArrays4.length;i++) {
            if(i%2==0) {
                DBWposX[c]  = Float.parseFloat(OArrays4[i].toString());
                c++;
            }
            else if(i%2==1) {
                DBWposY[d]  = Float.parseFloat(OArrays4[i].toString());
                d++;
            }
        }
        c=0;
        d=0;

        if(InitialPoint[0]!=DBRposX[0]){

        };
        if(InitialPoint[2]!=DBYposX[0]);
        if(InitialPoint[4]!=DBWposX[0]);
        /*for(i=0;i<InitialPointArrays.length;i++){
            Log.e("************","초기화 포인트"+InitialPointArrays[i]);
        }*/
    }

    protected boolean JSONinputList() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            billards = jsonObj.getJSONArray(TAG_RESULTS);
            Log.e("***********", "Billards.length()" + billards.length());
            for (int i = 0; i < billards.length(); i++) {
                JSONObject c = billards.getJSONObject(i);


                String id = c.getString(TAG_ID);
                String initialpoint = c.getString(TAG_INIT);
                String whiteball = c.getString(TAG_WHITE);
                String yellowball = c.getString(TAG_YELLOW);
                String redball = c.getString(TAG_RED);
                String cueball = c.getString(TAG_CUE);
                String targetball = c.getString(TAG_TARGET);

                CueBall=cueball;
                TargetBall=targetball;

                if (initialpoint == null || whiteball == null || yellowball == null || redball == null)
                    return true;

                split = initialpoint.split("_");
                for (int j = 0; j < split.length; j++) {
                    split2 = split[j].split("-");
                    for (int k = 0; k < split2.length; k++) {
                        InitialPointlist.add(split2[k]);
                    }
                }
                split = whiteball.split("_");
                for (int j = 0; j < split.length; j++) {
                    split2 = split[j].split("-");
                    for (int k = 0; k < split2.length; k++) {
                        WposXYlist.add(split2[k]);
                    }
                }
                split = yellowball.split("_");
                for (int j = 0; j < split.length; j++) {
                    split2 = split[j].split("-");
                    for (int k = 0; k < split2.length; k++) {
                        YposXYlist.add(split2[k]);
                    }
                }
                split = redball.split("_");
                for (int j = 0; j < split.length; j++) {
                    split2 = split[j].split("-");
                    for (int k = 0; k < split2.length; k++) {
                        RposXYlist.add(split2[k]);
                    }
                }
            }
            InputArray();
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("에러 처리", "JOSON 에러" + e);
        }
        return false;
    }


    public void getData(String url, final String ID/*final String RBallposX, final String RBallposY,final String YBallposX,final String YBallposY,final String WBallposX,final String WBallposY*/){
        class GetDataJSON extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String uri = params[0];

                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("POST");
                    con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    con.setDoInput(true);
                    con.setDoOutput(true);

                    String data = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(ID, "UTF-8");
//                    String data = URLEncoder.encode("wballX", "UTF-8") + "=" + URLEncoder.encode(WBallposX, "UTF-8");
//                    data += "&" + URLEncoder.encode("wbally", "UTF-8") + "=" + URLEncoder.encode(WBallposY, "UTF-8");
//                    data += "&" + URLEncoder.encode("yballx", "UTF-8") + "=" + URLEncoder.encode(YBallposX, "UTF-8");
//                    data += "&" + URLEncoder.encode("ybally", "UTF-8") + "=" + URLEncoder.encode(YBallposY, "UTF-8");
//                    data += "&" + URLEncoder.encode("rballx", "UTF-8") + "=" + URLEncoder.encode(RBallposX, "UTF-8");
//                    data += "&" + URLEncoder.encode("rbally", "UTF-8") + "=" + URLEncoder.encode(RBallposY, "UTF-8");

                    OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());


                    wr.write(data);
                    wr.flush();
                    wr.close();

                    con.setConnectTimeout(10000);
                    StringBuilder sb = new StringBuilder();
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while((json = bufferedReader.readLine())!= null){
                        sb.append(json+"\n");
                    }

                    return sb.toString().trim();

                }catch(Exception e){
                    Log.e("",""+e.getMessage());
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String result){
                Message msg= Message.obtain();
                Log.e("","받은 데이터"+result);
                myJSON=result;
                if(JSONinputList()){
                    msg.what = SEND_THREAD_STOP_MESSAGE;
                    mMainHandler.sendMessage(msg);
                };
                msg.what = SEND_DB_COMPLETE_MESSAGE;
                mMainHandler.sendMessage(msg);
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);

    }

    //스레드 본체인 run메소드에서 그리는 작업을 해준다.
    @Override
    public void run(){
        while (isRun) {//isRun의 초기 값이 true이므로 기본적으로 무한 루프이다.
            //Canvas객체 얻어오기
            canvas = sHolder.lockCanvas();//화면정보를 다 담을때까지 화면을 잠궈놓는다.

            //Log.e("좌표  ", " Yposx의값 : " + YposX[a] + "  YposY의 값 : " + YposY[a]+"a 의값 : "+a);
            //화면에 그리는 작업을 한다.
            try {

                canvas.drawColor(Color.WHITE);
                //동기화 블럭에서 작업을 해야한다.
                canvas.drawBitmap(Board, -10, -10, paint);

                /*for (int n = 0; n < RposX.length - 1; n++) {
                    paint.setColor(Color.RED);
                    paint.setStrokeWidth(5f);
                    canvas.drawLine(RposX[n], RposY[n], RposX[n + 1], RposY[n + 1], paint);
                }
                for (int n = 0; n < RposX.length - 1; n++) {
                    paint.setColor(Color.BLACK);
                    paint.setStrokeWidth(5f);
                    canvas.drawLine(YposX[n], YposY[n], YposX[n + 1], YposY[n + 1], paint);
                }*/


                synchronized (sHolder) {//그리기 위한 모든 작업을 하는곳
                    if (!(GlobalVariable.Global_Difficulty == "R")) {
                        //canvas객체를 이용해서 반복적인 그리기 작업을 한다.
                        if (GlobalVariable.linevisible) {
                            for (int n = 0; n < WposX.length - 1; n++) {
                                paint.setColor(Color.WHITE);
                                paint.setStrokeWidth(3f);
                                canvas.drawLine(WposX[n], WposY[n], WposX[n + 1], WposY[n + 1], paint);
                            }
                        }
                        if (!GlobalVariable.linevisible) {
                            paint.setColor(Color.WHITE);
                            paint.setTextSize(40f);
                            canvas.drawText("수구", Wball.getSx(), Wball.getSy() - 40, paint);
                            canvas.drawBitmap(WhiteBall, Wball.getSx(), Wball.getSy() - 20, paint);
                            paint.setColor(Color.YELLOW);
                            paint.setTextSize(40f);
                            canvas.drawText("제1적구", Yball.getSx() - 40, Yball.getSy() - 40, paint);
                        }
                        canvas.drawBitmap(RedBall, Rball.getSx() - 20, Rball.getSy() - 20, paint);
                        canvas.drawBitmap(YellowBall, Yball.getSx() - 20, Yball.getSy() - 20, paint);

                        if (GlobalVariable.BallGoDraw) {
                            canvas.drawBitmap(WhiteBall, Wball.tx - 20, Wball.ty - 20, paint);
                            Wball.LineGo(WposX[a], WposY[a]);
                            cnt++;
                            //Log.e("상태  ", " i의값 : " + i );

                            if (cnt == 25) {
                                Wball.stx = Wball.tx;
                                Wball.sty = Wball.ty;
                                if (a < (WposX.length - 1)) {
                                    ++a;
                                }
                                //Rball.stx = Rball.tx;
                                //Rball.sty = Rball.ty;
                                //Yball.stx = Yball.tx;
                                //Yball.sty = Yball.ty;
                            /*if(b<=(RposX.length-1)){
                                ++b;
                            }

                            if(c<=(YposX.length-1)){
                                ++c;
                            }*/
                                //Log.e("상태  ", " a의값 : " + a);
                                cnt = 0;
                            }
                        }

                    }

                    if (GlobalVariable.Global_Difficulty == "R") {
//                    if (GlobalVariable.linevisible) {
                        for (int n = 0; n < FWposX.length - 1; n++) {
                            paint.setColor(Color.WHITE);
                            paint.setStrokeWidth(3f);
                            canvas.drawLine(FWposX[n], FWposY[n], FWposX[n + 1], FWposY[n + 1], paint);
                        }

                        for (int n = 0; n < FRposX.length - 1; n++) {
                            paint.setColor(Color.RED);
                            paint.setStrokeWidth(3f);
                            canvas.drawLine(FRposX[n], FRposY[n], FRposX[n + 1], FRposY[n + 1], paint);
                        }
                        for (int n = 0; n < FYposX.length - 1; n++) {
                            paint.setColor(Color.YELLOW);
                            paint.setStrokeWidth(3f);
                            canvas.drawLine(FYposX[n], FYposY[n], FYposX[n + 1], FYposY[n + 1], paint);
                            //Log.e("************", "YposX:"+YposX[n]);
                            //Log.e("************", "YposY:"+YposY[n]);
                        }
                        //}


//                    }
                        if (!GlobalVariable.linevisible) {
                            paint.setColor(Color.WHITE);
                            paint.setTextSize(40f);
//                        canvas.drawText("수구", Wball.getSx(), Wball.getSy() - 40, paint);
                            canvas.drawBitmap(WhiteBall, Wball.getSx() - 20, Wball.getSy() - 20, paint);
                            paint.setColor(Color.YELLOW);
                            paint.setTextSize(40f);
//                        canvas.drawText("제1적구", Yball.getSx() - 40, Yball.getSy() - 40, paint);
                            canvas.drawBitmap(YellowBall, Yball.getSx() - 20, Yball.getSy() - 20, paint);
                            paint.setColor(Color.RED);
                            paint.setTextSize(40f);
//                        canvas.drawText("제2적구", Rball.getSx() - 40, Rball.getSy() - 40, paint);
                            canvas.drawBitmap(RedBall, Rball.getSx() - 20, Rball.getSy() - 20, paint);
                        }
                        if (GlobalVariable.BallGoDraw) {
                            Log.e("", "GlobalVariable.BallGoDraw   : " + GlobalVariable.BallGoDraw);
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    while (Bloop) {
                                        Log.e("", "Bloop   : " +Bloop);
                                        try {
                                                    Thread.sleep(8);
                                                    if(c==FYposX.length-1) {
                                                        c=0;
                                                        Bloop=false;
                                                        Log.e("","Yball"+c);
                                                    }
                                                    else if(c!=FYposX.length-1){
                                                        ++c;
                                                        Yball.stx = Yball.tx;
                                                        Yball.sty = Yball.ty;
                                                        Yball.LineGo(FYposX[c],FYposY[c]);
                                                       Log.e("","Yball"+Yball.stx);
                                                    }
                                                    if(a==FWposX.length-1) {
                                                        a=0;
//                                                        Wloop=false;
                                                    }
                                                    else if(a!=FWposX.length-1){
                                                        ++a;
                                                        Wball.stx = Wball.tx;
                                                        Wball.sty = Wball.ty;
                                                        Wball.LineGo(FWposX[a],FWposY[a]);
                                                    }
                                                    if(b==FRposX.length-1) {
                                                        b=0;
                                                    }
                                                    else if(b!=FRposX.length-1){
                                                        ++b;
                                                        Rball.stx = Rball.tx;
                                                        Rball.sty = Rball.ty;
                                                        Rball.LineGo(FRposX[b],FRposY[b]);
                                                    }
                                        } catch (InterruptedException e) {
                                        }
                                    }
                                }
                            });
                            canvas.drawBitmap(YellowBall, Yball.getSx() - 20, Yball.getSy() - 20, paint);
                            canvas.drawBitmap(WhiteBall, Wball.getSx() - 20, Wball.getSy() - 20, paint);
                            canvas.drawBitmap(RedBall, Rball.getSx() - 20, Rball.getSy() - 20, paint);
                        }
                    }
                }
            } finally {
                if (canvas != null)//실제로 화면을 그리는 곳(while을 돌면서 화면을 덧그리기 때문에 invalidate가 필요하지않다.
                    //잠근 화면을 풀고 작업한 canvas 객체를 전달해서 화면을 그린다.
                    sHolder.unlockCanvasAndPost(canvas);
            }
            //스레드를 일시 정지 하기 위해
            if (isWait) {//isWait의 초기값을 false 이다.
                try {
                    synchronized (this) {
                        wait();//notify 할때까지 기다린다.
                    }
                } catch (Exception e) {
                }
            }
        }
    }
//    public void CueTarget_order(int [] CueTarget){
//        if(CueTarget[0]==White && CueTarget[1]==Yellow){
//            if (a == DBWposX.length - 1) {
//                a = 0;
//                Bloop = false;
//            } else if (a != DBWposX.length - 1) {
//                ++a;
//                Wball.stx = Wball.tx;
//                Wball.sty = Wball.ty;
//                Wball.LineGo(DBWposX[a], DBWposY[a]);
//                Log.e("Wball", "a" + a);
//            }
//            if (c == DBYposX.length - 1) {
//                c = 0;
//            } else if (c != DBYposX.length - 1) {
//                ++c;
//                Yball.stx = Yball.tx;
//                Yball.sty = Yball.ty;
//                Yball.LineGo(DBYposX[c], DBYposY[c]);
//                Log.e("Yball", "c" + c);
//            }
//            if (b == DBRposX.length - 1) {
//                b = 0;
//            } else if (b != DBRposX.length - 1) {
//                ++b;
//                Rball.stx = Rball.tx;
//                Rball.sty = Rball.ty;
//                Rball.LineGo(DBRposX[b], DBRposY[b]);
//                Log.e("Rball", "b" + b);
//            }
//        }
//        if(CueTarget[0]==Yellow && CueTarget[1]==White){
//            if (a == DBYposX.length - 1) {
//                a = 0;
//                Bloop = false;
//            } else if (a != DBYposX.length - 1) {
//                ++a;
//                Yball.stx = Yball.tx;
//                Yball.sty = Yball.ty;
//                Yball.LineGo(DBYposX[a], DBYposY[a]);
//                Log.e("Wball", "a" + a);
//            }
//            if (c == DBWposX.length - 1) {
//                c = 0;
//            } else if (c != DBWposX.length - 1) {
//                ++c;
//                Wball.stx = Wball.tx;
//                Wball.sty = Wball.ty;
//                Wball.LineGo(DBWposX[c], DBWposY[c]);
//                Log.e("Yball", "c" + c);
//            }
//            if (b == DBRposX.length - 1) {
//                b = 0;
//            } else if (b != DBRposX.length - 1) {
//                ++b;
//                Rball.stx = Rball.tx;
//                Rball.sty = Rball.ty;
//                Rball.LineGo(DBRposX[b], DBRposY[b]);
//                Log.e("Rball", "b" + b);
//            }
//        }
//        if(CueTarget[0]==White && CueTarget[1]==Red){
//            if (a == DBWposX.length - 1) {
//                a = 0;
//                Bloop = false;
//            } else if (a != DBWposX.length - 1) {
//                ++a;
//                Wball.stx = Wball.tx;
//                Wball.sty = Wball.ty;
//                Wball.LineGo(DBWposX[a], DBWposY[a]);
//                Log.e("Wball", "a" + a);
//            }
//            if (c == DBRposX.length - 1) {
//                c = 0;
//            } else if (c != DBRposX.length - 1) {
//                ++c;
//                Rball.stx = Rball.tx;
//                Rball.sty = Rball.ty;
//                Rball.LineGo(DBRposX[c], DBRposY[c]);
//                Log.e("Yball", "c" + c);
//            }
//            if (b == DBYposX.length - 1) {
//                b = 0;
//            } else if (b != DBYposX.length - 1) {
//                ++b;
//                Yball.stx = Yball.tx;
//                Yball.sty = Yball.ty;
//                Yball.LineGo(DBYposX[b], DBYposY[b]);
//                Log.e("Rball", "b" + b);
//            }
//        }
//        if(CueTarget[0]==Yellow && CueTarget[1]==Red){
//            if (a == DBYposX.length - 1) {
//                a = 0;
//                Bloop = false;
//            } else if (a != DBYposX.length - 1) {
//                ++a;
//                Yball.stx = Yball.tx;
//                Yball.sty = Yball.ty;
//                Yball.LineGo(DBYposX[a], DBYposY[a]);
//                Log.e("Wball", "a" + a);
//            }
//            if (c == DBRposX.length - 1) {
//                c = 0;
//            } else if (c != DBRposX.length - 1) {
//                ++c;
//                Rball.stx = Rball.tx;
//                Rball.sty = Rball.ty;
//                Rball.LineGo(DBRposX[c], DBRposY[c]);
//                Log.e("Yball", "c" + c);
//            }
//            if (b == DBWposX.length - 1) {
//                b = 0;
//            } else if (b != DBWposX.length - 1) {
//                ++b;
//                Wball.stx = Wball.tx;
//                Wball.sty = Wball.ty;
//                Wball.LineGo(DBWposX[b], DBWposY[b]);
//                Log.e("Rball", "b" + b);
//            }
//        }






//    // Handler 클래스
//    class SendMassgeHandler extends Handler {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what) {
//                case SEND_THREAD_INFOMATION:
//                    break;
//                case SEND_THREAD_STOP_MESSAGE:
//                    isWait=true;
//                    break;
//                case SEND_DB_COMPLETE_MESSAGE:
//                    DB_Process();
//                    Log.e("","핸들러 작동*************************************************************");
//                    try {
//                        //스레드를 시작시킨다.
//                        start();
//                    }catch(Exception e){
//                        Log.e("***********","스레드 시작 시 에러 발생! 스레드를 다시 생성");
//                        //에러 발생하면 재시작 하기
//                    }
//                    break;
//                default:
//                    break;
//
//            }
//        }
//    };




}
