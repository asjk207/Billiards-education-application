package com.example.user.billardtrainningapplication;

import android.os.Handler;
import android.os.Message;

/**
 * Created by user on 2017-02-17.
 */

public class SendMessageHandler extends Handler {


    private static final int SEND_THREAD_INFOMATION = 0;
    private static final int SEND_THREAD_START_MESSAGE = 1;
    private static final int SEND_THREAD_STOP_MESSAGE = 2;
    @Override
    public void handleMessage(Message msg){
        super.handleMessage(msg);

        switch(msg.what){
            case SEND_THREAD_START_MESSAGE:
                break;
            case SEND_THREAD_STOP_MESSAGE:
                break;
            default:
                break;
        }
    }
}
