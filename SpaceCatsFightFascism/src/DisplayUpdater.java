import javafx.event.EventHandler;
import javafx.event.ActionEvent;

import java.util.Objects;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DisplayUpdater extends Application {

    GameController gc = new GameController();
    ViewElements gameElements = new ViewElements();
    boolean firstRun = true;
    Rectangle[][] planetIcons;
    Planet[][] planetLayout = new Planet[3][4];
    Text[][] tokenCounts;
    String broadcast;

    Pane root;
    Scene scene;

    @Override
    public void start(Stage stage){

        gc.startGame();
        root = new Pane();
        scene = new Scene(root, 1050, 600);

        //Setting title to the scene 
        stage.setTitle("Space Cats Fight Fascism");
        root.getChildren().add(gameElements.getBackgroundImage("res/game_bg.jpg"));

        //Menu bar
        root.getChildren().addAll(drawRectangle(840,0,210,600), drawRectangle(0, 450, 1050, 150));
        Text resistMenu = new Text(885, 50, "RESIST CARDS");
        resistMenu.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        root.getChildren().add(resistMenu);
        
        //Draw Planets
        root.getChildren().addAll(gameElements.drawPlanets(gc.getGameState().getPlanetLayout()));

        //Player Actions
        Buttons b = new Buttons();
        setButtonEvents(b.createPlayerButtons());
        root.getChildren().addAll(setButtonEvents(b.createPlayerButtons()));

        System.out.println(root.getChildren().size());
        redisplay();

        stage.setScene(scene);
        stage.show();
    }

    //update player controls/cards

    //Update Game info displayed
    public Text[] displayCurrentTurn(int playerTurn, int actionsLeft){
        Text[] toAdd = new Text[2];

        Text currTurn = new Text();
        Text actionsRemain = new Text();

        currTurn.setText("Current Turn: Player " + playerTurn);
        actionsRemain.setText("Actions Remaining: " + actionsLeft);

        currTurn.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        currTurn.setX(25);
        currTurn.setY(25);
        currTurn.setFill(Color.WHITE);

        actionsRemain.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        actionsRemain.setX(25);
        actionsRemain.setY(475);

        toAdd[0] = currTurn;
        toAdd[1] = actionsRemain;
        return toAdd;
    }

    public void initDisplay() {
        launch();
    }

    public void redisplay(){
        if(!firstRun){
            root.getChildren().remove(24, 58);
        }
        else{
            firstRun = false;
        }
        //Draw token counters
        root.getChildren().addAll(gameElements.updateTokenCounters(gc.getGameState().getPlanetLayout()));

        //Draw Flags
        root.getChildren().addAll(gameElements.drawFlags(gc.getGameState().getPlanetLayout()));

        //draw Cats
        root.getChildren().addAll(gameElements.drawCats(gc.getGameState().getPlayers()));

        //Display turn info
        root.getChildren().addAll(gameElements.displayCurrentTurn(gc.getGameState().getPlayerTurn()+1,gc.getActionCount(),gc.getGameState().getPlayers()[gc.getGameState().getPlayerTurn()].getCat().getScratchCount()));

        //Display game broadcast
        root.getChildren().addAll(gameMessage(broadcast));

        // Draw Resist Cards

        root.getChildren().addAll(setResistEvents(gameElements.displayResistCards(gc.getGameState().getPlayers()[gc.getGameState().getPlayerTurn()])));
        root.getChildren().addAll(gameElements.displayResistText(gc.getGameState().getPlayers()[gc.getGameState().getPlayerTurn()]));
        System.out.println(root.getChildren().size());

    }

    private Rectangle drawRectangle(int x, int y, int width, int height){

        Rectangle rect = new Rectangle(x,y,width,height);
        rect.setFill(Color.GREY);

        return rect;
    }

    private Text gameMessage(String msg){
        Text txt = new Text(20, 590, msg);
        txt.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        txt.setFill(Color.RED);
        return txt;
    }

    private Button[] setButtonEvents(Button[] playerActions){
        // Travel Up
        playerActions[0].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean validMove = gc.travel(2);
                if(validMove){
                    broadcast = "Move Up Successful!";
                }
                else{
                    broadcast = "You've reached the end of the galaxy! You can not move up!";
                }
                redisplay();
            }
        });
        // Travel Down
        playerActions[1].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean validMove = gc.travel(0);
                if(validMove){
                    broadcast = "Move Down Successful!";
                }
                else{
                    broadcast = "You've reached the end of the galaxy! You can not move down!";
                }
                redisplay();
            }
        });
        // Travel Left
        playerActions[2].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean validMove = gc.travel(1);
                if(validMove){
                    broadcast = "Move Left Successful!";
                }
                else{
                    broadcast = "You've reached the end of the galaxy! You can not move left!";
                }
                redisplay();
            }
        });
        // Travel Right
        playerActions[3].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean validMove = gc.travel(3);
                if(validMove){
                    broadcast = "Move Right Successful!";
                }
                else{
                    broadcast = "You've reached the end of the galaxy! You can not move right!";
                }
                redisplay();
            }
        });
        // Fight Fascism
        playerActions[4].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean validFight =  gc.fight();
                if(validFight){
                    broadcast = "Removed a Fascism Token from Current Planet!";
                }
                else{
                    broadcast = "There are no fascism tokens to remove here!";
                }
                redisplay();
            }
        });
        // Restock Resist Cards
        playerActions[5].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("RESTOCK BUTTON PRESSED");
                boolean validRestock = gc.restock();
                if(validRestock){
                    broadcast = "Successfully Restocked Resist Cards!";
                }
                else{
                    broadcast = "Your hand is full! You can not restock!";
                }
                redisplay();
            }
        });
        //Use Resist Card
        playerActions[6].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("RESIST BUTTON PRESSED");
                broadcast = "Please select a resist card in the Left Menu to use!";
                redisplay();
            }
        });
        //End Turn (early)
        playerActions[7].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("END BUTTON PRESSED");
                broadcast = "Turn Ended! Next player is up!";
                //AWAITING END TURN EARLY IMPLEMENTATION
                redisplay();
            }
        });
        return playerActions;
    }

    private Rectangle[] setResistEvents(Rectangle[] cardActions){
        // Travel Up
        cardActions[0].setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                System.out.println("RESIST 0 PICKED");
                Card toUse = gc.getGameState().getPlayers()[gc.getGameState().getPlayerTurn()].getDeck().getCards().get(0);
                if(Objects.isNull(toUse)){
                    broadcast = "No Resist Card to use!";
                }
                else{
                    gc.playCard(toUse);
                }
                redisplay();
            }
        });
        cardActions[1].setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                System.out.println("RESIST 1 PICKED");
                Card toUse = gc.getGameState().getPlayers()[gc.getGameState().getPlayerTurn()].getDeck().getCards().get(1);
                if(Objects.isNull(toUse)){
                    broadcast = "No Resist Card to use!";
                }
                else{
                    gc.playCard(toUse);
                }
                redisplay();
            }
        });
        cardActions[2].setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                System.out.println("RESIST 2 PICKED");
                Card toUse = gc.getGameState().getPlayers()[gc.getGameState().getPlayerTurn()].getDeck().getCards().get(2);
                if(Objects.isNull(toUse)){
                    broadcast = "No Resist Card to use!";
                }
                else{
                    gc.playCard(toUse);
                }
                redisplay();
            }
        });
        return cardActions;
    }
}
