package com.github.iskwipi.tusok.clickety;

import com.github.iskwipi.tusok.GamePanes;
import javafx.scene.Cursor;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Background extends ClickableShape{
    public Background(double width, double height, Paint paint){
        super(new Rectangle(width, height, paint));
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
                if(MovableShape.getFirstFocusedMovable().getOriginalX() != 0 &&
                        MovableShape.getFirstFocusedMovable().getOriginalY() != 0){
                    if(MovableShape.getFirstFocusedMovable().getType() == MovableType.FOOD){
                        ((FoodMovable) MovableShape.getFirstFocusedMovable()).getTimer().start();
                    }else if(MovableShape.getFirstFocusedMovable().getType() == MovableType.CONTAINER){
                        ((ContainerMovable) MovableShape.getFirstFocusedMovable()).getItems().forEach(MovableShape::reset);
                    }
                    MovableShape.getFirstFocusedMovable().reset();
                }else{
                    MovableShape.getFirstFocusedMovable().getShape().toBack();
                    GamePanes.MAIN.getPane().getChildren().remove(MovableShape.getFirstFocusedMovable().getShape());
                }
                MovableShape.getFirstFocusedMovable().getShape().setMouseTransparent(false);
                MovableShape.removeFirstFocusedMovable();
                if(MovableShape.getFocusedMovables().isEmpty()){
                    GamePanes.MAIN.getPane().setCursor(Cursor.DEFAULT);
                }
            }
        });
    }
}
