package com.github.iskwipi.tusok.clickety;

import com.github.iskwipi.tusok.GamePanes;
import com.github.iskwipi.tusok.Utilities;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

public class PolygonCustomizerMovable extends CustomizerMovable{
    private final double[] points;
    private final Paint paint;
    private final double maxX;
    private final double maxY;
    private final double relativeCenterX;
    private final double relativeCenterY;

    public PolygonCustomizerMovable(MovableName name,
                                    double[] points,
                                    Paint paint,
                                    double centerX, double centerY,
                                    double maxX, double maxY,
                                    double relativeCenterX, double relativeCenterY,
                                    Paint paintEdit){
        super(name, new Polygon(points), paint, centerX, centerY, paintEdit);
        this.points = points;
        this.paint = paint;
        this.maxX = maxX;
        this.maxY = maxY;
        this.relativeCenterX = relativeCenterX;
        this.relativeCenterY = relativeCenterY;
    }

    @Override
    public void moveShape(double centerX, double centerY){
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
        this.getShape().setTranslateX(this.getCenterX() - this.relativeCenterX);
        this.getShape().setTranslateY(this.getCenterY() - this.relativeCenterY);
    }

    @Override
    public MovableShape clone(){
        return new PolygonCustomizerMovable(
                this.getName(),
                this.points,
                this.paint,
                this.getCenterX(), this.getCenterY(),
                this.maxX, this.maxY,
                this.relativeCenterX, this.relativeCenterY,
                this.getPaintEdit()
        );
    }
}
