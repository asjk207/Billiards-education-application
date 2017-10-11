package com.example.user.billardtrainningapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 2017-02-08.
 */

public class DB_Adapter{

    String myJSON;

    JSONArray billards = null;

    private static final String TAG_RESULTS="result";
    private static final String TAG_ID = "ID";
    private static final String TAG_INIT = "InitialPoint";
    private static final String TAG_RED = "RedBall";
    private static final String TAG_YELLOW = "YellowBall";
    private static final String TAG_WHITE = "WhiteBall";
    private static final String TAG_CUE = "CueBall";
    private static final String TAG_TARGET = "TargetBall";

    //좌표 정보
    public List WposXY = new ArrayList();
    public List RposXY = new ArrayList();
    public List YposXY = new ArrayList();
    public List InitialPoint = new ArrayList();

    public float [] WposX;
    public float [] WposY;
    public float [] RposX;
    public float [] RposY;
    public float [] YposX;
    public float [] YposY;
    public float [] InitialPointArrays;

    // 문자열 분할 변수
    String[] split;
    String[] split2;

    //DB 처리 상태정보 변수
    public boolean completeset=false;


    public DB_Adapter(){

    }


    protected void JSONinputList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            billards = jsonObj.getJSONArray(TAG_RESULTS);
            Log.e("***********","Billards.length()"+billards.length());
            for(int i=0;i<billards.length();i++) {
                JSONObject c = billards.getJSONObject(i);


                String id = c.getString(TAG_ID);
                String initialpoint = c.getString(TAG_INIT);
                String whiteball = c.getString(TAG_WHITE);
                String yellowball = c.getString(TAG_YELLOW);
                String redball = c.getString(TAG_RED);
                String cueball = c.getString(TAG_CUE);
                String targetball = c.getString(TAG_TARGET);



                    split = initialpoint.split("_");
                    for (int j = 0; j < split.length; j++) {
                        String[] split2 = split[j].split("-");
                        for (int k = 0; k < split2.length; k++) {
                            InitialPoint.add(split2[k]);
                        }
                    }




                    split = whiteball.split("_");
                    for (int j = 0; j < split.length; j++) {
                        split2 = split[j].split("-");
                        for (int k = 0; k < split2.length; k++) {
                            WposXY.add(split2[k]);
                        }
                    }
                Log.e("***********","JSON WposXY"+WposXY);
                Log.e("***********","JSON initialpoint"+redball);


                split = yellowball.split("_");
                    for (int j = 0; j < split.length; j++) {
                        split2 = split[j].split("-");
                        for (int k = 0; k < split2.length; k++) {
                            YposXY.add(split2[k]);
                        }
                    }


                    split = redball.split("_");
                    for (int j = 0; j < split.length; j++) {
                        String[] split2 = split[j].split("-");
                        for (int k = 0; k < split2.length; k++) {
                            RposXY.add(split2[k]);
                        }
                    }
                }
                InputArray();


        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("에러 처리","JOSON 에러"+e);
        }

    }

    public void getData(String url, final String ID/*final String RBallposX, final String RBallposY,final String YBallposX,final String YBallposY,final String WBallposX,final String WBallposY*/){
        class GetDataJSON extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String uri = params[0];

                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("POST");
                    con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    con.setDoInput(true);
                    con.setDoOutput(true);

                    String data = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(ID, "UTF-8");
//                    String data = URLEncoder.encode("wballX", "UTF-8") + "=" + URLEncoder.encode(WBallposX, "UTF-8");
//                    data += "&" + URLEncoder.encode("wbally", "UTF-8") + "=" + URLEncoder.encode(WBallposY, "UTF-8");
//                    data += "&" + URLEncoder.encode("yballx", "UTF-8") + "=" + URLEncoder.encode(YBallposX, "UTF-8");
//                    data += "&" + URLEncoder.encode("ybally", "UTF-8") + "=" + URLEncoder.encode(YBallposY, "UTF-8");
//                    data += "&" + URLEncoder.encode("rballx", "UTF-8") + "=" + URLEncoder.encode(RBallposX, "UTF-8");
//                    data += "&" + URLEncoder.encode("rbally", "UTF-8") + "=" + URLEncoder.encode(RBallposY, "UTF-8");

                    OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());


                    wr.write(data);
                    wr.flush();
                    wr.close();

                    con.setConnectTimeout(10000);
                    StringBuilder sb = new StringBuilder();
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while((json = bufferedReader.readLine())!= null){
                        sb.append(json+"\n");
                    }

                    return sb.toString().trim();

                }catch(Exception e){
                    Log.e("",""+e.getMessage());
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String result){
                Log.e("","받은 데이터"+result);
                myJSON=result;
                JSONinputList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);

    }

    public void InputArray(){
        int c = 0;
        int d = 0;
        //텍스트에서 Object 형을 받아왔으므로
        // List -> Object[]
        //InitialPoint LIst 형변환
        Object[] OArrays = InitialPoint.toArray(new String[InitialPoint.size()]);

        InitialPointArrays = new float[OArrays.length];
        for (int i = 0; i < OArrays.length; i++) {
            InitialPointArrays[i] = Float.parseFloat(OArrays[i].toString());
        }

        //listRBallXY List 형 변환
        Object[] OArrays2 = RposXY.toArray(new String[RposXY.size()]);
        RposX = new float[OArrays2.length/2];
        RposY = new float[OArrays2.length/2];
        for (int i = 0; i < OArrays2.length; i++) {
            if(i%2==0) {
                RposY[c]  = Float.parseFloat(OArrays2[i].toString());
                c++;
            }
            else if(i%2==1) {
                RposX[d]  = Float.parseFloat(OArrays2[i].toString());
                d++;
            }
        }

        c=0;
        d=0;
        //listYBallXY List 형 변환
        Object[] OArrays3 = YposXY.toArray(new String[YposXY.size()]);
        YposX = new float[OArrays3.length/2];
        YposY = new float[OArrays3.length/2];

        for (int i = 0; i < OArrays3.length; i++) {
            if(i%2==0) {
                YposY[c]  = Float.parseFloat(OArrays3[i].toString());
                c++;
            }
            else if(i%2==1) {
                YposX[d]  = Float.parseFloat(OArrays3[i].toString());
                d++;
            }
        }
        c=0;
        d=0;
        //listWBallXY List 형 변환
        Object[] OArrays4 = WposXY.toArray(new String[WposXY.size()]);
        WposX = new float[(OArrays4.length+1)/2];
        WposY = new float[(OArrays4.length+1)/2];

        for(int i=0;i<OArrays4.length;i++) {
            if(i%2==0) {
                WposX[c]  = Float.parseFloat(OArrays4[i].toString());
                c++;
            }
            else if(i%2==1) {
                WposY[d]  = Float.parseFloat(OArrays4[i].toString());
                d++;
            }
        }
        c=0;
        d=0;

        /*for(i=0;i<InitialPointArrays.length;i++){
            Log.e("************","초기화 포인트"+InitialPointArrays[i]);
        }*/
    }
    public float[] getWposX() { return WposX;}
    public float[] getWposY() { return WposY;}
    public float[] getYposX() { return YposX;}
    public float[] getYposY() { return YposY;}
    public float[] getRposX() { return RposX;}
    public float[] getRposY() { return RposY;}
    public float[] getInitialPoint() { return InitialPointArrays;}



}

