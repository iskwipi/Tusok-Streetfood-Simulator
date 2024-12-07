package com.github.iskwipi.tusok;

import com.github.iskwipi.tusok.clickety.*;
import com.github.iskwipi.tusok.clickety.Spawner;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Tusok extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.setWidth(501);
        stage.setHeight(732);
        stage.setMaxWidth(501);
        stage.setMaxHeight(732);
        stage.setX((Screen.getPrimary().getBounds().getWidth() - stage.getWidth()) / 2);
        stage.setY(0);
        stage.setTitle("Tusok: Streetfood Simulator");
        stage.getIcons().add(new Image("C:\\Users\\neyro\\Documents\\Projects\\Personal\\Programs\\Tusok\\src\\main\\resources\\com\\github\\iskwipi\\tusok\\Icon.png"));
        stage.show();
    }

    private Parent createContent(){
        Pane mainPane = GamePanes.MAIN.getPane();

        Background background = new Background(486, 693,
                new ImagePattern(new Image("C:\\Users\\neyro\\Documents\\Projects\\Personal\\Programs\\Tusok\\src\\main\\resources\\com\\github\\iskwipi\\tusok\\Background.png"))
        );
        mainPane.getChildren().add(background.getShape());

        Background countertop = new Background(486, 396,
                new ImagePattern(new Image("C:\\Users\\neyro\\Documents\\Projects\\Personal\\Programs\\Tusok\\src\\main\\resources\\com\\github\\iskwipi\\tusok\\Countertop.png"))
        );
        countertop.getShape().setTranslateY(297);
        mainPane.getChildren().add(countertop.getShape());

        Spawner fishballSpawner = new Spawner(
                new Rectangle(87, 108),
                new ImagePattern(new Image("C:\\Users\\neyro\\Documents\\Projects\\Personal\\Programs\\Tusok\\src\\main\\resources\\com\\github\\iskwipi\\tusok\\Fishball Spawner.png")),
                390, 310,
                new EllipseFoodMovable(
                        MovableName.FISHBALL,
                        13,12,
                        10, 10,
                        new FoodSprite[]{
                                new FoodSprite(new ImagePattern(new Image("C:\\Users\\neyro\\Documents\\Projects\\Personal\\Programs\\Tusok\\src\\main\\resources\\com\\github\\iskwipi\\tusok\\Uncooked Fishball.png")),
                                        0, false),
                                new FoodSprite(new ImagePattern(new Image("C:\\Users\\neyro\\Documents\\Projects\\Personal\\Programs\\Tusok\\src\\main\\resources\\com\\github\\iskwipi\\tusok\\Cooked Fishball.png")),
                                        5, true),
                                new FoodSprite(new ImagePattern(new Image("C:\\Users\\neyro\\Documents\\Projects\\Personal\\Programs\\Tusok\\src\\main\\resources\\com\\github\\iskwipi\\tusok\\Burnt Fishball.png")),
                                        15, false)
                        },
                        0
                )
        );
        mainPane.getChildren().add(fishballSpawner.getShape());

        Spawner kikiamSpawner = new Spawner(
                new Rectangle(87, 108),
                new ImagePattern(new Image("C:\\Users\\neyro\\Documents\\Projects\\Personal\\Programs\\Tusok\\src\\main\\resources\\com\\github\\iskwipi\\tusok\\Kikiam Spawner.png")),
                390, 430,
                new EllipseFoodMovable(
                        MovableName.KIKIAM,
                        9,29,
                        10, 10,
                        new FoodSprite[]{
                                new FoodSprite(new ImagePattern(new Image("C:\\Users\\neyro\\Documents\\Projects\\Personal\\Programs\\Tusok\\src\\main\\resources\\com\\github\\iskwipi\\tusok\\Uncooked Kikiam.png")),
                                        0, false),
                                new FoodSprite(new ImagePattern(new Image("C:\\Users\\neyro\\Documents\\Projects\\Personal\\Programs\\Tusok\\src\\main\\resources\\com\\github\\iskwipi\\tusok\\Cooked Kikiam.png")),
                                        8, true),
                                new FoodSprite(new ImagePattern(new Image("C:\\Users\\neyro\\Documents\\Projects\\Personal\\Programs\\Tusok\\src\\main\\resources\\com\\github\\iskwipi\\tusok\\Burnt Kikiam.png")),
                                        20, false)
                        },
                        0
                )
        );
        mainPane.getChildren().add(kikiamSpawner.getShape());

        Spawner cupSpawner = new Spawner(
                new Rectangle(78, 234),
                new ImagePattern(new Image("C:\\Users\\neyro\\Documents\\Projects\\Personal\\Programs\\Tusok\\src\\main\\resources\\com\\github\\iskwipi\\tusok\\Cup Spawner.png")),
                310, 305,
                new QuadrilateralContainerMovable(
                        MovableName.CUP,
                        new double[]{
                                0.0, 0.0,
                                70.0, 0.0,
                                63.0, 58.0,
                                7.0, 58.0,
                        },
                        new ImagePattern(new Image("C:\\Users\\neyro\\Documents\\Projects\\Personal\\Programs\\Tusok\\src\\main\\resources\\com\\github\\iskwipi\\tusok\\Cup.png")),
                        350, 100,
                        10.0,
                        0.65,
                        70.0, 58.0,
                        35.0, 29.0
                )
        );
        mainPane.getChildren().add(cupSpawner.getShape());

        Spawner stickSpawner = new Spawner(
                new Rectangle(52, 172),
                new ImagePattern(new Image("C:\\Users\\neyro\\Documents\\Projects\\Personal\\Programs\\Tusok\\src\\main\\resources\\com\\github\\iskwipi\\tusok\\Stick Spawner.png")),
                250, 365,
                new RectangleUtensilMovable(
                        MovableName.STICK,
                        22, 99,
                        new ImagePattern(new Image("C:\\Users\\neyro\\Documents\\Projects\\Personal\\Programs\\Tusok\\src\\main\\resources\\com\\github\\iskwipi\\tusok\\Stick.png")),
                        265, 175,
                        true
                )
        );
        mainPane.getChildren().add(stickSpawner.getShape());

        Spawner sweetSauceSpawner = new Spawner(
                new Ellipse(63, 17),
                new ImagePattern(new Image("C:\\Users\\neyro\\Documents\\Projects\\Personal\\Programs\\Tusok\\src\\main\\resources\\com\\github\\iskwipi\\tusok\\Sweet Sauce.png")),
                85, 333,
                new PolygonCustomizerMovable(
                        MovableName.SWEET_SAUCE,
                        new double[]{
                                29.0, 0.0,
                                42.0, 0.0,
                                42.0, 92.0,
                                0.0, 92.0,
                                0.0, 63.0,
                                29.0, 63.0,
                        },
                        new ImagePattern(new Image("C:\\Users\\neyro\\Documents\\Projects\\Personal\\Programs\\Tusok\\src\\main\\resources\\com\\github\\iskwipi\\tusok\\Sweet Ladle.png")),
                        80, 275,
                        30.0, 90.0,
                        15.0, 75.0,
                        new ImagePattern(new Image("C:\\Users\\neyro\\Documents\\Projects\\Personal\\Programs\\Tusok\\src\\main\\resources\\com\\github\\iskwipi\\tusok\\Sweet Cup.png"))
                )
        );
        mainPane.getChildren().add(sweetSauceSpawner.getShape());

        Spawner spicySauceSpawner = new Spawner(
                new Ellipse(62, 16),
                new ImagePattern(new Image("C:\\Users\\neyro\\Documents\\Projects\\Personal\\Programs\\Tusok\\src\\main\\resources\\com\\github\\iskwipi\\tusok\\Spicy Sauce.png")),
                233, 332,
                new PolygonCustomizerMovable(
                        MovableName.SPICY_SAUCE,
                        new double[]{
                                29.0, 0.0,
                                42.0, 0.0,
                                42.0, 92.0,
                                0.0, 92.0,
                                0.0, 63.0,
                                29.0, 63.0,
                        },
                        new ImagePattern(new Image("C:\\Users\\neyro\\Documents\\Projects\\Personal\\Programs\\Tusok\\src\\main\\resources\\com\\github\\iskwipi\\tusok\\Spicy Ladle.png")),
                        195, 275,
                        30.0, 90.0,
                        15.0, 75.0,
                        new ImagePattern(new Image("C:\\Users\\neyro\\Documents\\Projects\\Personal\\Programs\\Tusok\\src\\main\\resources\\com\\github\\iskwipi\\tusok\\Spicy Cup.png"))
                )
        );
        mainPane.getChildren().add(spicySauceSpawner.getShape());

        PreparationArea preparationArea = new PreparationArea(
                new Rectangle(234, 105),
                new ImagePattern(new Image("C:\\Users\\neyro\\Documents\\Projects\\Personal\\Programs\\Tusok\\src\\main\\resources\\com\\github\\iskwipi\\tusok\\Preparation Area.png")),
                10, 425
        );
        mainPane.getChildren().add(preparationArea.getShape());

        Rectangle unimplemented = new Rectangle(234, 46,
                new ImagePattern(new Image("C:\\Users\\neyro\\Documents\\Projects\\Personal\\Programs\\Tusok\\src\\main\\resources\\com\\github\\iskwipi\\tusok\\Cash Register Area.png"))
        );
        unimplemented.setTranslateX(10);
        unimplemented.setTranslateY(375);
        unimplemented.setMouseTransparent(true);
        mainPane.getChildren().add(unimplemented);

        Cooker cooker = new Cooker(
                new Ellipse(161, 34),
                new ImagePattern(new Image("C:\\Users\\neyro\\Documents\\Projects\\Personal\\Programs\\Tusok\\src\\main\\resources\\com\\github\\iskwipi\\tusok\\Oil.png")),
                301, 600,
                new Rectangle(353, 94),
                new ImagePattern(new Image("C:\\Users\\neyro\\Documents\\Projects\\Personal\\Programs\\Tusok\\src\\main\\resources\\com\\github\\iskwipi\\tusok\\Pan Front.png")),
                125, 589
                );
        cooker.getShape().setOpacity(0.65);
        mainPane.getChildren().add(cooker.getShape());
        mainPane.getChildren().add(cooker.getCover());

        Bin bin = new Bin(
                new Rectangle(116, 134),
                new ImagePattern(new Image("C:\\Users\\neyro\\Documents\\Projects\\Personal\\Programs\\Tusok\\src\\main\\resources\\com\\github\\iskwipi\\tusok\\Bin.png")),
                5, 540
        );
        mainPane.getChildren().add(bin.getShape());

        CustomerGenerator.start();

        return mainPane;
    }

    public static void main(String[] args) {
        launch();
    }
}
