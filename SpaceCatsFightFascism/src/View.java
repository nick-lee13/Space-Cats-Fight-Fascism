import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class View extends Application {
    @Override
    public void start(Stage primaryStage)
    {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 1200, 628);
        //Setting title to the scene 
        primaryStage.setTitle("Space Cats Fight Fascism"); 
        Image img = new Image("https://cf.geekdo-images.com/luzMc4Fu5CY3dSavElurxA__imagepagezoom/img/SXmC9JOVZH6U9LCfIBFQ41Ogbbg=/fit-in/1200x900/filters:no_upscale():strip_icc()/pic4105088.jpg");
        BackgroundImage bImg = new BackgroundImage(img,
                                                   BackgroundRepeat.NO_REPEAT,
                                                   BackgroundRepeat.NO_REPEAT,
                                                   BackgroundPosition.DEFAULT,
                                                   BackgroundSize.DEFAULT);
        Background bGround = new Background(bImg);
        root.setBackground(bGround);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}