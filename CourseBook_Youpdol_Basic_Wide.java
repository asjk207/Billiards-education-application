package com.example.user.billardtrainningapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

/**
 * Created by user on 2017-04-05.
 */


public class CourseBook_Youpdol_Basic_Wide extends AppCompatActivity {

        //Y=옆돌리기 F=앞돌리기 H=걸어치기 T=횡단샷 C=빗겨치기 B=뒤돌리기
        int[] Y_Image_Array = {
        R.drawable.y1,R.drawable.y2,R.drawable.y3,R.drawable.y4,
        R.drawable.y5,R.drawable.y6,R.drawable.y7,R.drawable.y8,
        R.drawable.y9,R.drawable.y10,R.drawable.y11,R.drawable.y12
        };

        Intent CourseBook_Wide_Intent;
        Intent Youtube_Intent;
        String Route_Stage;
        ImageSwitcher CourseBook_Wide_ImageSwitcher;
        ImageSwitcher CourseBook_Wide_Value_ImageSwitcher;

        TextView CourseBook_Wide_Thick_Text;
        TextView CourseBook_Wide_PointnTip_Text;
        TextView CourseBook_Wide_Strock_Text;

@Override
protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coursebook_youpdol_basic_wide);

        Youtube_Intent = new Intent(getApplicationContext(), YouTubeActivity.class);

        CourseBook_Wide_Intent = getIntent();
        Route_Stage = CourseBook_Wide_Intent.getStringExtra("Level");

        CourseBook_Wide_ImageSwitcher = (ImageSwitcher) findViewById(R.id.coursebook_youpdol_basic_wide_imageswitcher);
        CourseBook_Wide_ImageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
@Override
public View makeView() {
        ImageView imageView = new ImageView(getApplicationContext());
        return imageView;
        }
        });
        CourseBook_Wide_Value_ImageSwitcher = (ImageSwitcher) findViewById(R.id.coursebook_youpdol_basic_wide_value_imageswitcher);
        CourseBook_Wide_Value_ImageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
