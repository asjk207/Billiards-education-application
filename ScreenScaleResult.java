package com.example.user.billardtrainningapplication;

import android.util.Log;

/**
 * Created by user on 2017-01-29.
 */




    //缩放计算的结果
    public class ScreenScaleResult
    {
        private double Widthratio;
        private double Heightratio;

        public int targetwidth;
        public int targetheight;

        public double dtargetwidth;
        public double dtargetheight;


        public ScreenScaleResult(int targetwidth,int targetheight)
        {
            this.targetwidth=targetwidth;
            this.targetheight=targetheight;
        }
        public ScreenScaleResult(double targetwidth,double targetheight)
        {
            this.dtargetwidth=targetwidth;
            this.dtargetheight=targetheight;
        }
        public void CalcDBScreenRatio(int width, int height){
            this.Heightratio=((double)targetheight/(double)width);
            this.Widthratio=((double)targetwidth/(double)height);
            Log.e("**************", "화면 비율   넓이"+Widthratio+"높이"+Heightratio);
        }

        public void CalcScreenRatio(int width, int height){
            this.Widthratio=(double)targetheight/(double)width;
            this.Heightratio=(double)targetwidth/(double)height;
            //Log.e("**************", "화면 비율   넓이"+Widthratio+"높이"+Heightratio);
        }
        public void CalcCorrectScreenRatio(int width, int height){
            this.Heightratio=(double)targetheight/(double)height;
            this.Widthratio=(double)targetwidth/(double)width;
            Log.e("**************", "화면 비율   넓이"+Widthratio+"높이"+Heightratio);
        }

        //비율과 가로세로 종횡비 바뀜
        public float getRatioPosX(float x){
            //Log.e("**************", "비율 맞춘 X좌표 "+(targetwidth-(Heightratio*y)));
            return (float)(Widthratio*x);
        }
        public float getRatioPosY(float y) {
            //Log.e("**************", "비율 맞춘 Y좌표 "+Widthratio*x);
            return (float)(Heightratio*y);
        }


        //비율만 줄임
        public int getRatioPosX2(int x){
            //Log.e("**************", "비율 맞춘 X좌표 "+(targetwidth-(Heightratio*y)));
            return (int)(Widthratio*x);
        }
        public int getRatioPosY2(int y) {
            //Log.e("**************", "비율 맞춘 Y좌표 "+Widthratio*x);
            return (int)(Heightratio*y);
        }





    }

