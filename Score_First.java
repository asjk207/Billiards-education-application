package com.example.user.billardtrainningapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by user on 2017-04-18.
 */

public class Score_First extends AppCompatActivity {

    Button Score_First_Check;
    Intent Score_First_Intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_first);

        Score_First_Intent = new Intent();

        Score_First_Check = (Button)findViewById(R.id.score_first_checkbutton);
        Score_First_Check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Score_First_Intent.getStringExtra("First_Attack") == null){
                    setResult(RESULT_CANCELED,Score_First_Intent);
                    finish();
                }
                else {
                    setResult(RESULT_OK, Score_First_Intent);
                    finish();
                }
            }
        });
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.first_yellow:
                Score_First_Intent.putExtra("First_Attack","yellow");
                break;
            case R.id.first_white:
                Score_First_Intent.putExtra("First_Attack","white");
                break;
        }
    }
}
