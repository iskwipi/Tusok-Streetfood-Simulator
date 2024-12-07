package com.github.iskwipi.tusok.clickety;

import com.github.iskwipi.tusok.GamePanes;
import com.github.iskwipi.tusok.Utilities;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

public class QuadrilateralContainerMovable extends ContainerMovable{
    private final double[] points;
    private final Paint paint;
    private final double opacity;
    private final double maxX;
    private final double maxY;
    private final double relativeCenterX;
    private final double relativeCenterY;

    public double[] getPoints(){
        return this.points;
    }

    public QuadrilateralContainerMovable(MovableName name,
                                         double[] points,
                                         Paint paint,
                                         double centerX, double centerY,
                                         double padding,
                                         double opacity,
                                         double maxX, double maxY,
                                         double relativeCenterX, double relativeCenterY){
        super(name, new Polygon(points), paint, centerX, centerY, padding);
        this.getShape().setOpacity(opacity);
        this.points = points;
        this.paint = paint;
        this.opacity = opacity;
        this.maxX = maxX;
        this.maxY = maxY;
        this.relativeCenterX = relativeCenterX;
        this.relativeCenterY = relativeCenterY;
    }

    @Override
    public void moveShape(double centerX, double centerY){
        double oldCenterX = this.getCenterX();
        double oldCenterY = this.getCenterY();
        this.setCenterX(Utilities.clamp(
                this.relativeCenterX,
                GamePanes.MAIN.getPane().getWidth() - (this.maxX - this.relativeCenterX),
                centerX
        ));
        this.setCenterY(Utilities.clamp(
                this.relativeCenterY,
                GamePanes.MAIN.getPane().getHeight() - (this.maxY - this.relativeCenterY),
                centerY
        ));
        if(this.getItems() != null){
            this.getItems().forEach(targetMovable -> targetMovable.moveShape(
                    this.getCenterX() + (targetMovable.getCenterX() - oldCenterX),
                    this.getCenterY() + (targetMovable.getCenterY() - oldCenterY)
            ));
        }
        this.getShape().setTranslateX(this.getCenterX() - this.relativeCenterX);
        this.getShape().setTranslateY(this.getCenterY() - this.relativeCenterY);
    }

    @Override
    public MovableShape clone(){
        return new QuadrilateralContainerMovable(
                this.getName(),
                this.points,
                this.paint,
                this.getCenterX(), this.getCenterY(),
                this.getPadding(),
                this.opacity,
                this.maxX, this.maxY,
                this.relativeCenterX, this.relativeCenterY
        );
    }
}
