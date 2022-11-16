import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.Pane;

public class MainMenu extends Application{
	
	private Parent createContent() {
		Pane root = new Pane();
		
		root.setPrefSize(1050, 600);
		
		MenuItems menuElements = new MenuItems();

		//Main Menu
		root.getChildren().add(menuElements.getBackgroundImage());
		root.getChildren().add(menuElements.getTitle("N E W   G A M E"));
		root.getChildren().add(menuElements.getMenu(1));
		
		return root;
		
	}
	@Override
	public void start(Stage primaryStage) throws Exception{
		Scene scene = new Scene(createContent());
		primaryStage.setTitle("Space Cats Fight Fascism");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
    
}