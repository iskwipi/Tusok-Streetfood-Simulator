package com.github.iskwipi.tusok.clickety;

import com.github.iskwipi.tusok.CustomerGenerator;
import com.github.iskwipi.tusok.GamePanes;
import javafx.animation.AnimationTimer;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class Order extends ClickableShape{
    private final int[] itemCounts;
    private final ArrayList<Shape> details;
    private final Rectangle patienceShape;
    private final Rectangle patienceBackground;
    private final AnimationTimer patienceTimer;
    private Customer customer;

    public ArrayList<Shape> getDetails(){
        return this.details;
    }

    public Rectangle getPatienceShape(){
        return this.patienceShape;
    }

    public Rectangle getPatienceBackground(){
        return this.patienceBackground;
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    public Order(Rectangle rectangle,
                 Paint orderPaint,
                 double originalX, double originalY,
                 MovableName[] items,
                 long patienceInSeconds){
        super(rectangle, originalX, originalY);
        this.getShape().setFill(orderPaint);
        this.itemCounts = new int[MovableName.values().length];
        for(MovableName item: items){
            if(item != null){
                this.itemCounts[item.ordinal()]++;
            }
        }
        this.details = new ArrayList<>();
        double detailsX = rectangle.getWidth() * 0.15;
        double detailsY = rectangle.getHeight() * 0.06;
        for(int i = 0; i < this.itemCounts.length; i++){
            if(this.itemCounts[i] != 0){
                Rectangle itemShape = new Rectangle();
                switch(MovableName.values()[i]){
                    case FISHBALL:
                        itemShape.setWidth(23);
                        itemShape.setHeight(23);
                        itemShape.setFill(new ImagePattern(new Image("C:\\Users\\neyro\\Documents\\Projects\\Personal\\Programs\\Tusok\\src\\main\\resources\\com\\github\\iskwipi\\tusok\\Fishball Detail.png")));
                        break;
                    case KIKIAM:
                        itemShape.setWidth(23);
                        itemShape.setHeight(23);
                        itemShape.setFill(new ImagePattern(new Image("C:\\Users\\neyro\\Documents\\Projects\\Personal\\Programs\\Tusok\\src\\main\\resources\\com\\github\\iskwipi\\tusok\\Kikiam Detail.png")));
                        break;
                    case SWEET_SAUCE:
                        itemShape.setWidth(83);
                        itemShape.setHeight(31);
                        itemShape.setFill(new ImagePattern(new Image("C:\\Users\\neyro\\Documents\\Projects\\Personal\\Programs\\Tusok\\src\\main\\resources\\com\\github\\iskwipi\\tusok\\Sweet.png")));
                        break;
                    case SPICY_SAUCE:
                        itemShape.setWidth(83);
                        itemShape.setHeight(31);
                        itemShape.setFill(new ImagePattern(new Image("C:\\Users\\neyro\\Documents\\Projects\\Personal\\Programs\\Tusok\\src\\main\\resources\\com\\github\\iskwipi\\tusok\\Spicy.png")));
                        break;

                }
                itemShape.setTranslateX(originalX + detailsX);
                itemShape.setTranslateY(originalY + detailsY);
                itemShape.setMouseTransparent(true);
                this.details.add(itemShape);
                if(i < MovableName.FOOD_ITEMS.ordinal()){
                    Rectangle multiply = new Rectangle(18, 23, new ImagePattern(new Image("C:\\Users\\neyro\\Documents\\Projects\\Personal\\Programs\\Tusok\\src\\main\\resources\\com\\github\\iskwipi\\tusok\\Multiplication Symbol.png")));
                    multiply.setTranslateX(originalX + detailsX + itemShape.getWidth() + 10);
                    multiply.setTranslateY(originalY + detailsY);
                    multiply.setMouseTransparent(true);
                    this.details.add(multiply);
                    if(this.itemCounts[i] / 10 > 0){
                        Rectangle num1 = new Rectangle(18, 23, new ImagePattern(new Image("C:\\Users\\neyro\\Documents\\Projects\\Personal\\Programs\\Tusok\\src\\main\\resources\\com\\github\\iskwipi\\tusok\\" + (this.itemCounts[i] / 10) + ".png")));
                        num1.setTranslateX(originalX + detailsX + itemShape.getWidth() + multiply.getWidth() + 13);
                        num1.setTranslateY(originalY + detailsY);
                        num1.setMouseTransparent(true);
                        this.details.add(num1);
                    }
                    Rectangle num2 = new Rectangle(18, 23, new ImagePattern(new Image("C:\\Users\\neyro\\Documents\\Projects\\Personal\\Programs\\Tusok\\src\\main\\resources\\com\\github\\iskwipi\\tusok\\" + (this.itemCounts[i] % 10) + ".png")));
                    if(this.itemCounts[i] / 10 > 0){
                        num2.setTranslateX(originalX + detailsX + itemShape.getWidth() + multiply.getWidth() + 23);
                    }else{
                        num2.setTranslateX(originalX + detailsX + itemShape.getWidth() + multiply.getWidth() + 20);
                    }
                    num2.setTranslateY(originalY + detailsY);
                    num2.setMouseTransparent(true);
                    this.details.add(num2);
                }
                detailsY += itemShape.getHeight() + 5;
            }
        }
        this.itemCounts[MovableName.STICK.ordinal()]++;
        this.patienceBackground = new Rectangle(rectangle.getWidth() * 0.75, rectangle.getHeight() * 0.25);
        this.patienceBackground.setTranslateX(originalX + rectangle.getWidth() * 0.13);
        this.patienceBackground.setTranslateY(originalY + rectangle.getHeight() * 0.80 - this.patienceBackground.getHeight());
        this.patienceBackground.setFill(Color.valueOf("818181"));
        this.patienceBackground.setMouseTransparent(true);
        this.patienceShape = new Rectangle(rectangle.getWidth() * 0.75, rectangle.getHeight() * 0.25);
        this.patienceShape.setTranslateX(originalX + rectangle.getWidth() * 0.13);
        this.patienceShape.setTranslateY(originalY + rectangle.getHeight() * 0.80 - this.patienceBackground.getHeight());
        this.patienceShape.setFill(Color.valueOf("45bb2d"));
        this.patienceShape.setMouseTransparent(true);
        this.patienceTimer = new AnimationTimer(){
            long startTime = -1;
            @Override
            public void handle(long currentTime){
                if(startTime == -1){
                    startTime = currentTime;
                }else{
                    double patiencePercentage = 1.0D - (currentTime - startTime) / (patienceInSeconds * 1000000000.0D);
                    if(patiencePercentage > 0.0D){
                        patienceShape.setWidth(rectangle.getWidth() * 0.75 * patiencePercentage);
                        if(patiencePercentage < 0.2D){
                            patienceShape.setFill(Color.valueOf("bb362d"));
                        }else if(patiencePercentage < 0.5D){
                            patienceShape.setFill(Color.valueOf("bb7f2d"));
                        }
                    }else{
                        stop();
                        int points = 0;
                        for(int i = 0; i < itemCounts.length; i++){
                            if(i < MovableName.FOOD_ITEMS.ordinal()){
                                points -= 10 * itemCounts[i];
                            }else if(i < MovableName.NON_FOOD_ITEMS.ordinal()){
                                points -= 5 * itemCounts[i];
                            }else if(i < MovableName.CUSTOMIZERS.ordinal()){
                                points -= 15 * itemCounts[i];
                            }
                        }
                        System.out.println("Points: " + points + "\nCash: " + 0 + "\n");
                        patienceBackground.toBack();
                        GamePanes.MAIN.getPane().getChildren().remove(patienceBackground);
                        patienceShape.toBack();
                        GamePanes.MAIN.getPane().getChildren().remove(patienceShape);
                        details.forEach(shape -> {
                            shape.toBack();
                            GamePanes.MAIN.getPane().getChildren().remove(shape);
                        });
                        customer.getShape().toBack();
                        GamePanes.MAIN.getPane().getChildren().remove(customer.getShape());
                        getShape().toBack();
                        GamePanes.MAIN.getPane().getChildren().remove(getShape());
                        CustomerGenerator.setCustomerX(customer.getOriginalX());
                        CustomerGenerator.generateCustomer();
                    }

                }
            }
        };
        this.patienceTimer.start();
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
                    int points = 0;
                    int cash = 0;
                    if(((ContainerMovable) MovableShape.getFirstFocusedMovable()).getItems() != null){
                        for(MovableShape targetMovable: ((ContainerMovable) MovableShape.getFirstFocusedMovable()).getItems()){
                            this.itemCounts[targetMovable.getName().ordinal()]--;
                            if(this.itemCounts[targetMovable.getName().ordinal()] >= 0){
                                if(targetMovable.getType() == MovableType.FOOD){
                                    points += 20;
                                    cash += 20;
                                }else if(targetMovable.getType() == MovableType.UTENSIL){
                                    points += 10;
                                }else if(targetMovable.getType() == MovableType.CUSTOMIZER){
                                    points += 30;
                                }
                            }
                            targetMovable.getShape().toBack();
                            GamePanes.MAIN.getPane().getChildren().remove(targetMovable.getShape());
                        }
                    }
                    for(int i = 0; i < this.itemCounts.length; i++){
                        if(this.itemCounts[i] < 0){
                            if(i < MovableName.FOOD_ITEMS.ordinal()){
                                cash += 20 * this.itemCounts[i];
                            }else if(i < MovableName.NON_FOOD_ITEMS.ordinal()){
                                cash += 10 * this.itemCounts[i];
                            }else if(i < MovableName.CUSTOMIZERS.ordinal()){
                                cash += 15 * this.itemCounts[i];
                            }
                        }else{
                            if(i < MovableName.FOOD_ITEMS.ordinal()){
                                points -= 10 * this.itemCounts[i];
                            }else if(i < MovableName.NON_FOOD_ITEMS.ordinal()){
                                points -= 5 * this.itemCounts[i];
                            }else if(i < MovableName.CUSTOMIZERS.ordinal()){
                                points -= 15 * this.itemCounts[i];
                            }
                        }
                    }
                    System.out.println("Points: " + points + "\nCash: " + cash + "\n");
                    MovableShape.getFirstFocusedMovable().getShape().toBack();
                    GamePanes.MAIN.getPane().getChildren().remove(MovableShape.getFirstFocusedMovable().getShape());
                    this.patienceTimer.stop();
                    patienceBackground.toBack();
                    GamePanes.MAIN.getPane().getChildren().remove(patienceBackground);
                    this.patienceShape.toBack();
                    GamePanes.MAIN.getPane().getChildren().remove(this.patienceShape);
                    this.details.forEach(shape -> {
                        shape.toBack();
                        GamePanes.MAIN.getPane().getChildren().remove(shape);
                    });
                    this.customer.getShape().toBack();
                    GamePanes.MAIN.getPane().getChildren().remove(this.customer.getShape());
                    this.getShape().toBack();
                    GamePanes.MAIN.getPane().getChildren().remove(this.getShape());
                    CustomerGenerator.setCustomerX(this.customer.getOriginalX());
                    CustomerGenerator.generateCustomer();
                    MovableShape.getFirstFocusedMovable().getShape().setMouseTransparent(false);
                    MovableShape.removeFirstFocusedMovable();
                }else if(MovableShape.getFirstFocusedMovable().getOriginalX() != 0 &&
                        MovableShape.getFirstFocusedMovable().getOriginalY() != 0){
                    if(MovableShape.getFirstFocusedMovable().getType() == MovableType.FOOD){
                        ((FoodMovable) MovableShape.getFirstFocusedMovable()).getTimer().start();
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
