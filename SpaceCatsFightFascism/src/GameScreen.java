import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GameScreen extends Application {
    @Override
    public void start(Stage stage){
        Pane root = new Pane();
        Scene scene = new Scene(root, 1050, 600);
        //Setting title to the scene 
        stage.setTitle("Space Cats Fight Fascism"); 
        try(InputStream is = Files.newInputStream(Paths.get("SpaceCatsFightFascism/res/game_bg.jpg"))){
			ImageView img = new ImageView(new Image(is));
			img.setFitWidth(1050);
			img.setFitHeight(600);
			root.getChildren().add(img);
		}
		catch(IOException e) {
			System.out.println("Couldn't load image");
		}
        root.getChildren().addAll(drawRectangle(840,0,210,600), drawRectangle(0, 450, 1050, 150));
        
        //Draw Planets
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 4; j++){
                root.getChildren().add(drawRectangle(60+(j*190), 105+(i*110), 150, 80));
            }
        }

        Buttons b = new Buttons();
        root.getChildren().addAll(b.createPlayerButtons());

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    //Draw rectangle object to screen
    private Rectangle drawRectangle(int x, int y, int width, int height){

        Rectangle rect = new Rectangle(x,y,width,height);
        rect.setFill(Color.LIGHTBLUE);

        return rect;
    }

    /*private void drawPlanets(){
        //drawPlanetCards()
        //drawTokens()
        //place cats
    }*/
}
