package com.github.iskwipi.tusok.clickety;

import com.github.iskwipi.tusok.GamePanes;
import com.github.iskwipi.tusok.Utilities;
import javafx.scene.shape.Ellipse;

public class EllipseFoodMovable extends FoodMovable{
    private final double radiusX;
    private final double radiusY;

    public EllipseFoodMovable(MovableName name,
                              double radiusX, double radiusY,
                              double centerX, double centerY,
                              FoodSprite[] sprites,
                              int startingSpriteIndex){
        super(name, new Ellipse(radiusX, radiusY),
                centerX, centerY, sprites, startingSpriteIndex);
        this.radiusX = radiusX;
        this.radiusY = radiusY;
    }

    @Override
    public void moveShape(double centerX, double centerY){
        this.setCenterX(Utilities.clamp(
                this.radiusX,
                GamePanes.MAIN.getPane().getWidth() - this.radiusX,
                centerX
        ));
        this.setCenterY(Utilities.clamp(
                this.radiusY,
                GamePanes.MAIN.getPane().getHeight() - this.radiusY,
                centerY
        ));
        this.getShape().setTranslateX(this.getCenterX());
        this.getShape().setTranslateY(this.getCenterY());
    }

    @Override
    public MovableShape clone(){
        return new EllipseFoodMovable(
                this.getName(),
                this.radiusX, this.radiusY,
                this.getCenterX(), this.getCenterY(),
                this.getSprites(),
                this.getStartingSpriteIndex()
        );
    }
}
