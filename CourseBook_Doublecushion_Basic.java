package com.example.user.billardtrainningapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by user on 2017-04-06.
 */

public class CourseBook_Doublecushion_Basic extends AppCompatActivity {
    ViewPager Doublecushion_Basic_Pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coursebook_basic_viewpager);

        Doublecushion_Basic_Pager = (ViewPager)findViewById(R.id.basic_pager);
        CourseBook_Basic_Viewpager adapter= new CourseBook_Basic_Viewpager(getLayoutInflater());
        Doublecushion_Basic_Pager.setAdapter(adapter);
    }

    public void CourseBook_doublecushion_wide(View v) {
        Intent CourseBook_Summary_Button_Intent;
        CourseBook_Summary_Button_Intent = new Intent(this, CourseBook_Doublecushion_Basic_Wide.class);

        switch (v.getId()) {
            case R.id.coursebook_doublecushion_button_1:
                CourseBook_Summary_Button_Intent.putExtra("Level", "1");
                startActivity(CourseBook_Summary_Button_Intent);
                break;
            case R.id.coursebook_doublecushion_button_2:
                CourseBook_Summary_Button_Intent.putExtra("Level", "2");
                startActivity(CourseBook_Summary_Button_Intent);
                break;
            case R.id.coursebook_doublecushion_button_3:
                CourseBook_Summary_Button_Intent.putExtra("Level", "3");
                startActivity(CourseBook_Summary_Button_Intent);
                break;
            case R.id.coursebook_doublecushion_button_4:
                CourseBook_Summary_Button_Intent.putExtra("Level", "4");
                startActivity(CourseBook_Summary_Button_Intent);
                break;
            case R.id.coursebook_doublecushion_button_5:
                CourseBook_Summary_Button_Intent.putExtra("Level", "5");
                startActivity(CourseBook_Summary_Button_Intent);
                break;
            case R.id.coursebook_doublecushion_button_6:
                CourseBook_Summary_Button_Intent.putExtra("Level", "6");
                startActivity(CourseBook_Summary_Button_Intent);
                break;
        }
    }
}
