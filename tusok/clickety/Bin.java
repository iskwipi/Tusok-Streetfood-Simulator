package com.github.iskwipi.tusok.clickety;

import com.github.iskwipi.tusok.GamePanes;
import javafx.scene.Cursor;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

public class Bin extends ClickableShape{
    public Bin(Shape shape, Paint paint, double originalX, double originalY){
        super(shape, originalX, originalY);
        this.getShape().setFill(paint);
        this.makeClickable();
        this.getShape().setOnMouseEntered(mouseEvent -> {
            if(MovableShape.getFocusedMovables().isEmpty()){
                GamePanes.MAIN.getPane().setCursor(Cursor.DEFAULT);
            }
        });
    }

    @Override
    public void makeClickable(){
        this.getShape().setOnMousePressed(mouseEvent -> {
            if(!MovableShape.getFocusedMovables().isEmpty()){
                if(MovableShape.getFirstFocusedMovable().getType() == MovableType.CONTAINER){
                    ((ContainerMovable) MovableShape.getFirstFocusedMovable()).getItems().forEach(targetMovable -> {
                        targetMovable.getShape().toBack();
                        targetMovable.getShape().setMouseTransparent(false);
                        GamePanes.MAIN.getPane().getChildren().remove(targetMovable.getShape());
                    });
                }
                MovableShape.getFocusedMovables().forEach(movableShape -> {
                    movableShape.getShape().toBack();
                    movableShape.getShape().setMouseTransparent(false);
                    GamePanes.MAIN.getPane().getChildren().remove(movableShape.getShape());
                });
                MovableShape.resetFocusedMovables();
                GamePanes.MAIN.getPane().setCursor(Cursor.DEFAULT);
            }
        });
    }
}
