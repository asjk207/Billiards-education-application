package com.example.user.billardtrainningapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by user on 2017-06-16.
 */

public class OnlineTrainningView extends SurfaceView implements SurfaceHolder.Callback{
    BallGothread ballGothread;

    public boolean promode;
    int width, height;
    Context context;
    SurfaceHolder holder;
    MyHandlerThread myHandlerThread;
    Message msg;
    Paint paint;

    Bitmap Board;


    public OnlineTrainningView(Context C, AttributeSet attrs){
        super(C, attrs);

        /*//터치 스크린 초점 맞추기
        this.requestFocus();
        this.setFocusableInTouchMode(true);*/
        this.context = C;
        init();
    }

    public OnlineTrainningView(Context C, AttributeSet attrs, Handler handler){
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

    /*@Override
    public boolean onTouchEvent(MotionEvent event){
        //유저 터치 감지
        float touchX = event.getX();
        float touchY = event.getY();
        Log.e("좌표"," x의값 : "+event.getX()+ "Y의 값 : "+event.getY());
        invalidate();
        return true;
    }*/



    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        Log.e("**************", "surfaceChanged()");

    }
    @Override
    public void surfaceCreated(SurfaceHolder holder){

        Log.e("***********","surfaceCreated()");
//        if(GlobalVariable.Global_Difficulty=="R") ballGothread.setScreenSizeNDBP(width, height);
//        if(!(GlobalVariable.Global_Difficulty=="R")) ballGothread.setScreenSizeNFP(width, height);
        Log.e("***************", "widh:"+width+" / height: "+height);
        try {
            //스레드를 시작시킨다.
            ballGothread.start();
        }catch(Exception e){
            Log.e("***********","스레드 시작 시 에러 발생! 스레드를 다시 생성");
            //에러 발생하면 재시작 하기
            restartThread();
        }

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
//        if(GlobalVariable.Global_Difficulty=="R")ballGothread.setScreenSizeNDBP(width,height);
//        if(!(GlobalVariable.Global_Difficulty=="R"))ballGothread.setScreenSizeNFP(width, height);
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

