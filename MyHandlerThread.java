package com.example.user.billardtrainningapplication;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * Created by user on 2017-02-27.
 */

public class MyHandlerThread extends Thread {
    private static final int THREAD_STOP = 0;
    private static final int THREAD_SLEEP = 1;
    public Handler mHandler;

    @Override
    public void run() {
        Looper.prepare();
        mHandler = new Handler() {
            public void HandleMessage(Message msg) {
                //데이터 메시지를 여기에서 처리한다.
                switch(msg.what){
                    case THREAD_STOP:

                        break;
                    case THREAD_SLEEP:

                        break;
                }
            }
        };
        Looper.loop();
    }

}
