package com.kilobolt.robotgame;


import java.util.ArrayList;

import android.graphics.Rect;

public class Army {

    // Constants are Here
    private int centerX = 500;
    private int centerY = 410;
    private boolean readyToFire = true;

    public void update() {
        // Moves Character or Scrolls Background accordingly.
        if( centerX > 250) {
            centerX -= 1;
        }
    }

    public void shoot() {

    }

    //get--------------------------------
    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public boolean isReadyToFire() {
        return readyToFire;
    }

    //set---------------------------------------------
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
