package com.kilobolt.robotgame;


import java.util.ArrayList;

import android.graphics.Rect;

public class Ufo {

    // Constants are Here
    private int centerX = 600;
    private int centerY = 50;
    private boolean readyToFire = false;
    private boolean hit = false;
    private int fired = 0;
    private int speedY= 4 ;
    private boolean up=false;
    private int reload = 2;
    private int lives = 8;

    public void update() {
        // Moves Character or Scrolls Background accordingly.
        if(centerY< 50) {
            up = false;
            reload--;
        }
        if( centerY > 350) up= true;
        if(up == false) centerY+=speedY;
        if(up == true) centerY-=speedY;

        shoot();

        if( lives < 4 )
        {
            speedY= 6 ;
        }
    }

    public void shoot() {
        if (reload ==0 ) {
            readyToFire = true;
            reload =2;
        }
    }
//get-------------------------------
    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public boolean isReadyToFire() {
        return readyToFire;
    }

    public boolean isHit() {
        return hit;
    }

    public int getFired() {
        return fired;
    }

    public int getLives() {
        return lives;
    }

//set------------------------------------------------
    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public void setReadyToFire(boolean readyToFire) {
        this.readyToFire = readyToFire;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public void setFired(int fired) {
        this.fired = fired;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
}
