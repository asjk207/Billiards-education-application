package com.example.user.billardtrainningapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
    //쓰레드 생성자
    private MyThread thread;

    TrainningActivity activity;
    Paint paint;
    float posX [] = new float[]{100, 29, 208, 516, 380};
    float posY [] = new float[]{570, 650, 208, 380, 450};
    CBall Wball=new CBall(100,800,3,3);
    CBall Yball=new CBall(380,450,3,3);
    CBall Rball=new CBall(100,600,3,3);
    Bitmap bgBitmap;
    Bitmap bmp;
    float bmpx;
    public TrainnigView(Context C, AttributeSet attrs){
        super(C, attrs);
        this.activity=activity;
        /*//터치 스크린 초점 맞추기
        this.requestFocus();
        this.setFocusableInTouchMode(true);*/
        SurfaceHolder holder =getHolder();
        holder.addCallback(this);

    }



    @Override
    protected  void onDraw(Canvas canvas){
        super.onDraw(canvas);
        //배경을 그립니다.
        paint.setColor(Color.GREEN);
        canvas.drawRect(30, 200, 30+Constant.Boardwidth, 200+Constant.Boardheight, paint);

        paint.setColor(Color.RED);
        canvas.drawCircle(Rball.getSx(),Rball.getSy(),Constant.BALL_SIZE,paint);
        paint.setColor(Color.YELLOW);
        canvas.drawCircle(Yball.getSx(),Yball.getSy(),Constant.BALL_SIZE,paint);

        paint.setColor(Color.WHITE);
        canvas.drawCircle(Wball.getSx()+Constant.V,Wball.getSy()+Constant.V,Constant.BALL_SIZE,paint);

        //Wball.drawSelf(posX[0], posY[0], canvas, paint);


    }


    @Override
    public boolean onTouchEvent(MotionEvent event){
        //유저 터치 감지
        float touchX = event.getX();
        float touchY = event.getY();
        Log.i("좌표"," x의값 : "+event.getX()+ "Y의 값 : "+event.getY());
        invalidate();
        return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        paint=new Paint();//붓을 만들다.
        paint.setAntiAlias(true);
        //그림의 위치를 초기화 합니다.
        //bmpx=(Constant.SCREEN_WIDTH-bmp.getWidth())/2;

        //스레드를 시작한다.
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = false;

        //스레드를 중지시킨다.
        thread.setRunning(false);
        while(retry){
            try{
                thread.join();
                retry=false;
            } catch(InterruptedException e){

            }
        }
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
    }



}
