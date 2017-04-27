package com.example.user.billardtrainningapplication;

/**
 * Created by user on 2017-01-26.
 */

public class BallVariable {
    //두께 1/8, 2/8, 3/8, 4/8, 5/8, 6/8, 7/8
    private int thick;
    //팁 1T, 2T, 3T, 4T
    private int tip;

    public BallVariable( int thick, int tip){
        this.thick=thick;
        this.tip=tip;
    }
    public void setThick(int thick){
        this.thick=thick;
    }
    public void setTip(int tip){
        this.tip=tip;
    }

    public int getThick() {
        return thick;
    }
    public int getTip(){
        return tip;
    }
}
