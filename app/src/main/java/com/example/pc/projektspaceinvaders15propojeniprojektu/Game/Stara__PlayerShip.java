package com.example.pc.projektspaceinvaders15propojeniprojektu.Game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;

import com.example.pc.projektspaceinvaders15propojeniprojektu.R;


public class Stara__PlayerShip {

    private final int screenY;
    private final int screenX;
    RectF rect;

    private Bitmap bitmap;
    private float length;
    private float height;
    private float x;
    private float y;
    private float shipSpeed;
    public final int STOPPED = 0;
    public final int LEFT = 1;
    public final int RIGHT = 2;
    private int shipMoving = STOPPED;

    public Stara__PlayerShip(Context context, int screenX, int screenY){
        this.screenX = screenX;
        this.screenY = screenY;
        rect = new RectF();

        length = screenX/10;
        height = screenY/10;
        x = screenX / 2;
        y = screenY - 20;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.playership);

        // -----------------------------------------------------------------  tady mi to vyhazuje chybu ------------------

        // natáhněte bitmapu na velikost odpovídající rozlišení obrazovky
        bitmap = Bitmap.createScaledBitmap(bitmap,
                (int) (length),
                (int) (height),
                false);

        // Jak rychle je pádlo v pixelech za sekundu
        shipSpeed = 250;
    }

    public RectF getRect(){
        return rect;
    }
    public Bitmap getBitmap(){
        return bitmap;
    }
    public float getX(){
        return x;
    }
    public float getLength(){
        return length;
    }
    public int getShipMoving(){ return shipMoving;}

    public void setMovementState(int state){
        shipMoving = state;
    }

    // -------------- tahle metda nastavi pozice na urcena mistata,
    // -------------- Dela se to tak ze "shipSpeed" se odecita a pricita podle potreby, to se upravi pomoci "fps"
    // -------------- podelenim fps zajisti spravne nastaveni v case aby to nebylo rozsekane
    public void update(long fps){
//        System.out.println(" public void update(long fps){"+ x);
//        System.out.println(" public void update(long fps){"+ (x - shipSpeed / fps)+" "+ (x + shipSpeed / fps));

            float xx = 0;

            if(shipMoving == LEFT){
                xx = x - shipSpeed / fps;

                if(!(xx <=0)&&!(xx>= screenX)) {
                    x = xx;
                }

            }

            if(shipMoving == RIGHT){
                xx = x + shipSpeed / fps;

                if(!(xx <=0)&&!(xx>= screenX)) {
                    x = xx;
                }
            }

            rect.top = y;
            rect.bottom = y + height;
            rect.left = x;
            rect.right = x + length;

    }
}

