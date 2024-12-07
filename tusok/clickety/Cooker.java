package com.github.iskwipi.tusok.clickety;

import com.github.iskwipi.tusok.GamePanes;
import javafx.scene.Cursor;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

public class Cooker extends ClickableShape{
    private final Shape cover;

    public Shape getCover(){
        return this.cover;
    }

    public Cooker(Shape shape, Paint paint, double originalX, double originalY,
                  Shape coverShape, Paint coverPaint, double coverX, double coverY){
        super(shape, originalX, originalY);
        this.getShape().setFill(paint);
        this.cover = coverShape;
        this.cover.setFill(coverPaint);
        this.cover.setTranslateX(coverX);
        this.cover.setTranslateY(coverY);
        this.cover.setMouseTransparent(true);
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
                if(MovableShape.getFirstFocusedMovable().getType() == MovableType.FOOD){
                    MovableShape.getFirstFocusedMovable().setOriginalX(MovableShape.getFirstFocusedMovable().getShape().getTranslateX());
                    MovableShape.getFirstFocusedMovable().setOriginalY(MovableShape.getFirstFocusedMovable().getShape().getTranslateY());
                    ((FoodMovable) MovableShape.getFirstFocusedMovable()).getTimer().start();
                    MovableShape.getFirstFocusedMovable().getShape().setMouseTransparent(false);
                    MovableShape.removeFirstFocusedMovable();
                    this.cover.toFront();
                    if(!MovableShape.getFocusedMovables().isEmpty()){
                        MovableShape.getFocusedMovables().forEach(targetMovable -> targetMovable.getShape().toFront());
                    }
                }else if(MovableShape.getFirstFocusedMovable().getOriginalX() != 0 &&
                        MovableShape.getFirstFocusedMovable().getOriginalY() != 0){
                    if(MovableShape.getFirstFocusedMovable().getType() == MovableType.CONTAINER){
                        ((ContainerMovable) MovableShape.getFirstFocusedMovable()).getItems().forEach(MovableShape::reset);
                    }
                    MovableShape.getFirstFocusedMovable().reset();
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
