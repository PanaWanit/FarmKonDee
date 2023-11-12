package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Pair;
import logic.GameController;
import logic.base.BaseAnimal;
import logic.util.IDUtil;
import logic.util.Randomizer;
import logic.util.TimeUtil;
import logic.AnimalName;

/**
 * 
 * This class represent the animal farm page
 *
 */
public class AnimalFarmPage {

	/**
	 * A field represents the GridPane of where the animal locating
	 */
	@FXML
	private GridPane animalGridPane;
	/**
	 * A field represents the Label of the current time of the day
	 */
	@FXML
	private Label timeLabel;
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
		update();
		displayAnimal();
	}

	/**
	 * A function to update the current graphic to up with the data
	 */
	private void update() {
		timeLabel.setText("Time: " + TimeUtil.timeFormat(GameController.getInstance().getDayTime()));
		setAmbientLight();
	}

	/**
	 * A function to display animal according to the location in animal grid pane
	 */
	private void displayAnimal() {
		ArrayList<BaseAnimal> animalList = GameController.getInstance().getAnimalList();
		for (int i = 0; i < animalList.size(); i++) {
			Pair<Integer, Integer> PII = Randomizer.getEmptySlot(animalGridPane, 3, 4);
			int row = PII.getKey(), col = PII.getValue();
			Image animalImage = IDUtil.getSelectedAnimalImage(animalList.get(i).getName().toString(), false);
			ImageView animalImageView = new ImageView();
			ImageView productImageView = new ImageView();
			if (animalList.get(i).canGetProduct()) {
				productImageView = IDUtil.getAnimalProductImage(animalList.get(i).getName().toString());
				productImageView.setId(String.valueOf(i));
				productImageView.setOnMouseClicked(event -> getProductFromAnimal(event));
				animalImage = IDUtil.getSelectedAnimalImage(animalList.get(i).getName().toString(), true);
			}
			animalImageView.setImage(animalImage);
			animalGridPane.add(animalImageView, col, row);
			animalGridPane.add(productImageView, col, row);
		}
	}

	/**
	 * A event function for collect the product from the animal by clicking the
	 * product
	 * 
	 * @param event event from mouse event get from clicking the product
	 */
	@FXML
	private void getProductFromAnimal(MouseEvent event) {
		ArrayList<BaseAnimal> animalList = GameController.getInstance().getAnimalList();
		ImageView clickedProduct = (ImageView) event.getSource();
		int animalNumber = Integer.parseInt(clickedProduct.getId());
		animalList.get(animalNumber).getProduct();
		clickedProduct.setImage(null);
		update();
	}

	/**
	 * A function to set the ambient light for the environment ambient light for the
	 * time
	 */
	private void setAmbientLight() {
		int currentTime = TimeUtil.currentHour(GameController.getInstance().getDayTime());
		if (currentTime >= 18) {
			ambient.setFill(Color.DARKBLUE);
			ambient.setOpacity(0.3);
		} else if (currentTime >= 16) {
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
