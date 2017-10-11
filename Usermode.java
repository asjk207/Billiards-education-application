package com.example.user.billardtrainningapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by user on 2017-03-28.
 */

public class Usermode extends AppCompatActivity {

    public static final int REQUEST_CODE_NOTUSER = 1011;

    boolean User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usermode);

        ImageButton imageButton = (ImageButton)findViewById(R.id.usermode_check);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(User==false){
                    Intent NotUserLogin_intent = new Intent(Usermode.this,NotUserLogin.class);
                    startActivityForResult(NotUserLogin_intent,REQUEST_CODE_NOTUSER);
                }
                else if(User==true){
                    //로그인정보 가져오기기
                }
           }
        });

    }
    public void choose_user(View v){
        switch (v.getId()){
            case R.id.usermode_user:
                User=true;
                break;
            case R.id.usermode_notuser:
                User=false;
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_CODE_NOTUSER:
                if (resultCode==RESULT_OK){
                    setResult(RESULT_OK,data);
                    finish();
                }
                break;
        }
    }
}
