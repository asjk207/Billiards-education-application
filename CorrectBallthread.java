package com.example.user.billardtrainningapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Created by user on 2017-03-02.
 */

public class CorrectBallthread extends Thread {

    Context context;


    SurfaceHolder sHolder; //핵심개체이며 화면의 구성에 대한 정보를 모두 가지고 있다.

    //쓰레드 변수
    boolean isRun = true;
    boolean isWait = false;

    //헨들러 변수
    private Handler mHandler;
    public Message msg;



    Canvas canvas; //화면에 무언가를 그리기 위한 캔바스 객체(sHolder가 가지고 있다)
    Paint paint= new Paint();
    int width,height;
    int twidth,theight;

    //공에 대한 정보

    CBall Wball;
    CBall Yball;
    CBall Rball;

    //좌표 정보
    int WposX [];
    int WposY [];
    private int RposX [];
    private int RposY [];
    private int YposX [];
    private int YposY [];
    int InitialPoint[];


    //상태 변수
    int cnt=0;
    int a=0;
    int c=0;
    int b=0;
    int n=0;

    //비트맵 정보
    Bitmap Board;
    Bitmap RedBall;
    Bitmap YellowBall;
    Bitmap WhiteBall;

    int tBoardheight;
    int tBallheight;

    //CorretDialog screen 비율
    double correctscreenratio=0.6;

    //파싱된 볼정보
    int casenum=11;


    Thread thisthread;



