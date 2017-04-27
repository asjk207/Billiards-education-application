package com.example.user.billardtrainningapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by user on 2017-02-02.
 */

public class Difficulty extends AppCompatActivity {

    Intent Difficulty_Intent;
    String Menu_to_Difficulty;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.difficulty);

        Difficulty_Intent = getIntent();
        Menu_to_Difficulty = Difficulty_Intent.getStringExtra("Menu_Difficulty");
    }
    public void onClick(View view){
        Intent Menu_Intent = new Intent(this,TrainningActivity.class);
        Intent Route_intent = new Intent(this, Route.class);
        switch (view.getId()) {
            case R.id.basic:
                GlobalVariable.Global_Difficulty="B";
                if(GlobalVariable.Menu_Switcher == 1){
                    Menu_Intent.putExtra("RouteLevel","bp_"+Menu_to_Difficulty);
                    Menu_Intent.addFlags(Menu_Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(Menu_Intent);
                }
                else{
                    startActivity(Route_intent);
                }
                break;
            case R.id.apply:
                GlobalVariable.Global_Difficulty="A";
                if(GlobalVariable.Menu_Switcher == 1){
                    Menu_Intent.putExtra("RouteLevel","ap_"+Menu_to_Difficulty);
                    Menu_Intent.addFlags(Menu_Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(Menu_Intent);
                }
                else {
                    startActivity(Route_intent);
                }
                break;
            case R.id.real:
                GlobalVariable.Global_Difficulty="R";
                if(GlobalVariable.Menu_Switcher == 1){
                    Menu_Intent.putExtra("RouteLevel",Menu_to_Difficulty);
                    Menu_Intent.addFlags(Menu_Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(Menu_Intent);
                }
                else {
                    startActivity(Route_intent);
                }
                break;
        }
    }
}
