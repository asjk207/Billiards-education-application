package com.example.user.billardtrainningapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by user on 2017-02-11.
 */

public class FileINOUT{
    private Context context;
    AttributeSet attribute;

    private List InitialPoint = new ArrayList();
    private List listRBallXY = new ArrayList();
    private List listYBallXY = new ArrayList();
    private List listWBallXY = new ArrayList();
    String CaseBasicThick;
    String CaseBasicPoint;
    String CaseBasicTip;
    String CaseApplicateThick;
    String CaseApplicatePoint ;
    String CaseApplicateTip;
    String Stroke;

    //수구 제1적구 받아오는 변수
    String sugu;
    String onejukgu;

    //쿠션 변수 받아오는 부분
    public String onecushion;
    public String threecushion;

    //최종 좌표값이 들어있는 배열들
    public int [] InitialPointArrays;
    public int [] listRBallXArrays;
    public int [] listRBallYArrays;
    public int [] listYBallXArrays;
    public int [] listYBallYArrays;
    public int [] listWBallXArrays;
    public int [] listWBallYArrays;


    int i,a=1;
    int c, d;


    public FileINOUT(Context context) {
        this.context=context;
        //this.attribute=atrribute;
    }
    public void Ballfileread(Context context){
        String resName="";
        if(!(GlobalVariable.Global_Difficulty=="R")) resName = "@raw/"+GlobalVariable.casekinds+"_"+GlobalVariable.casenum;
        else if(GlobalVariable.Global_Difficulty=="R") resName = "@raw/pro_db"+GlobalVariable.DBcasenum;
        int resID = context.getResources().getIdentifier(resName, "id", context.getPackageName());


        // 파일의 내용을 읽어서 TextView 에 보여주기
        try {
            // 파일에서 읽은 데이터를 저장하기 위해서 만든 변수
            StringBuffer data = new StringBuffer();
            InputStream fis = context.getResources().openRawResource(resID);//파일명
            BufferedReader buffer = new BufferedReader(new InputStreamReader(fis));
            String str = buffer.readLine(); // 파일에서 한줄을 읽어옴
            while (str != null) {
                String[] split = str.split("_");

                switch (a) {
                    case 8:
                        for (i = 0; i < split.length; i++) {
                            String[] split2 = split[i].split("-");
                            for (int j = 0; j < split2.length; j++) {
                                InitialPoint.add(split2[j]);
                            }
                        }
                        break;
                    case 10:
                        for (i = 0; i < split.length; i++) {
                            String[] split2 = split[i].split("-");
                            for (int j = 0; j < split2.length; j++) {
                                listRBallXY.add(split2[j]);
                            }
                        }
                        break;
                    case 12:
                        for (i = 0; i < split.length; i++) {
                            String[] split2 = split[i].split("-");
                            for (int j = 0; j < split2.length; j++) {
                                listYBallXY.add(split2[j]);
                            }
                        }
                        break;
                    case 14:
                        for (i = 0; i < split.length; i++) {
                            String[] split2 = split[i].split("-");
                            for (int j = 0; j < split2.length; j++) {
                                listWBallXY.add(split2[j]);
                            }
                        }
                        break;
                }

                str = buffer.readLine();
                a++;
                //Log.e("***********", "문자열 삽입 마침 :            " + a);
            }
            //버퍼, 인풋 스트림 해제
            fis.close();
            buffer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Casefileread(Context context, String casekinds){
        String resName = "@raw/"+casekinds+"_"+GlobalVariable.casenum;
        int resID = context.getResources().getIdentifier(resName, "id", context.getPackageName());


        // 파일의 내용을 읽어서 TextView 에 보여주기
        try {
            // 파일에서 읽은 데이터를 저장하기 위해서 만든 변수
            StringBuffer data2 = new StringBuffer();
            InputStream fis2 = context.getResources().openRawResource(resID);//파일명
            BufferedReader buffer2 = new BufferedReader(new InputStreamReader(fis2));
            String str2 = buffer2.readLine(); // 파일에서 한줄을 읽어옴
            while (str2 != null) {
                String[] split = str2.split(":");

                switch (a) {
                    case 1:
                        CaseBasicThick=split[1];
                        break;
                    case 2:
                        String[] tmp=split[1].split("\\s");
                        CaseBasicPoint=tmp[1];
                        CaseBasicTip=tmp[2];
                        break;
                    case 3:
                        CaseApplicateThick=split[1];
                        break;
                    case 4:
                        String[] tmp2=split[1].split("\\s");
                        CaseApplicatePoint=tmp2[1];
                        if(tmp2[1].equals("null"))break;
                        else {
                            CaseApplicateTip = tmp2[2];
                            break;
                        }
                    case 5:
                        Stroke=split[1];
                        break;
                    case 16:
                        sugu=split[1];
                        break;
                    case 17:
                        onejukgu=split[1];
                        break;
                    case 19:
                        onecushion=split[1];
                        break;
                    case 20:
                        threecushion=split[1];
                        break;
                }
                if(GlobalVariable.Global_Difficulty=="R"){
                    switch (a) {
                        case 16:
                            if(split[1].equals("true")) GlobalVariable.ReverseX=true;
                            else if(split[1].equals("false")) GlobalVariable.ReverseX=false;
                            break;
                        case 17:
                            if(split[1].equals("true")) GlobalVariable.ReverseY=true;
                            else if(split[1].equals("false")) GlobalVariable.ReverseY=false;
                            break;
                        case 18:
                            GlobalVariable.wratio=Double.parseDouble(split[1]);
                            break;
                        case 19:
                            GlobalVariable.hratio=Double.parseDouble(split[1]);
                            break;
                        case 20:
                            GlobalVariable.BposX=Integer.parseInt(split[1]);
                            break;
                        case 21:
                            GlobalVariable.BposY=Integer.parseInt(split[1]);
                            break;
                    }
                }
                str2 = buffer2.readLine();
                a++;
            }
            fis2.close();
            buffer2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e("FILEINOUT",""+onecushion);
    }

       public void InputArray(){
        //텍스트에서 Object 형을 받아왔으므로
        // List -> Object[]
        //InitialPoint LIst 형변환
        Object[] OArrays = InitialPoint.toArray(new String[InitialPoint.size()]);
        InitialPointArrays = new int[OArrays.length];
        for (i = 0; i < OArrays.length; i++) {
            InitialPointArrays[i] = Integer.parseInt(OArrays[i].toString());
        }
        //listRBallXY List 형 변환
        Object[] OArrays2 = listRBallXY.toArray(new String[listRBallXY.size()]);
        listRBallXArrays = new int[OArrays2.length/2];
        listRBallYArrays = new int[OArrays2.length/2];
        for (i = 0; i < OArrays2.length; i++) {
            if(i%2==0) {
                listRBallXArrays[c]  = Integer.parseInt(OArrays2[i].toString());
                c++;
            }
            else if(i%2==1) {
                listRBallYArrays[d]  = Integer.parseInt(OArrays2[i].toString());
                d++;
            }
        }
        /*for (i = 0; i < listRBallXArrays.length; i++) {
            Log.e("******","RBallXArays["+i+"]:  "+listRBallXArrays[i]);
        }
        for (i = 0; i < listRBallYArrays.length; i++) {
            Log.e("******","RBallYArays["+i+"]:  "+listRBallYArrays[i]);
        }*/
        c=0;
        d=0;
        //listYBallXY List 형 변환
        Object[] OArrays3 = listYBallXY.toArray(new String[listYBallXY.size()]);
        listYBallXArrays = new int[OArrays3.length/2];
        listYBallYArrays = new int[OArrays3.length/2];

        for (i = 0; i < OArrays3.length; i++) {
            if(i%2==0) {
                listYBallXArrays[c]  = Integer.parseInt(OArrays3[i].toString());
                c++;
            }
            else if(i%2==1) {
                listYBallYArrays[d]  = Integer.parseInt(OArrays3[i].toString());
                d++;
            }
        }
        c=0;
        d=0;
        //listWBallXY List 형 변환
        Object[] OArrays4 = listWBallXY.toArray(new String[listWBallXY.size()]);
        listWBallXArrays = new int[(OArrays4.length+1)/2];
        listWBallYArrays = new int[(OArrays4.length+1)/2];

        for(i=0;i<OArrays4.length;i++) {
            if(i%2==0) {
                listWBallXArrays[c]  = Integer.parseInt(OArrays4[i].toString());
                c++;
            }
            else if(i%2==1) {
                listWBallYArrays[d]  = Integer.parseInt(OArrays4[i].toString());
                d++;
            }
        }
        c=0;
        d=0;

        /*for(i=0;i<InitialPointArrays.length;i++){
            Log.e("************","초기화 포인트"+InitialPointArrays[i]);
        }*/
    }


    public int[] getInitialPointArrays(){
        return InitialPointArrays;
    }
    public int[] getListRBallXArrays(){
        return listRBallXArrays;
    }
    public int[] getListRBallYArrays(){
        return listRBallYArrays;
    }

    public int[] getListYBallXArrays(){
        return listYBallXArrays;
    }

    public int[] getListYBallYArrays(){
        return listYBallYArrays;
    }

    public int[] getListWBallXArrays(){
        return listWBallXArrays;
    }

    public int[] getListWBallYArrays(){
        return listWBallYArrays;
    }






}

