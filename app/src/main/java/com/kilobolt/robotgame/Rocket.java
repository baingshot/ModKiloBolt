package com.kilobolt.robotgame;

import android.graphics.Rect;

public class Rocket {

    private int x, y, speed, speedX,speedY;
    private int directionx, directiony;
        private boolean visible;
    private boolean targetselected;
    private Rect r;
    private boolean navedenie;

    public Rocket(int startX, int startY){
        x = startX;
        y = startY;

        directionx=0;
        directiony=0;

        speed = 3;
        visible = true;
        targetselected = false;
        navedenie = false;

        r = new Rect(0, 0, 0, 0);
    }

    public void update(){

        if(targetselected == false) {
            y -= speed;
            r.set(x, y, x + 10, y + 100);
            if (x > 800) {
                visible = false;
                r = null;
            }
            if (y < -50 || y> 480) {
                visible = false;
                r = null;
            }
        }
        else
        {
            //finding alpha
            /*
            b = -y - kx; (put 1- st point)
            y= -kx -b;
            k= (y+b)/x; (put 2- nd point)
            alpha = arctg(k);
             */
            if( navedenie == false ) {
                /*double k;
                k = (directiony - y) / (x - directionx);
                alpha = -Math.atan(k);
                speedX= (int) Math.round(speed * Math.cos(alpha));
                speedY= (int) Math.round(speed * Math.sin(alpha));*/
                int k = 70;
                speedX = (int)((directionx - x)/k);
                speedY = (int)((directiony - y)/k);
                if( speedY < 0 ){ speedY-=1;}
                navedenie =true;
            }
            x = x + speedX;
            y = y + speedY;

            r.set(x, y, x + 100, y + 10);
            if (x > 800) {
                visible = false;
                r = null;
            }
            if (y < -50 || y> 480) {
                visible = false;
                r = null;
            }
        }

    }


//get-----------------------------
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return visible;
    }

    public boolean isTargetselected() {
        return targetselected;
    }

    //set------------------------------
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setTargetselected(boolean targetselected) {
        this.targetselected = targetselected;
    }

    public void setDirection(int x, int y) {
        this.directionx = x;
        this.directiony = y;
    }

}