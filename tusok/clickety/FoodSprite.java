package com.github.iskwipi.tusok.clickety;

import javafx.scene.paint.Paint;

public class FoodSprite{
    private final Paint paint;
    private final long seconds;
    private final boolean containable;

    public Paint getPaint(){
        return this.paint;
    }

    public double getSeconds(){
        return seconds;
    }

    public boolean isContainable(){
        return containable;
    }

    public FoodSprite(Paint paint, long seconds, boolean containable){
        this.paint = paint;
        this.seconds = seconds;
        this.containable = containable;
    }
}
