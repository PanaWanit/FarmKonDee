package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import logic.GameController;
import logic.Player;

/**
 * 
 * This class represent the main class
 *
 */
public class Main extends Application {
	/**
	 * A field represents the MediaPlayer of which the music going to play
	 */
	private MediaPlayer backgroundMusicPlayer;
	/**
	 * A field represents the Stage of primaryStage
	 */
	private static Stage primaryStage;

	/**
	 * JavaFX method. Called when the JavaFX application launches.
	 * 
	 * @param primaryStage The main stage of the application.
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			URL bgmFile = ClassLoader.getSystemResource("music/BGM.mp3");
			Media bgm = new Media(bgmFile.toString());
			backgroundMusicPlayer = new MediaPlayer(bgm);
			backgroundMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
			backgroundMusicPlayer.play();
			Main.primaryStage = primaryStage;
			switchScene("MainMenuPage.fxml");
			primaryStage.setResizable(false);
			primaryStage.setTitle("FarmKonDee");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * A function to change the scene
	 * 
	 * @param fxmlFile fxml file name that going to be change to
	 */
	public static void switchScene(String fxmlFile) {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxmlFile));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * A function to stop the application
	 */
	@Override
	public void stop() {
		backgroundMusicPlayer.stop();
		backgroundMusicPlayer.dispose();
	}

	/**
	 * Launches the JavaFX application.
	 * 
	 * @param args Application arguments.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
