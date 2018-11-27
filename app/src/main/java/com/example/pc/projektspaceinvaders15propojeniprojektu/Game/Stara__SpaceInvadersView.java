/*
naše vlákno                                                                                         //za1.
SurfaceHolder uzamkne povrch předtím, než nakreslíme grafiku                                        //za2.
Boolean, který budeme nastavovat a deaktivovat když hra běží - nebo ne.                             //za3.
pauza                                                                                               //za4.
Canvas and a Paint object                                                                           //za5.
Tato proměnná sleduje rychlost hry                                                                  //za6.
slouží k výpočtu fps                                                                                //za7.
size of the screen in pixels                                                                        //za8.
hracova lod                                                                                         //za9.
hracova kulka                                                                                       //za10.
Útočící kuličky                                                                                     //za11.
60 útočníků                                                                                         //za12.
Přístřešky hráče                                                                                    //za13.
score                                                                                               //za14.
životy                                                                                              //za15.
Jak hrozný by měl být zvuk?                                                                         //za16.         // zvuk
Který zvuk ohrožení by měl hrát dál                                                                 //za17.         // zvuk
Kdy jsme naposledy hráli hrozivý zvuk                                                               //za18.         // zvuk
konstruktor                                                                                         //za19.


*/



package com.example.pc.projektspaceinvaders15propojeniprojektu.Game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.media.ThumbnailUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.pc.projektspaceinvaders15propojeniprojektu.MainActivity;
import com.example.pc.projektspaceinvaders15propojeniprojektu.R;

import java.util.ArrayList;

public class Stara__SpaceInvadersView extends SurfaceView implements Runnable{

    Context context;
    private Thread gameThread = null;                                                               //za1.
    private SurfaceHolder ourHolder;                                                                //za2.
    private volatile boolean playing;                                                               //za3.
    private boolean paused = true;                                                                  //za4.
    private Canvas canvas;                                                                          //za5.
    private Paint paint;
    private long fps;                                                                               //za6.
    private long timeThisFrame;                                                                     //za7.
    private int screenX;                                                                            //za8.
    private int screenY;
    private Stara__PlayerShip staraPlayerShip;                                                                  //za9.
   /* private Stara__Bullet staraBullet;  */                                                                        //za10.
    private ArrayList<Stara__Bullet> bulletsPlayShip = new ArrayList<>();
    private Stara__Bullet[] invadersStaraBullets = new Stara__Bullet[200];                                             //za11.
    private int nextBullet;
    private int maxInvaderBullets = 100;
    private Stara__Invader[] staraInvaders = new Stara__Invader[60];                                                   //za12.
    private int numInvaders = 0;
    private Stara__DefenceBrick[] bricks = new Stara__DefenceBrick[400];                                          //za13.
    private int numBricks;
    private int score = 0;                                                                          //za14.
    private int lives = 3;                                                                         //za15.
    private long menaceInterval = 1000;                                                             //za16.
    private boolean uhOrOh;                                                                         //za17.
    private long lastMenaceTime = System.currentTimeMillis();                                       //za18.
    private Nova_GyroskopBullet nova_gyroskopBullet;
    private Nova_VykresleniMonitoru drawMonitor;


    public Stara__SpaceInvadersView(Context context, int x, int y, MainActivity mActivity) {        //za19.
        super(context);


        this.context = context;


        ourHolder = getHolder();
        paint = new Paint();

        screenX = x;
        screenY = y;


        nova_gyroskopBullet= new Nova_GyroskopBullet(mActivity);


        prepareLevel();
        surfaceView();


    }

    private void surfaceView(){
        Bitmap bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.obrazek1a);

        System.out.println("before cropped height " + bitmap1.getHeight() + "and width: " + bitmap1.getWidth());
        Bitmap had = ThumbnailUtils.extractThumbnail(bitmap1, 200, 150);
        System.out.println("after cropped height "+had.getHeight() +"and width: " + had.getWidth());

