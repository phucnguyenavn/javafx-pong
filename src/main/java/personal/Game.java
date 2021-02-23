package personal;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import personal.objects.Ball;
import personal.objects.Paddle;

import java.util.logging.Logger;


/**
 * JavaFX App
 */
public class Game extends Application {
    private static final Logger logger = Logger.getLogger(Game.class.getName());
    public static final double WIDTH =600;
    public static final double HEIGHT=300;
    private final double FPS = 16.7;
    private Ball ball;
    private Paddle left;
    private Paddle right;
    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        var scene = new Scene(root,WIDTH,HEIGHT);
        ball = new Ball();
        left = new Paddle(true);
        right= new Paddle(false);
        move(scene,left,right);
        Timeline animation = new Timeline(new KeyFrame(Duration.millis(FPS),e-> ball.move()));
        animation.setCycleCount(Animation.INDEFINITE);
        animation.setAutoReverse(true);
        animation.play();
        root.getChildren().addAll(ball,left,right);
        stage.setScene(scene);
        stage.setTitle("Pong");
        stage.setResizable(false);
        scene.setFill(Color.BLACK);
        stage.show();
    }
    private void move(Scene scene, Paddle left, Paddle right){
        scene.setOnKeyPressed(keyEvent -> {
                    switch (keyEvent.getCode()){
                        case W:left.moveUp();break;
                        case UP:right.moveUp();break;
                        case S:left.moveDown();break;
                        case DOWN:right.moveDown();break;

                    }
           // logger.info(left.getLayoutX()+" "+left.getLayoutY());
                }
        );

    }
    public static void main(String[] args) {
        launch();
    }

}