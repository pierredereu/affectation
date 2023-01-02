package hud;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
	public void start(Stage stage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
		Scene scene = new Scene(root);
		
		FXMLLoader.load(getClass().getResource("CreerAffectation.fxml"));
		
		
        stage.setScene(scene);
        stage.setTitle("Main Menu");
        stage.show();
	}
	
	public static void main(String[] args) {
	        Application.launch(args);
	}

}
