import javafx.scene.control.Button;

public class Buttons {
    public Button[] createPlayerButtons(){

        Button travelUp = new Button();
        Button travelDown = new Button();
        Button travelLeft = new Button();
        Button travelRight = new Button();
        Button fightFascism = new Button();
        Button restock = new Button();
        Button playResistCard = new Button();
        Button endTurn = new Button();

        travelUp.setText("TRAVEL UP");
        travelDown.setText("TRAVEL DOWN");
        travelLeft.setText("TRAVEL LEFT");
        travelRight.setText("TRAVEL RIGHT");
        fightFascism.setText("FIGHT FASCISM");
        restock.setText("RESTOCK");
        playResistCard.setText("PLAY RESIST CARD");
        endTurn.setText("END TURN");

        travelUp.setLayoutX(75);
        travelDown.setLayoutX(60);
        travelLeft.setLayoutX(20);
        travelRight.setLayoutX(110);
        fightFascism.setLayoutX(220);
        restock.setLayoutX(370);
        playResistCard.setLayoutX(505);
        endTurn.setLayoutX(690);

        travelUp.setLayoutY(490);
        travelDown.setLayoutY(550);
        travelLeft.setLayoutY(520);
        travelRight.setLayoutY(520);
        fightFascism.setLayoutY(520);
        restock.setLayoutY(520);
        playResistCard.setLayoutY(520);
        endTurn.setLayoutY(520);

        Button[] buttons = {travelUp,travelDown,travelLeft,travelRight,fightFascism,restock,playResistCard,endTurn};
        return buttons;
    }
}
