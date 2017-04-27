package com.example.user.billardtrainningapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class Level extends AppCompatActivity {
    Intent intent;
    Intent lv_intent;
    String route;
    ImageButton levelButton[]=new ImageButton[9];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level);

        intent = new Intent(this, TrainningActivity.class);
        lv_intent=getIntent();
        levelButton[0]=(ImageButton)findViewById(R.id.lv1);
        levelButton[1]=(ImageButton)findViewById(R.id.lv2);
        levelButton[2]=(ImageButton)findViewById(R.id.lv3);
        levelButton[3]=(ImageButton)findViewById(R.id.lv4);
        levelButton[4]=(ImageButton)findViewById(R.id.lv5);
        levelButton[5]=(ImageButton)findViewById(R.id.lv6);
        levelButton[6]=(ImageButton)findViewById(R.id.lv7);
        levelButton[7]=(ImageButton)findViewById(R.id.lv8);
        levelButton[8]=(ImageButton)findViewById(R.id.lv9);

        route=lv_intent.getStringExtra("ROUTE");
        Log.e("",""+route);
        switch(route){
            case "bp_apdol":
                levelButton[0].setEnabled(true);
                levelButton[1].setEnabled(true);
                levelButton[2].setEnabled(true);
                levelButton[3].setEnabled(false);
                levelButton[4].setEnabled(false);
                levelButton[5].setEnabled(false);
                levelButton[6].setEnabled(false);
                levelButton[7].setEnabled(false);
                levelButton[8].setEnabled(false);
                break;
            case "bp_bitgyou":
                levelButton[0].setEnabled(true);
                levelButton[1].setEnabled(true);
                levelButton[2].setEnabled(false);
                levelButton[3].setEnabled(false);
                levelButton[4].setEnabled(false);
                levelButton[5].setEnabled(false);
                levelButton[6].setEnabled(false);
                levelButton[7].setEnabled(false);
                levelButton[8].setEnabled(false);
                break;
            case "bp_doublecushion":
                levelButton[0].setEnabled(true);
                levelButton[1].setEnabled(true);
                levelButton[2].setEnabled(false);
                levelButton[3].setEnabled(false);
                levelButton[4].setEnabled(false);
                levelButton[5].setEnabled(false);
                levelButton[6].setEnabled(false);
                levelButton[7].setEnabled(false);
                levelButton[8].setEnabled(false);
                break;
            case "bp_dwidol":
                levelButton[0].setEnabled(true);
                levelButton[1].setEnabled(true);
                levelButton[2].setEnabled(false);
                levelButton[3].setEnabled(false);
                levelButton[4].setEnabled(false);
                levelButton[5].setEnabled(false);
                levelButton[6].setEnabled(false);
                levelButton[7].setEnabled(false);
                levelButton[8].setEnabled(false);
                break;
            case "bp_ggeoga":
                levelButton[0].setEnabled(true);
                levelButton[1].setEnabled(true);
                levelButton[2].setEnabled(true);
                levelButton[3].setEnabled(false);
                levelButton[4].setEnabled(false);
                levelButton[5].setEnabled(false);
                levelButton[6].setEnabled(false);
                levelButton[7].setEnabled(false);
                levelButton[8].setEnabled(false);
                break;
            case "bp_youpdol":
                levelButton[0].setEnabled(true);
                levelButton[1].setEnabled(true);
                levelButton[2].setEnabled(true);
                levelButton[3].setEnabled(false);
                levelButton[4].setEnabled(false);
                levelButton[5].setEnabled(false);
                levelButton[6].setEnabled(false);
                levelButton[7].setEnabled(false);
                levelButton[8].setEnabled(false);
                break;
            case "ap_dwidol":
                levelButton[0].setEnabled(true);
                levelButton[1].setEnabled(true);
                levelButton[2].setEnabled(true);
                levelButton[3].setEnabled(true);
                levelButton[4].setEnabled(true);
                levelButton[5].setEnabled(true);
                levelButton[6].setEnabled(false);
                levelButton[7].setEnabled(false);
                levelButton[8].setEnabled(false);
                break;
            case "ap_apdol":
                levelButton[0].setEnabled(true);
                levelButton[1].setEnabled(true);
                levelButton[2].setEnabled(true);
                levelButton[3].setEnabled(true);
                levelButton[4].setEnabled(true);
                levelButton[5].setEnabled(true);
                levelButton[6].setEnabled(true);
                levelButton[7].setEnabled(true);
                levelButton[8].setEnabled(false);

                break;
            case "ap_bitgyou":
                levelButton[0].setEnabled(true);
                levelButton[1].setEnabled(true);
                levelButton[2].setEnabled(true);
                levelButton[3].setEnabled(false);
                levelButton[4].setEnabled(false);
                levelButton[5].setEnabled(false);
                levelButton[6].setEnabled(false);
                levelButton[7].setEnabled(false);
                levelButton[8].setEnabled(false);

                break;
            case "ap_doublecushion":
                levelButton[0].setEnabled(true);
                levelButton[1].setEnabled(true);
                levelButton[2].setEnabled(true);
                levelButton[3].setEnabled(true);
                levelButton[4].setEnabled(true);
                levelButton[5].setEnabled(false);
                levelButton[6].setEnabled(false);
                levelButton[7].setEnabled(false);
                levelButton[8].setEnabled(false);
                break;
            case "ap_ggeoga":
                levelButton[0].setEnabled(true);
                levelButton[1].setEnabled(true);
                levelButton[2].setEnabled(true);
                levelButton[3].setEnabled(true);
                levelButton[4].setEnabled(true);
                levelButton[5].setEnabled(false);
                levelButton[6].setEnabled(false);
                levelButton[7].setEnabled(false);
                levelButton[8].setEnabled(false);
                break;
            case "ap_youpdol":
                levelButton[0].setEnabled(true);
                levelButton[1].setEnabled(true);
                levelButton[2].setEnabled(true);
                levelButton[3].setEnabled(true);
                levelButton[4].setEnabled(true);
                levelButton[5].setEnabled(true);
                levelButton[6].setEnabled(true);
                levelButton[7].setEnabled(true);
                levelButton[8].setEnabled(true);
                break;
        }

    }
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.lv1:
                intent.putExtra("RouteLevel",route+"1");
                intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.lv2:
                intent.putExtra("RouteLevel",route+"2");
                intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.lv3:
                intent.putExtra("RouteLevel",route+"3");
                intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.lv4:
                intent.putExtra("RouteLevel",route+"4");
                intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.lv5:
                intent.putExtra("RouteLevel",route+"5");
                intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.lv6:
                intent.putExtra("RouteLevel",route+"6");
                intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.lv7:
                intent.putExtra("RouteLevel",route+"7");
                intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.lv8:
                intent.putExtra("RouteLevel",route+"8");
                intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.lv9:
                intent.putExtra("RouteLevel",route+"9");
                intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;

        }
    }
}