    //생성자
    public CorrectBallthread(Context context, SurfaceHolder sHolder){
        this.context = context;
        this.sHolder = sHolder;
        thisthread=this.currentThread();

        Bitmap_process();
        File_process();




    }
    public void File_process(){
        //File 에서 받은 공 좌표정보 초기화
        FileINOUT BFile=new FileINOUT(context);
        BFile.Ballfileread(context);
        BFile.InputArray();

        //ScreenScaleResult 출력하는 화면에 맞추어 좌표 비율 맞춤
        ScreenScaleResult SSR=new ScreenScaleResult((int)(1150*correctscreenratio),(int)(2300*correctscreenratio));
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
        Log.e("**************", "RedX좌표 "+InitialPoint[0]+"RedY좌표 "+InitialPoint[1]);
        Yball=new CBall(SSR.getRatioPosX2(InitialPoint[2]),SSR.getRatioPosY2(InitialPoint[3]));
        Log.e("**************", "YellowX좌표 "+InitialPoint[2]+"YellowY좌표 "+InitialPoint[3]);
        Wball=new CBall(SSR.getRatioPosX2(InitialPoint[4]),SSR.getRatioPosY2(InitialPoint[5]));
        Log.e("**************", "WhiteX좌표 "+InitialPoint[4]+"WhiteY좌표 "+InitialPoint[5]);


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


    public void Bitmap_process(){
        //Board 비트맵 정보 초기화(사이즈 맞춤)
        Board = BitmapFactory.decodeResource(context.getResources(),R.drawable.board);
        RedBall = BitmapFactory.decodeResource(context.getResources(),R.drawable.rball2);
        YellowBall = BitmapFactory.decodeResource(context.getResources(),R.drawable.yball2);
        WhiteBall = BitmapFactory.decodeResource(context.getResources(),R.drawable.wball2);



        Board=Bitmap.createScaledBitmap(Board,(int)(1150*correctscreenratio),(int)(2300*correctscreenratio),true);
        Log.e("************", "보드 높이"+tBoardheight);
        RedBall=Bitmap.createScaledBitmap(RedBall,(int)(Constant.BALL_WIDTH*correctscreenratio),(int)(Constant.BALL_HEIGHT*correctscreenratio),true);
        YellowBall=Bitmap.createScaledBitmap(YellowBall,(int)(Constant.BALL_WIDTH*correctscreenratio),(int)(Constant.BALL_HEIGHT*correctscreenratio),true);
        WhiteBall=Bitmap.createScaledBitmap(WhiteBall,(int)(Constant.BALL_WIDTH*correctscreenratio),(int)(Constant.BALL_HEIGHT*correctscreenratio),true);
    }

    //화면의 폭과 높이를 전달 받는다
    public void setScreenSize(int width, int height){
        this.width = width;
        this.height = height;
        //Log.e("***********", "width"+width+" / height: "+ height);
    }

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

    //스레드 본체인 run메소드에서 그리는 작업을 해준다.
    @Override
    public void run(){
        while (isRun) {//isRun의 초기 값이 true이므로 기본적으로 무한 루프이다.
            //Canvas객체 얻어오기
            canvas = sHolder.lockCanvas();//화면정보를 다 담을때까지 화면을 잠궈놓는다.


            //Log.e("좌표  ", " Yposx의값 : " + YposX[a] + "  YposY의 값 : " + YposY[a]+"a 의값 : "+a);
            //화면에 그리는 작업을 한다.
            try {
                canvas.drawColor(Color.BLACK);
                //동기화 블럭에서 작업을 해야한다.
                canvas.drawBitmap(Board,0 , 0, paint);

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
                    //canvas객체를 이용해서 반복적인 그리기 작업을 한다.

                    for (int n = 0; n < WposX.length-1 ; n++) {
                        paint.setColor(Color.WHITE);
                        paint.setStrokeWidth(2f);
                        canvas.drawLine(WposX[n], WposY[n], WposX[n + 1], WposY[n + 1], paint);
                    }
                    canvas.drawBitmap(WhiteBall, Wball.getSx() - 20, Wball.getSy() - 20, paint);
                    canvas.drawBitmap(RedBall, Rball.getSx() - 20, Rball.getSy() - 20, paint);
                    canvas.drawBitmap(YellowBall, Yball.getSx() - 20, Yball.getSy() - 20, paint);


                    //canvas.drawBitmap(WhiteBall,Wball.tx-20,Wball.ty-20,paint);
                    //canvas.drawBitmap(RedBall,Rball.tx-20,Rball.ty-20,paint);
                    //canvas.drawBitmap(YellowBall,Yball.tx-20,Yball.ty-20,paint);

                    //Wball.LineGo(WposX[a], WposY[a]);
                    //Log.e("좌표  ", " Wposx의값 : " + WposX[a] + "  WposY의 값 : " + WposY[a]+"a 의값 : "+a);
                    //Rball.LineGo(RposX[b], RposY[b]);
                    //Log.e("좌표  ", " Rposx의값 : " + RposX[b] + "  RposY의 값 : " + RposY[b]+"b 의값 : "+b);
                    //Yball.LineGo(YposX[c], YposY[c]);
                    //Log.e("좌표  ", " Wposx의값 : " + WposX[a] + "  WposY의 값 : " + WposY[a]);


                    cnt++;
                    //Log.e("상태  ", " i의값 : " + i );

                    if (cnt == 3) {
                        Wball.stx = Wball.tx;
                        Wball.sty = Wball.ty;
                        //Rball.stx = Rball.tx;
                        //Rball.sty = Rball.ty;
                        //Yball.stx = Yball.tx;
                        //Yball.sty = Yball.ty;
                            /*if(b<=(RposX.length-1)){
                                ++b;
                            }
                            if(a<=(WposX.length-1)){
                                ++a;
                            }
                            if(c<=(YposX.length-1)){
                                ++c;
                            }*/
                        //Log.e("상태  ", " a의값 : " + a);
                        cnt = 0;
                    }
                        /*if (GlobalVariable.nextstate) {
                            isRun=false;
                            a=0;
                            b=0;
                            c=0;
                            File_process();
                            GlobalVariable.nextstate=false;
                            isRun=true;
                        }*/
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
                        isRun=false;
                        wait();//notify 할때까지 기다린다.
                    }
                } catch (Exception e) {
                }
            }
        }
    }

}
