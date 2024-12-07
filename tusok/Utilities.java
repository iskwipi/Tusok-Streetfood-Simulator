package com.github.iskwipi.tusok;

import javafx.event.EventType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class Utilities{
    public static double clamp(double min, double max, double value){
        if(value < min)         return min;
        else if(max < value)    return max;
        else                    return value;
    }

    public static MouseEvent createMouseEvent(EventType<MouseEvent> mouseEvent,
                                   MouseButton button, int clickCount,
                                   double x, double y){
        return new MouseEvent(mouseEvent, x, y, 0, 0,
                button, clickCount, true, true, true, true, true,
                true, true, true, true, true, null);
    }
}
