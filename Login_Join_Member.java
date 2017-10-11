package com.example.user.billardtrainningapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by user on 2017-05-10.
 */

public class Login_Join_Member extends AppCompatActivity {

    EditText editText_ID;
    EditText editText_Password;
    EditText editText_Password2;
    EditText editText_Email;
    InputMethodManager imm;
    String Test_Password1;
    String Test_Password2;
    String Join_Email;
    String Join_ID;
    String Join_Password;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_join_member);

        mAuth = FirebaseAuth.getInstance();
        editText_ID = (EditText)findViewById(R.id.member_id);
        editText_Password = (EditText)findViewById(R.id.member_password);
        editText_Password2 = (EditText)findViewById(R.id.member_password2);
        editText_Email = (EditText)findViewById(R.id.member_email);
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

        editText_Password2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if(actionId == EditorInfo.IME_ACTION_SEND){
                    imm.hideSoftInputFromWindow(editText_Password2.getWindowToken(), 0);
                    handled = true;
                }
                return handled;
            }
        });

        editText_Email.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if(actionId == EditorInfo.IME_ACTION_SEND){
                    imm.hideSoftInputFromWindow(editText_Email.getWindowToken(), 0);
                    handled = true;
                }
                return handled;
            }
        });

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

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

        switch (v.getId()) {

            case R.id.member_check:
                Test_Password1=editText_Password.getText().toString();
                Test_Password2=editText_Password2.getText().toString();
                if(Test_Password1.equals(Test_Password2)){
                    Join_Email = editText_Email.getText().toString();
                    Join_ID = editText_ID.getText().toString();
                    Join_Password = editText_Password.getText().toString();
//                    서버에 추가
//                    Password
//                     Email
//                    insertToDatabase(Join_ID, Join_Email, Join_Password);

                    mAuth.createUserWithEmailAndPassword(Join_Email, Join_Password)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Log.d("", "createUserWithEmail:onComplete:" + task.isSuccessful());

                                    // If sign in fails, display a message to the user. If sign in succeeds
                                    // the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener.
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(Login_Join_Member.this, "auth_failed",
                                                Toast.LENGTH_SHORT).show();
                                    }

                                    // ...
                                }
                            });

                    finish();
                }
                else {
                    Toast.makeText(Login_Join_Member.this,"비밀번호 확인과 비밀번호가 일치하지 않습니다.",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void insertToDatabase(String name, String email, String password){

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
                    String name = (String)params[0];
                    String email = (String)params[1];
                    String password = (String)params[2];

                    String link="http://119.207.205.72/html/usergaip.php";

                    URL url = new URL(link);
                    /*
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    */
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);

                    String data  = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8");
                    data += "&" + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
                    data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

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
        task.execute(name,email,password);
    }
}
