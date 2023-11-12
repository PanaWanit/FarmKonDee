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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.game.MarketPrices;
import logic.Player;
import logic.VegetableName;
import logic.AnimalName;
import logic.GameController;
import logic.util.IDUtil;

/**
 * 
 * This class represent the animal store page
 *
 */
public class AnimalStorePage {
	/**
	 * A field represents the Label for the money
	 */
	@FXML
	private Label moneyLabel;
	/**
	 * A field represents the Label for the player's own chicken
	 */
	@FXML
	private Label chickenOwnLabel;
	/**
	 * A field represents the Label for the player's own cow
	 */
	@FXML
	private Label cowOwnLabel;
	/**
	 * A field represents the Text for the warning text
	 */
	@FXML
	private Text warningText;
	/**
	 * A field represents the int of max animal capacity that player can have
	 */
	private int maxAnimalCapacity = 12;

	/**
	 * A fxml initialize function when the page has been opened
	 */
	@FXML
	private void initialize() {
		update();
	}

	/**
	 * A function to update the current graphic to up with the data
	 */
	private void update() {
		informationExceededCapacity();
		moneyLabel.setText("Money: " + Integer.toString(GameController.getInstance().getPlayer().getMoney()));
		chickenOwnLabel.setText("Own: "
				+ Integer.toString(GameController.getInstance().getPlayer().getAnimalCount(AnimalName.CHICKEN)));
		cowOwnLabel.setText(
				"Own: " + Integer.toString(GameController.getInstance().getPlayer().getAnimalCount(AnimalName.COW)));
	}

	/**
	 * Checker for showing the warning text if player exceeded capacity of the
	 * animal
	 * 
	 * @return true if sum of the both animal exceed the max animal capacity
	 */
	private boolean informationExceededCapacity() {
		if (GameController.getInstance().getPlayer().getBothAnimalCount() == maxAnimalCapacity) {
			warningText.setVisible(true);
			return true;
		} else {
			warningText.setVisible(false);
			return false;
		}

	}

	/**
	 * A function to buy animal from the store by click at the buy button
	 * 
	 * @param event event from mouse event get from clicking the buy button
	 * @throws IOException
	 */
	@FXML
	private void buyAnimalFromStore(ActionEvent event) throws IOException {
		Button clickedButton = (Button) event.getSource();
		AnimalName buyAnimalName = IDUtil.getAnimalName(clickedButton.getId());
		int buyAnimalPrice = IDUtil.getAnimalPrice(clickedButton.getId());
		if (!informationExceededCapacity()) {
			if (GameController.getInstance().canBuy(buyAnimalPrice)) {
				GameController.getInstance().buy(buyAnimalName);
			}
		}
		update();
	}

	/**
	 * A function to sell animal to the store by click at the sell button
	 * 
	 * @param event event from mouse event get from clicking the sell button
	 * @throws IOException
	 */
	@FXML
	private void sellAnimalToStore(ActionEvent event) throws IOException {
		Button clickedButton = (Button) event.getSource();
		AnimalName sellAnimalName = IDUtil.getAnimalName(clickedButton.getId());
		if (GameController.getInstance().getPlayer().getAnimalCount(sellAnimalName) > 0) {
			GameController.getInstance().sell(sellAnimalName);
		}
		update();
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
