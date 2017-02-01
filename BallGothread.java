package com.example.user.billardtrainningapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

import java.util.ArrayList;

/**
 * Created by user on 2017-02-01.
 */

public class BallGothread extends Thread {
    Context context;
    SurfaceHolder sHolder; //핵심개체이며 화면의 구성에 대한 정보를 모두 가지고 있다.
    boolean isRun = true;
    boolean isWait = false;
    Canvas canvas; //화면에 무언가를 그리기 위한 캔바스 객체(sHolder가 가지고 있다)


    //생성자
    public BallGothread(Context context, SurfaceHolder sHolder){
        this.context = context;
        this.sHolder = sHolder;

        //원을 그릴 색 만들어서 배열에 저장하기
        initPaint();
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
            //화면에 그리는 작업을 한다.
            try {
                //동기화 블럭에서 작업을 해야한다.
                synchronized (sHolder) {//그리기 위한 모든 작업을 하는곳
                    //canvas객체를 이용해서 반복적인 그리기 작업을 한다.

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






}
