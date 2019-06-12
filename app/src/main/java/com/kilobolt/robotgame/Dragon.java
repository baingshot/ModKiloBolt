package com.kilobolt.robotgame;


import java.util.ArrayList;

import android.graphics.Rect;

public class Dragon {

    // Constants are Here
    private int centerX = 700;
    private int centerY = 345;
    private boolean readyToFire = true;

    //private ArrayList<Rocket> Rockets = new ArrayList<Rocket>();

    public void update() {
        // Moves Character or Scrolls Background accordingly.
        if( centerX > 250) {
            centerX -= 1;
        }
    }

    public void shoot() {

    }

    //get-------------------------
    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public boolean isReadyToFire() {
        return readyToFire;
    }

    //set--------------------------
    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public void setReadyToFire(boolean readyToFire) {
        this.readyToFire = readyToFire;
    }

}
