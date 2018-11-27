package com.example.pc.projektspaceinvaders15propojeniprojektu.Game;

import android.graphics.RectF;

public class Stara__DefenceBrick {
    private RectF rect;

    private boolean isVisible;

    public Stara__DefenceBrick(int row, int column, int shelterNumber, int screenX, int screenY){
        int width = screenX / 70;
        int height = screenY / 50;
        isVisible = true;
        int brickPadding = 1;
        int shelterPadding = screenX / 15;
        int startHeight = screenY - (screenY /8 * 2);

        rect = new RectF(
                column * width
                        + brickPadding
                        + (shelterPadding * shelterNumber)
                        + shelterPadding
                        + shelterPadding * shelterNumber
                ,
                row * height
                        + brickPadding
                        + startHeight
                ,
                column * width
                        + width - brickPadding
                        + (shelterPadding * shelterNumber)
                        + shelterPadding + shelterPadding * shelterNumber
                ,
                row * height
                        + height - brickPadding
                        + startHeight);
    }

    public RectF getRect(){
        return this.rect;
    }

    public void setInvisible(){
        isVisible = false;
    }

    public boolean getVisibility(){
        return isVisible;
    }
}
