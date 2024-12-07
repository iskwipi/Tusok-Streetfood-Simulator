package com.github.iskwipi.tusok.clickety;

import com.github.iskwipi.tusok.GamePanes;
import javafx.scene.Cursor;
import javafx.scene.shape.Shape;

public abstract class FoodMovable extends MovableShape{
    private final FoodTimer timer;
    private final FoodSprite[] sprites;
    private final int startingSpriteIndex;

    public FoodTimer getTimer(){
        return this.timer;
    }

    public FoodSprite[] getSprites(){
        return this.sprites;
    }

    public int getStartingSpriteIndex(){
        return this.startingSpriteIndex;
    }

    public FoodMovable(MovableName name,
                       Shape shape,
                       double centerX, double centerY,
                       FoodSprite[] sprites,
                       int startingSpriteIndex){
        super(MovableType.FOOD, name, shape, centerX, centerY,
                sprites[startingSpriteIndex].isContainable());
        this.getShape().setFill(sprites[startingSpriteIndex].getPaint());
        this.sprites = sprites;
        this.startingSpriteIndex = startingSpriteIndex;
        this.timer = new FoodTimer(){
            @Override
            public void handle(long currentTime){
                if(!isRunning()){
                    setStartTime(currentTime - getSecondsElapsed() * 1000000000L);
                    setRunning(true);
                }else{
                    setSecondsElapsed((currentTime - getStartTime()) / 1000000000L);
                    for(FoodSprite sprite: sprites){
                        if(getSecondsElapsed() == sprite.getSeconds()){
                            getShape().setFill(sprite.getPaint());
                            setContainable(sprite.isContainable());
                            break;
                        }
                    }
                }
            }
        };
        this.makeClickable();
    }

    @Override
    public void makeClickable(){
        this.getShape().setOnMousePressed(mouseEvent -> {
            if(MovableShape.getFocusedMovables().isEmpty() ||
                    MovableShape.getFirstFocusedMovable().getType() == MovableType.FOOD &&
                    MovableShape.getFirstFocusedMovable().isContainable() == this.isContainable()){
                this.getTimer().stop();
                this.getShape().setMouseTransparent(true);
                MovableShape.addFocusedMovable(this);
                GamePanes.MAIN.getPane().setCursor(Cursor.CLOSED_HAND);
            }
        });
    }
}
