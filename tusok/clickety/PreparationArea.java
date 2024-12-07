package com.github.iskwipi.tusok.clickety;

import com.github.iskwipi.tusok.GamePanes;
import javafx.scene.Cursor;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

public class PreparationArea extends ClickableShape{
    public PreparationArea(Shape shape, Paint paint, double originalX, double originalY){
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
                    MovableShape.getFirstFocusedMovable().setOriginalX(MovableShape.getFirstFocusedMovable().getShape().getTranslateX());
                    MovableShape.getFirstFocusedMovable().setOriginalY(MovableShape.getFirstFocusedMovable().getShape().getTranslateY());
                    ((ContainerMovable) MovableShape.getFirstFocusedMovable()).getItems().forEach(targetMovable -> {
                        targetMovable.setOriginalX(targetMovable.getShape().getTranslateX());
                        targetMovable.setOriginalY(targetMovable.getShape().getTranslateY());
                    });
                    MovableShape.getFirstFocusedMovable().getShape().setMouseTransparent(false);
                    MovableShape.removeFirstFocusedMovable();
                }else if(MovableShape.getFirstFocusedMovable().getType() == MovableType.FOOD &&
                        MovableShape.getFirstFocusedMovable().getOriginalX() != 0 &&
                        MovableShape.getFirstFocusedMovable().getOriginalY() != 0){
                    MovableShape.getFirstFocusedMovable().reset();
                    ((FoodMovable) MovableShape.getFirstFocusedMovable()).getTimer().start();
                    MovableShape.getFirstFocusedMovable().getShape().setMouseTransparent(false);
                    MovableShape.removeFirstFocusedMovable();
                    if(MovableShape.getFocusedMovables().isEmpty()){
                        GamePanes.MAIN.getPane().setCursor(Cursor.DEFAULT);
                    }
                }else{
                    MovableShape.getFirstFocusedMovable().getShape().toBack();
                    GamePanes.MAIN.getPane().getChildren().remove(MovableShape.getFirstFocusedMovable().getShape());
                    MovableShape.getFirstFocusedMovable().getShape().setMouseTransparent(false);
                    MovableShape.removeFirstFocusedMovable();
                    if(MovableShape.getFocusedMovables().isEmpty()){
                        GamePanes.MAIN.getPane().setCursor(Cursor.DEFAULT);
                    }
                }
            }
        });
    }
}
