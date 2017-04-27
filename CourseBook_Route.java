package com.example.user.billardtrainningapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by user on 2017-02-09.
 */

public class CourseBook_Route extends AppCompatActivity {

    Intent Route_to_Youpdol_Basic_intent;
    Intent Route_to_Dwidol_Basic_intent;
    Intent Route_to_Apdol_Basic_intent;
    Intent Route_to_Bitgyou_Basic_intent;
    Intent Route_to_Ggeoga_Basic_intent;
    Intent Route_to_Doublecushion_Basic_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coursebook_route);

        Route_to_Youpdol_Basic_intent = new Intent(this, CourseBook_Youpdol_Basic.class);
        Route_to_Apdol_Basic_intent = new Intent(this, CourseBook_Apdol_Basic.class);
        Route_to_Dwidol_Basic_intent = new Intent(this, CourseBook_Dwidol_Basic.class);
        Route_to_Bitgyou_Basic_intent = new Intent(this, CourseBook_Bitgyou_Basic.class);
        Route_to_Ggeoga_Basic_intent = new Intent(this, CourseBook_Ggeoga_Basic.class);
        Route_to_Doublecushion_Basic_intent = new Intent(this, CourseBook_Doublecushion_Basic.class);
    }

    //ViewPager 구별 변수 1=옆돌리기 2=앞돌리기 3=뒤돌리기 4=빗겨치기 5=꺽어치기 6=횡단샷
    public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_sidespin:
                    startActivity(Route_to_Youpdol_Basic_intent);
                    GlobalVariable.ViewPager_Switcher = 1;
                    break;
                case R.id.button_backspin:
                    startActivity(Route_to_Dwidol_Basic_intent);
                    GlobalVariable.ViewPager_Switcher = 3;
                    break;
                case R.id.button_frontspin:
                    startActivity(Route_to_Apdol_Basic_intent);
                    GlobalVariable.ViewPager_Switcher = 2;
                    break;
                case R.id.button_scratch:
                    startActivity(Route_to_Bitgyou_Basic_intent);
                    GlobalVariable.ViewPager_Switcher = 4;
                    break;
                case R.id.button_hang:
                    startActivity(Route_to_Ggeoga_Basic_intent);
                    GlobalVariable.ViewPager_Switcher = 5;
                    break;
                case R.id.button_traverse:
                    startActivity(Route_to_Doublecushion_Basic_intent);
                    GlobalVariable.ViewPager_Switcher = 6;
                    break;
        }
    }
}
//youpdol,dwidol,apdol,bitgyou,ggeoga,doublecushion


