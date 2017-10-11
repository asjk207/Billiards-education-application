package com.example.user.billardtrainningapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;

/**
 * Created by user on 2017-01-26.
 */

public class CBall {
    public float startx;   //공의 시작 위치 X
    public float starty;   //공의 시작 위치 Y
    public float posX[] = new float[6];    //공의 다음 위치 배열 X
    public float posY[] = new float[6];    //공의 다음 위치 배열 Y
    public float stx;          //각 좌표마다의 시작위치
    public float sty;
    public int tx;           //공의 현재 위치
    public int ty;

    public float dtx;           //공의 현재 위치
    public float dty;

    public double vx;    //공의 X축 속도
    public double vy;    //공의 Y축 속도

    public float m;        //가고자 하는 직선의 기울기
    public float b;
    private float rotateX;
    private float rotateY;

    public CBall(int startx, int starty)
    {
        //this.bitmaps=bitmaps;
        //this.gameView=gameView;
        this.startx=startx;
        this.starty=starty;
        this.stx=startx;
        this.sty=starty;
        this.tx=startx;
        this.ty=starty;
        this.vx=vx;
        this.vy=vy;
        //this.x=pos[0];
        //this.y=pos[1];
        //this.table=gameView.table;
        //rotateX=bitmaps[0].getWidth()/2;
        //rotateY=bitmaps[0].getHeight()/2;
    }

    public CBall(float startx, float starty)
    {
        //this.bitmaps=bitmaps;
        //this.gameView=gameView;
        this.startx=startx;
        this.starty=starty;
        this.stx=startx;
        this.sty=starty;
        this.dtx=startx;
        this.dty=starty;
        this.vx=vx;
        this.vy=vy;
        //this.x=pos[0];
        //this.y=pos[1];
        //this.table=gameView.table;
        //rotateX=bitmaps[0].getWidth()/2;
        //rotateY=bitmaps[0].getHeight()/2;
    }

   public void drawSelf(float posX, float posY, Canvas canvas, Paint paint)
    {


    }

    public boolean posCorrect(float posX, float posY){
        boolean state1 = false;
        boolean state2 = false;

         return true;
    }

    public void LineGo(int posX, int posY){
        //직선의 방정식
        //기울기
        //Log.i("st좌표  "," stx의값 : "+stx+ "  sty의 값 : "+sty);
       // m=(posX-stx)/(posY-sty);
        //b=sty-m*stx;

        //b=sx-sy;
        /*if(m==0) {
            if((posY-sty)==0) {
                vx=(posX-stx)/30;
                vy=0;
            }
            else if((posX-stx)==0){
                vx=0;
                vy=(posY-sty)/30;
            }
        }*/

        vx=(posX-stx)/25;
        vy=(posY-sty)/25;


        tx+=(int)vx;
        ty+=(int)vy;
//        Log.e("값  ", "stx : " + stx + " vx : " + vx + "  tx : " + tx + "stx 값 : "+stx);
        //tx=posX;
        //ty=posY;

    }
    public void LineGo(float posX, float posY){
        //직선의 방정식
        //기울기
        //Log.i("st좌표  "," stx의값 : "+stx+ "  sty의 값 : "+sty);
        // m=(posX-stx)/(posY-sty);
        //b=sty-m*stx;

        //b=sx-sy;
        /*if(m==0) {
            if((posY-sty)==0) {
                vx=(posX-stx)/30;
                vy=0;
            }
            else if((posX-stx)==0){
                vx=0;
                vy=(posY-sty)/30;
            }
        }*/

        vx=(posX-stx);
        vy=(posY-sty);


        tx+=vx;
        ty+=vy;
//        Log.e("값  ", "stx 값: " + stx + "sty 값 : "+sty);
        //tx=posX;
        //ty=posY;

    }
    public void setSx(float x) {
        this.startx=x;
    }
    public void setSy(float y) { this.starty=y; }


    public float getSx() {
        return startx;
    }
    public float getSy() {
        return starty;
    }



}
