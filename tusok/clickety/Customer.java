package com.github.iskwipi.tusok.clickety;

import com.github.iskwipi.tusok.GamePanes;
import com.github.iskwipi.tusok.Utilities;
import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Customer extends ClickableShape{
    private final Order order;

    public Order getOrder(){
        return order;
    }

    public Customer(Rectangle rectangle, Paint paint,
                    double originalX, double originalY,
                    Order order){
        super(rectangle, originalX, originalY);
        this.getShape().setFill(paint);
        this.order = order;
        this.order.setCustomer(this);
        this.makeClickable();
        this.getShape().setOnMouseEntered(mouseEvent -> {
            if(MovableShape.getFocusedMovables().isEmpty()){
                GamePanes.MAIN.getPane().setCursor(Cursor.DEFAULT);
            }
        });
    }

    @Override
    public void makeClickable(){
        this.getShape().setOnMousePressed(mouseEvent ->
                MouseEvent.fireEvent(order.getShape(),
                    Utilities.createMouseEvent(
                            MouseEvent.MOUSE_PRESSED,
                            MouseButton.PRIMARY,
                            1,
                            order.getOriginalX(),
                            order.getOriginalY()
                )
        ));
    }
}