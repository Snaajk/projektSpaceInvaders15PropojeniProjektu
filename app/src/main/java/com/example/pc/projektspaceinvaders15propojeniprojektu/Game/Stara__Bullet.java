package com.example.pc.projektspaceinvaders15propojeniprojektu.Game;

import android.graphics.Canvas;
import android.graphics.RectF;

public class Stara__Bullet {

    private float x;
    private float y;
    private Canvas canvas;
    private RectF rect;
    public final int UP = 0;
    public final int DOWN = 1;
    int heading = -1;
    float speed =  100;
    private int width = 10;
    private int height;
    private boolean isActive;
    private float pokusX = 0;
    private boolean navigationBullet;
    private Nova_GyroskopBullet nova_gyroskopBullet;

    public Stara__Bullet(int screenY) {
        height = screenY / 100;
        isActive = false;
        rect = new RectF();
    }

    public Stara__Bullet(int screenY, Canvas canvas) {
        this.canvas = canvas;
        height = screenY / 100;
        isActive = false;
        rect = new RectF();
    }

    public boolean shoot(float startX, float startY, int direction, boolean navigationBullet, Nova_GyroskopBullet nova_gyroskopBullet) {
        this.navigationBullet = navigationBullet;
        this.nova_gyroskopBullet = nova_gyroskopBullet;

        if (!isActive) {
            x = startX;
            y = startY;
            heading = direction;
            isActive = true;
            pokusX = 0;
            return true;
        }
        return false;
    }



    public void update(long fps){

        if(heading == UP){
            y = y - speed / fps;
        }else{
            y = y + speed / fps;
        }
        rect.left = x + pokusX;
        rect.right = x + width + pokusX;
        rect.top = y;
        rect.bottom = y + height;

//        pokusX = navigationBullet() + 50/fps;
        pokusX = pokusX + navigationBullet();
    }

    private float navigationBullet(){
        if(navigationBullet == true){
            int i = nova_gyroskopBullet.getYSenzor();
            i = i - 90;
            return (float) i/4;
        }else{
            return 0;
        }

    }

    public RectF getRect(){
        return  rect;
    }

    public boolean getStatus(){
        return isActive;
    }

    public void setInactive(){
        isActive = false;
    }

    public float getImpactPointY(){
        if (heading == DOWN){
            return y + height;
        }else{
            return  y;
        }

    }
}
