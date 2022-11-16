import java.io.IOException;
import java.io.InputStream;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
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

        root.getChildren().addAll(playerButtons());

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

    private void drawPlanets(){
        //drawPlanetCards()
        //drawTokens()
        //place cats

    }

    private Button[] playerButtons(){

        Button travel = new Button();
        Button fightFascism = new Button();
        Button restock = new Button();
        Button playResistCard = new Button();
        Button endTurn = new Button();

        travel.setText("TRAVEL");
        fightFascism.setText("FIGHT FASCISM");
        restock.setText("RESTOCK");
        playResistCard.setText("PLAY RESIST CARD");
        endTurn.setText("END TURN");

        //TEMP
        travel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("TRAVEL BUTTON PRESSED");
            }
        });
        fightFascism.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("FIGHT BUTTON PRESSED");
            }
        });
        restock.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("RESTOCK BUTTON PRESSED");
            }
        });
        playResistCard.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("RESIST BUTTON PRESSED");
            }
        });
        endTurn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("END BUTTON PRESSED");
            }
        });

        travel.setLayoutX(75);
        fightFascism.setLayoutX(200);
        restock.setLayoutX(370);
        playResistCard.setLayoutX(505);
        endTurn.setLayoutX(690);

        travel.setLayoutY(520);
        fightFascism.setLayoutY(520);
        restock.setLayoutY(520);
        playResistCard.setLayoutY(520);
        endTurn.setLayoutY(520);

        Button[] buttons = {travel,fightFascism,restock,playResistCard,endTurn};
        return buttons;
    }
}