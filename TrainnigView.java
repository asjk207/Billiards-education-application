package com.example.user.billardtrainningapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Icon;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by user on 2017-01-27.
 */

public class TrainnigView extends SurfaceView implements SurfaceHolder.Callback{
    BallGothread ballGothread;

    public boolean promode;
    int width, height;
    Context context;
    SurfaceHolder holder;
    MyHandlerThread myHandlerThread;
    Message msg;
    Paint paint;

    Bitmap Board;

    //볼 검색 카운트
    int ball_cnt=0;
    float WballX;
    float WballY;
    float YballX;
    float YballY;
    float RballX;
    float RballY;


    public TrainnigView(Context C, AttributeSet attrs){
        super(C, attrs);

        /*//터치 스크린 초점 맞추기
        this.requestFocus();
        this.setFocusableInTouchMode(true);*/
        this.context = C;
        init();
    }

    public TrainnigView(Context C, AttributeSet attrs, Handler handler){
        super(C, attrs);
        /*//터치 스크린 초점 맞추기
        this.requestFocus();
        this.setFocusableInTouchMode(true);*/
        this.context = C;

        init();
    }
    public void setScreenSize(int width, int height){
        this.width=width;
        this.height=height;
    }

    public void init(){
        holder =getHolder();
        holder.addCallback(this);
        //스레드 객체 생성
        ballGothread = new BallGothread(context, holder);

    }
/*
    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(GlobalVariable.Global_Difficulty=="R") {
            ball_cnt++;
            if(ball_cnt==1) {
                WballX = event.getX();
                WballY = event.getY();
                ballGothread.Wball.setSx(WballX);
                ballGothread.Wball.setSy(WballY);
            }
            if(ball_cnt==2) {
                YballX = event.getX();
                YballY = event.getY();
                ballGothread.Yball.setSx(YballX);
                ballGothread.Yball.setSy(YballY);
            }
            if(ball_cnt==3) {
                RballX = event.getX();
                RballY = event.getY();
                ballGothread.Rball.setSx(RballX);
                ballGothread.Rball.setSy(RballY);
                ballGothread.isWait=true;
                ballGothread.DB_Process(RballX,RballY,YballX,YballY,WballX,WballY);
                ballGothread.isWait=false;
            }
            Log.e("좌표", " x의값 : " + event.getX() + "Y의 값 : " + event.getY());
            invalidate();
        }
        return true;
    }
*/


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        Log.e("**************", "surfaceChanged()");

    }
    @Override
    public void surfaceCreated(SurfaceHolder holder){

        Log.e("***********","surfaceCreated()");
        ballGothread.setScreenSize(width, height);
        Log.e("***************", "widh:"+width+" / height: "+height);
//       if(!(GlobalVariable.Global_Difficulty=="R")) {
           try {
               //스레드를 시작시킨다.
               ballGothread.start();
           } catch (Exception e) {
               Log.e("***********", "스레드 시작 시 에러 발생! 스레드를 다시 생성");
               //에러 발생하면 재시작 하기
               restartThread();
           }
//       }

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.e("***********","surfaceDestroyed()");
        //에러 없이 스레드를 안전하게 종료하기 위해
        boolean tryJoin = true;
        //스레드를 중지시킨다.

        /*
        while(tryJoin){//join이 성공할때까지
            try{
                ballGothread.join();
                tryJoin=false;
            } catch(Exception e){

            }
        }
        */
    }


    public void restartThread(){
        //스레드 정지
        //ballGothread.stopForever();
        //스레드 비우고
        ballGothread=null;
        //객체 다시 생성
        ballGothread = new BallGothread(context, holder);
        //화면의 폭과 높이 전달
        ballGothread.setScreenSize(width,height);
        //스레드 시작
        ballGothread.start();
    }

    //스레드 일시정지 메소드
    public void pauseThread(){
        ballGothread.pauseNResume(true);
    }
    public void resumeThread(){
        ballGothread.pauseNResume(false);
    }



}
