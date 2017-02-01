package com.example.user.billardtrainningapplication;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by user on 2017-01-23.
 */

public class TrainningActivity extends AppCompatActivity {
    private TrainnigView trainnigView;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);
        trainnigView=(TrainnigView) findViewById(R.id.TrainningView);
    }
}




