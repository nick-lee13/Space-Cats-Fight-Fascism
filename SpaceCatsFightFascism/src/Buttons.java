import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Buttons {
    public Button[] createPlayerButtons(){

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
