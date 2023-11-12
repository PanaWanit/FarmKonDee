package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import logic.GameController;
import logic.util.IDUtil;
import logic.util.TimeUtil;

/**
 * 
 * This class represent the city page
 *
 */
public class CityPage {

	/**
	 * A field represents the Label for the money
	 */
	@FXML
	private Label moneyLabel;
	/**
	 * A field represents the Rectangle of the environment ambient light for the
	 * time
	 */
	@FXML
	private Rectangle ambient;

	/**
	 * A fxml initialize function when the page has been opened
	 */
	@FXML
	private void initialize() {
		moneyLabel.setText("Money: " + Integer.toString(GameController.getInstance().getPlayer().getMoney()));
		setAmbientLight();
	}

	/**
	 * A function to set the ambient light for the environment ambient light for the
	 * time
	 */
	private void setAmbientLight() {
		int currentTime = TimeUtil.currentHour(GameController.getInstance().getDayTime());
		int dark = 18;
		int evening = 16;
		if (currentTime >= dark) {
			ambient.setFill(Color.DARKBLUE);
			ambient.setOpacity(0.3);
		} else if (currentTime >= evening) {
			ambient.setFill(Color.BROWN);
			ambient.setOpacity(0.2);
		} else {
			ambient.setFill(null);
		}
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
