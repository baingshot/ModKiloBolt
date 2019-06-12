package com.kilobolt.robotgame;


import java.util.ArrayList;

import android.graphics.Rect;

public class Wall {

    // Constants are Here
    private int centerX = 200;
    private int centerY = 300;
    private int fired = 0;
    private int lives = 5;

    public void update() {
        // Moves Character or Scrolls Background accordingly.

    }

    public void shoot() {

    }

    //get------------------------------
    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public int getFired() {
        return fired;
    }

    public int getLives() {
        return lives;
    }

    //set-------------------------------
    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public void setFired(int fired) {
        this.fired = fired;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

}
