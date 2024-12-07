package com.github.iskwipi.tusok.clickety;

import com.github.iskwipi.tusok.GamePanes;
import com.github.iskwipi.tusok.Utilities;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class RectangleUtensilMovable extends UtensilMovable{
    private final double width;
    private final double length;
    private final Paint paint;

    public RectangleUtensilMovable(MovableName name,
                                   double width, double length,
                                   Paint paint,
                                   double centerX, double centerY,
                                   boolean containable){
        super(name, new Rectangle(width, length, paint),
                centerX, centerY, containable);
        this.width = width;
        this.length = length;
        this.paint = paint;
    }

    @Override
    public void moveShape(double centerX, double centerY){
        this.setCenterX(Utilities.clamp(
                this.width / 2,
                GamePanes.MAIN.getPane().getWidth() - this.width / 2,
                centerX
        ));
        this.setCenterY(Utilities.clamp(
                this.length / 2,
                GamePanes.MAIN.getPane().getHeight() - this.length / 2,
                centerY
        ));
        this.getShape().setTranslateX(this.getCenterX() - this.width / 2);
        this.getShape().setTranslateY(this.getCenterY() - this.length / 2);
    }

    @Override
    public MovableShape clone(){
        return new RectangleUtensilMovable(
                this.getName(),
                this.width, this.length,
                this.paint,
                this.getCenterX(), this.getCenterY(),
                this.isContainable()
        );
    }
}
