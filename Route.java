package com.example.user.billardtrainningapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class Route extends AppCompatActivity {

    Intent Level_intent;
    Intent route_intent;
    String Level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.route);

        route_intent = getIntent();
        Level_intent = new Intent(this, Level.class);
        Level=route_intent.getStringExtra("Menu_Route");
        Log.e("",""+Level);


    }

    public void onClick(View view){
        Intent Menu_Intent = new Intent(this,TrainningActivity.class);
        switch (view.getId()) {
            case R.id.button_sidespin:
                if(GlobalVariable.Menu_Switcher == 1){
                    if(GlobalVariable.Global_Difficulty.equals("B")) Menu_Intent.putExtra("RouteLevel","bp_youpdol"+Level);
                    else if(GlobalVariable.Global_Difficulty.equals("A")) Menu_Intent.putExtra("RouteLevel","ap_youpdol"+Level);
                    Menu_Intent.addFlags(Menu_Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(Menu_Intent);
                }
                else{
                    if(GlobalVariable.Global_Difficulty.equals("B")) Level_intent.putExtra("ROUTE","bp_youpdol");
                    else if(GlobalVariable.Global_Difficulty.equals("A")) Level_intent.putExtra("ROUTE","ap_youpdol");
                    Level_intent.addFlags(Level_intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(Level_intent);
                }
                break;
            case R.id.button_backspin:
                if(GlobalVariable.Menu_Switcher == 1){
                    if(GlobalVariable.Global_Difficulty.equals("B")) Menu_Intent.putExtra("RouteLevel","bp_dwidol"+Level);
                    else if(GlobalVariable.Global_Difficulty.equals("A")) Menu_Intent.putExtra("RouteLevel","ap_dwidol"+Level);
                    Menu_Intent.addFlags(Menu_Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(Menu_Intent);
                }
                else{
                    if(GlobalVariable.Global_Difficulty.equals("B")) Level_intent.putExtra("ROUTE","bp_dwipdol");
                    else if(GlobalVariable.Global_Difficulty.equals("A")) Level_intent.putExtra("ROUTE","ap_dwidol");
                    Level_intent.addFlags(Level_intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(Level_intent);
                }
                break;
            case R.id.button_frontspin:
                if(GlobalVariable.Menu_Switcher == 1){
                    if(GlobalVariable.Global_Difficulty.equals("B")) Menu_Intent.putExtra("RouteLevel","bp_apdol"+Level);
                    else if(GlobalVariable.Global_Difficulty.equals("A")) Menu_Intent.putExtra("RouteLevel","ap_apdol"+Level);
                    Menu_Intent.addFlags(Menu_Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(Menu_Intent);
                }
                else{
                    if(GlobalVariable.Global_Difficulty.equals("B")) Level_intent.putExtra("ROUTE","bp_apdol");
                    else if(GlobalVariable.Global_Difficulty.equals("A")) Level_intent.putExtra("ROUTE","ap_apdol");
                    Level_intent.addFlags(Level_intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(Level_intent);
                }
                break;
            case R.id.button_scratch:
                if(GlobalVariable.Menu_Switcher == 1){
                    if(GlobalVariable.Global_Difficulty.equals("B")) Menu_Intent.putExtra("RouteLevel","bp_bitgyou"+Level);
                    else if(GlobalVariable.Global_Difficulty.equals("A")) Menu_Intent.putExtra("RouteLevel","ap_bitgyou"+Level);
                    Menu_Intent.addFlags(Menu_Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(Menu_Intent);
                }
                else{
                    if(GlobalVariable.Global_Difficulty.equals("B")) Level_intent.putExtra("ROUTE","bp_bitgyou");
                    else if(GlobalVariable.Global_Difficulty.equals("A")) Level_intent.putExtra("ROUTE","ap_bitgyou");
                    Level_intent.addFlags(Level_intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(Level_intent);
                    break;
                }
                break;
            case R.id.button_hang:
                if(GlobalVariable.Menu_Switcher == 1){
                    if(GlobalVariable.Global_Difficulty.equals("B")) Menu_Intent.putExtra("RouteLevel","bp_ggeoga"+Level);
                    else if(GlobalVariable.Global_Difficulty.equals("A")) Menu_Intent.putExtra("RouteLevel","ap_ggeoga"+Level);
                    Menu_Intent.addFlags(Menu_Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(Menu_Intent);
                }
                else{
                    if(GlobalVariable.Global_Difficulty.equals("B")) Level_intent.putExtra("ROUTE","bp_ggeoga");
                    else if(GlobalVariable.Global_Difficulty.equals("A")) Level_intent.putExtra("ROUTE","ap_ggeoga");
                    Level_intent.addFlags(Level_intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(Level_intent);
                    break;
                }
                break;
            case R.id.button_traverse:
                if(GlobalVariable.Menu_Switcher == 1){
                    if(GlobalVariable.Global_Difficulty.equals("B")) Menu_Intent.putExtra("RouteLevel","bp_doublecushion"+Level);
                    else if(GlobalVariable.Global_Difficulty.equals("A")) Menu_Intent.putExtra("RouteLevel","ap_doublecushion"+Level);
                    Menu_Intent.addFlags(Menu_Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(Menu_Intent);
                }
                else{
                    if(GlobalVariable.Global_Difficulty.equals("B")) Level_intent.putExtra("ROUTE","bp_doublecushion");
                    else if(GlobalVariable.Global_Difficulty.equals("A")) Level_intent.putExtra("ROUTE","ap_doublecushion");
                    startActivity(Level_intent);
                    break;
                }
                break;
        }
    }
}
