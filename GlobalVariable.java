package com.example.user.billardtrainningapplication;

import android.app.Application;

/**
 * Created by user on 2017-02-16.
 */
//Activity 간의 전역변수
public class GlobalVariable extends Application {

    public static boolean linevisible=false;
    public static boolean BallGoDraw=false;

    public static String casekinds;
    public static int casenum=1;
    //교본에서 유투브로 casenum전달 변수
    public static int CourseBook_Youtube_Casenum = 1;
    //유투브 교본 구별 변수 1=교본 0=훈련
    public static int CourseBook_Youtube_Switcher = 0;
    //ViewPager 구별 변수 1=옆돌리기 2=앞돌리기 3=뒤돌리기 4=빗겨치기 5=꺽어치기 6=횡단샷
    public static int ViewPager_Switcher=0;
    //0=null 1=수구 2=제1적구
    public static int whiteball=0;
    public static int yellowball=0;
    public static int redball=0;

    public static int casecount;

    public static String Thick;
    public static String Point;
    public static String Tip;
    public static String SetThick=null;
    public static String SetPoint=null;
    public static String SetTip=null;
    public static String SetStrock=null;
    public static int CaseCorrect=1;

    //문제유형 B=기본정석 A=정석응용 R=실전문제
    public static String Global_Difficulty;
    //Menu->Other Button = 1; Default = 0
    public static int Menu_Switcher=0;

    public static int[] OneValueArray = {
            R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4, R.drawable.a5, R.drawable.a6, R.drawable.a7, R.drawable.ar1, R.drawable.ar2, R.drawable.ar3, R.drawable.ar4, R.drawable.ar5, R.drawable.ar6, R.drawable.ar7,/*13*/
            R.drawable.c0,R.drawable.c1, R.drawable.c2, R.drawable.c3,/*17*/
            R.drawable.b1, R.drawable.b2, R.drawable.b3, R.drawable.b4, R.drawable.b5, R.drawable.b6, R.drawable.b7, R.drawable.b8, R.drawable.b9, R.drawable.b10, R.drawable.b11, R.drawable.b12/*29*/,
            R.drawable.thick_normal
    };
    public static int[] yOneValueArray = {
            R.drawable.ya1, R.drawable.ya2, R.drawable.ya3, R.drawable.ya4, R.drawable.ya5, R.drawable.ya6, R.drawable.ya7, R.drawable.yar1, R.drawable.yar2, R.drawable.yar3, R.drawable.yar4, R.drawable.yar5, R.drawable.yar6, R.drawable.yar7,/*13*/
            R.drawable.ythick_normal/*14*/
    };
    public static int[] TwoValueArray = {
            R.drawable.b1c1,R.drawable.b1c2,R.drawable.b1c3,R.drawable.b1c4,/*3*/
            R.drawable.b2c1,R.drawable.b2c2,R.drawable.b2c3,R.drawable.b2c4,/*7*/
            R.drawable.b3c1,R.drawable.b3c2,R.drawable.b3c3,R.drawable.b3c4,/*11*/
            R.drawable.b4c1,R.drawable.b4c2,R.drawable.b4c3,R.drawable.b4c4,/*15*/
            R.drawable.b5c1,R.drawable.b5c2,R.drawable.b5c3,R.drawable.b5c4,/*19*/
            R.drawable.b6c1,R.drawable.b6c2,R.drawable.b6c3,R.drawable.b6c4,/*23*/
            R.drawable.b7c1,R.drawable.b7c2,R.drawable.b7c3,R.drawable.b7c4,/*27*/
            R.drawable.b8c1,R.drawable.b8c2,R.drawable.b8c3,R.drawable.b8c4,/*31*/
            R.drawable.b9c1,R.drawable.b9c2,R.drawable.b9c3,R.drawable.b9c4,/*35*/
            R.drawable.b10c1,R.drawable.b10c2,R.drawable.b10c3,R.drawable.b10c4,/*39*/
            R.drawable.b11c1,R.drawable.b11c2,R.drawable.b11c3,R.drawable.b11c4,/*43*/
            R.drawable.b12c1,R.drawable.b12c2,R.drawable.b12c3,R.drawable.b12c4,/*47*/
    };

