import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class DisplayUpdater extends Application {

    @Override
    public void start(Stage stage){

        Pane root = new Pane();
        Scene scene = new Scene(root, 1050, 600);
        ViewElements gameElements = new ViewElements();

        //Setting title to the scene 
        stage.setTitle("Space Cats Fight Fascism");
        root.getChildren().add(gameElements.getBackgroundImage("res/game_bg.jpg"));


        root.getChildren().addAll(drawRectangle(840,0,210,600), drawRectangle(0, 450, 1050, 150));
        
        //Draw Planets
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 4; j++){
                root.getChildren().add(drawRectangle(60+(j*190), 105+(i*110), 150, 80));
            }
        }

        Buttons b = new Buttons();
        root.getChildren().addAll(b.createPlayerButtons());
        root.getChildren().add(updateCatDisplay());

        stage.setScene(scene);
        stage.show();
    }

    //update cat locations
    public Circle updateCatDisplay(){
        //GET CATS AND THEIR LOCATIONS HERE
        Circle cat = new Circle(80+190,165,18,Color.RED);
        return cat;

    }

    //update token counts
    /*public void tokenDisplay(Planet[][]){
        
    }*/


    //update flags

    //update player controls/cards

    public void initDisplay(String[] args) {
        launch(args);
    }

    private Rectangle drawRectangle(int x, int y, int width, int height){

        Rectangle rect = new Rectangle(x,y,width,height);
        rect.setFill(Color.LIGHTBLUE);

        return rect;
    }
}
