package com.example.user.billardtrainningapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

/**
 * Created by user on 2017-01-26.
 */

public class CBall {
    private float sx;   //공의 시작 위치 X
    private float sy;   //공의 시작 위치 Y
    public float posX[]= new float[6];    //공의 다음 위치 배열 X
    public float posY[] = new float[6];    //공의 다음 위치 배열 Y
    public float tx;
    public float ty;
    public float vx=100;    //공의 X축 속도
    public float vy=100;    //공의 Y축 속도
    private float rotateX;
    private float rotateY;

    public CBall(float sx, float sy, float vx, float vy)
    {
        //this.bitmaps=bitmaps;
        //this.gameView=gameView;
        this.sx=sx;
        this.sy=sy;
        this.tx=sx;
        this.ty=sy;
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
        boolean state = false;
        if((posX-tx)<1&&(posY-ty)<1) return state=true;
        else return false;
    }

    public void Go(float posX, float posY){
        tx+=vx;
        ty+=vy;

        if (posX >= tx && posY >= ty) {
            tx += vx;
            ty += vy;
        }
        else if (posX >= tx && posY <= ty) {
            tx += vx;
            ty -= vy;
        }
        else if (posX <= tx && posY >= ty) {
            tx -= vx;
            ty += vy;
        }
        else {
            tx -= vx;
            ty -= vy;
        }
    }

    public float getSx() {
        return sx;
    }
    public float getSy() {
        return sy;
    }



}