        drawMonitor.setPicture(had);
    }

    private void prepareLevel(){

        staraPlayerShip = new Stara__PlayerShip(context, screenX, screenY);

        /////////////////////=======================================kulka zacatek------------------
        bulletsPlayShip.add(new Stara__Bullet(screenY, canvas));
      /*  bulletsPlayShip.add(new Stara__Bullet(screenY, canvas));
         bulletsPlayShip.add(new Stara__Bullet(screenY, canvas));
        bulletsPlayShip.add(new Stara__Bullet(screenY, canvas));
       bulletsPlayShip.add(new Stara__Bullet(screenY, canvas));*/
        /////////////////////=======================================kulka konec------------------


        for(int i = 0; i < invadersStaraBullets.length; i++){
            invadersStaraBullets[i] = new Stara__Bullet(screenY);
        }

        nepratele();
        vykresleniKostek();

       drawMonitor = new Nova_VykresleniMonitoru(canvas,paint,ourHolder);
        menaceInterval = 1000;

    }




    private void vykresleniKostek(){

        numBricks = 0;
        for(int shelterNumber = 0; shelterNumber < 8; shelterNumber++){
            for(int column = 0; column < 5; column ++ ) {
                for (int row = 0; row < 5; row++) {
                    bricks[numBricks] = new Stara__DefenceBrick(row, column, shelterNumber, screenX, screenY);
                    numBricks++;
                }
            }
        }
    }

    private void nepratele(){
        numInvaders = 0;
        for(int column = 0; column < 6; column ++ ){
            for(int row = 0; row < 5; row ++ ){
                staraInvaders[numInvaders] = new Stara__Invader(context, row, column, screenX, screenY);
                numInvaders ++;
            }
        }
    }

    @Override
    public void run() {
        while (playing) {

            long startFrameTime = System.currentTimeMillis();

            if(!paused){
                update();
            }

            draw();

            timeThisFrame = System.currentTimeMillis() - startFrameTime;
            if (timeThisFrame >= 1) {
                fps = 500 / timeThisFrame;
            }
            if(!paused) {
                if ((startFrameTime - lastMenaceTime)> menaceInterval) {
                    if (uhOrOh) {
                        Nova_Zvuk_2.getUh(context);

                    } else {
                        Nova_Zvuk_2.getOh(context);
                    }
                    lastMenaceTime = System.currentTimeMillis();
                    uhOrOh = !uhOrOh;
                }
            }


        }



    }

    private void update(){
        boolean bumped = false;
        boolean lost = false;
        staraPlayerShip.update(fps);

        /////////////////////=======================================kulka zacatek------------------
        for(Stara__Bullet bullet: bulletsPlayShip){
            if(bullet.getStatus()){
                bullet.update(fps);
            }
        }
        /////////////////////=======================================kulka konec------------------

        bumped = updateInviders(bumped);
        updateDedInvideres(bumped, lost);

        if(lost){
            prepareLevel();
        }

        /////////////////////=======================================kulka zacatek------------------
        for(Stara__Bullet bullet: bulletsPlayShip){
            if(bullet.getImpactPointY() < 0){
                bullet.setInactive();
            }
        }
        /////////////////////=======================================kulka konec------------------

        for(int i = 0; i < invadersStaraBullets.length; i++){

            if(invadersStaraBullets[i].getImpactPointY() > screenY){
                invadersStaraBullets[i].setInactive();
            }
        }

        updateDeth();
        updateKolizeKulkaLod();
        updateKulkaHraceKostka();
        updateOdpocetZivotu();
    }

    private void updateOdpocetZivotu(){

        for(int i = 0; i < invadersStaraBullets.length; i++){
            if(invadersStaraBullets[i].getStatus()){
                if(RectF.intersects(staraPlayerShip.getRect(), invadersStaraBullets[i].getRect())){
                    invadersStaraBullets[i].setInactive();
                    lives --;
                    Nova_Zvuk_2.getPlayerExplode(context);

                    if(lives == 0){
                        paused = true;
                        lives = 3;
                        score = 0;
                        prepareLevel();
                    }
                }
            }
        }
    }

    private void updateKulkaHraceKostka(){
        /////////////////////=======================================kulka zacatek------------------

        for(Stara__Bullet bullet: bulletsPlayShip){
            if(bullet.getStatus()){
                for(int i = 0; i < numBricks; i++){
                    if(bricks[i].getVisibility()){
                        if(RectF.intersects(bullet.getRect(), bricks[i].getRect())){
                            bullet.setInactive();
                            bricks[i].setInvisible();
                        Nova_Zvuk_2.getDamageShelter(context);
                        }
                    }
                }
            }
        }

        /////////////////////=======================================kulka konec------------------
    }

    private void updateKolizeKulkaLod(){
        for(int i = 0; i < invadersStaraBullets.length; i++){
            if(invadersStaraBullets[i].getStatus()){
                for(int j = 0; j < numBricks; j++){
                    if(bricks[j].getVisibility()){
                        if(RectF.intersects(invadersStaraBullets[i].getRect(), bricks[j].getRect())){
                            // A collision has occurred
                            invadersStaraBullets[i].setInactive();
                            bricks[j].setInvisible();
                            Nova_Zvuk_2.getDamageShelter(context);
                        }
                    }
                }
            }
        }
    }

    private void updateDeth(){
        /////////////////////=======================================kulka zacatek------------------

        for(Stara__Bullet bullet: bulletsPlayShip){
            if(bullet.getStatus()) {
                for (int i = 0; i < numInvaders; i++) {
                    if (staraInvaders[i].getVisibility()) {
                        if (RectF.intersects(bullet.getRect(), staraInvaders[i].getRect())) {
                            staraInvaders[i].setInvisible();
                            Nova_Zvuk_2.getInvaderExplode(context);
                            bullet.setInactive();
                            score = score + 10;

                            if(score == numInvaders * 10){
                                paused = true;
                                score = 0;

                                prepareLevel();
                            }
                        }
                    }
                }
            }
        }

        /////////////////////=======================================kulka konec------------------
    }


    private void updateDedInvideres(boolean bumped, boolean lost){

        if(bumped){
            for(int i = 0; i < numInvaders; i++){
                staraInvaders[i].dropDownAndReverse();

                if(staraInvaders[i].getY() > screenY - screenY / 10){
                    lost = true;
                }
            }

            menaceInterval = menaceInterval - 80;
        }
    }

    private boolean updateInviders(boolean bumped){
        for(int i = 0; i < invadersStaraBullets.length; i++){
            if(invadersStaraBullets[i].getStatus()) {
                invadersStaraBullets[i].update(fps);
            }
        }

        for(int i = 0; i < numInvaders; i++){

            if(staraInvaders[i].getVisibility()) {

                staraInvaders[i].update(fps);

                if(staraInvaders[i].takeAim(staraPlayerShip.getX(), staraPlayerShip.getLength())){

                    /////////////////////=======================================kulka zacatek------------------

                    for(Stara__Bullet bullet: bulletsPlayShip){
                        if(invadersStaraBullets[nextBullet].shoot(staraInvaders[i].getX() + staraInvaders[i].getLength() / 2, staraInvaders[i].getY(), bullet.DOWN,false,nova_gyroskopBullet)) {

                            nextBullet++;

                            if (nextBullet == maxInvaderBullets) {
                                nextBullet = 0;
                            }
                        }
                    }

                    /////////////////////=======================================kulka konec------------------
                }

                if (staraInvaders[i].getX() > screenX - staraInvaders[i].getLength()|| staraInvaders[i].getX() < 0){
                    bumped = true;
                }
            }
        }
        return bumped;
    }

