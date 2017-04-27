package com.example.user.billardtrainningapplication;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by user on 2017-02-09.
 */

public class Strock_dialog  extends AppCompatActivity {

    Intent resultIntent = new Intent();
    String n;

    RadioButton normal;
    RadioButton smooth;
    RadioButton smooth_push;
    RadioButton smooth_cut;
    RadioButton power_push;
    RadioButton power_cut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.strock_dialog);

        n=null;
        resultIntent.putExtra("strock",n);

        normal = (RadioButton)findViewById(R.id.normal_strock);
        smooth = (RadioButton)findViewById(R.id.smooth_strock);
        smooth_push = (RadioButton)findViewById(R.id.smoothNpush_strock);
        smooth_cut = (RadioButton)findViewById(R.id.smoothNcut_strock);
        power_push = (RadioButton)findViewById(R.id.powerNpush_strock);
        power_cut = (RadioButton)findViewById(R.id.powerNcut_strock);

        Button closedButton = (Button)findViewById(R.id.s_checkbutton);
        closedButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                if(resultIntent.getStringExtra("strock") == n){
                    setResult(RESULT_CANCELED,resultIntent);
                    finish();
                }
                else {
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            }
        });
        if(GlobalVariable.SetStrock != null){
            switch (GlobalVariable.SetStrock){
                case "normal":
                    normal.setChecked(true);
                    break;
                case "smooth":
                    smooth.setChecked(true);
                    break;
                case "smooth/push":
                    smooth_push.setChecked(true);
                    break;
                case "smooth/cut":
                    smooth_cut.setChecked(true);
                    break;
                case "power/push":
                    power_push.setChecked(true);
                    break;
                case "power/cut":
                    power_cut.setChecked(true);
                    break;
            }
        }
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev){
        Rect dialogBounds = new Rect();
        getWindow().getDecorView().getHitRect(dialogBounds);
        if(!dialogBounds.contains((int)ev.getX(),(int)ev.getY())){
            return false;
        }
        return super.dispatchTouchEvent(ev);
    }
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.normal_strock:
                resultIntent.putExtra("strock","normal");
                GlobalVariable.SetStrock="normal";
                break;
            case R.id.smooth_strock:
                resultIntent.putExtra("strock","smooth");
                GlobalVariable.SetStrock="smooth";
                break;
            case R.id.smoothNpush_strock:
                resultIntent.putExtra("strock","smooth/push");
                GlobalVariable.SetStrock="smooth/push";
                break;
            case R.id.smoothNcut_strock:
                resultIntent.putExtra("strock","smooth/cut");
                GlobalVariable.SetStrock="smooth/cut";
                break;
            case R.id.powerNpush_strock:
                resultIntent.putExtra("strock","power/push");
                GlobalVariable.SetStrock="power/push";
                break;
            case R.id.powerNcut_strock:
                resultIntent.putExtra("strock","power/cut");
                GlobalVariable.SetStrock="power/cut";
                break;
        }
    }
}