package com.example.user.billardtrainningapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by user on 2017-03-28.
 */

public class Small_dialog extends AppCompatActivity {

    Intent Get_message;
    String Message;
    TextView Dialog_change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.small_dialog);

        Get_message=getIntent();
        Dialog_change = (TextView)findViewById(R.id.small) ;
        Message=Get_message.getStringExtra("Message").toString();

        if(Message.equals("shortage"))Dialog_change.setText("모든 변수를 입력해주세요.");
        else if(Message.equals("No"))Dialog_change.setText("두께를 먼저 선택해 주세요.");
        else if(Message.equals("correct"))Dialog_change.setText("정답입니다.");
        else Dialog_change.setText("오답입니다.");
    }
}