// ---------------------------------------------------------------------------- moje trida zacatek funguje
    // tato trida se vola ze vnitr funkce
    private void draw() {

        /////////////////////=======================================kulka zacatek------------------

        for(Stara__Bullet bullet: bulletsPlayShip){
            // If so try and spawn a staraBullet
            drawMonitor.draw(ourHolder, canvas, paint, staraPlayerShip, bullet, invadersStaraBullets, staraInvaders, numInvaders, screenX, screenY, numBricks, bricks,context,uhOrOh);

        }

        /////////////////////=======================================kulka konec------------------

        drawMonitor.vypisUpdate(score,lives, nova_gyroskopBullet);
    }
// ---------------------------------------------------------------------------- moje trida konec funguje

    public void pause() {

        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            Log.e("Error:", "joining thread");
        }
    }

    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {

            case MotionEvent.ACTION_DOWN:

                paused = false;

                if(motionEvent.getY() > screenY - screenY / 8) {

//                    System.out.println("staraPlayerShip.getX()"+ staraPlayerShip.getX());



                        if (motionEvent.getX() > screenX / 2) {

                                staraPlayerShip.setMovementState(staraPlayerShip.RIGHT);

                        } else {
                            staraPlayerShip.setMovementState(staraPlayerShip.LEFT);
                        }
                }

                if(motionEvent.getY() < screenY - screenY / 8) {

                            if(bulletsPlayShip.get(0).shoot(staraPlayerShip.getX()+ staraPlayerShip.getLength()/2,screenY, bulletsPlayShip.get(0).UP,true,nova_gyroskopBullet)){
                        Nova_Zvuk_2.getShoot(context);

                            }
                }

                break;

            case MotionEvent.ACTION_UP:
                if(motionEvent.getY() > screenY - screenY / 10) {
                    staraPlayerShip.setMovementState(staraPlayerShip.STOPPED);
                }
                break;
        }
//        System.out.println("onTouchEvent 2  " + screenX+" "+ screenY+ " "+ paused+" "+motionEvent.getY()+" "+ staraPlayerShip.getShipMoving());
        return true;
    }
}

/*
 //za1.
// This is our thread
// Toto je naše vlákno

//za2.
// Our SurfaceHolder to lock the surface before we draw our graphics
// Náš SurfaceHolder uzamkne povrch předtím, než nakreslíme grafiku

//za3.
// A boolean which we will set and unset
// when the game is running- or not.
// Boolean, který budeme nastavovat a deaktivovat
// když hra běží - nebo ne.

//za4.
// Game is paused at the start
// pauza

//za5.
// A Canvas and a Paint object

//za6.
// This variable tracks the game frame rate
// Tato proměnná sleduje rychlost hry

//za7.
// This is used to help calculate the fps
// slouží k výpočtu fps

//za8.
// The size of the screen in pixels

//za9.
// The players ship
// hracova lod

//za10.
// The player's staraBullet
// hracova kulka

//za11.
    // The staraInvaders bullets
    // Útočící kuličky

 //za12.
       // Up to 60 staraInvaders
    // Až 60 útočníků

 //za13.
    // The player's shelters are built from bricks
    // Přístřešky hráče jsou postaveny z cihel

 //za16.
       // How menacing should the sound be?
    // Jak hrozný by měl být zvuk?

 //za17.
    // Which menace sound should play next
    // Který zvuk ohrožení by měl hrát dál

  //za18.
        // When did we last play a menacing sound
    //Kdy jsme naposledy hráli hrozivý zvuk

  //za19.
   // When the we initialize (call new()) on gameView
    // This special constructor method runs
    // Když inicializujeme (zavoláme nový ()) na gameView
    // Tato metoda speciálního konstruktoru běží

*/
