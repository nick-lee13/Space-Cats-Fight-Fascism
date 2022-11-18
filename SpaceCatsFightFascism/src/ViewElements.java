import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ViewElements {

	//Tries to return ImageView for background
    public ImageView getBackgroundImage(String path){
        try(InputStream is = Files.newInputStream(Paths.get(path))){
			ImageView img = new ImageView(new Image(is));
			img.setFitWidth(1050);
			img.setFitHeight(600);
			return img;
		}
		catch(IOException e) {
			System.out.println("Couldn't load image");
            return null;
		}
    }

	public Rectangle[] drawPlanets(Planet[][] planetLayout){
		Rectangle[] planetIcons = new Rectangle[12];
		int count = 0;
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 4; j++){
				Rectangle rect = new Rectangle(60+(j*190), 105+(i*110), 150, 80);
				//rect.setFill(new ImagePattern(planetLayout[i][j].getImage()));
				rect.setFill(Color.LIGHTBLUE);
				planetIcons[count] = rect;
				count++;
			}
		}

		return planetIcons;
	}

	//update token counts
    public Text[] updateTokenCounters(Planet[][] planetLayout){
        Text[] tokenCounts = new Text[12];
        int count = 0;
        for(int i = 0; i < 3; i++){
			for(int j = 0; j < 4; j++){
                int currToken = planetLayout[i][j].getTokens();
				Text text = new Text("TOKENS: "+ Math.abs(currToken));
                text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
                text.setX(65+(j*190));
                text.setY(120+(i*110));
                if(currToken < 0){
                    text.setFill(Color.BLUE);
                }
                else if(currToken > 0){
                    text.setFill(Color.ORANGE);
                }
                else{
                    text.setFill(Color.GRAY);
                }
                tokenCounts[count] = text;
                count++;
			}
		}
        return tokenCounts;
    }

	//update flags
    public Text[] drawFlags(Planet[][] planetLayout){
        Text[] flags = new Text[12];
        int count = 0;
        for(int i = 0; i < 3; i++){
			for(int j = 0; j < 4; j++){
                int currToken = planetLayout[i][j].getTokens();
				Text text = new Text();
				if(currToken >= 4){
					text.setText("LIB");
                    text.setFill(Color.BLUE);
                }
                else if(currToken <= -4){
					text.setText("OCC");
                    text.setFill(Color.ORANGE);
                }
                else{
                    text.setFill(Color.TRANSPARENT);
                }
                text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
                text.setX(180+(j*190));
                text.setY(120+(i*110));
                flags[count] = text;
                count++;
            }
        }
		return flags;
    }

	//update cat locations
    public Circle[] drawCats(Player[] players){
        Circle[] catIcons = new Circle[players.length];

        for(int i = 0; i < players.length; i++){
            int x = 80 + (players[i].getPlayerNum()*35) + (players[i].getCat().getPlanet().getIndex()[1]*190);
            int y = 160 + (players[i].getCat().getPlanet().getIndex()[0]*110);
            Circle catLoc = new Circle(x,y,15);
            if(players[i].getPlayerNum() == 0){ //CHANGE TO CAT ICONS! possible with P1 Text or something
                catLoc.setFill(Color.GREEN);
            }
            if(players[i].getPlayerNum() == 1){
                catLoc.setFill(Color.RED);
            }
            catIcons[i] = catLoc;
        }

        return catIcons;
    }

	//Update Game info displayed
    public Text[] displayCurrentTurn(int playerTurn, int actionsLeft, int scratchCount){
        Text[] toAdd = new Text[2];

        Text currTurn = new Text();
        Text actionsRemain = new Text();

        currTurn.setText("Current Turn: Player " + playerTurn + " Scratch Count: " + scratchCount);
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

	//Update resist cards available
	public Rectangle[] displayResistCards(Player player){
		Rectangle[] cardIcons = new Rectangle[3];
		for(int i = 0; i < 3; i++){
			Rectangle rect = new Rectangle(880,70+(175*i),140,160);
			rect.setFill(Color.ORANGE);
			cardIcons[i] = rect;
		}
		return cardIcons;
	}

	public Text[] displayResistText(Player player){
		Text[] texts = new Text[3];
		for(int i = 0; i < 3; i++){
			Text txt = new Text(880,70+(175*i),player.getDeck().getCards().get(i).getName());
			txt.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
			texts[i] = txt;
		}
		return texts;
	}

    public Title getTitle(String titleText){
        Title title = new Title (titleText);
		title.setTranslateX(50);
		title.setTranslateY(50);

        return title;
    }

    public VBox getMenu(int menuID){
        MenuBox vbox;
        if(menuID == 0){
            vbox = new MenuBox(
				new MenuItem("2 PLAYER"),
				new MenuItem("3 PLAYER"),
				new MenuItem("4 PLAYER"),
				new MenuItem("GITHUB REPO"),	
				new MenuItem("QUIT"));
        }
        else{
            vbox = new MenuBox(
				new MenuItem("PLAYFUL KITTENS (EASIEST)"),
				new MenuItem("COURAGREOUS CUBS (EASY)"),
				new MenuItem("SPACE CATS (NORMAL)"),
				new MenuItem("LEGENDARY LEOPARDS (HARD)"),	
				new MenuItem("SUPERNOVA TIGERS (HARDEST)"));
        }
		vbox.setTranslateX(100);
		vbox.setTranslateY(150);

        return vbox;
    }

    private class Title extends StackPane{
		public Title(String name) {
			Rectangle bg = new Rectangle(375, 60);
			bg.setStroke(Color.WHITE);
			bg.setStrokeWidth(2);
			bg.setFill(null);
			
			Text text = new Text(name);
			text.setFill(Color.WHITE);
			text.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 50));
			
			setAlignment(Pos.CENTER);
			getChildren().addAll(bg,text);
		}
	}

    private class MenuBox extends VBox{
		public MenuBox(MenuItem...items) {
			getChildren().add(createSeperator());
			
			for(MenuItem item : items) {
				getChildren().addAll(item, createSeperator());
			}
		}
		
		private Line createSeperator() {
			Line sep = new Line();
			sep.setEndX(210);
			sep.setStroke(Color.DARKGREY);
			return sep;
		}
		
	}

    private class MenuItem extends StackPane{
		public MenuItem(String name) {
			LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, new Stop[] { 
				new Stop(0, Color.ORANGE),
				new Stop(0.1, Color.BLACK),
				new Stop(0.9, Color.BLACK),
				new Stop(1, Color.ORANGE)
				
			});
			
			Rectangle bg = new Rectangle(200,30);
			bg.setOpacity(0.4);
			
			Text text = new Text(name);
			text.setFill(Color.DARKGREY);
			text.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD,20));
			
			setAlignment(Pos.CENTER);
			getChildren().addAll(bg, text);
			setOnMouseEntered(event -> {
				bg.setFill(gradient);
				text.setFill(Color.WHITE);
				
			});
			
			setOnMouseExited(event -> {
				bg.setFill(Color.BLACK);
				text.setFill(Color.DARKGREY);
			});
			setOnMousePressed(event -> {
				bg.setFill(Color.ORANGE);
				getClickEvent(name);
			});
			
			setOnMouseReleased(event -> {
				bg.setFill(gradient);
			});
			
			}
		}

        private void getClickEvent(String name){
            if(name.equals("2 PLAYER")){

            }
            else if(name.equals("PLAYFUL KITTENS")){

            }
        }
}
