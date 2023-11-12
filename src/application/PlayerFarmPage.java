package application;

import java.io.IOException;
import java.net.SecureCacheResponse;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Pair;
import logic.FarmBlock;
import logic.FarmLand;
import logic.GameController;
import logic.GrowthStage;
import logic.VegetableName;
import logic.base.BaseAnimal;
import logic.base.BaseVegetable;
import logic.game.GameData;
import logic.util.IDUtil;
import logic.util.Randomizer;
import logic.util.TimeUtil;

/**
 * 
 * This class represent the player farm page
 *
 */
public class PlayerFarmPage {
	/**
	 * A field represents the Label for the money
	 */
	@FXML
	private Label moneyLabel;
	/**
	 * A field represents the Label for the day
	 */
	@FXML
	private Label dayLabel;
	/**
	 * A field represents the Label for the time
	 */
	@FXML
	private Label timeLabel;
	/**
	 * A field represents the GridPane of where the animal locating
	 */
	@FXML
	private GridPane animalPlot;
	/**
	 * A field represents the GridPane of farm plot number 0
	 */
	@FXML
	private GridPane farmPlot0;
	/**
	 * A field represents the GridPane of farm plot number 1
	 */
	@FXML
	private GridPane farmPlot1;
	/**
	 * A field represents the GridPane of farm plot number 2
	 */
	@FXML
	private GridPane farmPlot2;
	/**
	 * A field represents the GridPane of farm plot number 3
	 */
	@FXML
	private GridPane farmPlot3;
	/**
	 * A field represents the GridPane of farm plot number 4
	 */
	@FXML
	private GridPane farmPlot4;
	/**
	 * A field represents the GridPane of farm plot number 5
	 */
	@FXML
	private GridPane farmPlot5;
	/**
	 * A field represents the Rectangle of the environment ambient light for the
	 * time
	 */
	@FXML
	private Rectangle ambient;
	/**
	 * A field represents the specific FarmLand
	 */
	private FarmLand farmLand;

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
		dayLabel.setText("Day : " + Integer.toString(GameController.getInstance().getCurrentDay()));
		moneyLabel.setText("Money : " + Integer.toString(GameController.getInstance().getPlayer().getMoney()));
		timeLabel.setText("Time : " + TimeUtil.timeFormat(GameController.getInstance().getDayTime()));
		displayAnimalPlot();
		displayPlantPlot();
		setAmbientLight();
	}

	/**
	 * A function to display animal to the GridPane called animalPlot
	 */
	private void displayAnimalPlot() {
		animalPlot.getChildren().clear();
		ArrayList<BaseAnimal> animalList = GameController.getInstance().getAnimalList();
		for (int i = 0; i < animalList.size(); i++) {
			Pair<Integer, Integer> PII = Randomizer.getEmptySlot(animalPlot, 4, 4);
			int row = PII.getKey(), col = PII.getValue();
			Image animalImage = IDUtil.getSelectedAnimalImage(animalList.get(i).getName().toString(), false);
			ImageView animalImageViewer = new ImageView();
			if (animalList.get(i).getName().toString().equals("Chicken")) {
				animalImageViewer.setFitWidth(30); // set an initial size
				animalImageViewer.setFitHeight(30);
			} else {
				animalImageViewer.setFitWidth(50); // set an initial size
				animalImageViewer.setFitHeight(50);
			}
			animalImageViewer.setImage(animalImage);
			animalPlot.add(animalImageViewer, col, row);
		}
	}

	/**
	 * A function to display plant to the farm plots according to the data of
	 * FarmLand
	 */
	private void displayPlantPlot() {
		GridPane[] farmPlot = { farmPlot0, farmPlot1, farmPlot2, farmPlot3, farmPlot4, farmPlot5 };
		for (int current = 0; current < 6; current++) {
			farmLand = GameController.getInstance().getFarmList().get(current);
			farmPlot[current].getChildren().clear();
			for (int i = 0; i < farmLand.getFarmSize(); i++) {
				for (int j = 0; j < farmLand.getFarmSize(); j++) {
					displayIndividualPlant(i, j, current, farmLand.getContent(i, j).getVegetable(), farmPlot);
				}
			}
		}
	}

	/**
	 * A function to display the single individual plant in plant plot
	 * 
	 * @param row                 location of row that plant is located
	 * @param col                 location of column that plant is located
	 * @param current             FarmLand current number
	 * @param individualPlantType Type of plant in this location
	 */
	private void displayIndividualPlant(int row, int col, int current, BaseVegetable individualPlantType,
			GridPane[] farmPlot) {
		ImageView plantBackground = new ImageView();
		if (farmLand.getContent(row, col).isWatered()) {
			plantBackground.setImage(new Image("images/WetPlot.png"));
			plantBackground.setFitWidth(18);
			plantBackground.setFitHeight(18);
			farmPlot[current].add(plantBackground, col, row);
		}
		if (Objects.isNull(individualPlantType))
			return;
		String vegetableName = individualPlantType.getName().toString();
		GrowthStage vegetableStage = individualPlantType.getGrowthStage();
		ImageView plantImage = new ImageView();
		plantImage.setFitWidth(15);
		plantImage.setFitHeight(15);
		if (farmLand.getContent(row, col).getVegetable().isDead())
			plantImage.setImage(new Image("images/vegetable/DeadBush.png"));
		else
			plantImage.setImage(IDUtil.getSelectedVegetableImage(vegetableName, vegetableStage));
		farmPlot[current].add(plantImage, col, row);
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
	 * A event function for end the day
	 * 
	 * @param event event from mouse event get from clicking the end of the day
	 *              button
	 * @throws IOException
	 */
	@FXML
	private void EndTheDay(ActionEvent event) throws IOException {
		GameController.getInstance().setDayTime(0);
		GameController.getInstance().setCurrentDay(GameController.getInstance().getCurrentDay() + 1);
		for (int i = 0; i < 6; i++) {
			FarmLand farmLand = GameController.getInstance().getFarmList().get(i);
			farmLand.updateAll();
		}
		ArrayList<BaseAnimal> animalList = GameController.getInstance().getAnimalList();
		for (int i = 0; i < animalList.size(); i++) {
			animalList.get(i).setLeftProduceDate(animalList.get(i).getLeftProduceDate() - 1);
		}
		update();
		if (GameController.getInstance().isMoneyPassEndGame() || GameController.getInstance().getCurrentDay() == 25) {
			Main.switchScene("EndingPage.fxml");
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

	/**
	 * A event function for switch to the selected farm plot
	 * 
	 * @param event event from action event
	 * @throws IOException
	 */
	@FXML
	private void switchToSelectedFarmPlot(ActionEvent event) throws IOException {
		Button clickedButton = (Button) event.getSource();
		int selectedFarmPlotNumber = Integer.parseInt(IDUtil.getSelectedFarmPlotNumber(clickedButton.getId()));
		GameData.setCurrentFarmLand(selectedFarmPlotNumber);
		Parent root = FXMLLoader.load(getClass().getResource("FarmPlotPage.fxml"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}