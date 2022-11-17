import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DisplayUpdater extends Application {

    GameController gc = new GameController();
    Rectangle[][] planetIcons;
    Planet[][] planetLayout = new Planet[3][4];
    Text[][] tokenCounts;

    Pane root;
    Scene scene;


    @Override
    public void start(Stage stage){

        root = new Pane();
        scene = new Scene(root, 1050, 600);
        ViewElements gameElements = new ViewElements();

        //Setting title to the scene 
        stage.setTitle("Space Cats Fight Fascism");
        root.getChildren().add(gameElements.getBackgroundImage("res/game_bg.jpg"));


        //Menu bar
        root.getChildren().addAll(drawRectangle(840,0,210,600), drawRectangle(0, 450, 1050, 150));
        
        //Draw Planets
        root.getChildren().addAll(gameElements.drawPlanets(planetLayout)); //REPLACE WITH gc.getGameState().getPlanetLayout()

        //Draw token counters
        root.getChildren().addAll(gameElements.updateTokenCounters(planetLayout)); //REPLACE WITH gc.getGameState().getPlanetLayout()

        //Draw Flags
        root.getChildren().addAll(gameElements.drawFlags(planetLayout));//REPLACE WITH gc.getGameState().getPlanetLayout()

        //Player Actions
        Buttons b = new Buttons();
        root.getChildren().addAll(b.createPlayerButtons());

        stage.setScene(scene);
        stage.show();
    }

    //update cat locations
    /*public Circle drawCat(Cat cat){

    }*/

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