    public static  int[] NoTipArray = {
            R.drawable.a1bxc0,R.drawable.a2bxc0,R.drawable.a3bxc0,R.drawable.a4bxc0,R.drawable.a5bxc0,R.drawable.a6bxc0,R.drawable.a7bxc0,/*6*/
            R.drawable.ar1bxc0,R.drawable.ar2bxc0,R.drawable.ar3bxc0,R.drawable.ar4bxc0,R.drawable.ar5bxc0,R.drawable.ar6bxc0,R.drawable.ar7bxc0,/*13*/
            R.drawable.bxc0/*14*/
    };
    public static  int[] yNoTipArray = {
            R.drawable.ya1bxc0,R.drawable.ya2bxc0,R.drawable.ya3bxc0,R.drawable.ya4bxc0,R.drawable.ya5bxc0,R.drawable.ya6bxc0,R.drawable.ya7bxc0,/*6*/
            R.drawable.yar1bxc0,R.drawable.yar2bxc0,R.drawable.yar3bxc0,R.drawable.yar4bxc0,R.drawable.yar5bxc0,R.drawable.yar6bxc0,R.drawable.yar7bxc0/*13*/
    };
    public static int[] ThreeValueArray = {
            R.drawable.a1b1c1,R.drawable.a1b1c2,R.drawable.a1b1c3,R.drawable.a1b1c4,R.drawable.a1b2c1,R.drawable.a1b2c2,R.drawable.a1b2c3,R.drawable.a1b2c4,
            R.drawable.a1b3c1,R.drawable.a1b3c2,R.drawable.a1b3c3,R.drawable.a1b3c4,R.drawable.a1b4c1,R.drawable.a1b4c2,R.drawable.a1b4c3,R.drawable.a1b4c4,
            R.drawable.a1b5c1,R.drawable.a1b5c2,R.drawable.a1b5c3,R.drawable.a1b5c4,R.drawable.a1b6c1,R.drawable.a1b6c2,R.drawable.a1b6c3,R.drawable.a1b6c4,
            R.drawable.a1b7c1,R.drawable.a1b7c2,R.drawable.a1b7c3,R.drawable.a1b7c4,R.drawable.a1b8c1,R.drawable.a1b8c2,R.drawable.a1b8c3,R.drawable.a1b8c4,
            R.drawable.a1b9c1,R.drawable.a1b9c2,R.drawable.a1b9c3,R.drawable.a1b9c4,R.drawable.a1b10c1,R.drawable.a1b10c2,R.drawable.a1b10c3,R.drawable.a1b10c4,
            R.drawable.a1b11c1,R.drawable.a1b11c2,R.drawable.a1b11c3,R.drawable.a1b11c4,R.drawable.a1b12c1,R.drawable.a1b12c2,R.drawable.a1b12c3,R.drawable.a1b12c4,

            R.drawable.a2b1c1,R.drawable.a2b1c2,R.drawable.a2b1c3,R.drawable.a2b1c4,R.drawable.a2b2c1,R.drawable.a2b2c2,R.drawable.a2b2c3,R.drawable.a2b2c4,
            R.drawable.a2b3c1,R.drawable.a2b3c2,R.drawable.a2b3c3,R.drawable.a2b3c4,R.drawable.a2b4c1,R.drawable.a2b4c2,R.drawable.a2b4c3,R.drawable.a2b4c4,
            R.drawable.a2b5c1,R.drawable.a2b5c2,R.drawable.a2b5c3,R.drawable.a2b5c4,R.drawable.a2b6c1,R.drawable.a2b6c2,R.drawable.a2b6c3,R.drawable.a2b6c4,
            R.drawable.a2b7c1,R.drawable.a2b7c2,R.drawable.a2b7c3,R.drawable.a2b7c4,R.drawable.a2b8c1,R.drawable.a2b8c2,R.drawable.a2b8c3,R.drawable.a2b8c4,
            R.drawable.a2b9c1,R.drawable.a2b9c2,R.drawable.a2b9c3,R.drawable.a2b9c4,R.drawable.a2b10c1,R.drawable.a2b10c2,R.drawable.a2b10c3,R.drawable.a2b10c4,
            R.drawable.a2b11c1,R.drawable.a2b11c2,R.drawable.a2b11c3,R.drawable.a2b11c4,R.drawable.a2b12c1,R.drawable.a2b12c2,R.drawable.a2b12c3,R.drawable.a2b12c4,

            R.drawable.a3b1c1, R.drawable.a3b1c2, R.drawable.a3b1c3, R.drawable.a3b1c4,  R.drawable.a3b2c1, R.drawable.a3b2c2,R.drawable.a3b2c3,R.drawable.a3b2c4,
            R.drawable.a3b3c1, R.drawable.a3b3c2, R.drawable.a3b3c3, R.drawable.a3b3c4,  R.drawable.a3b4c1, R.drawable.a3b4c2,R.drawable.a3b4c3,R.drawable.a3b4c4,
            R.drawable.a3b5c1, R.drawable.a3b5c2, R.drawable.a3b5c3, R.drawable.a3b5c4,  R.drawable.a3b6c1, R.drawable.a3b6c2,R.drawable.a3b6c3,R.drawable.a3b6c4,
            R.drawable.a3b7c1, R.drawable.a3b7c2, R.drawable.a3b7c3, R.drawable.a3b7c4,  R.drawable.a3b8c1, R.drawable.a3b8c2,R.drawable.a3b8c3,R.drawable.a3b8c4,
            R.drawable.a3b9c1, R.drawable.a3b9c2, R.drawable.a3b9c3, R.drawable.a3b9c4,  R.drawable.a3b10c1,R.drawable.a3b10c2,R.drawable.a3b10c3,R.drawable.a3b10c4,
            R.drawable.a3b11c1,R.drawable.a3b11c2,R.drawable.a3b11c3,R.drawable.a3b11c4,R.drawable.a3b12c1,R.drawable.a3b12c2,R.drawable.a3b12c3,R.drawable.a3b12c4,

            R.drawable.a4b1c1, R.drawable.a4b1c2, R.drawable.a4b1c3, R.drawable.a4b1c4,  R.drawable.a4b2c1, R.drawable.a4b2c2,R.drawable.a4b2c3,R.drawable.a4b2c4,
            R.drawable.a4b3c1, R.drawable.a4b3c2, R.drawable.a4b3c3, R.drawable.a4b3c4,  R.drawable.a4b4c1, R.drawable.a4b4c2,R.drawable.a4b4c3,R.drawable.a4b4c4,
            R.drawable.a4b5c1, R.drawable.a4b5c2, R.drawable.a4b5c3, R.drawable.a4b5c4,  R.drawable.a4b6c1, R.drawable.a4b6c2,R.drawable.a4b6c3,R.drawable.a4b6c4,
            R.drawable.a4b7c1, R.drawable.a4b7c2, R.drawable.a4b7c3, R.drawable.a4b7c4,  R.drawable.a4b8c1, R.drawable.a4b8c2,R.drawable.a4b8c3,R.drawable.a4b8c4,
            R.drawable.a4b9c1, R.drawable.a4b9c2, R.drawable.a4b9c3, R.drawable.a4b9c4,  R.drawable.a4b10c1,R.drawable.a4b10c2,R.drawable.a4b10c3,R.drawable.a4b10c4,
            R.drawable.a4b11c1,R.drawable.a4b11c2,R.drawable.a4b11c3,R.drawable.a4b11c4,R.drawable.a4b12c1,R.drawable.a4b12c2,R.drawable.a4b12c3,R.drawable.a4b12c4,

            R.drawable.a5b1c1, R.drawable.a5b1c2, R.drawable.a5b1c3, R.drawable.a5b1c4,  R.drawable.a5b2c1, R.drawable.a5b2c2,R.drawable.a5b2c3,R.drawable.a5b2c4,
            R.drawable.a5b3c1, R.drawable.a5b3c2, R.drawable.a5b3c3, R.drawable.a5b3c4,  R.drawable.a5b4c1, R.drawable.a5b4c2,R.drawable.a5b4c3,R.drawable.a5b4c4,
            R.drawable.a5b5c1, R.drawable.a5b5c2, R.drawable.a5b5c3, R.drawable.a5b5c4,  R.drawable.a5b6c1, R.drawable.a5b6c2,R.drawable.a5b6c3,R.drawable.a5b6c4,
            R.drawable.a5b7c1, R.drawable.a5b7c2, R.drawable.a5b7c3, R.drawable.a5b7c4,  R.drawable.a5b8c1, R.drawable.a5b8c2,R.drawable.a5b8c3,R.drawable.a5b8c4,
            R.drawable.a5b9c1, R.drawable.a5b9c2, R.drawable.a5b9c3, R.drawable.a5b9c4,  R.drawable.a5b10c1,R.drawable.a5b10c2,R.drawable.a5b10c3,R.drawable.a5b10c4,
            R.drawable.a5b11c1,R.drawable.a5b11c2,R.drawable.a5b11c3,R.drawable.a5b11c4,R.drawable.a5b12c1,R.drawable.a5b12c2,R.drawable.a5b12c3,R.drawable.a5b12c4,

            R.drawable.a6b1c1, R.drawable.a6b1c2, R.drawable.a6b1c3, R.drawable.a6b1c4,  R.drawable.a6b2c1, R.drawable.a6b2c2,R.drawable.a6b2c3,R.drawable.a6b2c4,
            R.drawable.a6b3c1, R.drawable.a6b3c2, R.drawable.a6b3c3, R.drawable.a6b3c4,  R.drawable.a6b4c1, R.drawable.a6b4c2,R.drawable.a6b4c3,R.drawable.a6b4c4,
            R.drawable.a6b5c1, R.drawable.a6b5c2, R.drawable.a6b5c3, R.drawable.a6b5c4,  R.drawable.a6b6c1, R.drawable.a6b6c2,R.drawable.a6b6c3,R.drawable.a6b6c4,
            R.drawable.a6b7c1, R.drawable.a6b7c2, R.drawable.a6b7c3, R.drawable.a6b7c4,  R.drawable.a6b8c1, R.drawable.a6b8c2,R.drawable.a6b8c3,R.drawable.a6b8c4,
            R.drawable.a6b9c1, R.drawable.a6b9c2, R.drawable.a6b9c3, R.drawable.a6b9c4,  R.drawable.a6b10c1,R.drawable.a6b10c2,R.drawable.a6b10c3,R.drawable.a6b10c4,
            R.drawable.a6b11c1,R.drawable.a6b11c2,R.drawable.a6b11c3,R.drawable.a6b11c4,R.drawable.a6b12c1,R.drawable.a6b12c2,R.drawable.a6b12c3,R.drawable.a6b12c4,

            R.drawable.a7b1c1, R.drawable.a7b1c2, R.drawable.a7b1c3, R.drawable.a7b1c4,  R.drawable.a7b2c1, R.drawable.a7b2c2,R.drawable.a7b2c3,R.drawable.a7b2c4,
            R.drawable.a7b3c1, R.drawable.a7b3c2, R.drawable.a7b3c3, R.drawable.a7b3c4,  R.drawable.a7b4c1, R.drawable.a7b4c2,R.drawable.a7b4c3,R.drawable.a7b4c4,
            R.drawable.a7b5c1, R.drawable.a7b5c2, R.drawable.a7b5c3, R.drawable.a7b5c4,  R.drawable.a7b6c1, R.drawable.a7b6c2,R.drawable.a7b6c3,R.drawable.a7b6c4,
            R.drawable.a7b7c1, R.drawable.a7b7c2, R.drawable.a7b7c3, R.drawable.a7b7c4,  R.drawable.a7b8c1, R.drawable.a7b8c2,R.drawable.a7b8c3,R.drawable.a7b8c4,
            R.drawable.a7b9c1, R.drawable.a7b9c2, R.drawable.a7b9c3, R.drawable.a7b9c4,  R.drawable.a7b10c1,R.drawable.a7b10c2,R.drawable.a7b10c3,R.drawable.a7b10c4,
            R.drawable.a7b11c1,R.drawable.a7b11c2,R.drawable.a7b11c3,R.drawable.a7b11c4,R.drawable.a7b12c1,R.drawable.a7b12c2,R.drawable.a7b12c3,R.drawable.a7b12c4,

            R.drawable.ar1b1c1,R.drawable.ar1b1c2,R.drawable.ar1b1c3,R.drawable.ar1b1c4,R.drawable.ar1b2c1,R.drawable.ar1b2c2,R.drawable.ar1b2c3,R.drawable.ar1b2c4,
            R.drawable.ar1b3c1,R.drawable.ar1b3c2,R.drawable.ar1b3c3,R.drawable.ar1b3c4,R.drawable.ar1b4c1,R.drawable.ar1b4c2,R.drawable.ar1b4c3,R.drawable.ar1b4c4,
            R.drawable.ar1b5c1,R.drawable.ar1b5c2,R.drawable.ar1b5c3,R.drawable.ar1b5c4,R.drawable.ar1b6c1,R.drawable.ar1b6c2,R.drawable.ar1b6c3,R.drawable.ar1b6c4,
            R.drawable.ar1b7c1,R.drawable.ar1b7c2,R.drawable.ar1b7c3,R.drawable.ar1b7c4,R.drawable.ar1b8c1,R.drawable.ar1b8c2,R.drawable.ar1b8c3,R.drawable.ar1b8c4,
            R.drawable.ar1b9c1,R.drawable.ar1b9c2,R.drawable.ar1b9c3,R.drawable.ar1b9c4,R.drawable.ar1b10c1,R.drawable.ar1b10c2,R.drawable.ar1b10c3,R.drawable.ar1b10c4,
            R.drawable.ar1b11c1,R.drawable.ar1b11c2,R.drawable.ar1b11c3,R.drawable.ar1b11c4,R.drawable.ar1b12c1,R.drawable.ar1b12c2,R.drawable.ar1b12c3,R.drawable.ar1b12c4,

            R.drawable.ar2b1c1,R.drawable.ar2b1c2,R.drawable.ar2b1c3,R.drawable.ar2b1c4,R.drawable.ar2b2c1,R.drawable.ar2b2c2,R.drawable.ar2b2c3,R.drawable.ar2b2c4,
            R.drawable.ar2b3c1,R.drawable.ar2b3c2,R.drawable.ar2b3c3,R.drawable.ar2b3c4,R.drawable.ar2b4c1,R.drawable.ar2b4c2,R.drawable.ar2b4c3,R.drawable.ar2b4c4,
            R.drawable.ar2b5c1,R.drawable.ar2b5c2,R.drawable.ar2b5c3,R.drawable.ar2b5c4,R.drawable.ar2b6c1,R.drawable.ar2b6c2,R.drawable.ar2b6c3,R.drawable.ar2b6c4,
            R.drawable.ar2b7c1,R.drawable.ar2b7c2,R.drawable.ar2b7c3,R.drawable.ar2b7c4,R.drawable.ar2b8c1,R.drawable.ar2b8c2,R.drawable.ar2b8c3,R.drawable.ar2b8c4,
            R.drawable.ar2b9c1,R.drawable.ar2b9c2,R.drawable.ar2b9c3,R.drawable.ar2b9c4,R.drawable.ar2b10c1,R.drawable.ar2b10c2,R.drawable.ar2b10c3,R.drawable.ar2b10c4,
            R.drawable.ar2b11c1,R.drawable.ar2b11c2,R.drawable.ar2b11c3,R.drawable.ar2b11c4,R.drawable.ar2b12c1,R.drawable.ar2b12c2,R.drawable.ar2b12c3,R.drawable.ar2b12c4,

            R.drawable.ar3b1c1, R.drawable.ar3b1c2, R.drawable.ar3b1c3, R.drawable.ar3b1c4,  R.drawable.ar3b2c1, R.drawable.ar3b2c2,R.drawable.ar3b2c3,R.drawable.ar3b2c4,
            R.drawable.ar3b3c1, R.drawable.ar3b3c2, R.drawable.ar3b3c3, R.drawable.ar3b3c4,  R.drawable.ar3b4c1, R.drawable.ar3b4c2,R.drawable.ar3b4c3,R.drawable.ar3b4c4,
            R.drawable.ar3b5c1, R.drawable.ar3b5c2, R.drawable.ar3b5c3, R.drawable.ar3b5c4,  R.drawable.ar3b6c1, R.drawable.ar3b6c2,R.drawable.ar3b6c3,R.drawable.ar3b6c4,
            R.drawable.ar3b7c1, R.drawable.ar3b7c2, R.drawable.ar3b7c3, R.drawable.ar3b7c4,  R.drawable.ar3b8c1, R.drawable.ar3b8c2,R.drawable.ar3b8c3,R.drawable.ar3b8c4,
            R.drawable.ar3b9c1, R.drawable.ar3b9c2, R.drawable.ar3b9c3, R.drawable.ar3b9c4,  R.drawable.ar3b10c1,R.drawable.ar3b10c2,R.drawable.ar3b10c3,R.drawable.ar3b10c4,
            R.drawable.ar3b11c1,R.drawable.ar3b11c2,R.drawable.ar3b11c3,R.drawable.ar3b11c4,R.drawable.ar3b12c1,R.drawable.ar3b12c2,R.drawable.ar3b12c3,R.drawable.ar3b12c4,

            R.drawable.ar4b1c1, R.drawable.ar4b1c2, R.drawable.ar4b1c3, R.drawable.ar4b1c4,  R.drawable.ar4b2c1, R.drawable.ar4b2c2,R.drawable.ar4b2c3,R.drawable.ar4b2c4,
            R.drawable.ar4b3c1, R.drawable.ar4b3c2, R.drawable.ar4b3c3, R.drawable.ar4b3c4,  R.drawable.ar4b4c1, R.drawable.ar4b4c2,R.drawable.ar4b4c3,R.drawable.ar4b4c4,
            R.drawable.ar4b5c1, R.drawable.ar4b5c2, R.drawable.ar4b5c3, R.drawable.ar4b5c4,  R.drawable.ar4b6c1, R.drawable.ar4b6c2,R.drawable.ar4b6c3,R.drawable.ar4b6c4,
            R.drawable.ar4b7c1, R.drawable.ar4b7c2, R.drawable.ar4b7c3, R.drawable.ar4b7c4,  R.drawable.ar4b8c1, R.drawable.ar4b8c2,R.drawable.ar4b8c3,R.drawable.ar4b8c4,
            R.drawable.ar4b9c1, R.drawable.ar4b9c2, R.drawable.ar4b9c3, R.drawable.ar4b9c4,  R.drawable.ar4b10c1,R.drawable.ar4b10c2,R.drawable.ar4b10c3,R.drawable.ar4b10c4,
            R.drawable.ar4b11c1,R.drawable.ar4b11c2,R.drawable.ar4b11c3,R.drawable.ar4b11c4,R.drawable.ar4b12c1,R.drawable.ar4b12c2,R.drawable.ar4b12c3,R.drawable.ar4b12c4,

            R.drawable.ar5b1c1, R.drawable.ar5b1c2, R.drawable.ar5b1c3, R.drawable.ar5b1c4,  R.drawable.ar5b2c1, R.drawable.ar5b2c2,R.drawable.ar5b2c3,R.drawable.ar5b2c4,
            R.drawable.ar5b3c1, R.drawable.ar5b3c2, R.drawable.ar5b3c3, R.drawable.ar5b3c4,  R.drawable.ar5b4c1, R.drawable.ar5b4c2,R.drawable.ar5b4c3,R.drawable.ar5b4c4,
            R.drawable.ar5b5c1, R.drawable.ar5b5c2, R.drawable.ar5b5c3, R.drawable.ar5b5c4,  R.drawable.ar5b6c1, R.drawable.ar5b6c2,R.drawable.ar5b6c3,R.drawable.ar5b6c4,
            R.drawable.ar5b7c1, R.drawable.ar5b7c2, R.drawable.ar5b7c3, R.drawable.ar5b7c4,  R.drawable.ar5b8c1, R.drawable.ar5b8c2,R.drawable.ar5b8c3,R.drawable.ar5b8c4,
            R.drawable.ar5b9c1, R.drawable.ar5b9c2, R.drawable.ar5b9c3, R.drawable.ar5b9c4,  R.drawable.ar5b10c1,R.drawable.ar5b10c2,R.drawable.ar5b10c3,R.drawable.ar5b10c4,
            R.drawable.ar5b11c1,R.drawable.ar5b11c2,R.drawable.ar5b11c3,R.drawable.ar5b11c4,R.drawable.ar5b12c1,R.drawable.ar5b12c2,R.drawable.ar5b12c3,R.drawable.ar5b12c4,

            R.drawable.ar6b1c1, R.drawable.ar6b1c2, R.drawable.ar6b1c3, R.drawable.ar6b1c4,  R.drawable.ar6b2c1, R.drawable.ar6b2c2,R.drawable.ar6b2c3,R.drawable.ar6b2c4,
            R.drawable.ar6b3c1, R.drawable.ar6b3c2, R.drawable.ar6b3c3, R.drawable.ar6b3c4,  R.drawable.ar6b4c1, R.drawable.ar6b4c2,R.drawable.ar6b4c3,R.drawable.ar6b4c4,
            R.drawable.ar6b5c1, R.drawable.ar6b5c2, R.drawable.ar6b5c3, R.drawable.ar6b5c4,  R.drawable.ar6b6c1, R.drawable.ar6b6c2,R.drawable.ar6b6c3,R.drawable.ar6b6c4,
            R.drawable.ar6b7c1, R.drawable.ar6b7c2, R.drawable.ar6b7c3, R.drawable.ar6b7c4,  R.drawable.ar6b8c1, R.drawable.ar6b8c2,R.drawable.ar6b8c3,R.drawable.ar6b8c4,
            R.drawable.ar6b9c1, R.drawable.ar6b9c2, R.drawable.ar6b9c3, R.drawable.ar6b9c4,  R.drawable.ar6b10c1,R.drawable.ar6b10c2,R.drawable.ar6b10c3,R.drawable.ar6b10c4,
            R.drawable.ar6b11c1,R.drawable.ar6b11c2,R.drawable.ar6b11c3,R.drawable.ar6b11c4,R.drawable.ar6b12c1,R.drawable.ar6b12c2,R.drawable.ar6b12c3,R.drawable.ar6b12c4,

            R.drawable.ar7b1c1, R.drawable.ar7b1c2, R.drawable.ar7b1c3, R.drawable.ar7b1c4,  R.drawable.ar7b2c1, R.drawable.ar7b2c2,R.drawable.ar7b2c3,R.drawable.ar7b2c4,
            R.drawable.ar7b3c1, R.drawable.ar7b3c2, R.drawable.ar7b3c3, R.drawable.ar7b3c4,  R.drawable.ar7b4c1, R.drawable.ar7b4c2,R.drawable.ar7b4c3,R.drawable.ar7b4c4,
            R.drawable.ar7b5c1, R.drawable.ar7b5c2, R.drawable.ar7b5c3, R.drawable.ar7b5c4,  R.drawable.ar7b6c1, R.drawable.ar7b6c2,R.drawable.ar7b6c3,R.drawable.ar7b6c4,
            R.drawable.ar7b7c1, R.drawable.ar7b7c2, R.drawable.ar7b7c3, R.drawable.ar7b7c4,  R.drawable.ar7b8c1, R.drawable.ar7b8c2,R.drawable.ar7b8c3,R.drawable.ar7b8c4,
            R.drawable.ar7b9c1, R.drawable.ar7b9c2, R.drawable.ar7b9c3, R.drawable.ar7b9c4,  R.drawable.ar7b10c1,R.drawable.ar7b10c2,R.drawable.ar7b10c3,R.drawable.ar7b10c4,
            R.drawable.ar7b11c1,R.drawable.ar7b11c2,R.drawable.ar7b11c3,R.drawable.ar7b11c4,R.drawable.ar7b12c1,R.drawable.ar7b12c2,R.drawable.ar7b12c3,R.drawable.ar7b12c4,
    };
    public static int[] yThreeValueArray = {
            R.drawable.ya1b1c1,R.drawable.ya1b1c2,R.drawable.ya1b1c3,R.drawable.ya1b1c4,R.drawable.ya1b2c1,R.drawable.ya1b2c2,R.drawable.ya1b2c3,R.drawable.ya1b2c4,
            R.drawable.ya1b3c1,R.drawable.ya1b3c2,R.drawable.ya1b3c3,R.drawable.ya1b3c4,R.drawable.ya1b4c1,R.drawable.ya1b4c2,R.drawable.ya1b4c3,R.drawable.ya1b4c4,
            R.drawable.ya1b5c1,R.drawable.ya1b5c2,R.drawable.ya1b5c3,R.drawable.ya1b5c4,R.drawable.ya1b6c1,R.drawable.ya1b6c2,R.drawable.ya1b6c3,R.drawable.ya1b6c4,
            R.drawable.ya1b7c1,R.drawable.ya1b7c2,R.drawable.ya1b7c3,R.drawable.ya1b7c4,R.drawable.ya1b8c1,R.drawable.ya1b8c2,R.drawable.ya1b8c3,R.drawable.ya1b8c4,
            R.drawable.ya1b9c1,R.drawable.ya1b9c2,R.drawable.ya1b9c3,R.drawable.ya1b9c4,R.drawable.ya1b10c1,R.drawable.ya1b10c2,R.drawable.ya1b10c3,R.drawable.ya1b10c4,
            R.drawable.ya1b11c1,R.drawable.ya1b11c2,R.drawable.ya1b11c3,R.drawable.ya1b11c4,R.drawable.ya1b12c1,R.drawable.ya1b12c2,R.drawable.ya1b12c3,R.drawable.ya1b12c4,

            R.drawable.ya2b1c1,R.drawable.ya2b1c2,R.drawable.ya2b1c3,R.drawable.ya2b1c4,R.drawable.ya2b2c1,R.drawable.ya2b2c2,R.drawable.ya2b2c3,R.drawable.ya2b2c4,
            R.drawable.ya2b3c1,R.drawable.ya2b3c2,R.drawable.ya2b3c3,R.drawable.ya2b3c4,R.drawable.ya2b4c1,R.drawable.ya2b4c2,R.drawable.ya2b4c3,R.drawable.ya2b4c4,
            R.drawable.ya2b5c1,R.drawable.ya2b5c2,R.drawable.ya2b5c3,R.drawable.ya2b5c4,R.drawable.ya2b6c1,R.drawable.ya2b6c2,R.drawable.ya2b6c3,R.drawable.ya2b6c4,
            R.drawable.ya2b7c1,R.drawable.ya2b7c2,R.drawable.ya2b7c3,R.drawable.ya2b7c4,R.drawable.ya2b8c1,R.drawable.ya2b8c2,R.drawable.ya2b8c3,R.drawable.ya2b8c4,
            R.drawable.ya2b9c1,R.drawable.ya2b9c2,R.drawable.ya2b9c3,R.drawable.ya2b9c4,R.drawable.ya2b10c1,R.drawable.ya2b10c2,R.drawable.ya2b10c3,R.drawable.ya2b10c4,
            R.drawable.ya2b11c1,R.drawable.ya2b11c2,R.drawable.ya2b11c3,R.drawable.ya2b11c4,R.drawable.ya2b12c1,R.drawable.ya2b12c2,R.drawable.ya2b12c3,R.drawable.ya2b12c4,

            R.drawable.ya3b1c1, R.drawable.ya3b1c2, R.drawable.ya3b1c3, R.drawable.ya3b1c4,  R.drawable.ya3b2c1, R.drawable.ya3b2c2,R.drawable.ya3b2c3,R.drawable.ya3b2c4,
            R.drawable.ya3b3c1, R.drawable.ya3b3c2, R.drawable.ya3b3c3, R.drawable.ya3b3c4,  R.drawable.ya3b4c1, R.drawable.ya3b4c2,R.drawable.ya3b4c3,R.drawable.ya3b4c4,
            R.drawable.ya3b5c1, R.drawable.ya3b5c2, R.drawable.ya3b5c3, R.drawable.ya3b5c4,  R.drawable.ya3b6c1, R.drawable.ya3b6c2,R.drawable.ya3b6c3,R.drawable.ya3b6c4,
            R.drawable.ya3b7c1, R.drawable.ya3b7c2, R.drawable.ya3b7c3, R.drawable.ya3b7c4,  R.drawable.ya3b8c1, R.drawable.ya3b8c2,R.drawable.ya3b8c3,R.drawable.ya3b8c4,
            R.drawable.ya3b9c1, R.drawable.ya3b9c2, R.drawable.ya3b9c3, R.drawable.ya3b9c4,  R.drawable.ya3b10c1,R.drawable.ya3b10c2,R.drawable.ya3b10c3,R.drawable.ya3b10c4,
            R.drawable.ya3b11c1,R.drawable.ya3b11c2,R.drawable.ya3b11c3,R.drawable.ya3b11c4,R.drawable.ya3b12c1,R.drawable.ya3b12c2,R.drawable.ya3b12c3,R.drawable.ya3b12c4,

            R.drawable.ya4b1c1, R.drawable.ya4b1c2, R.drawable.ya4b1c3, R.drawable.ya4b1c4,  R.drawable.ya4b2c1, R.drawable.ya4b2c2,R.drawable.ya4b2c3,R.drawable.ya4b2c4,
            R.drawable.ya4b3c1, R.drawable.ya4b3c2, R.drawable.ya4b3c3, R.drawable.ya4b3c4,  R.drawable.ya4b4c1, R.drawable.ya4b4c2,R.drawable.ya4b4c3,R.drawable.ya4b4c4,
            R.drawable.ya4b5c1, R.drawable.ya4b5c2, R.drawable.ya4b5c3, R.drawable.ya4b5c4,  R.drawable.ya4b6c1, R.drawable.ya4b6c2,R.drawable.ya4b6c3,R.drawable.ya4b6c4,
            R.drawable.ya4b7c1, R.drawable.ya4b7c2, R.drawable.ya4b7c3, R.drawable.ya4b7c4,  R.drawable.ya4b8c1, R.drawable.ya4b8c2,R.drawable.ya4b8c3,R.drawable.ya4b8c4,
            R.drawable.ya4b9c1, R.drawable.ya4b9c2, R.drawable.ya4b9c3, R.drawable.ya4b9c4,  R.drawable.ya4b10c1,R.drawable.ya4b10c2,R.drawable.ya4b10c3,R.drawable.ya4b10c4,
            R.drawable.ya4b11c1,R.drawable.ya4b11c2,R.drawable.ya4b11c3,R.drawable.ya4b11c4,R.drawable.ya4b12c1,R.drawable.ya4b12c2,R.drawable.ya4b12c3,R.drawable.ya4b12c4,

            R.drawable.ya5b1c1, R.drawable.ya5b1c2, R.drawable.ya5b1c3, R.drawable.ya5b1c4,  R.drawable.ya5b2c1, R.drawable.ya5b2c2,R.drawable.ya5b2c3,R.drawable.ya5b2c4,
            R.drawable.ya5b3c1, R.drawable.ya5b3c2, R.drawable.ya5b3c3, R.drawable.ya5b3c4,  R.drawable.ya5b4c1, R.drawable.ya5b4c2,R.drawable.ya5b4c3,R.drawable.ya5b4c4,
            R.drawable.ya5b5c1, R.drawable.ya5b5c2, R.drawable.ya5b5c3, R.drawable.ya5b5c4,  R.drawable.ya5b6c1, R.drawable.ya5b6c2,R.drawable.ya5b6c3,R.drawable.ya5b6c4,
            R.drawable.ya5b7c1, R.drawable.ya5b7c2, R.drawable.ya5b7c3, R.drawable.ya5b7c4,  R.drawable.ya5b8c1, R.drawable.ya5b8c2,R.drawable.ya5b8c3,R.drawable.ya5b8c4,
            R.drawable.ya5b9c1, R.drawable.ya5b9c2, R.drawable.ya5b9c3, R.drawable.ya5b9c4,  R.drawable.ya5b10c1,R.drawable.ya5b10c2,R.drawable.ya5b10c3,R.drawable.ya5b10c4,
            R.drawable.ya5b11c1,R.drawable.ya5b11c2,R.drawable.ya5b11c3,R.drawable.ya5b11c4,R.drawable.ya5b12c1,R.drawable.ya5b12c2,R.drawable.ya5b12c3,R.drawable.ya5b12c4,

            R.drawable.ya6b1c1, R.drawable.ya6b1c2, R.drawable.ya6b1c3, R.drawable.ya6b1c4,  R.drawable.ya6b2c1, R.drawable.ya6b2c2,R.drawable.ya6b2c3,R.drawable.ya6b2c4,
            R.drawable.ya6b3c1, R.drawable.ya6b3c2, R.drawable.ya6b3c3, R.drawable.ya6b3c4,  R.drawable.ya6b4c1, R.drawable.ya6b4c2,R.drawable.ya6b4c3,R.drawable.ya6b4c4,
            R.drawable.ya6b5c1, R.drawable.ya6b5c2, R.drawable.ya6b5c3, R.drawable.ya6b5c4,  R.drawable.ya6b6c1, R.drawable.ya6b6c2,R.drawable.ya6b6c3,R.drawable.ya6b6c4,
            R.drawable.ya6b7c1, R.drawable.ya6b7c2, R.drawable.ya6b7c3, R.drawable.ya6b7c4,  R.drawable.ya6b8c1, R.drawable.ya6b8c2,R.drawable.ya6b8c3,R.drawable.ya6b8c4,
            R.drawable.ya6b9c1, R.drawable.ya6b9c2, R.drawable.ya6b9c3, R.drawable.ya6b9c4,  R.drawable.ya6b10c1,R.drawable.ya6b10c2,R.drawable.ya6b10c3,R.drawable.ya6b10c4,
            R.drawable.ya6b11c1,R.drawable.ya6b11c2,R.drawable.ya6b11c3,R.drawable.ya6b11c4,R.drawable.ya6b12c1,R.drawable.ya6b12c2,R.drawable.ya6b12c3,R.drawable.ya6b12c4,

            R.drawable.ya7b1c1, R.drawable.ya7b1c2, R.drawable.ya7b1c3, R.drawable.ya7b1c4,  R.drawable.ya7b2c1, R.drawable.ya7b2c2,R.drawable.ya7b2c3,R.drawable.ya7b2c4,
            R.drawable.ya7b3c1, R.drawable.ya7b3c2, R.drawable.ya7b3c3, R.drawable.ya7b3c4,  R.drawable.ya7b4c1, R.drawable.ya7b4c2,R.drawable.ya7b4c3,R.drawable.ya7b4c4,
            R.drawable.ya7b5c1, R.drawable.ya7b5c2, R.drawable.ya7b5c3, R.drawable.ya7b5c4,  R.drawable.ya7b6c1, R.drawable.ya7b6c2,R.drawable.ya7b6c3,R.drawable.ya7b6c4,
            R.drawable.ya7b7c1, R.drawable.ya7b7c2, R.drawable.ya7b7c3, R.drawable.ya7b7c4,  R.drawable.ya7b8c1, R.drawable.ya7b8c2,R.drawable.ya7b8c3,R.drawable.ya7b8c4,
            R.drawable.ya7b9c1, R.drawable.ya7b9c2, R.drawable.ya7b9c3, R.drawable.ya7b9c4,  R.drawable.ya7b10c1,R.drawable.ya7b10c2,R.drawable.ya7b10c3,R.drawable.ya7b10c4,
            R.drawable.ya7b11c1,R.drawable.ya7b11c2,R.drawable.ya7b11c3,R.drawable.ya7b11c4,R.drawable.ya7b12c1,R.drawable.ya7b12c2,R.drawable.ya7b12c3,R.drawable.ya7b12c4,

            R.drawable.yar1b1c1,R.drawable.yar1b1c2,R.drawable.yar1b1c3,R.drawable.yar1b1c4,R.drawable.yar1b2c1,R.drawable.yar1b2c2,R.drawable.yar1b2c3,R.drawable.yar1b2c4,
            R.drawable.yar1b3c1,R.drawable.yar1b3c2,R.drawable.yar1b3c3,R.drawable.yar1b3c4,R.drawable.yar1b4c1,R.drawable.yar1b4c2,R.drawable.yar1b4c3,R.drawable.yar1b4c4,
            R.drawable.yar1b5c1,R.drawable.yar1b5c2,R.drawable.yar1b5c3,R.drawable.yar1b5c4,R.drawable.yar1b6c1,R.drawable.yar1b6c2,R.drawable.yar1b6c3,R.drawable.yar1b6c4,
            R.drawable.yar1b7c1,R.drawable.yar1b7c2,R.drawable.yar1b7c3,R.drawable.yar1b7c4,R.drawable.yar1b8c1,R.drawable.yar1b8c2,R.drawable.yar1b8c3,R.drawable.yar1b8c4,
            R.drawable.yar1b9c1,R.drawable.yar1b9c2,R.drawable.yar1b9c3,R.drawable.yar1b9c4,R.drawable.yar1b10c1,R.drawable.yar1b10c2,R.drawable.yar1b10c3,R.drawable.yar1b10c4,
            R.drawable.yar1b11c1,R.drawable.yar1b11c2,R.drawable.yar1b11c3,R.drawable.yar1b11c4,R.drawable.yar1b12c1,R.drawable.yar1b12c2,R.drawable.yar1b12c3,R.drawable.yar1b12c4,

            R.drawable.yar2b1c1,R.drawable.yar2b1c2,R.drawable.yar2b1c3,R.drawable.yar2b1c4,R.drawable.yar2b2c1,R.drawable.yar2b2c2,R.drawable.yar2b2c3,R.drawable.yar2b2c4,
            R.drawable.yar2b3c1,R.drawable.yar2b3c2,R.drawable.yar2b3c3,R.drawable.yar2b3c4,R.drawable.yar2b4c1,R.drawable.yar2b4c2,R.drawable.yar2b4c3,R.drawable.yar2b4c4,
            R.drawable.yar2b5c1,R.drawable.yar2b5c2,R.drawable.yar2b5c3,R.drawable.yar2b5c4,R.drawable.yar2b6c1,R.drawable.yar2b6c2,R.drawable.yar2b6c3,R.drawable.yar2b6c4,
            R.drawable.yar2b7c1,R.drawable.yar2b7c2,R.drawable.yar2b7c3,R.drawable.yar2b7c4,R.drawable.yar2b8c1,R.drawable.yar2b8c2,R.drawable.yar2b8c3,R.drawable.yar2b8c4,
            R.drawable.yar2b9c1,R.drawable.yar2b9c2,R.drawable.yar2b9c3,R.drawable.yar2b9c4,R.drawable.yar2b10c1,R.drawable.yar2b10c2,R.drawable.yar2b10c3,R.drawable.yar2b10c4,
            R.drawable.yar2b11c1,R.drawable.yar2b11c2,R.drawable.yar2b11c3,R.drawable.yar2b11c4,R.drawable.yar2b12c1,R.drawable.yar2b12c2,R.drawable.yar2b12c3,R.drawable.yar2b12c4,

            R.drawable.yar3b1c1, R.drawable.yar3b1c2, R.drawable.yar3b1c3, R.drawable.yar3b1c4,  R.drawable.yar3b2c1, R.drawable.yar3b2c2,R.drawable.yar3b2c3,R.drawable.yar3b2c4,
            R.drawable.yar3b3c1, R.drawable.yar3b3c2, R.drawable.yar3b3c3, R.drawable.yar3b3c4,  R.drawable.yar3b4c1, R.drawable.yar3b4c2,R.drawable.yar3b4c3,R.drawable.yar3b4c4,
            R.drawable.yar3b5c1, R.drawable.yar3b5c2, R.drawable.yar3b5c3, R.drawable.yar3b5c4,  R.drawable.yar3b6c1, R.drawable.yar3b6c2,R.drawable.yar3b6c3,R.drawable.yar3b6c4,
            R.drawable.yar3b7c1, R.drawable.yar3b7c2, R.drawable.yar3b7c3, R.drawable.yar3b7c4,  R.drawable.yar3b8c1, R.drawable.yar3b8c2,R.drawable.yar3b8c3,R.drawable.yar3b8c4,
            R.drawable.yar3b9c1, R.drawable.yar3b9c2, R.drawable.yar3b9c3, R.drawable.yar3b9c4,  R.drawable.yar3b10c1,R.drawable.yar3b10c2,R.drawable.yar3b10c3,R.drawable.yar3b10c4,
            R.drawable.yar3b11c1,R.drawable.yar3b11c2,R.drawable.yar3b11c3,R.drawable.yar3b11c4,R.drawable.yar3b12c1,R.drawable.yar3b12c2,R.drawable.yar3b12c3,R.drawable.yar3b12c4,

            R.drawable.yar4b1c1, R.drawable.yar4b1c2, R.drawable.yar4b1c3, R.drawable.yar4b1c4,  R.drawable.yar4b2c1, R.drawable.yar4b2c2,R.drawable.yar4b2c3,R.drawable.yar4b2c4,
            R.drawable.yar4b3c1, R.drawable.yar4b3c2, R.drawable.yar4b3c3, R.drawable.yar4b3c4,  R.drawable.yar4b4c1, R.drawable.yar4b4c2,R.drawable.yar4b4c3,R.drawable.yar4b4c4,
            R.drawable.yar4b5c1, R.drawable.yar4b5c2, R.drawable.yar4b5c3, R.drawable.yar4b5c4,  R.drawable.yar4b6c1, R.drawable.yar4b6c2,R.drawable.yar4b6c3,R.drawable.yar4b6c4,
            R.drawable.yar4b7c1, R.drawable.yar4b7c2, R.drawable.yar4b7c3, R.drawable.yar4b7c4,  R.drawable.yar4b8c1, R.drawable.yar4b8c2,R.drawable.yar4b8c3,R.drawable.yar4b8c4,
            R.drawable.yar4b9c1, R.drawable.yar4b9c2, R.drawable.yar4b9c3, R.drawable.yar4b9c4,  R.drawable.yar4b10c1,R.drawable.yar4b10c2,R.drawable.yar4b10c3,R.drawable.yar4b10c4,
            R.drawable.yar4b11c1,R.drawable.yar4b11c2,R.drawable.yar4b11c3,R.drawable.yar4b11c4,R.drawable.yar4b12c1,R.drawable.yar4b12c2,R.drawable.yar4b12c3,R.drawable.yar4b12c4,

            R.drawable.yar5b1c1, R.drawable.yar5b1c2, R.drawable.yar5b1c3, R.drawable.yar5b1c4,  R.drawable.yar5b2c1, R.drawable.yar5b2c2,R.drawable.yar5b2c3,R.drawable.yar5b2c4,
            R.drawable.yar5b3c1, R.drawable.yar5b3c2, R.drawable.yar5b3c3, R.drawable.yar5b3c4,  R.drawable.yar5b4c1, R.drawable.yar5b4c2,R.drawable.yar5b4c3,R.drawable.yar5b4c4,
            R.drawable.yar5b5c1, R.drawable.yar5b5c2, R.drawable.yar5b5c3, R.drawable.yar5b5c4,  R.drawable.yar5b6c1, R.drawable.yar5b6c2,R.drawable.yar5b6c3,R.drawable.yar5b6c4,
            R.drawable.yar5b7c1, R.drawable.yar5b7c2, R.drawable.yar5b7c3, R.drawable.yar5b7c4,  R.drawable.yar5b8c1, R.drawable.yar5b8c2,R.drawable.yar5b8c3,R.drawable.yar5b8c4,
            R.drawable.yar5b9c1, R.drawable.yar5b9c2, R.drawable.yar5b9c3, R.drawable.yar5b9c4,  R.drawable.yar5b10c1,R.drawable.yar5b10c2,R.drawable.yar5b10c3,R.drawable.yar5b10c4,
            R.drawable.yar5b11c1,R.drawable.yar5b11c2,R.drawable.yar5b11c3,R.drawable.yar5b11c4,R.drawable.yar5b12c1,R.drawable.yar5b12c2,R.drawable.yar5b12c3,R.drawable.yar5b12c4,

            R.drawable.yar6b1c1, R.drawable.yar6b1c2, R.drawable.yar6b1c3, R.drawable.yar6b1c4,  R.drawable.yar6b2c1, R.drawable.yar6b2c2,R.drawable.yar6b2c3,R.drawable.yar6b2c4,
            R.drawable.yar6b3c1, R.drawable.yar6b3c2, R.drawable.yar6b3c3, R.drawable.yar6b3c4,  R.drawable.yar6b4c1, R.drawable.yar6b4c2,R.drawable.yar6b4c3,R.drawable.yar6b4c4,
            R.drawable.yar6b5c1, R.drawable.yar6b5c2, R.drawable.yar6b5c3, R.drawable.yar6b5c4,  R.drawable.yar6b6c1, R.drawable.yar6b6c2,R.drawable.yar6b6c3,R.drawable.yar6b6c4,
            R.drawable.yar6b7c1, R.drawable.yar6b7c2, R.drawable.yar6b7c3, R.drawable.yar6b7c4,  R.drawable.yar6b8c1, R.drawable.yar6b8c2,R.drawable.yar6b8c3,R.drawable.yar6b8c4,
            R.drawable.yar6b9c1, R.drawable.yar6b9c2, R.drawable.yar6b9c3, R.drawable.yar6b9c4,  R.drawable.yar6b10c1,R.drawable.yar6b10c2,R.drawable.yar6b10c3,R.drawable.yar6b10c4,
            R.drawable.yar6b11c1,R.drawable.yar6b11c2,R.drawable.yar6b11c3,R.drawable.yar6b11c4,R.drawable.yar6b12c1,R.drawable.yar6b12c2,R.drawable.yar6b12c3,R.drawable.yar6b12c4,

            R.drawable.yar7b1c1, R.drawable.yar7b1c2, R.drawable.yar7b1c3, R.drawable.yar7b1c4,  R.drawable.yar7b2c1, R.drawable.yar7b2c2,R.drawable.yar7b2c3,R.drawable.yar7b2c4,
            R.drawable.yar7b3c1, R.drawable.yar7b3c2, R.drawable.yar7b3c3, R.drawable.yar7b3c4,  R.drawable.yar7b4c1, R.drawable.yar7b4c2,R.drawable.yar7b4c3,R.drawable.yar7b4c4,
            R.drawable.yar7b5c1, R.drawable.yar7b5c2, R.drawable.yar7b5c3, R.drawable.yar7b5c4,  R.drawable.yar7b6c1, R.drawable.yar7b6c2,R.drawable.yar7b6c3,R.drawable.yar7b6c4,
            R.drawable.yar7b7c1, R.drawable.yar7b7c2, R.drawable.yar7b7c3, R.drawable.yar7b7c4,  R.drawable.yar7b8c1, R.drawable.yar7b8c2,R.drawable.yar7b8c3,R.drawable.yar7b8c4,
            R.drawable.yar7b9c1, R.drawable.yar7b9c2, R.drawable.yar7b9c3, R.drawable.yar7b9c4,  R.drawable.yar7b10c1,R.drawable.yar7b10c2,R.drawable.yar7b10c3,R.drawable.yar7b10c4,
            R.drawable.yar7b11c1,R.drawable.yar7b11c2,R.drawable.yar7b11c3,R.drawable.yar7b11c4,R.drawable.yar7b12c1,R.drawable.yar7b12c2,R.drawable.yar7b12c3,R.drawable.yar7b12c4,
    };

    @Override
    public void onCreate() {
        //전역 변수 초기화
        super.onCreate();
        linevisible=false;
        this.casenum=1;
    }
    @Override
    public void onTerminate(){
        super.onTerminate();
    }

    public static void setVariable(String vpoint, String vtip, String vthick){
        Point=vpoint;
        Tip=vtip;
        Thick=vthick;
    }
}
