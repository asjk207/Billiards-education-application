package com.example.user.billardtrainningapplication;

/**
 * Created by user on 2017-01-26.
 */

public class CBoard {
    private int Boardwidth;
    private int Boardheight;

    public int getHeight() {
        return Boardwidth;
    }

    public int getWidth(){
        return Boardheight;
    }

    public CBoard(int height, int width) {
        this.Boardheight = height;
        this.Boardwidth = width;
    }

}
