package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import logic.FarmLand;
import logic.GameController;
import logic.Player;
import logic.util.IDUtil;
import logic.util.TimeUtil;

/**
 * 
 * This class represent the main menu page
 *
 */
public class MainMenuPage {
	/**
	 * A field represents the Button to close the application
	 */
	private Button closeButton;

	/**
	 * A fxml initialize function when the page has been opened
	 */
	@FXML
	private void initialize() {
		GameController.setInstance(null);
		GameController.getInstance();
	}

	/**
	 * A event function for the button to exit the application
	 * 
	 * @param event event from mouse event
	 */
	@FXML
	private void handleCloseButtonAction(ActionEvent event) {
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}

	/**
	 * A event function for the button to switch the scene
	 * 
	 * @param event event from action event
	 */
	@FXML
	private void switchSceneEventHandler(ActionEvent event) {
		if (event.getSource() instanceof Button) {
			Button clickedButton = (Button) event.getSource();
			String buttonId = IDUtil.getSelectedSceneId(clickedButton.getId());
			Main.switchScene(buttonId + ".fxml");
		}
	}
}