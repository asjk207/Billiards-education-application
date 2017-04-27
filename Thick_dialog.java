package com.example.user.billardtrainningapplication;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ViewSwitcher;

/**
 * Created by user on 2017-02-08.
 */

public class Thick_dialog extends AppCompatActivity{

    ImageSwitcher imageSwitcher;

    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioButton radioButton3;
    RadioButton radioButton4;
    RadioButton radioButton5;
    RadioButton radioButton6;
    RadioButton radioButton7;
    RadioButton radioButton1R;
    RadioButton radioButton2R;
    RadioButton radioButton3R;
    RadioButton radioButton4R;
    RadioButton radioButton5R;
    RadioButton radioButton6R;
    RadioButton radioButton7R;

    Intent resultIntent = new Intent();
    String n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thick_dialog);

        imageSwitcher = (ImageSwitcher)findViewById(R.id.thick_imageswticher) ;
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                return imageView;
            }
        });
        switch (GlobalVariable.whiteball){
            case 1:
                switch (GlobalVariable.redball) {
                    case 2:
                        imageSwitcher.setImageResource(GlobalVariable.OneValueArray[30]);
                        imageSwitcher.invalidate();
                        break;
                }
                switch (GlobalVariable.yellowball) {
                    case 2:
                        imageSwitcher.setImageResource(GlobalVariable.yOneValueArray[14]);
                        imageSwitcher.invalidate();
                    break;
                }
                break;
        }

        radioButton1 = (RadioButton)findViewById(R.id.radioButton1);
        radioButton2 = (RadioButton)findViewById(R.id.radioButton2);
        radioButton3 = (RadioButton)findViewById(R.id.radioButton3);
        radioButton4 = (RadioButton)findViewById(R.id.radioButton4);
        radioButton5 = (RadioButton)findViewById(R.id.radioButton5);
        radioButton6 = (RadioButton)findViewById(R.id.radioButton6);
        radioButton7 = (RadioButton)findViewById(R.id.radioButton7);
        radioButton1R = (RadioButton)findViewById(R.id.radioButton1R);
        radioButton2R = (RadioButton)findViewById(R.id.radioButton2R);
        radioButton3R = (RadioButton)findViewById(R.id.radioButton3R);
        radioButton4R = (RadioButton)findViewById(R.id.radioButton4R);
        radioButton5R = (RadioButton)findViewById(R.id.radioButton5R);
        radioButton6R = (RadioButton)findViewById(R.id.radioButton6R);
        radioButton7R = (RadioButton)findViewById(R.id.radioButton7R);

        n=null;

        Button checkButton = (Button)findViewById(R.id.checkbutton);
        checkButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                if(resultIntent.getStringExtra("thick")==n){
                    setResult(RESULT_CANCELED,resultIntent);
                    finish();
                }
                else {
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            }
        });
        if(GlobalVariable.SetThick != null) {
            if (GlobalVariable.whiteball == 1 && GlobalVariable.redball == 2) {
                switch (GlobalVariable.SetThick) {
                    case "a1":
                        imageSwitcher.setImageResource(GlobalVariable.OneValueArray[0]);
                        imageSwitcher.invalidate();
                        radioButton1.setChecked(true);
                        break;
                    case "a2":
                        imageSwitcher.setImageResource(GlobalVariable.OneValueArray[1]);
                        imageSwitcher.invalidate();
                        radioButton2.setChecked(true);
                        break;
                    case "a3":
                        imageSwitcher.setImageResource(GlobalVariable.OneValueArray[2]);
                        imageSwitcher.invalidate();
                        radioButton3.setChecked(true);
                        break;
                    case "a4":
                        imageSwitcher.setImageResource(GlobalVariable.OneValueArray[3]);
                        imageSwitcher.invalidate();
                        radioButton4.setChecked(true);
                        break;
                    case "a5":
                        imageSwitcher.setImageResource(GlobalVariable.OneValueArray[4]);
                        imageSwitcher.invalidate();
                        radioButton5.setChecked(true);
                        break;
                    case "a6":
                        imageSwitcher.setImageResource(GlobalVariable.OneValueArray[5]);
                        imageSwitcher.invalidate();
                        radioButton6.setChecked(true);
                        break;
                    case "a7":
                        imageSwitcher.setImageResource(GlobalVariable.OneValueArray[6]);
                        imageSwitcher.invalidate();
                        radioButton7.setChecked(true);
                        break;

                    case "ar1":
                        imageSwitcher.setImageResource(GlobalVariable.OneValueArray[7]);
                        imageSwitcher.invalidate();
                        radioButton1R.setChecked(true);
                        break;
                    case "ar2":
                        imageSwitcher.setImageResource(GlobalVariable.OneValueArray[8]);
                        imageSwitcher.invalidate();
                        radioButton2R.setChecked(true);
                        break;
                    case "ar3":
                        imageSwitcher.setImageResource(GlobalVariable.OneValueArray[9]);
                        imageSwitcher.invalidate();
                        radioButton3R.setChecked(true);
                        break;
                    case "ar4":
                        imageSwitcher.setImageResource(GlobalVariable.OneValueArray[10]);
                        imageSwitcher.invalidate();
                        radioButton4R.setChecked(true);
                        break;
                    case "ar5":
                        imageSwitcher.setImageResource(GlobalVariable.OneValueArray[11]);
                        imageSwitcher.invalidate();
                        radioButton5R.setChecked(true);
                        break;
                    case "ar6":
                        imageSwitcher.setImageResource(GlobalVariable.OneValueArray[12]);
                        imageSwitcher.invalidate();
                        radioButton6R.setChecked(true);
                        break;
                    case "ar7":
                        imageSwitcher.setImageResource(GlobalVariable.OneValueArray[13]);
                        imageSwitcher.invalidate();
                        radioButton7R.setChecked(true);
                        break;
                }
            }
            else if(GlobalVariable.whiteball==1 && GlobalVariable.yellowball==2){
                switch (GlobalVariable.SetThick) {
                    case "a1":
                        imageSwitcher.setImageResource(GlobalVariable.yOneValueArray[0]);
                        imageSwitcher.invalidate();
                        radioButton1.setChecked(true);
                        break;
                    case "a2":
                        imageSwitcher.setImageResource(GlobalVariable.yOneValueArray[1]);
                        imageSwitcher.invalidate();
                        radioButton2.setChecked(true);
                        break;
                    case "a3":
                        imageSwitcher.setImageResource(GlobalVariable.yOneValueArray[2]);
                        imageSwitcher.invalidate();
                        radioButton3.setChecked(true);
                        break;
                    case "a4":
                        imageSwitcher.setImageResource(GlobalVariable.yOneValueArray[3]);
                        imageSwitcher.invalidate();
                        radioButton4.setChecked(true);
                        break;
                    case "a5":
                        imageSwitcher.setImageResource(GlobalVariable.yOneValueArray[4]);
                        imageSwitcher.invalidate();
                        radioButton5.setChecked(true);
                        break;
                    case "a6":
                        imageSwitcher.setImageResource(GlobalVariable.yOneValueArray[5]);
                        imageSwitcher.invalidate();
                        radioButton6.setChecked(true);
                        break;
                    case "a7":
                        imageSwitcher.setImageResource(GlobalVariable.yOneValueArray[6]);
                        imageSwitcher.invalidate();
                        radioButton7.setChecked(true);
                        break;

                    case "ar1":
                        imageSwitcher.setImageResource(GlobalVariable.yOneValueArray[7]);
                        imageSwitcher.invalidate();
                        radioButton1R.setChecked(true);
                        break;
                    case "ar2":
                        imageSwitcher.setImageResource(GlobalVariable.yOneValueArray[8]);
                        imageSwitcher.invalidate();
                        radioButton2R.setChecked(true);
                        break;
                    case "ar3":
                        imageSwitcher.setImageResource(GlobalVariable.yOneValueArray[9]);
                        imageSwitcher.invalidate();
                        radioButton3R.setChecked(true);
                        break;
                    case "ar4":
                        imageSwitcher.setImageResource(GlobalVariable.yOneValueArray[10]);
                        imageSwitcher.invalidate();
                        radioButton4R.setChecked(true);
                        break;
                    case "ar5":
                        imageSwitcher.setImageResource(GlobalVariable.yOneValueArray[11]);
                        imageSwitcher.invalidate();
                        radioButton5R.setChecked(true);
                        break;
                    case "ar6":
                        imageSwitcher.setImageResource(GlobalVariable.yOneValueArray[12]);
                        imageSwitcher.invalidate();
                        radioButton6R.setChecked(true);
                        break;
                    case "ar7":
                        imageSwitcher.setImageResource(GlobalVariable.yOneValueArray[13]);
                        imageSwitcher.invalidate();
                        radioButton7R.setChecked(true);
                        break;
                }
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
    public void onClick(View view) {
        if (GlobalVariable.whiteball == 1 && GlobalVariable.redball == 2) {
            switch (view.getId()) {
                case R.id.radioButton1:
                    imageSwitcher.setImageResource(GlobalVariable.OneValueArray[0]);
                    imageSwitcher.invalidate();
                    resultIntent.putExtra("thick", "a1");
                    GlobalVariable.SetThick = "a1";
                    radioButton1.setChecked(true);
                    radioButton2.setChecked(false);
                    radioButton3.setChecked(false);
                    radioButton4.setChecked(false);
                    radioButton5.setChecked(false);
                    radioButton6.setChecked(false);
                    radioButton7.setChecked(false);
                    radioButton1R.setChecked(false);
                    radioButton2R.setChecked(false);
                    radioButton3R.setChecked(false);
                    radioButton4R.setChecked(false);
                    radioButton5R.setChecked(false);
                    radioButton6R.setChecked(false);
                    radioButton7R.setChecked(false);
                    break;
                case R.id.radioButton2:
                    imageSwitcher.setImageResource(GlobalVariable.OneValueArray[1]);
                    imageSwitcher.invalidate();
                    resultIntent.putExtra("thick", "a2");
                    GlobalVariable.SetThick = "a2";
                    radioButton1.setChecked(false);
                    radioButton2.setChecked(true);
                    radioButton3.setChecked(false);
                    radioButton4.setChecked(false);
                    radioButton5.setChecked(false);
                    radioButton6.setChecked(false);
                    radioButton7.setChecked(false);
                    radioButton1R.setChecked(false);
                    radioButton2R.setChecked(false);
                    radioButton3R.setChecked(false);
                    radioButton4R.setChecked(false);
                    radioButton5R.setChecked(false);
                    radioButton6R.setChecked(false);
                    radioButton7R.setChecked(false);
                    break;
                case R.id.radioButton3:
                    imageSwitcher.setImageResource(GlobalVariable.OneValueArray[2]);
                    imageSwitcher.invalidate();
                    resultIntent.putExtra("thick", "a3");
                    GlobalVariable.SetThick = "a3";
                    radioButton1.setChecked(false);
                    radioButton2.setChecked(false);
                    radioButton3.setChecked(true);
                    radioButton4.setChecked(false);
                    radioButton5.setChecked(false);
                    radioButton6.setChecked(false);
                    radioButton7.setChecked(false);
                    radioButton1R.setChecked(false);
                    radioButton2R.setChecked(false);
                    radioButton3R.setChecked(false);
                    radioButton4R.setChecked(false);
                    radioButton5R.setChecked(false);
                    radioButton6R.setChecked(false);
                    radioButton7R.setChecked(false);
                    break;
                case R.id.radioButton4:
                    imageSwitcher.setImageResource(GlobalVariable.OneValueArray[3]);
                    imageSwitcher.invalidate();
                    resultIntent.putExtra("thick", "a4");
                    GlobalVariable.SetThick = "a4";
                    radioButton1.setChecked(false);
                    radioButton2.setChecked(false);
                    radioButton3.setChecked(false);
                    radioButton4.setChecked(true);
                    radioButton5.setChecked(false);
                    radioButton6.setChecked(false);
                    radioButton7.setChecked(false);
                    radioButton1R.setChecked(false);
                    radioButton2R.setChecked(false);
                    radioButton3R.setChecked(false);
                    radioButton4R.setChecked(false);
                    radioButton5R.setChecked(false);
                    radioButton6R.setChecked(false);
                    radioButton7R.setChecked(false);
                    break;
                case R.id.radioButton5:
                    imageSwitcher.setImageResource(GlobalVariable.OneValueArray[4]);
                    imageSwitcher.invalidate();
                    resultIntent.putExtra("thick", "a5");
                    GlobalVariable.SetThick = "a5";
                    radioButton1.setChecked(false);
                    radioButton2.setChecked(false);
                    radioButton3.setChecked(false);
                    radioButton4.setChecked(false);
                    radioButton5.setChecked(true);
                    radioButton6.setChecked(false);
                    radioButton7.setChecked(false);
                    radioButton1R.setChecked(false);
                    radioButton2R.setChecked(false);
                    radioButton3R.setChecked(false);
                    radioButton4R.setChecked(false);
                    radioButton5R.setChecked(false);
                    radioButton6R.setChecked(false);
                    radioButton7R.setChecked(false);
                    break;
                case R.id.radioButton6:
                    imageSwitcher.setImageResource(GlobalVariable.OneValueArray[5]);
                    imageSwitcher.invalidate();
                    resultIntent.putExtra("thick", "a6");
                    GlobalVariable.SetThick = "a6";
                    radioButton1.setChecked(false);
                    radioButton2.setChecked(false);
                    radioButton3.setChecked(false);
                    radioButton4.setChecked(false);
                    radioButton5.setChecked(false);
                    radioButton6.setChecked(true);
                    radioButton7.setChecked(false);
                    radioButton1R.setChecked(false);
                    radioButton2R.setChecked(false);
                    radioButton3R.setChecked(false);
                    radioButton4R.setChecked(false);
                    radioButton5R.setChecked(false);
                    radioButton6R.setChecked(false);
                    radioButton7R.setChecked(false);
                    break;
                case R.id.radioButton7:
                    imageSwitcher.setImageResource(GlobalVariable.OneValueArray[6]);
                    imageSwitcher.invalidate();
                    resultIntent.putExtra("thick", "a7");
                    GlobalVariable.SetThick = "a7";
                    radioButton1.setChecked(false);
                    radioButton2.setChecked(false);
                    radioButton3.setChecked(false);
                    radioButton4.setChecked(false);
                    radioButton5.setChecked(false);
                    radioButton6.setChecked(false);
                    radioButton7.setChecked(true);
                    radioButton1R.setChecked(false);
                    radioButton2R.setChecked(false);
                    radioButton3R.setChecked(false);
                    radioButton4R.setChecked(false);
                    radioButton5R.setChecked(false);
                    radioButton6R.setChecked(false);
                    radioButton7R.setChecked(false);
                    break;

                case R.id.radioButton1R:
                    imageSwitcher.setImageResource(GlobalVariable.OneValueArray[7]);
                    imageSwitcher.invalidate();
                    resultIntent.putExtra("thick", "ar1");
                    GlobalVariable.SetThick = "ar1";
                    radioButton1.setChecked(false);
                    radioButton2.setChecked(false);
                    radioButton3.setChecked(false);
                    radioButton4.setChecked(false);
                    radioButton5.setChecked(false);
                    radioButton6.setChecked(false);
                    radioButton7.setChecked(false);
                    radioButton1R.setChecked(true);
                    radioButton2R.setChecked(false);
                    radioButton3R.setChecked(false);
                    radioButton4R.setChecked(false);
                    radioButton5R.setChecked(false);
                    radioButton6R.setChecked(false);
                    radioButton7R.setChecked(false);
                    break;
                case R.id.radioButton2R:
                    imageSwitcher.setImageResource(GlobalVariable.OneValueArray[8]);
                    imageSwitcher.invalidate();
                    resultIntent.putExtra("thick", "ar2");
                    GlobalVariable.SetThick = "ar2";
                    radioButton1.setChecked(false);
                    radioButton2.setChecked(false);
                    radioButton3.setChecked(false);
                    radioButton4.setChecked(false);
                    radioButton5.setChecked(false);
                    radioButton6.setChecked(false);
                    radioButton7.setChecked(false);
                    radioButton1R.setChecked(false);
                    radioButton2R.setChecked(true);
                    radioButton3R.setChecked(false);
                    radioButton4R.setChecked(false);
                    radioButton5R.setChecked(false);
                    radioButton6R.setChecked(false);
                    radioButton7R.setChecked(false);
                    break;
                case R.id.radioButton3R:
                    imageSwitcher.setImageResource(GlobalVariable.OneValueArray[9]);
                    imageSwitcher.invalidate();
                    resultIntent.putExtra("thick", "ar3");
                    GlobalVariable.SetThick = "ar3";
                    radioButton1.setChecked(false);
                    radioButton2.setChecked(false);
                    radioButton3.setChecked(false);
                    radioButton4.setChecked(false);
                    radioButton5.setChecked(false);
                    radioButton6.setChecked(false);
                    radioButton7.setChecked(false);
                    radioButton1R.setChecked(false);
                    radioButton2R.setChecked(false);
                    radioButton3R.setChecked(true);
                    radioButton4R.setChecked(false);
                    radioButton5R.setChecked(false);
                    radioButton6R.setChecked(false);
                    radioButton7R.setChecked(false);
                    break;
                case R.id.radioButton4R:
                    imageSwitcher.setImageResource(GlobalVariable.OneValueArray[10]);
                    imageSwitcher.invalidate();
                    resultIntent.putExtra("thick", "ar4");
                    GlobalVariable.SetThick = "ar4";
                    radioButton1.setChecked(false);
                    radioButton2.setChecked(false);
                    radioButton3.setChecked(false);
                    radioButton4.setChecked(false);
                    radioButton5.setChecked(false);
                    radioButton6.setChecked(false);
                    radioButton7.setChecked(false);
                    radioButton1R.setChecked(false);
                    radioButton2R.setChecked(false);
                    radioButton3R.setChecked(false);
                    radioButton4R.setChecked(true);
                    radioButton5R.setChecked(false);
                    radioButton6R.setChecked(false);
                    radioButton7R.setChecked(false);
                    break;
                case R.id.radioButton5R:
                    imageSwitcher.setImageResource(GlobalVariable.OneValueArray[11]);
                    imageSwitcher.invalidate();
                    resultIntent.putExtra("thick", "ar5");
                    GlobalVariable.SetThick = "ar5";
                    radioButton1.setChecked(false);
                    radioButton2.setChecked(false);
                    radioButton3.setChecked(false);
                    radioButton4.setChecked(false);
                    radioButton5.setChecked(false);
                    radioButton6.setChecked(false);
                    radioButton7.setChecked(false);
                    radioButton1R.setChecked(false);
                    radioButton2R.setChecked(false);
                    radioButton3R.setChecked(false);
                    radioButton4R.setChecked(false);
                    radioButton5R.setChecked(true);
                    radioButton6R.setChecked(false);
                    radioButton7R.setChecked(false);
                    break;
                case R.id.radioButton6R:
                    imageSwitcher.setImageResource(GlobalVariable.OneValueArray[12]);
                    imageSwitcher.invalidate();
                    resultIntent.putExtra("thick", "ar6");
                    GlobalVariable.SetThick = "ar6";
                    radioButton1.setChecked(false);
                    radioButton2.setChecked(false);
                    radioButton3.setChecked(false);
                    radioButton4.setChecked(false);
                    radioButton5.setChecked(false);
                    radioButton6.setChecked(false);
                    radioButton7.setChecked(false);
                    radioButton1R.setChecked(false);
                    radioButton2R.setChecked(false);
                    radioButton3R.setChecked(false);
                    radioButton4R.setChecked(false);
                    radioButton5R.setChecked(false);
                    radioButton6R.setChecked(true);
                    radioButton7R.setChecked(false);
                    break;
                case R.id.radioButton7R:
                    imageSwitcher.setImageResource(GlobalVariable.OneValueArray[13]);
                    imageSwitcher.invalidate();
                    resultIntent.putExtra("thick", "ar7");
                    GlobalVariable.SetThick = "ar7";
                    radioButton1.setChecked(false);
                    radioButton2.setChecked(false);
                    radioButton3.setChecked(false);
                    radioButton4.setChecked(false);
                    radioButton5.setChecked(false);
                    radioButton6.setChecked(false);
                    radioButton7.setChecked(false);
                    radioButton1R.setChecked(false);
                    radioButton2R.setChecked(false);
                    radioButton3R.setChecked(false);
                    radioButton4R.setChecked(false);
                    radioButton5R.setChecked(false);
                    radioButton6R.setChecked(false);
                    radioButton7R.setChecked(true);
                    break;
            }
        }
        else if(GlobalVariable.whiteball==1 && GlobalVariable.yellowball==2){
            switch (view.getId()) {
                case R.id.radioButton1:
                    imageSwitcher.setImageResource(GlobalVariable.yOneValueArray[0]);
                    imageSwitcher.invalidate();
                    resultIntent.putExtra("thick", "a1");
                    GlobalVariable.SetThick = "a1";
                    radioButton1.setChecked(true);
                    radioButton2.setChecked(false);
                    radioButton3.setChecked(false);
                    radioButton4.setChecked(false);
                    radioButton5.setChecked(false);
                    radioButton6.setChecked(false);
                    radioButton7.setChecked(false);
                    radioButton1R.setChecked(false);
                    radioButton2R.setChecked(false);
                    radioButton3R.setChecked(false);
                    radioButton4R.setChecked(false);
                    radioButton5R.setChecked(false);
                    radioButton6R.setChecked(false);
                    radioButton7R.setChecked(false);
                    break;
                case R.id.radioButton2:
                    imageSwitcher.setImageResource(GlobalVariable.yOneValueArray[1]);
                    imageSwitcher.invalidate();
                    resultIntent.putExtra("thick", "a2");
                    GlobalVariable.SetThick = "a2";
                    radioButton1.setChecked(false);
                    radioButton2.setChecked(true);
                    radioButton3.setChecked(false);
                    radioButton4.setChecked(false);
                    radioButton5.setChecked(false);
                    radioButton6.setChecked(false);
                    radioButton7.setChecked(false);
                    radioButton1R.setChecked(false);
                    radioButton2R.setChecked(false);
                    radioButton3R.setChecked(false);
                    radioButton4R.setChecked(false);
                    radioButton5R.setChecked(false);
                    radioButton6R.setChecked(false);
                    radioButton7R.setChecked(false);
                    break;
                case R.id.radioButton3:
                    imageSwitcher.setImageResource(GlobalVariable.yOneValueArray[2]);
                    imageSwitcher.invalidate();
                    resultIntent.putExtra("thick", "a3");
                    GlobalVariable.SetThick = "a3";
                    radioButton1.setChecked(false);
                    radioButton2.setChecked(false);
                    radioButton3.setChecked(true);
                    radioButton4.setChecked(false);
                    radioButton5.setChecked(false);
                    radioButton6.setChecked(false);
                    radioButton7.setChecked(false);
                    radioButton1R.setChecked(false);
                    radioButton2R.setChecked(false);
                    radioButton3R.setChecked(false);
                    radioButton4R.setChecked(false);
                    radioButton5R.setChecked(false);
                    radioButton6R.setChecked(false);
                    radioButton7R.setChecked(false);
                    break;
                case R.id.radioButton4:
                    imageSwitcher.setImageResource(GlobalVariable.yOneValueArray[3]);
                    imageSwitcher.invalidate();
                    resultIntent.putExtra("thick", "a4");
                    GlobalVariable.SetThick = "a4";
                    radioButton1.setChecked(false);
                    radioButton2.setChecked(false);
                    radioButton3.setChecked(false);
                    radioButton4.setChecked(true);
                    radioButton5.setChecked(false);
                    radioButton6.setChecked(false);
                    radioButton7.setChecked(false);
                    radioButton1R.setChecked(false);
                    radioButton2R.setChecked(false);
                    radioButton3R.setChecked(false);
                    radioButton4R.setChecked(false);
                    radioButton5R.setChecked(false);
                    radioButton6R.setChecked(false);
                    radioButton7R.setChecked(false);
                    break;
                case R.id.radioButton5:
                    imageSwitcher.setImageResource(GlobalVariable.yOneValueArray[4]);
                    imageSwitcher.invalidate();
                    resultIntent.putExtra("thick", "a5");
                    GlobalVariable.SetThick = "a5";
                    radioButton1.setChecked(false);
                    radioButton2.setChecked(false);
                    radioButton3.setChecked(false);
                    radioButton4.setChecked(false);
                    radioButton5.setChecked(true);
                    radioButton6.setChecked(false);
                    radioButton7.setChecked(false);
                    radioButton1R.setChecked(false);
                    radioButton2R.setChecked(false);
                    radioButton3R.setChecked(false);
                    radioButton4R.setChecked(false);
                    radioButton5R.setChecked(false);
                    radioButton6R.setChecked(false);
                    radioButton7R.setChecked(false);
                    break;
                case R.id.radioButton6:
                    imageSwitcher.setImageResource(GlobalVariable.yOneValueArray[5]);
                    imageSwitcher.invalidate();
                    resultIntent.putExtra("thick", "a6");
                    GlobalVariable.SetThick = "a6";
                    radioButton1.setChecked(false);
                    radioButton2.setChecked(false);
                    radioButton3.setChecked(false);
                    radioButton4.setChecked(false);
                    radioButton5.setChecked(false);
                    radioButton6.setChecked(true);
                    radioButton7.setChecked(false);
                    radioButton1R.setChecked(false);
                    radioButton2R.setChecked(false);
                    radioButton3R.setChecked(false);
                    radioButton4R.setChecked(false);
                    radioButton5R.setChecked(false);
                    radioButton6R.setChecked(false);
                    radioButton7R.setChecked(false);
                    break;
                case R.id.radioButton7:
                    imageSwitcher.setImageResource(GlobalVariable.yOneValueArray[6]);
                    imageSwitcher.invalidate();
                    resultIntent.putExtra("thick", "a7");
                    GlobalVariable.SetThick = "a7";
                    radioButton1.setChecked(false);
                    radioButton2.setChecked(false);
                    radioButton3.setChecked(false);
                    radioButton4.setChecked(false);
                    radioButton5.setChecked(false);
                    radioButton6.setChecked(false);
                    radioButton7.setChecked(true);
                    radioButton1R.setChecked(false);
                    radioButton2R.setChecked(false);
                    radioButton3R.setChecked(false);
                    radioButton4R.setChecked(false);
                    radioButton5R.setChecked(false);
                    radioButton6R.setChecked(false);
                    radioButton7R.setChecked(false);
                    break;

                case R.id.radioButton1R:
                    imageSwitcher.setImageResource(GlobalVariable.yOneValueArray[7]);
                    imageSwitcher.invalidate();
                    resultIntent.putExtra("thick", "ar1");
                    GlobalVariable.SetThick = "ar1";
                    radioButton1.setChecked(false);
                    radioButton2.setChecked(false);
                    radioButton3.setChecked(false);
                    radioButton4.setChecked(false);
                    radioButton5.setChecked(false);
                    radioButton6.setChecked(false);
                    radioButton7.setChecked(false);
                    radioButton1R.setChecked(true);
                    radioButton2R.setChecked(false);
                    radioButton3R.setChecked(false);
                    radioButton4R.setChecked(false);
                    radioButton5R.setChecked(false);
                    radioButton6R.setChecked(false);
                    radioButton7R.setChecked(false);
                    break;
                case R.id.radioButton2R:
                    imageSwitcher.setImageResource(GlobalVariable.yOneValueArray[8]);
                    imageSwitcher.invalidate();
                    resultIntent.putExtra("thick", "ar2");
                    GlobalVariable.SetThick = "ar2";
                    radioButton1.setChecked(false);
                    radioButton2.setChecked(false);
                    radioButton3.setChecked(false);
                    radioButton4.setChecked(false);
                    radioButton5.setChecked(false);
                    radioButton6.setChecked(false);
                    radioButton7.setChecked(false);
                    radioButton1R.setChecked(false);
                    radioButton2R.setChecked(true);
                    radioButton3R.setChecked(false);
                    radioButton4R.setChecked(false);
                    radioButton5R.setChecked(false);
                    radioButton6R.setChecked(false);
                    radioButton7R.setChecked(false);
                    break;
                case R.id.radioButton3R:
                    imageSwitcher.setImageResource(GlobalVariable.yOneValueArray[9]);
                    imageSwitcher.invalidate();
                    resultIntent.putExtra("thick", "ar3");
                    GlobalVariable.SetThick = "ar3";
                    radioButton1.setChecked(false);
                    radioButton2.setChecked(false);
                    radioButton3.setChecked(false);
                    radioButton4.setChecked(false);
                    radioButton5.setChecked(false);
                    radioButton6.setChecked(false);
                    radioButton7.setChecked(false);
                    radioButton1R.setChecked(false);
                    radioButton2R.setChecked(false);
                    radioButton3R.setChecked(true);
                    radioButton4R.setChecked(false);
                    radioButton5R.setChecked(false);
                    radioButton6R.setChecked(false);
                    radioButton7R.setChecked(false);
                    break;
                case R.id.radioButton4R:
                    imageSwitcher.setImageResource(GlobalVariable.yOneValueArray[10]);
                    imageSwitcher.invalidate();
                    resultIntent.putExtra("thick", "ar4");
                    GlobalVariable.SetThick = "ar4";
                    radioButton1.setChecked(false);
                    radioButton2.setChecked(false);
                    radioButton3.setChecked(false);
                    radioButton4.setChecked(false);
                    radioButton5.setChecked(false);
                    radioButton6.setChecked(false);
                    radioButton7.setChecked(false);
                    radioButton1R.setChecked(false);
                    radioButton2R.setChecked(false);
                    radioButton3R.setChecked(false);
                    radioButton4R.setChecked(true);
                    radioButton5R.setChecked(false);
                    radioButton6R.setChecked(false);
                    radioButton7R.setChecked(false);
                    break;
                case R.id.radioButton5R:
                    imageSwitcher.setImageResource(GlobalVariable.yOneValueArray[11]);
                    imageSwitcher.invalidate();
                    resultIntent.putExtra("thick", "ar5");
                    GlobalVariable.SetThick = "ar5";
                    radioButton1.setChecked(false);
                    radioButton2.setChecked(false);
                    radioButton3.setChecked(false);
                    radioButton4.setChecked(false);
                    radioButton5.setChecked(false);
                    radioButton6.setChecked(false);
                    radioButton7.setChecked(false);
                    radioButton1R.setChecked(false);
                    radioButton2R.setChecked(false);
                    radioButton3R.setChecked(false);
                    radioButton4R.setChecked(false);
                    radioButton5R.setChecked(true);
                    radioButton6R.setChecked(false);
                    radioButton7R.setChecked(false);
                    break;
                case R.id.radioButton6R:
                    imageSwitcher.setImageResource(GlobalVariable.yOneValueArray[12]);
                    imageSwitcher.invalidate();
                    resultIntent.putExtra("thick", "ar6");
                    GlobalVariable.SetThick = "ar6";
                    radioButton1.setChecked(false);
                    radioButton2.setChecked(false);
                    radioButton3.setChecked(false);
                    radioButton4.setChecked(false);
                    radioButton5.setChecked(false);
                    radioButton6.setChecked(false);
                    radioButton7.setChecked(false);
                    radioButton1R.setChecked(false);
                    radioButton2R.setChecked(false);
                    radioButton3R.setChecked(false);
                    radioButton4R.setChecked(false);
                    radioButton5R.setChecked(false);
                    radioButton6R.setChecked(true);
                    radioButton7R.setChecked(false);
                    break;
                case R.id.radioButton7R:
                    imageSwitcher.setImageResource(GlobalVariable.yOneValueArray[13]);
                    imageSwitcher.invalidate();
                    resultIntent.putExtra("thick", "ar7");
                    GlobalVariable.SetThick = "ar7";
                    radioButton1.setChecked(false);
                    radioButton2.setChecked(false);
                    radioButton3.setChecked(false);
                    radioButton4.setChecked(false);
                    radioButton5.setChecked(false);
                    radioButton6.setChecked(false);
                    radioButton7.setChecked(false);
                    radioButton1R.setChecked(false);
                    radioButton2R.setChecked(false);
                    radioButton3R.setChecked(false);
                    radioButton4R.setChecked(false);
                    radioButton5R.setChecked(false);
                    radioButton6R.setChecked(false);
                    radioButton7R.setChecked(true);
                    break;
            }
        }
    }
}