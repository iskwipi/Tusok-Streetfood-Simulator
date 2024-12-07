package com.github.iskwipi.tusok.clickety;

public class Line extends javafx.scene.shape.Line{
    private final double slope;

    public Line(double startX, double startY,
                double endX, double endY){
        super(startX, startY, endX, endY);
        slope = (endY - startY) / (endX - startX);
    }

    public double getXFromY(double y){
        return this.getStartX() + (y - this.getStartY()) / slope;
    }

    public double getYFromX(double x){
        return this.getStartY() + (x - this.getStartX()) * slope;
    }
}
