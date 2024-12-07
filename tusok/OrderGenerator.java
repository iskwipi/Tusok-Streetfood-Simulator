package com.github.iskwipi.tusok;

import com.github.iskwipi.tusok.clickety.MovableName;

import java.util.Random;

public class OrderGenerator{
    public static MovableName[] generateItems(){
        int foodItemSlots = 10;
        int itemsIndex = 0;
        MovableName[] foodItemChoices = new MovableName[MovableName.FOOD_ITEMS.ordinal()];
        MovableName[] items = new MovableName[foodItemSlots + 1];
        Random random = new Random();
        for(int i = 0; i < MovableName.FOOD_ITEMS.ordinal(); i++){
            int randomNumber = random.nextInt(foodItemChoices.length);
            while(foodItemChoices[randomNumber] != null){
                randomNumber = (randomNumber + 1) % foodItemChoices.length;
            }
            foodItemChoices[randomNumber] = MovableName.values()[i];
        }
        for(MovableName foodItemChoice : foodItemChoices){
            if(foodItemSlots > 0){
                int count = random.nextInt(foodItemSlots) + 1;
                for (int j = 0; j < count; j++){
                    items[itemsIndex++] = foodItemChoice;
                    foodItemSlots--;
                }
            }
        }
        if(foodItemSlots == 10){
            int randomNumber = random.nextInt(MovableName.FOOD_ITEMS.ordinal());
            for(int i = 0; i < 5; i++){
                items[itemsIndex++] = MovableName.values()[randomNumber];
                foodItemSlots--;
            }
        }
        int randomNumber = random.nextInt(MovableName.CUSTOMIZERS.ordinal() - MovableName.NON_FOOD_ITEMS.ordinal());
        switch(randomNumber){
            case 1: case 2:
                items[itemsIndex] = MovableName.values()[MovableName.CUSTOMIZERS.ordinal() - randomNumber];
                break;
            default:
                break;
        }
        return items;
    }
}
