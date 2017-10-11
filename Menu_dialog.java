package com.example.user.billardtrainningapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by user on 2017-02-14.
 */

public class Menu_dialog extends AppCompatActivity {

    String[] BArray = {
            "bp_youpdol1", "bp_youpdol2", "bp_youpdol3",
            "bp_dwidol1", "dwidol2", "dwidol3",
            "bp_apdol1", "bp_apdol2", "bp_apdol3",
            "bp_bitgyou1", "bp_bitgyou2", "bp_bitgyou3",
            "bp_ggeoga1", "bp_ggeoga2", "bp_ggeoga3",
            "bp_doublecushion1", "bp_doublecushion2", "bp_doublecushion3",
    };
    String[] AArray = {
            /*8*/"ap_youpdol1", "ap_youpdol2", "ap_youpdol3", "ap_youpdol4", "ap_youpdol5", "ap_youpdol6", "ap_youpdol7", "ap_youpdol8", "ap_youpdol9",
            /*14*/"ap_dwidol1", "ap_dwidol2", "ap_dwidol3", "ap_dwidol4", "ap_dwidol5", "ap_dwidol6",
            /*22*/"ap_apdol1", "ap_apdol2", "ap_apdol3", "ap_apdol4", "ap_apdol5", "ap_apdol6", "ap_apdol7", "ap_apdol8",
            /*25*/"ap_bitgyou1", "ap_bitgyou2", "ap_bitgyou3",
            /*30*/"ap_ggeoga1", "ap_ggeoga2", "ap_ggeoga3", "ap_ggeoga4", "ap_ggeoga5",
            /*35*/"ap_doublecushion1", "ap_doublecushion2", "ap_doublecushion3", "ap_doublecushion4", "ap_doublecushion5"
    };

