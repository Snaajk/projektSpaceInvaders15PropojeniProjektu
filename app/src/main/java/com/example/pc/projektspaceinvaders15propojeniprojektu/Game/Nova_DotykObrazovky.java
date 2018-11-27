package com.example.pc.projektspaceinvaders15propojeniprojektu.Game;

import android.content.Context;
import android.view.MotionEvent;

public class Nova_DotykObrazovky {

    public boolean onTouchEvent(MotionEvent motionEvent, Context context, Stara__PlayerShip staraPlayerShip, Stara__Bullet staraBullet, int screenX, int screenY, boolean paused, Nova_GyroskopBullet nova_gyroskopBullet) {


        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {

            case MotionEvent.ACTION_DOWN:

                paused = false;

                if(motionEvent.getY() > screenY - screenY / 8) {
                    if (motionEvent.getX() > screenX / 2) {
                        staraPlayerShip.setMovementState(staraPlayerShip.RIGHT);
                    } else {
                        staraPlayerShip.setMovementState(staraPlayerShip.LEFT);
                    }
                }

                if(motionEvent.getY() < screenY - screenY / 8) {

                    if(staraBullet.shoot(staraPlayerShip.getX()+ staraPlayerShip.getLength()/2,screenY, staraBullet.UP, false, nova_gyroskopBullet)){
//                       Nova_Zvuk.getShoot(context);
                    }
                }
                break;

            // Hráč odstranil prst z obrazovky
            case MotionEvent.ACTION_UP:
                if(motionEvent.getY() > screenY - screenY / 10) {
                    staraPlayerShip.setMovementState(staraPlayerShip.STOPPED);
                }
                break;
        }
        return true;
    }



//    public boolean getPaused(){
//        return paused;
//    }
//
//
//    public int getScreenX() {
//        return screenX;
//    }
//
//    public int getScreenY() {
//        return screenY;
//    }
}