@Override
public View makeView() {
        ImageView imageView = new ImageView(getApplicationContext());
        return imageView;
        }
        });

        CourseBook_Wide_Thick_Text = (TextView)findViewById(R.id.coursebook_youpdol_basic_wide_thick_text);
        CourseBook_Wide_PointnTip_Text = (TextView)findViewById(R.id.coursebook_youpdol_basic_wide_pointntip_text);
        CourseBook_Wide_Strock_Text = (TextView)findViewById(R.id.coursebook_youpdol_basic_wide_strock_text);

        switch (Route_Stage){
        case "1":
        CourseBook_Wide_ImageSwitcher.setImageResource(Y_Image_Array[0]);
        CourseBook_Wide_ImageSwitcher.invalidate();
        CourseBook_Wide_Value_ImageSwitcher.setImageResource(GlobalVariable.yNoTipArray[3]);
        CourseBook_Wide_Value_ImageSwitcher.invalidate();
        CourseBook_Wide_Thick_Text.setText("4/8");
        CourseBook_Wide_PointnTip_Text.setText("12시방향 / 0팁");
        CourseBook_Wide_Strock_Text.setText("부드러운 스트로크");
                Youtube_Intent.putExtra("CourseBook_to_Youtube","youpdol1");
                GlobalVariable.CourseBook_Youtube_Casenum=1;
        break;
        case "2":
        CourseBook_Wide_ImageSwitcher.setImageResource(Y_Image_Array[1]);
        CourseBook_Wide_ImageSwitcher.invalidate();
        CourseBook_Wide_Value_ImageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[176]);
        CourseBook_Wide_Value_ImageSwitcher.invalidate();
        CourseBook_Wide_Thick_Text.setText("4/8");
        CourseBook_Wide_PointnTip_Text.setText("9시방향 / 1팁");
        CourseBook_Wide_Strock_Text.setText("부드러운 스트로크");
                Youtube_Intent.putExtra("CourseBook_to_Youtube","youpdol1");
                GlobalVariable.CourseBook_Youtube_Casenum=2;
        break;
        case "3":
        CourseBook_Wide_ImageSwitcher.setImageResource(Y_Image_Array[2]);
        CourseBook_Wide_ImageSwitcher.invalidate();
        CourseBook_Wide_Value_ImageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[177]);
        CourseBook_Wide_Value_ImageSwitcher.invalidate();
        CourseBook_Wide_Thick_Text.setText("4/8");
        CourseBook_Wide_PointnTip_Text.setText("9시방향 / 2팁");
        CourseBook_Wide_Strock_Text.setText("부드러운 스트로크");
                Youtube_Intent.putExtra("CourseBook_to_Youtube","youpdol1");
                GlobalVariable.CourseBook_Youtube_Casenum=3;
        break;
        case "4":
        CourseBook_Wide_ImageSwitcher.setImageResource(Y_Image_Array[3]);
        CourseBook_Wide_ImageSwitcher.invalidate();
        CourseBook_Wide_Value_ImageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[178]);
        CourseBook_Wide_Value_ImageSwitcher.invalidate();
        CourseBook_Wide_Thick_Text.setText("4/8");
        CourseBook_Wide_PointnTip_Text.setText("9시방향 / 3팁");
        CourseBook_Wide_Strock_Text.setText("부드러운 스트로크");
                Youtube_Intent.putExtra("CourseBook_to_Youtube","youpdol1");
                GlobalVariable.CourseBook_Youtube_Casenum=4;
        break;
        case "5":
        CourseBook_Wide_ImageSwitcher.setImageResource(Y_Image_Array[4]);
        CourseBook_Wide_ImageSwitcher.invalidate();
        CourseBook_Wide_Value_ImageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[93]);
        CourseBook_Wide_Value_ImageSwitcher.invalidate();
        CourseBook_Wide_Thick_Text.setText("2/8");
        CourseBook_Wide_PointnTip_Text.setText("12시방향 / 2팁");
        CourseBook_Wide_Strock_Text.setText("부드러운 스트로크");
                Youtube_Intent.putExtra("CourseBook_to_Youtube","youpdol2");
                GlobalVariable.CourseBook_Youtube_Casenum=1;
        break;
        case "6":
        CourseBook_Wide_ImageSwitcher.setImageResource(Y_Image_Array[5]);
        CourseBook_Wide_ImageSwitcher.invalidate();
        CourseBook_Wide_Value_ImageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[89]);
        CourseBook_Wide_Value_ImageSwitcher.invalidate();
        CourseBook_Wide_Thick_Text.setText("2/8");
        CourseBook_Wide_PointnTip_Text.setText("11시방향 / 2팁");
        CourseBook_Wide_Strock_Text.setText("부드러운 스트로크");
                Youtube_Intent.putExtra("CourseBook_to_Youtube","youpdol2");
                GlobalVariable.CourseBook_Youtube_Casenum=2;
        break;
        case "7":
        CourseBook_Wide_ImageSwitcher.setImageResource(Y_Image_Array[6]);
        CourseBook_Wide_ImageSwitcher.invalidate();
        CourseBook_Wide_Value_ImageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[133]);
        CourseBook_Wide_Value_ImageSwitcher.invalidate();
        CourseBook_Wide_Thick_Text.setText("3/8");
        CourseBook_Wide_PointnTip_Text.setText("10시방향 / 2팁");
        CourseBook_Wide_Strock_Text.setText("부드러운 스트로크");
                Youtube_Intent.putExtra("CourseBook_to_Youtube","youpdol2");
                GlobalVariable.CourseBook_Youtube_Casenum=3;
        break;
        case "8":
        CourseBook_Wide_ImageSwitcher.setImageResource(Y_Image_Array[7]);
        CourseBook_Wide_ImageSwitcher.invalidate();
        CourseBook_Wide_Value_ImageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[178]);
        CourseBook_Wide_Value_ImageSwitcher.invalidate();
        CourseBook_Wide_Thick_Text.setText("4/8");
        CourseBook_Wide_PointnTip_Text.setText("9시방향 / 3팁");
        CourseBook_Wide_Strock_Text.setText("부드러운 스트로크");
                Youtube_Intent.putExtra("CourseBook_to_Youtube","youpdol2");
                GlobalVariable.CourseBook_Youtube_Casenum=4;
        break;
        case "9":
        CourseBook_Wide_ImageSwitcher.setImageResource(Y_Image_Array[8]);
        CourseBook_Wide_ImageSwitcher.invalidate();
        CourseBook_Wide_Value_ImageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[189]);
        CourseBook_Wide_Value_ImageSwitcher.invalidate();
        CourseBook_Wide_Thick_Text.setText("4/8");
        CourseBook_Wide_PointnTip_Text.setText("12시방향 / 2팁");
        CourseBook_Wide_Strock_Text.setText("부드러운 스트로크");
                Youtube_Intent.putExtra("CourseBook_to_Youtube","youpdol3");
                GlobalVariable.CourseBook_Youtube_Casenum=1;
        break;
        case "10":
        CourseBook_Wide_ImageSwitcher.setImageResource(Y_Image_Array[9]);
        CourseBook_Wide_ImageSwitcher.invalidate();
        CourseBook_Wide_Value_ImageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[185]);
        CourseBook_Wide_Value_ImageSwitcher.invalidate();
        CourseBook_Wide_Thick_Text.setText("4/8");
        CourseBook_Wide_PointnTip_Text.setText("11시방향 / 2팁");
        CourseBook_Wide_Strock_Text.setText("부드러운 스트로크");
                Youtube_Intent.putExtra("CourseBook_to_Youtube","youpdol3");
                GlobalVariable.CourseBook_Youtube_Casenum=2;
        break;
        case "11":
        CourseBook_Wide_ImageSwitcher.setImageResource(Y_Image_Array[10]);
        CourseBook_Wide_ImageSwitcher.invalidate();
        CourseBook_Wide_Value_ImageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[181]);
        CourseBook_Wide_Value_ImageSwitcher.invalidate();
        CourseBook_Wide_Thick_Text.setText("4/8");
        CourseBook_Wide_PointnTip_Text.setText("10시방향 / 2팁");
        CourseBook_Wide_Strock_Text.setText("부드러운 스트로크");
                Youtube_Intent.putExtra("CourseBook_to_Youtube","youpdol3");
                GlobalVariable.CourseBook_Youtube_Casenum=3;
        break;
        case "12":
        CourseBook_Wide_ImageSwitcher.setImageResource(Y_Image_Array[11]);
        CourseBook_Wide_ImageSwitcher.invalidate();
        CourseBook_Wide_Value_ImageSwitcher.setImageResource(GlobalVariable.yThreeValueArray[182]);
        CourseBook_Wide_Value_ImageSwitcher.invalidate();
        CourseBook_Wide_Thick_Text.setText("4/8");
        CourseBook_Wide_PointnTip_Text.setText("10시방향 / 3팁");
        CourseBook_Wide_Strock_Text.setText("부드러운 스트로크");
                Youtube_Intent.putExtra("CourseBook_to_Youtube","youpdol3");
                GlobalVariable.CourseBook_Youtube_Casenum=4;
        break;
        }
        }
        public void coursebook_youtube(View v){
                GlobalVariable.CourseBook_Youtube_Switcher = 1;
                startActivity(Youtube_Intent);
        }
        }