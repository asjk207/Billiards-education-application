package com.example.user.billardtrainningapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by user on 2017-04-05.
 */

public class Loading_KPU extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_kpu);

        Handler h = new Handler();
        h.postDelayed(new Loading_KPU.Loadinghandler(), 1500);
    }
    class Loadinghandler implements Runnable{
        public void run(){

            startActivity(new Intent(getApplication(),MainActivity.class));
            Loading_KPU.this.finish();
        }
    }
}
