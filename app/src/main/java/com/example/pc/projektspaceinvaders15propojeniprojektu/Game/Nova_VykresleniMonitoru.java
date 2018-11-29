package com.example.pc.projektspaceinvaders15propojeniprojektu.Game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

public class Nova_VykresleniMonitoru  {
    private Context context;
    private SurfaceHolder ourHolder;
    private Canvas canvas;
    private Paint paint;
    private int screenX;
    private int screenY;
    private Stara__PlayerShip staraPlayerShip;
    private Stara__Bullet staraBullet;
    private Stara__Bullet[] invadersStaraBullets = new Stara__Bullet[200];
    private Stara__Invader[] staraInvaders = new Stara__Invader[60];
    private int numInvaders = 0;
    private Stara__DefenceBrick[] bricks = new Stara__DefenceBrick[400];
    private int numBricks;
    private int score = 0;
    private int lives = 10;
    private boolean uhOrOh;
    private Nova_GyroskopBullet nova_gyroskopBullet;
    private Bitmap backgroudBitmap;

    public Nova_VykresleniMonitoru(Canvas canvas, Paint paint, SurfaceHolder ourHolder){


//        this.canvas = canvas;
//        this.paint = paint;
//        this.ourHolder = ourHolder;

    }

    public SurfaceHolder draw(SurfaceHolder ourHolder, Canvas canvas, Paint paint, Stara__PlayerShip staraPlayerShip,
                     Stara__Bullet staraBullet, Stara__Bullet[] invadersStaraBullets, Stara__Invader[] staraInvaders,
                     int numInvaders, int screenX, int screenY, int numBricks, Stara__DefenceBrick[] bricks, Context context,
                     boolean uhOrOh){

        this.ourHolder = ourHolder;
        this.canvas = canvas;
        this.paint = paint;
        this.staraPlayerShip = staraPlayerShip;
        this.staraBullet =staraBullet;
        this.invadersStaraBullets = invadersStaraBullets;
        this.staraInvaders = staraInvaders;
        this.numInvaders = numInvaders;
        this.screenX = screenX;
        this.screenY = screenY;
        this.numBricks = numBricks;
        this.bricks = bricks;
        this.context = context;
        this.uhOrOh = uhOrOh;

        if (ourHolder.getSurface().isValid()) {
            canvas = ourHolder.lockCanvas();
            canvas.drawColor(Color.argb(255, 126, 28, 182));
            setFoto(context, canvas, paint);
            paint.setColor(Color.argb(255,  255, 255, 255));
            canvas.drawBitmap(staraPlayerShip.getBitmap(), staraPlayerShip.getX(), screenY - 50, paint);

            for(int i = 0; i < numInvaders; i++){
                if(staraInvaders[i].getVisibility()) {
                    if(uhOrOh) {
                        canvas.drawBitmap(staraInvaders[i].getBitmap(), staraInvaders[i].getX(), staraInvaders[i].getY(), paint);
                    }else{
                        canvas.drawBitmap(staraInvaders[i].getBitmap2(), staraInvaders[i].getX(), staraInvaders[i].getY(), paint);
                    }
                }
            }

            // Nakreslete cihly, pokud jsou viditelné
            for(int i = 0; i < numBricks; i++){
                if(bricks[i].getVisibility()) {
                    canvas.drawRect(bricks[i].getRect(), paint);
                }
            }
            // zobrazeni pratelskych kulek
            if(staraBullet.getStatus()){
                canvas.drawRect(staraBullet.getRect(), paint);
            }

            for(int i = 0; i < invadersStaraBullets.length; i++){
                if(invadersStaraBullets[i].getStatus()) {
                    canvas.drawRect(invadersStaraBullets[i].getRect(), paint);
                }
            }

            paint.setColor(Color.argb(255,  249, 129, 0));
            paint.setTextSize(screenX/24);
//            canvas.drawText("Score: " + score + "   Lives: " + lives+ "Y: " +   nova_gyroskopBullet.getYSenzor(), 10,70, paint);


             ourHolder.unlockCanvasAndPost(canvas);
        }
        return ourHolder;
    }


    private void setFoto(Context context, Canvas canvas, Paint paint){                                        //za4.

//        Bitmap bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.obrazek1);
       /* backgroudBitmap = Bitmap.createScaledBitmap(backgroudBitmap,(int) screenX,(int) screenY,false);
        float i =0.0f, y = 0.0f;
        canvas.drawBitmap(backgroudBitmap, i, y, paint);*/
    }

    public void setPicture(Bitmap bitmap){
        this.backgroudBitmap = bitmap;
    }

    public void vypisUpdate(int score, int lives, Nova_GyroskopBullet nova_gyroskopBullet){
        this.score = score;
        this.lives = lives;
        this.nova_gyroskopBullet = nova_gyroskopBullet;
        draw(ourHolder, canvas, paint, staraPlayerShip, staraBullet, invadersStaraBullets, staraInvaders, numInvaders, screenX, screenY, numBricks, bricks, context, uhOrOh);

    }
}
