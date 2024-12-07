package com.github.iskwipi.tusok.clickety;

import com.github.iskwipi.tusok.GamePanes;
import javafx.scene.Cursor;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public abstract class ContainerMovable extends MovableShape{
    private final ArrayList<MovableShape> items;
    private final double padding;

    public ArrayList<MovableShape> getItems(){
        return this.items;
    }

    public double getPadding(){
        return this.padding;
    }

    public ContainerMovable(MovableName name,
                            Shape shape,
                            Paint paint,
                            double centerX, double centerY,
                            double padding){
        super(MovableType.CONTAINER, name, shape, centerX, centerY, false);
        this.getShape().setFill(paint);
        this.padding = padding;
        this.items = new ArrayList<>();
        this.makeClickable();
    }

    @Override
    public void makeClickable(){
        this.getShape().setOnMousePressed(mouseEvent -> {
            if(MovableShape.getFocusedMovables().isEmpty()) {
                this.getShape().setMouseTransparent(true);
                MovableShape.addFocusedMovable(this);
                GamePanes.MAIN.getPane().setCursor(Cursor.CLOSED_HAND);
            }else if(MovableShape.getFirstFocusedMovable().getType() == MovableType.CUSTOMIZER){
                this.getShape().setFill(((CustomizerMovable) MovableShape.getFirstFocusedMovable()).getPaintEdit());
                this.items.removeIf(movableShape -> movableShape.getType() == MovableType.CUSTOMIZER);
                this.items.add(MovableShape.getFirstFocusedMovable());
                MovableShape.getFirstFocusedMovable().getShape().toBack();
                GamePanes.MAIN.getPane().getChildren().remove(MovableShape.getFirstFocusedMovable().getShape());
                MovableShape.getFirstFocusedMovable().getShape().setMouseTransparent(false);
                MovableShape.removeFirstFocusedMovable();
                GamePanes.MAIN.getPane().setCursor(Cursor.OPEN_HAND);
            }else if(MovableShape.getFirstFocusedMovable().isContainable()){
                if(this.getClass() == QuadrilateralContainerMovable.class){
                    double[] points = ((QuadrilateralContainerMovable) this).getPoints();
                    Line rightBoundary = new Line(points[2], points[3], points[4], points[5]);
                    Line bottomBoundary = new Line(points[4], points[5], points[6], points[7]);
                    Line leftBoundary = new Line(points[6], points[7], points[0], points[1]);
                    double xOffset;
                    double yOffset;
                    switch(MovableShape.getFirstFocusedMovable().getType()){
                        case FOOD:
                            xOffset = ((Ellipse) MovableShape.getFirstFocusedMovable().getShape()).getRadiusX();
                            yOffset = ((Ellipse) MovableShape.getFirstFocusedMovable().getShape()).getRadiusY();
                            break;
                        case UTENSIL:
                            xOffset = ((Rectangle) MovableShape.getFirstFocusedMovable().getShape()).getWidth() / 2;
                            yOffset = ((Rectangle) MovableShape.getFirstFocusedMovable().getShape()).getHeight() / 2;
                            break;
                        default:
                            xOffset = 0;
                            yOffset = 0;
                            break;
                    }
                    for(int i = -1; i <= 1; i++){
                        double difference = (MovableShape.getFirstFocusedMovable().getCenterX() + xOffset) - this.getOriginalX() -
                                rightBoundary.getXFromY(MovableShape.getFirstFocusedMovable().getCenterY() - this.getOriginalY() + yOffset * i) +
                                padding;
                        if(difference > 0){
                            MovableShape.getFirstFocusedMovable().moveShape(
                                    MovableShape.getFirstFocusedMovable().getCenterX() - difference,
                                    MovableShape.getFirstFocusedMovable().getCenterY()
                            );
                        }
                    }
                    for(int i = -1; i <= 1; i++){
                        double difference = (MovableShape.getFirstFocusedMovable().getCenterX() - xOffset) - this.getOriginalX() -
                                leftBoundary.getXFromY(MovableShape.getFirstFocusedMovable().getCenterY() - this.getOriginalY() + yOffset * i) -
                                padding;
                        if(difference < 0){
                            MovableShape.getFirstFocusedMovable().moveShape(
                                    MovableShape.getFirstFocusedMovable().getCenterX() - difference,
                                    MovableShape.getFirstFocusedMovable().getCenterY()
                            );
                        }
                    }
                    for(int i = -1; i <= 1; i++){
                        double difference = (MovableShape.getFirstFocusedMovable().getCenterY() + yOffset) - this.getOriginalY() -
                                bottomBoundary.getYFromX(MovableShape.getFirstFocusedMovable().getCenterX() - this.getOriginalX() + xOffset * i) +
                                padding;
                        if(difference > 0){
                            MovableShape.getFirstFocusedMovable().moveShape(
                                    MovableShape.getFirstFocusedMovable().getCenterX(),
                                    MovableShape.getFirstFocusedMovable().getCenterY() - difference
                            );
                        }
                    }
                }
                MovableShape.getFirstFocusedMovable().setOriginalX(MovableShape.getFirstFocusedMovable().getShape().getTranslateX());
                MovableShape.getFirstFocusedMovable().setOriginalY(MovableShape.getFirstFocusedMovable().getShape().getTranslateY());
                this.items.add(MovableShape.getFirstFocusedMovable());
                MovableShape.removeFirstFocusedMovable();
                this.getShape().toFront();
                if(!MovableShape.getFocusedMovables().isEmpty()){
                    MovableShape.getFocusedMovables().forEach(targetMovable -> targetMovable.getShape().toFront());
                }else{
                    GamePanes.MAIN.getPane().setCursor(Cursor.OPEN_HAND);
                }
            }else if(MovableShape.getFirstFocusedMovable().getOriginalX() != 0 &&
                    MovableShape.getFirstFocusedMovable().getOriginalY() != 0){
                if(MovableShape.getFirstFocusedMovable().getType() == MovableType.FOOD){
                    ((FoodMovable) MovableShape.getFirstFocusedMovable()).getTimer().start();
                }else if(MovableShape.getFirstFocusedMovable().getType() == MovableType.CONTAINER){
                    ((ContainerMovable) MovableShape.getFirstFocusedMovable()).getItems().forEach(MovableShape::reset);
                }
                MovableShape.getFirstFocusedMovable().reset();
                MovableShape.getFirstFocusedMovable().getShape().setMouseTransparent(false);
                MovableShape.removeFirstFocusedMovable();
                if(MovableShape.getFocusedMovables().isEmpty()){
                    GamePanes.MAIN.getPane().setCursor(Cursor.OPEN_HAND);
                }
            }else{
                MovableShape.getFirstFocusedMovable().getShape().toBack();
                GamePanes.MAIN.getPane().getChildren().remove(MovableShape.getFirstFocusedMovable().getShape());
                MovableShape.getFirstFocusedMovable().getShape().setMouseTransparent(false);
                MovableShape.removeFirstFocusedMovable();
                if(MovableShape.getFocusedMovables().isEmpty()){
                    GamePanes.MAIN.getPane().setCursor(Cursor.OPEN_HAND);
                }
            }
        });
    }
}