    Intent resultIntent = new Intent();
    Intent Info_Intent;
    String Info;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_dialog);

        Info_Intent = getIntent();
        Info = Info_Intent.getStringExtra("Info");
        i = 0;
        GlobalVariable.Menu_Switcher = 0;

    }

    public void onClicked(View view) throws InterruptedException {
        switch (view.getId()) {
            case R.id.difficulty_choose:
                GlobalVariable.Menu_Switcher = 1;
                setResult(RESULT_OK, resultIntent);
                finish();
                Intent Difficulty_choose_intent = new Intent(this, Difficulty.class);

                if (GlobalVariable.Global_Difficulty == "B") {
                    if (BArray[0].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "youpdol1");
                    else if (BArray[1].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "youpdol2");
                    else if (BArray[2].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "youpdol3");
                    else if (BArray[3].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "dwidol1");
                    else if (BArray[4].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "dwidol2");
                    else if (BArray[5].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "dwidol3");
                    else if (BArray[6].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "apdol1");
                    else if (BArray[7].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "apdol2");
                    else if (BArray[8].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "apdol3");
                    else if (BArray[9].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "bitgyou1");
                    else if (BArray[10].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "bitgyou2");
                    else if (BArray[11].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "bitgyou3");
                    else if (BArray[12].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "ggeoga1");
                    else if (BArray[13].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "ggeoga2");
                    else if (BArray[14].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "ggeoga3");
                    else if (BArray[15].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "doublecushion1");
                    else if (BArray[16].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "doublecushion2");
                    else if (BArray[17].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "doublecushion3");
                } else if (GlobalVariable.Global_Difficulty == "A") {
                    if (AArray[0].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "youpdol1");
                    else if (AArray[1].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "youpdol2");
                    else if (AArray[2].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "youpdol3");
                    else if (AArray[3].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "youpdol4");
                    else if (AArray[4].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "youpdol5");
                    else if (AArray[5].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "youpdol6");
                    else if (AArray[6].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "youpdol7");
                    else if (AArray[7].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "youpdol8");
                    else if (AArray[8].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "youpdol9");
                    else if (AArray[9].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "dwidol1");
                    else if (AArray[10].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "dwidol2");
                    else if (AArray[11].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "dwidol3");
                    else if (AArray[12].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "dwidol4");
                    else if (AArray[13].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "dwidol5");
                    else if (AArray[14].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "dwidol6");
                    else if (AArray[15].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "apdol1");
                    else if (AArray[16].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "apdol2");
                    else if (AArray[17].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "apdol3");
                    else if (AArray[18].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "apdol4");
                    else if (AArray[19].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "apdol5");
                    else if (AArray[20].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "apdol6");
                    else if (AArray[21].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "apdol7");
                    else if (AArray[22].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "apdol8");
                    else if (AArray[23].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "bitgyou1");
                    else if (AArray[24].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "bitgyou2");
                    else if (AArray[25].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "bitgyou3");
                    else if (AArray[26].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "ggeoga1");
                    else if (AArray[27].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "ggeoga2");
                    else if (AArray[28].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "ggeoga3");
                    else if (AArray[29].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "ggeoga4");
                    else if (AArray[30].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "ggeoga5");
                    else if (AArray[31].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "doublecushion1");
                    else if (AArray[32].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "doublecushion2");
                    else if (AArray[33].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "doublecushion3");
                    else if (AArray[34].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "doublecushion4");
                    else if (AArray[35].equals(Info))
                        Difficulty_choose_intent.putExtra("Menu_Difficulty", "doublecushion5");
                }
                Thread.sleep(500);
                Difficulty_choose_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                Difficulty_choose_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(Difficulty_choose_intent);
                break;
            case R.id.route_choose:
                GlobalVariable.Menu_Switcher = 1;
                setResult(RESULT_OK, resultIntent);
                finish();
                Intent Route_choose_intent = new Intent(this, Route.class);
                if (GlobalVariable.Global_Difficulty == "B") {
                    if (BArray[0].equals(Info) || BArray[3].equals(Info) || BArray[6].equals(Info) || BArray[9].equals(Info) || BArray[12].equals(Info) || BArray[15].equals(Info))
                        Route_choose_intent.putExtra("Menu_Route", "1");
                    else if (BArray[1].equals(Info) || BArray[4].equals(Info) || BArray[7].equals(Info) || BArray[10].equals(Info) || BArray[13].equals(Info) || BArray[16].equals(Info))
                        Route_choose_intent.putExtra("Menu_Route", "2");
                    else if (BArray[2].equals(Info) || BArray[5].equals(Info) || BArray[8].equals(Info) || BArray[11].equals(Info) || BArray[14].equals(Info) || BArray[17].equals(Info))
                        Route_choose_intent.putExtra("Menu_Route", "3");
                } else if (GlobalVariable.Global_Difficulty == "A") {
                    if (AArray[0].equals(Info) || AArray[9].equals(Info) || AArray[15].equals(Info) || AArray[23].equals(Info) || AArray[26].equals(Info) || AArray[31].equals(Info))
                        Route_choose_intent.putExtra("Menu_Route", "1");
                    else if (AArray[1].equals(Info) || AArray[10].equals(Info) || AArray[16].equals(Info) || AArray[24].equals(Info) || AArray[27].equals(Info) || AArray[32].equals(Info))
                        Route_choose_intent.putExtra("Menu_Route", "2");
                    else if (AArray[2].equals(Info) || AArray[11].equals(Info) || AArray[17].equals(Info) || AArray[25].equals(Info) || AArray[28].equals(Info) || AArray[33].equals(Info))
                        Route_choose_intent.putExtra("Menu_Route", "3");
                    else if (AArray[3].equals(Info) || AArray[12].equals(Info) || AArray[18].equals(Info) || AArray[29].equals(Info) || AArray[34].equals(Info))
                        Route_choose_intent.putExtra("Menu_Route", "4");
                    else if (AArray[4].equals(Info) || AArray[13].equals(Info) || AArray[19].equals(Info) || AArray[30].equals(Info) || AArray[35].equals(Info))
                        Route_choose_intent.putExtra("Menu_Route", "5");
                    else if (AArray[5].equals(Info) || AArray[14].equals(Info) || AArray[20].equals(Info))
                        Route_choose_intent.putExtra("Menu_Route", "6");
                    else if (AArray[6].equals(Info) || AArray[21].equals(Info))
                        Route_choose_intent.putExtra("Menu_Route", "7");
                    else if (AArray[7].equals(Info) || AArray[22].equals(Info))
                        Route_choose_intent.putExtra("Menu_Route", "8");
                    else if (AArray[8].equals(Info))
                        Route_choose_intent.putExtra("Menu_Route", "9");
                }
                Thread.sleep(500);
                startActivity(Route_choose_intent);
                break;
            case R.id.level_choose:
                GlobalVariable.Menu_Switcher = 1;
                setResult(RESULT_OK, resultIntent);
                finish();
                Intent Level_choose_intent = new Intent(this, Level.class);
                if (GlobalVariable.Global_Difficulty == "B") {
                    for (i = 0; i < 3; i++) {
                        if (BArray[i].equals(Info))
                            Level_choose_intent.putExtra("ROUTE", "bp_youpdol");
                        else if (BArray[i + 9].equals(Info))
                            Level_choose_intent.putExtra("ROUTE", "bp_dwidol");
                        else if (BArray[i + 6].equals(Info))
                            Level_choose_intent.putExtra("ROUTE", "bp_apdol");
                        else if (BArray[i + 9].equals(Info))
                            Level_choose_intent.putExtra("ROUTE", "bp_bitgyou");
                        else if (BArray[i + 12].equals(Info))
                            Level_choose_intent.putExtra("ROUTE", "bp_ggeoga");
                        else if (BArray[i + 15].equals(Info))
                            Level_choose_intent.putExtra("ROUTE", "bp_doublecushion");
                    }
                } else if (GlobalVariable.Global_Difficulty == "A") {
                    if (AArray[0].equals(Info))
                        Level_choose_intent.putExtra("ROUTE", "ap_youpdol");
                    else if (AArray[1].equals(Info))
                        Level_choose_intent.putExtra("ROUTE", "ap_youpdol");
                    else if (AArray[2].equals(Info))
                        Level_choose_intent.putExtra("ROUTE", "ap_youpdol");
                    else if (AArray[3].equals(Info))
                        Level_choose_intent.putExtra("ROUTE", "ap_youpdol");
                    else if (AArray[4].equals(Info))
                        Level_choose_intent.putExtra("ROUTE", "ap_youpdol");
                    else if (AArray[5].equals(Info))
                        Level_choose_intent.putExtra("ROUTE", "ap_youpdol");
                    else if (AArray[6].equals(Info))
                        Level_choose_intent.putExtra("ROUTE", "ap_youpdol");
                    else if (AArray[7].equals(Info))
                        Level_choose_intent.putExtra("ROUTE", "ap_youpdol");
                    else if (AArray[8].equals(Info))
                        Level_choose_intent.putExtra("ROUTE", "ap_youpdol");
                    else if (AArray[9].equals(Info))
                        Level_choose_intent.putExtra("ROUTE", "ap_dwidol");
                    else if (AArray[10].equals(Info))
                        Level_choose_intent.putExtra("ROUTE", "ap_dwidol");
                    else if (AArray[11].equals(Info))
                        Level_choose_intent.putExtra("ROUTE", "ap_dwidol");
                    else if (AArray[12].equals(Info))
                        Level_choose_intent.putExtra("ROUTE", "ap_dwidol");
                    else if (AArray[13].equals(Info))
                        Level_choose_intent.putExtra("ROUTE", "ap_dwidol");
                    else if (AArray[14].equals(Info))
                        Level_choose_intent.putExtra("ROUTE", "ap_dwidol");
                    else if (AArray[15].equals(Info))
                        Level_choose_intent.putExtra("ROUTE", "ap_apdol");
                    else if (AArray[16].equals(Info))
                        Level_choose_intent.putExtra("ROUTE", "ap_apdol");
                    else if (AArray[17].equals(Info))
                        Level_choose_intent.putExtra("ROUTE", "ap_apdol");
                    else if (AArray[18].equals(Info))
                        Level_choose_intent.putExtra("ROUTE", "ap_apdol");
                    else if (AArray[19].equals(Info))
                        Level_choose_intent.putExtra("ROUTE", "ap_apdol");
                    else if (AArray[20].equals(Info))
                        Level_choose_intent.putExtra("ROUTE", "ap_apdol");
                    else if (AArray[21].equals(Info))
                        Level_choose_intent.putExtra("ROUTE", "ap_apdol");
                    else if (AArray[22].equals(Info))
                        Level_choose_intent.putExtra("ROUTE", "ap_apdol");
                    else if (AArray[23].equals(Info))
                        Level_choose_intent.putExtra("ROUTE", "ap_bitgyou");
                    else if (AArray[24].equals(Info))
                        Level_choose_intent.putExtra("ROUTE", "ap_bitgyou");
                    else if (AArray[25].equals(Info))
                        Level_choose_intent.putExtra("ROUTE", "ap_bitgyou");
                    else if (AArray[26].equals(Info))
                        Level_choose_intent.putExtra("ROUTE", "ap_ggeoga");
                    else if (AArray[27].equals(Info))
                        Level_choose_intent.putExtra("ROUTE", "ap_ggeoga");
                    else if (AArray[28].equals(Info))
                        Level_choose_intent.putExtra("ROUTE", "ap_ggeoga");
                    else if (AArray[29].equals(Info))
                        Level_choose_intent.putExtra("ROUTE", "ap_ggeoga");
                    else if (AArray[30].equals(Info))
                        Level_choose_intent.putExtra("ROUTE", "ap_ggeoga");
                    else if (AArray[31].equals(Info))
                        Level_choose_intent.putExtra("ROUTE", "ap_doublecushion");
                    else if (AArray[32].equals(Info))
                        Level_choose_intent.putExtra("ROUTE", "ap_doublecushion");
                    else if (AArray[33].equals(Info))
                        Level_choose_intent.putExtra("ROUTE", "ap_doublecushion");
                    else if (AArray[34].equals(Info))
                        Level_choose_intent.putExtra("ROUTE", "ap_doublecushion");
                    else if (AArray[35].equals(Info))
                        Level_choose_intent.putExtra("ROUTE", "ap_doublecushion");
                }
                Thread.sleep(500);
                startActivity(Level_choose_intent);
                break;
            case R.id.coursebook_choose:
                GlobalVariable.Menu_Switcher = 1;
                Intent coursebook_choose_intent = new Intent(this, CourseBook_Route.class);
                setResult(RESULT_CANCELED, resultIntent);
                finish();
                Thread.sleep(500);
                startActivity(coursebook_choose_intent);
                break;
            case R.id.exit_program:
                insertToDatabase(GlobalVariable.ID, String.valueOf(GlobalVariable.right_cnt),String.valueOf(GlobalVariable.wrong_cnt));
                ActivityCompat.finishAffinity(this);
                break;
        }
    }

    private void insertToDatabase(String ID, String rightcnt, String wrcnt){

        class InsertData extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //loading = ProgressDialog.show(Login_Join_Member.this, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //loading.dismiss();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {
                try{
                    String ID = (String)params[0];
                    String rightcnt = (String)params[1];
                    String wrcnt = (String)params[2];

                    String link="http://119.207.205.72/";

                    URL url = new URL(link);
                    /*
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    */
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);

                    String data  = URLEncoder.encode("ID", "UTF-8") + "=" + URLEncoder.encode(ID, "UTF-8");
                    data += "&" + URLEncoder.encode("rightcnt", "UTF-8") + "=" + URLEncoder.encode(rightcnt, "UTF-8");
                    data += "&" + URLEncoder.encode("wrongcnt", "UTF-8") + "=" + URLEncoder.encode(wrcnt, "UTF-8");

                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                    wr.write( data );
                    wr.flush();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    // Read Server Response
                    while((line = reader.readLine()) != null)
                    {
                        sb.append(line);
                        break;
                    }
                    return sb.toString();
                }
                catch(Exception e){
                    return new String("Exception: " + e.getMessage());
                }
            }
        }

        InsertData task = new InsertData();
        task.execute(ID, rightcnt, wrcnt);
    }
}

