package com.example.user.billardtrainningapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
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

        final ImageButton Sidespin_Button = (ImageButton)findViewById(R.id.button_sidespin);
        Sidespin_Button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    Sidespin_Button.setImageResource(R.drawable.sidespin_pressed);
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    Sidespin_Button.setImageResource(R.drawable.sidespin);
                }
                return false;
            }
        });
        final ImageButton Backspin_Button = (ImageButton)findViewById(R.id.button_backspin);
        Backspin_Button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    Backspin_Button.setImageResource(R.drawable.backspin_pressed);
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    Backspin_Button.setImageResource(R.drawable.backspin);
                }
                return false;
            }
        });
        final ImageButton Frontspin_Button = (ImageButton)findViewById(R.id.button_frontspin);
        Frontspin_Button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    Frontspin_Button.setImageResource(R.drawable.frontspin_pressed);
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    Frontspin_Button.setImageResource(R.drawable.frontspin);
                }
                return false;
            }
        });
        final ImageButton Scratch_Button = (ImageButton)findViewById(R.id.button_scratch);
        Scratch_Button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    Scratch_Button.setImageResource(R.drawable.scratch_pressed);
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    Scratch_Button.setImageResource(R.drawable.scratch);
                }
                return false;
            }
        });
        final ImageButton Ggeoga_Button = (ImageButton)findViewById(R.id.button_hang);
        Ggeoga_Button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    Ggeoga_Button.setImageResource(R.drawable.ggeoga_pressed);
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    Ggeoga_Button.setImageResource(R.drawable.ggeoga);
                }
                return false;
            }
        });
        final ImageButton Traverse_Button = (ImageButton)findViewById(R.id.button_traverse);
        Traverse_Button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    Traverse_Button.setImageResource(R.drawable.traverse_pressed);
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    Traverse_Button.setImageResource(R.drawable.traverse);
                }
                return false;
            }
        });
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
                    if(GlobalVariable.Global_Difficulty.equals("B")) Level_intent.putExtra("ROUTE","bp_dwidol");
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
