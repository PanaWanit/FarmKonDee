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
import javafx.stage.Stage;
import logic.game.MarketPrices;
import logic.util.IDUtil;
import logic.AnimalName;
import logic.GameController;
import logic.Player;
import logic.VegetableName;
import logic.vegetable.Lime;
import logic.vegetable.Orange;
import logic.vegetable.Potato;
import logic.vegetable.Pumpkin;

/**
 * 
 * This class represent the vegetable store page
 *
 */
public class VegetableStorePage {
	/**
	 * A field represents the Label for the money
	 */
	@FXML
	private Label moneyLabel;
	/**
	 * A field represents the Label for the player's own potato
	 */
	@FXML
	private Label potatoOwnLabel;
	/**
	 * A field represents the Label for the player's own orange
	 */
	@FXML
	private Label orangeOwnLabel;
	/**
	 * A field represents the Label for the player's own lime
	 */
	@FXML
	private Label limeOwnLabel;
	/**
	 * A field represents the Label for the player's own pumpkin
	 */
	@FXML
	private Label pumpkinOwnLabel;

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
		moneyLabel.setText("Money: " + Integer.toString(GameController.getInstance().getPlayer().getMoney()));
		potatoOwnLabel.setText("Own: "
				+ Integer.toString(GameController.getInstance().getPlayer().getVegetableCount(VegetableName.POTATO)));
		orangeOwnLabel.setText("Own: "
				+ Integer.toString(GameController.getInstance().getPlayer().getVegetableCount(VegetableName.ORANGE)));
		limeOwnLabel.setText("Own: "
				+ Integer.toString(GameController.getInstance().getPlayer().getVegetableCount(VegetableName.LIME)));
		pumpkinOwnLabel.setText("Own: "
				+ Integer.toString(GameController.getInstance().getPlayer().getVegetableCount(VegetableName.PUMPKIN)));
	}

	/**
	 * A function to buy vegetable from the store by click at the buy button
	 * 
	 * @param event event from mouse event get from clicking the buy button
	 * @throws IOException
	 */
	@FXML
	private void buyVegetableFromStore(ActionEvent event) throws IOException {
		Button clickedButton = (Button) event.getSource();
		VegetableName buyVegetableName = IDUtil.getVegetableName(clickedButton.getId());
		int buyVegetablePrice = IDUtil.getVegetablePrice(clickedButton.getId());
		if (GameController.getInstance().canBuy(buyVegetablePrice)) {
			GameController.getInstance().buy(buyVegetableName);
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
