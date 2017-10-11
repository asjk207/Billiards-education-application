package com.example.user.billardtrainningapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by user on 2017-05-10.
 */

public class Login extends AppCompatActivity {

    EditText editText_ID;
    EditText editText_Password;
    InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        editText_ID = (EditText)findViewById(R.id.login_id);
        editText_Password = (EditText)findViewById(R.id.login_password);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);


        editText_ID.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if(actionId == EditorInfo.IME_ACTION_SEND){
                    imm.hideSoftInputFromWindow(editText_ID.getWindowToken(), 0);
                    handled = true;
                }
                return handled;
            }
        });

        editText_Password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if(actionId == EditorInfo.IME_ACTION_SEND){
                    imm.hideSoftInputFromWindow(editText_Password.getWindowToken(), 0);
                    handled = true;
                }
                return handled;
            }
        });
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Rect dialogBounds = new Rect();
        getWindow().getDecorView().getHitRect(dialogBounds);
        if (!dialogBounds.contains((int) ev.getX(), (int) ev.getY())) {
            return false;
        }
        return super.dispatchTouchEvent(ev);
    }

    public void Login_OnClick(View v){
        switch (v.getId()){
            case R.id.login_check:
                GlobalVariable.ID = editText_ID.getText().toString();
                GlobalVariable.Password = editText_Password.getText().toString();
                //서버에 등록된 아이디와 비번 비교문구 필요
                loginToDatabase(GlobalVariable.ID, GlobalVariable.Password);
                GlobalVariable.User=true;
                //-----------------------------------------
                //아이디와 비번이 틀리면
                GlobalVariable.User=false;
                //Toast.makeText(Login.this,"아이디 / 비밀번호가 일치하지 않습니다.",Toast.LENGTH_SHORT).show();
                //------------------------------------------
                break;
            case R.id.login_join_member:
                Intent Login_Join_Member_Intent = new Intent(Login.this,Login_Join_Member.class);
                startActivity(Login_Join_Member_Intent);
                break;
            case R.id.login_find_member:
                Toast.makeText(Login.this,"아이디/패스워드 찾기",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void loginToDatabase(String name, String password){

        class InsertData extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //loading = ProgressDialog.show(Login.this, "Please Wait", null, true, true);
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
                    String name = (String)params[0];
                    String password = (String)params[1];

                    String link="http://119.207.205.72/html/login.php";

                    URL url = new URL(link);
                    /*
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    */


                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    String data  = URLEncoder.encode("loginID", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8");
                    data += "&" + URLEncoder.encode("loginPassword", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                    wr.write( data );
                    wr.flush();



                    //long ti = System.currentTimeMillis( ) ; /// == 시간 체크용 == 서버에 따라 리퀘스트 오는 시간이 매우 오래걸림
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    //Log.e( "---recTime---", "" + ( System.currentTimeMillis( ) - ti ) ) ;

                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    // Read Server Response
                    while((line = reader.readLine()) != null)
                    {
                        sb.append(line);
                        Log.e("reader 확인", ""+sb);
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
        task.execute(name,password);
    }
}
