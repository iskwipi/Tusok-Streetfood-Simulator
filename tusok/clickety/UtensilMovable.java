package com.github.iskwipi.tusok.clickety;

import com.github.iskwipi.tusok.GamePanes;
import javafx.scene.Cursor;
import javafx.scene.shape.Shape;

public abstract class UtensilMovable extends MovableShape{
    public UtensilMovable(MovableName name,
                          Shape shape,
                          double centerX, double centerY,
                          boolean containable){
        super(MovableType.UTENSIL, name, shape, centerX, centerY, containable);
        this.makeClickable();
    }

    @Override
    public void makeClickable(){
        this.getShape().setOnMousePressed(mouseEvent -> {
            if(MovableShape.getFocusedMovables().isEmpty() ||
                    MovableShape.getFirstFocusedMovable().getType() == MovableType.UTENSIL){
                this.getShape().setMouseTransparent(true);
                MovableShape.addFocusedMovable(this);
                GamePanes.MAIN.getPane().setCursor(Cursor.CLOSED_HAND);
            }
        });
    }
}
