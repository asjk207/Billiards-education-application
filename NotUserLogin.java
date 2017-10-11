package com.example.user.billardtrainningapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by user on 2017-03-28.
 */

public class NotUserLogin extends AppCompatActivity {

    EditText editText_name;
    EditText editText_handy;

    String Score_Handy;
    String Score_Name;

    Intent Not_User_Login;

    InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notuserlogin);

        Not_User_Login = new Intent();

        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        editText_name = (EditText)findViewById(R.id.score_name);
        editText_handy = (EditText)findViewById(R.id.score_handy);

        editText_handy.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if(actionId == EditorInfo.IME_ACTION_SEND){
                    imm.hideSoftInputFromWindow(editText_handy.getWindowToken(), 0);
                    handled = true;
                }
                return handled;
            }
        });

        editText_name.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if(actionId == EditorInfo.IME_ACTION_SEND){
                    imm.hideSoftInputFromWindow(editText_name.getWindowToken(), 0);
                    handled = true;
                }
                return handled;
            }
        });

        ImageButton imageButton = (ImageButton)findViewById(R.id.handy_check);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Score_Name = editText_name.getText().toString();
                Not_User_Login.putExtra("Name", Score_Name);
                Score_Handy = editText_handy.getText().toString();
                Not_User_Login.putExtra("Handy", Score_Handy);

                if(Not_User_Login.getStringExtra("Di")==null||Not_User_Login.getStringExtra("Name")==null||Not_User_Login.getStringExtra("Handy")==null){
                    Toast.makeText(NotUserLogin.this,"이름,핸디,당구대 크기를 모두 선택해주세요.",Toast.LENGTH_SHORT).show();
                }
                else {
                    setResult(RESULT_OK, Not_User_Login);
                    finish();
                }
            }
        });

    }
    public void choose_di(View v){
        switch (v.getId()){
            case R.id.bigdi:
                Not_User_Login.putExtra("Di","Big");
                break;
            case R.id.middi:
                Not_User_Login.putExtra("Di","Mid");
                break;
        }
    }
}
