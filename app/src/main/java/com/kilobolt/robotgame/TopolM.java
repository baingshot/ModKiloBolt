package com.kilobolt.robotgame;


import java.util.ArrayList;

import android.graphics.Rect;

public class TopolM {

    // Constants are Here
    private int centerX = 70;
    private int centerY = 345;
    private boolean readyToFire = true;

    private Rocket rocketOB;

    public void update() {
        // Moves Character or Scrolls Background accordingly.

    }

    public void shoot() {
        if (readyToFire) {
            rocketOB = new Rocket(centerX+5, centerY - 80);
            readyToFire = false;
        }
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }


    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public Rocket getRocket()
    {
        return rocketOB;
    }

    public boolean isReadyToFire() {
        return readyToFire;
    }

    public void setReadyToFire(boolean readyToFire) {
        this.readyToFire = readyToFire;
    }

}
