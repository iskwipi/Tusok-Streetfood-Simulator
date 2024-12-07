package com.github.iskwipi.tusok;

import com.github.iskwipi.tusok.clickety.Customer;
import com.github.iskwipi.tusok.clickety.Order;
import javafx.animation.PauseTransition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Random;

public class CustomerGenerator{
    private final static int customerTypes = 2;
    private final static double customerY = 137;
    private static double customerX = 5;

    public static void setCustomerX(double x){
        customerX = x;
    }

    public static void start(){
        PauseTransition delay1 = new PauseTransition(Duration.seconds(1));
        delay1.setOnFinished(actionEvent -> {
            Customer customer1 = new Customer1(5, customerY);
            GamePanes.MAIN.getPane().getChildren().add(customer1.getShape());
            GamePanes.MAIN.getPane().getChildren().add(customer1.getOrder().getPatienceBackground());
            GamePanes.MAIN.getPane().getChildren().add(customer1.getOrder().getPatienceShape());
            GamePanes.MAIN.getPane().getChildren().add(customer1.getOrder().getShape());
            customer1.getOrder().getDetails().forEach(shape -> {
                shape.toBack();
                GamePanes.MAIN.getPane().getChildren().add(shape);
            });
        });
        delay1.play();
        PauseTransition delay2 = new PauseTransition(Duration.seconds(2));
        delay2.setOnFinished(actionEvent -> {
        Customer customer2 = new Customer2(238, customerY);
            GamePanes.MAIN.getPane().getChildren().add(customer2.getShape());
            GamePanes.MAIN.getPane().getChildren().add(customer2.getOrder().getPatienceBackground());
            GamePanes.MAIN.getPane().getChildren().add(customer2.getOrder().getPatienceShape());
            GamePanes.MAIN.getPane().getChildren().add(customer2.getOrder().getShape());
            customer2.getOrder().getDetails().forEach(shape -> {
                shape.toBack();
                GamePanes.MAIN.getPane().getChildren().add(shape);
            });
        });
        delay2.play();
    }

    public static void generateCustomer(){
        double queuedX = customerX;
        Random random = new Random();
        PauseTransition delay = new PauseTransition(Duration.seconds(5));
        delay.setOnFinished(actionEvent -> {
            Customer customer = switch (random.nextInt(customerTypes)){
                case 0 -> new Customer1(queuedX, customerY);
                case 1 -> new Customer2(queuedX, customerY);
                default -> null;
            };
            GamePanes.MAIN.getPane().getChildren().add(customer.getShape());
            GamePanes.MAIN.getPane().getChildren().add(customer.getOrder().getPatienceBackground());
            GamePanes.MAIN.getPane().getChildren().add(customer.getOrder().getPatienceShape());
            GamePanes.MAIN.getPane().getChildren().add(customer.getOrder().getShape());
            customer.getOrder().getDetails().forEach(shape -> {
                shape.toBack();
                GamePanes.MAIN.getPane().getChildren().add(shape);
            });
        });
        delay.play();
    }
}

class Customer1 extends Customer{
    public Customer1(double originalX, double originalY){
        super(
                new Rectangle(135, 161),
                new ImagePattern(new Image("C:\\Users\\neyro\\Documents\\Projects\\Personal\\Programs\\Tusok\\src\\main\\resources\\com\\github\\iskwipi\\tusok\\Boy.png")),
                originalX, originalY,
                new Order(
                        new Rectangle(109, 151),
                        new ImagePattern(new Image("C:\\Users\\neyro\\Documents\\Projects\\Personal\\Programs\\Tusok\\src\\main\\resources\\com\\github\\iskwipi\\tusok\\Order.png")),
                        originalX + 128, originalY - 55,
                        OrderGenerator.generateItems(),
                        60
                )
        );
    }
}

class Customer2 extends Customer{
    public Customer2(double originalX, double originalY){
        super(
                new Rectangle(135, 161),
                new ImagePattern(new Image("C:\\Users\\neyro\\Documents\\Projects\\Personal\\Programs\\Tusok\\src\\main\\resources\\com\\github\\iskwipi\\tusok\\Girl.png")),
                originalX, originalY,
                new Order(
                        new Rectangle(109, 151),
                        new ImagePattern(new Image("C:\\Users\\neyro\\Documents\\Projects\\Personal\\Programs\\Tusok\\src\\main\\resources\\com\\github\\iskwipi\\tusok\\Order.png")),
                        originalX + 128, originalY - 55,
                        OrderGenerator.generateItems(),
                        80
                )
        );
    }
}
