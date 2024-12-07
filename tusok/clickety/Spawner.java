package com.github.iskwipi.tusok.clickety;

import com.github.iskwipi.tusok.GamePanes;
import com.github.iskwipi.tusok.Utilities;
import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

public class Spawner extends ClickableShape{
    private final MovableShape spawn;

    public Spawner(Shape shape,
                   Paint paint,
                   double originalX, double originalY,
                   MovableShape spawn){
        super(shape, originalX, originalY);
        this.getShape().setFill(paint);
        this.spawn = spawn;
        this.makeClickable();
        this.getShape().setOnMouseEntered(mouseEvent -> {
            if(MovableShape.getFocusedMovables().isEmpty()){
                GamePanes.MAIN.getPane().setCursor(Cursor.OPEN_HAND);
            }
        });
    }

    @Override
    public void makeClickable(){
        this.getShape().setOnMousePressed(mouseEvent -> {
            if(MovableShape.getFocusedMovables().isEmpty() ||
                    this.spawn.getType() != MovableType.CONTAINER &&
                    this.spawn.getType() != MovableType.CUSTOMIZER &&
                    MovableShape.getFirstFocusedMovable().getType() == this.spawn.getType() &&
                    MovableShape.getFirstFocusedMovable().isContainable() == this.spawn.isContainable()){
                MovableShape spawn = this.spawn.clone();
                spawn.moveShape(mouseEvent.getSceneX(), mouseEvent.getSceneY());
                GamePanes.MAIN.getPane().getChildren().add(spawn.getShape());
                MouseEvent.fireEvent(spawn.getShape(),
                        Utilities.createMouseEvent(
                                MouseEvent.MOUSE_PRESSED,
                                MouseButton.PRIMARY,
                                1,
                                mouseEvent.getSceneX(),
                                mouseEvent.getSceneY()
                        )
                );
            }else if(!MovableShape.getFocusedMovables().isEmpty()){
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
                    GamePanes.MAIN.getPane().setCursor(Cursor.OPEN_HAND);
                }
            }
        });
    }
}
