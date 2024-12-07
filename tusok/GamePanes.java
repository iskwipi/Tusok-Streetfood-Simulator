package com.github.iskwipi.tusok;

import com.github.iskwipi.tusok.clickety.ContainerMovable;
import com.github.iskwipi.tusok.clickety.MovableShape;
import com.github.iskwipi.tusok.clickety.MovableType;
import javafx.scene.Cursor;
import javafx.scene.layout.Pane;

public enum GamePanes{
    MAIN(501, 732){{
        this.getPane().setOnMouseMoved(mouseEvent -> {
            if(!MovableShape.getFocusedMovables().isEmpty()){
                this.getPane().setCursor(Cursor.CLOSED_HAND);
                if(MovableShape.getFirstFocusedMovable().getType() == MovableType.CONTAINER){
                    ((ContainerMovable) MovableShape.getFirstFocusedMovable()).getItems().forEach(targetMovable -> targetMovable.getShape().toFront());
                }
                MovableShape.getFocusedMovables().forEach(movableShape -> {
                    movableShape.getShape().toFront();
                    movableShape.moveShape(
                            mouseEvent.getSceneX(),
                            mouseEvent.getSceneY()
                    );
                });
            }
        });
    }};

    private final Pane pane;

    public Pane getPane(){
        return this.pane;
    }

    GamePanes(double width, double height){
        this.pane = new Pane();
        this.pane.setPrefSize(width, height);
        this.pane.setMaxWidth(width);
        this.pane.setMaxHeight(height);
    }
}
