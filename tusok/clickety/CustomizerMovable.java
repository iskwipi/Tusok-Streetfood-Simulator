package com.github.iskwipi.tusok.clickety;

import com.github.iskwipi.tusok.GamePanes;
import javafx.scene.Cursor;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

public abstract class CustomizerMovable extends MovableShape{
    private final Paint paintEdit;

    public Paint getPaintEdit(){
        return this.paintEdit;
    }

    public CustomizerMovable(MovableName name,
                             Shape shape,
                             Paint paint,
                             double centerX, double centerY,
                             Paint paintEdit){
        super(MovableType.CUSTOMIZER, name, shape, centerX, centerY, false);
        this.getShape().setFill(paint);
        this.paintEdit = paintEdit;
        this.makeClickable();
    }

    @Override
    public void makeClickable(){
        this.getShape().setOnMousePressed(mouseEvent -> {
            if(MovableShape.getFocusedMovables().isEmpty()){
                this.getShape().setMouseTransparent(true);
                MovableShape.addFocusedMovable(this);
                GamePanes.MAIN.getPane().setCursor(Cursor.CLOSED_HAND);
            }
        });
    }
}