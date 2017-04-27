package com.example.user.billardtrainningapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by user on 2017-03-02.
 */

public class CorrectView extends SurfaceView implements SurfaceHolder.Callback{

    CorrectBallthread mcorrectballthread;


    int width, height;
    Context context;
    SurfaceHolder holder;

    Paint paint;

    Bitmap Board;

    int x,y;


    public CorrectView(Context C, AttributeSet attrs){
        super(C, attrs);
        /*//터치 스크린 초점 맞추기
        this.requestFocus();
        this.setFocusableInTouchMode(true);*/
        this.context = C;
        init();
    }

    public CorrectView(Context C){
        super(C);
        /*//터치 스크린 초점 맞추기
        this.requestFocus();
        this.setFocusableInTouchMode(true);*/
        this.context = C;

        init();
    }

    public void init(){
        holder =getHolder();
        holder.addCallback(this);
        //스레드 객체 생성
        mcorrectballthread = new CorrectBallthread(context, holder);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        //유저 터치 감지
        float touchX = event.getX();
        float touchY = event.getY();
        x = (int)event.getX();
        y = (int)event.getY();

        Log.e("좌표"," x의값 : "+event.getX()+ "Y의 값 : "+event.getY());
        invalidate();
        return true;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint_text = new Paint();

        paint_text.setTextSize(50);
        canvas.drawText("("+x+","+y+")",x,y,paint_text);
        super.onDraw(canvas);
    }



    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        Log.e("**************", "surfaceChanged()");
        this.width = width;
        this.height = height;
        mcorrectballthread.setScreenSize(width, height);
        Log.e("***************", "widh:"+width+" / height: "+height);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        Log.e("***********","surfaceCreated()");
        try {
            //스레드를 시작시킨다.
            mcorrectballthread.start();

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

        //스레드를 중지시킨다.



    }

    public void restartThread(){
        //스레드 정지
        //ballGothread.stopForever();
        //스레드 비우고
        mcorrectballthread=null;
        //객체 다시 생성
        mcorrectballthread = new CorrectBallthread(context, holder);
        //화면의 폭과 높이 전달
        mcorrectballthread.setScreenSize(width, height);
        //스레드 시작
        mcorrectballthread.start();
    }

    //스레드 일시정지 메소드
    public void pauseThread(){
        mcorrectballthread.pauseNResume(true);
    }
    public void resumeThread(){
        mcorrectballthread.pauseNResume(false);
    }


}
