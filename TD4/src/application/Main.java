package application;

import javafx.application.Application;
import javafx.stage.Stage;
import java.net.URL;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.fxml.*;
import javafx.scene.layout.VBox;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) 
	{
		try 
		{
			URL fxmlURL=getClass().getResource("/vue/produits.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
			Node root = fxmlLoader.load();
			Scene scene = new Scene((VBox) root, 600, 400);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Ma premiere fenetre JavaFX");
			primaryStage.show();
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}