package com.github.iskwipi.tusok.clickety;

import com.github.iskwipi.tusok.GamePanes;
import javafx.scene.Cursor;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public abstract class MovableShape extends ClickableShape implements Movable{
    private final static ArrayList<MovableShape> focusedMovables = new ArrayList<>();
    private final MovableType type;
    private final MovableName name;
    private double centerX;
    private double centerY;
    private boolean containable;

    public static void addFocusedMovable(MovableShape focusedMovable){
        MovableShape.focusedMovables.add(focusedMovable);
    }

    public static void removeFirstFocusedMovable(){
        for(int i = 1; i < MovableShape.focusedMovables.size(); i++){
            MovableShape.focusedMovables.set(i - 1, MovableShape.focusedMovables.get(i));
        }
        MovableShape.focusedMovables.remove(MovableShape.focusedMovables.size() - 1);
        MovableShape.focusedMovables.trimToSize();
    }

    public static MovableShape getFirstFocusedMovable(){
        return MovableShape.focusedMovables.get(0);
    }

    public static void resetFocusedMovables(){
        MovableShape.focusedMovables.clear();
    }

    public static ArrayList<MovableShape> getFocusedMovables(){
        return focusedMovables;
    }

    public MovableType getType(){
        return this.type;
    }

    public MovableName getName(){
        return this.name;
    }

    public void setCenterX(double centerX){
        this.centerX = centerX;
    }

    public double getCenterX(){
        return centerX;
    }

    public void setCenterY(double centerY){
        this.centerY = centerY;
    }

    public double getCenterY(){
        return centerY;
    }

    public void setContainable(boolean containable){
        this.containable = containable;
    }

    public boolean isContainable(){
        return containable;
    }

    public MovableShape(MovableType type,
                        MovableName name,
                        Shape shape,
                        double centerX, double centerY,
                        boolean containable){
        super(shape);
        this.type = type;
        this.name = name;
        this.centerX = centerX;
        this.centerY = centerY;
        this.containable = containable;
        this.moveShape(centerX, centerY);
        this.getShape().setOnMouseEntered(mouseEvent -> GamePanes.MAIN.getPane().setCursor(Cursor.OPEN_HAND));
    }

    public abstract MovableShape clone();
}
