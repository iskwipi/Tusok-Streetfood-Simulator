package com.github.iskwipi.tusok.clickety;

import javafx.scene.shape.Shape;

public abstract class ClickableShape implements Clickable{
    private final Shape shape;
    private double originalX;
    private double originalY;

    public Shape getShape(){
        return shape;
    }

    public void setOriginalX(double originalX){
        this.originalX = originalX;
    }

    public double getOriginalX(){
        return originalX;
    }

    public void setOriginalY(double originalY){
        this.originalY = originalY;
    }

    public double getOriginalY(){
        return originalY;
    }

    public void reset(){
        this.shape.setTranslateX(this.originalX);
        this.shape.setTranslateY(this.originalY);
    }

    public ClickableShape(Shape shape){
        this.shape = shape;
    }

    public ClickableShape(Shape shape, double originalX, double originalY){
        this.shape = shape;
        this.originalX = originalX;
        this.originalY = originalY;
        this.getShape().setTranslateX(originalX);
        this.getShape().setTranslateY(originalY);
    }
}
