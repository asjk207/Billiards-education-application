package com.example.user.billardtrainningapplication;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by user on 2017-04-11.
 */

public class CourseBook_Basic_Viewpager extends PagerAdapter {

    LayoutInflater inflater;

    //ViewPager 구별 변수 1=옆돌리기 2=앞돌리기 3=뒤돌리기 4=빗겨치기 5=꺽어치기 6=횡단샷
    public CourseBook_Basic_Viewpager(LayoutInflater inflater) {
        this.inflater=inflater;
    }
    @Override
    public int getCount() {
        if(GlobalVariable.ViewPager_Switcher == 1 || GlobalVariable.ViewPager_Switcher == 2 || GlobalVariable.ViewPager_Switcher == 5) return 3;
        else if (GlobalVariable.ViewPager_Switcher == 3 || GlobalVariable.ViewPager_Switcher == 4 || GlobalVariable.ViewPager_Switcher == 6) return 2;
        else return 1;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view=null;
        switch (GlobalVariable.ViewPager_Switcher) {
            case 1:
            switch (position) {
                case 0:
                    view = inflater.inflate(R.layout.coursebook_youpdol_basic_1, null);
                    break;
                case 1:
                    view = inflater.inflate(R.layout.coursebook_youpdol_basic_2, null);
                    break;
                case 2:
                    view = inflater.inflate(R.layout.coursebook_youpdol_basic_3, null);
                    break;
            }
                break;
            case 2:
                switch (position) {
                    case 0:
                        view = inflater.inflate(R.layout.coursebook_apdol_basic_1, null);
                        break;
                    case 1:
                        view = inflater.inflate(R.layout.coursebook_apdol_basic_2, null);
                        break;
                    case 2:
                        view = inflater.inflate(R.layout.coursebook_apdol_basic_3, null);
                        break;
                }
                break;
            case 3:
                switch (position) {
                    case 0:
                        view = inflater.inflate(R.layout.coursebook_dwidol_basic_1, null);
                        break;
                    case 1:
                        view = inflater.inflate(R.layout.coursebook_dwidol_basic_2, null);
                        break;
                }
                break;
            case 4:
                switch (position) {
                    case 0:
                        view = inflater.inflate(R.layout.coursebook_bitgyou_basic_1, null);
                        break;
                    case 1:
                        view = inflater.inflate(R.layout.coursebook_bitgyou_basic_2, null);
                        break;
                }
                break;
            case 5:
                switch (position) {
                    case 0:
                        view = inflater.inflate(R.layout.coursebook_ggeoga_basic_1, null);
                        break;
                    case 1:
                        view = inflater.inflate(R.layout.coursebook_ggeoga_basic_2, null);
                        break;
                    case 2:
                        view = inflater.inflate(R.layout.coursebook_ggeoga_basic_3, null);
                        break;
                }
                break;
            case 6:
                switch (position) {
                    case 0:
                        view = inflater.inflate(R.layout.coursebook_doublecushion_basic_1, null);
                        break;
                    case 1:
                        view = inflater.inflate(R.layout.coursebook_doublecushion_basic_2, null);
                        break;
                }
                break;
        }
        container.addView(view);
            return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(View v, Object obj) {
        return v==obj;
    }
}