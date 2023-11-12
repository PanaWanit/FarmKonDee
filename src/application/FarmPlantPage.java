package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.CyclicBarrier;

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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;
import logic.FarmBlock;
import logic.FarmLand;
import logic.GameController;
import logic.GrowthStage;
import logic.VegetableName;
import logic.base.BaseVegetable;
import logic.game.ActivityTime;
import logic.game.GameData;
import logic.util.IDUtil;
import logic.util.ObjectUtil;
import logic.util.TimeUtil;

/**
 * 
 * This class represent the farm plant page for planting the plant
 *
 */
public class FarmPlantPage {
	/**
	 * A field represents the GridPane where the plant located
	 */
	@FXML
	private GridPane plantGridPane;
	/**
	 * A field represents the Label for the time
	 */
	@FXML
	private Label timeLabel;
	/**
	 * A field represents the GridPane for all of the available item to use
	 */
	@FXML
	private GridPane itemGridPane;
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
	 * A field represents the Rectangle of the environment ambient light for the
	 * time
	 */
	@FXML
	private Rectangle ambient;
	/**
	 * A field represents the Text for the warning text
	 */
	@FXML
	private Text warningText;
	/**
	 * A field represents the String of the selected tool
	 */
	private String selectedTool;
	/**
	 * A field represents the Image of the selected tool
	 */
	private Image selectedToolImage;
	/**
	 * A field represents the String of the selected plant
	 */
	private String selectedPlant;
	/**
	 * A field represents the Image of the selected plant
	 */
	private Image selectedPlantImage;
	/**
	 * A field represents the BaseVegetable of which type of plant going to plant
	 */
	private BaseVegetable vegetableToPlant;
	/**
	 * A field represents the current FarmLand
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
		farmLand = GameController.getInstance().getFarmList().get(GameData.getCurrentFarmLand());
		potatoOwnLabel.setText("Own: "
				+ Integer.toString(GameController.getInstance().getPlayer().getVegetableCount(VegetableName.POTATO)));
		orangeOwnLabel.setText("Own: "
				+ Integer.toString(GameController.getInstance().getPlayer().getVegetableCount(VegetableName.ORANGE)));
		limeOwnLabel.setText("Own: "
				+ Integer.toString(GameController.getInstance().getPlayer().getVegetableCount(VegetableName.LIME)));
		pumpkinOwnLabel.setText("Own: "
				+ Integer.toString(GameController.getInstance().getPlayer().getVegetableCount(VegetableName.PUMPKIN)));
		timeLabel.setText("Time : " + TimeUtil.timeFormat(GameController.getInstance().getDayTime()));
		displayPlantPlot();
		setAmbientLight();
		informationExceededCapacity();
	}

	/**
	 * A function to display the graphic of current stage of plant plot
	 */
	private void displayPlantPlot() {
		for (int i = 0; i < farmLand.getFarmSize(); i++) {
			for (int j = 0; j < farmLand.getFarmSize(); j++) {
				for (Node child : plantGridPane.getChildren()) {
					if (GridPane.getRowIndex(child) == i && GridPane.getColumnIndex(child) == j) {
						StackPane individualPlantStackPane = (StackPane) child;
						displayIndividualPlantContent(individualPlantStackPane, i, j);
						break;
					}
				}
			}
		}
	}

	/**
	 * A function to display the single individual plant content in plant plot
	 * 
	 * @param individualPlantStackPane individual plant layer of contents
	 * @param row                      location of row that plant is located
	 * @param col                      location of column that plant is located
	 */
	private void displayIndividualPlantContent(StackPane individualPlantStackPane, int row, int col) {
		Label growthLabel = (Label) individualPlantStackPane.getChildren().get(3);
		Label hpLabel = (Label) individualPlantStackPane.getChildren().get(2);
		ImageView waterLayer = (ImageView) individualPlantStackPane.getChildren().get(0);
		ImageView vegetableLayer = (ImageView) individualPlantStackPane.getChildren().get(1);
		BaseVegetable individualPlantType = farmLand.getContent(row, col).getVegetable();
		if (farmLand.getContent(row, col).isWatered())
			waterLayer.setImage(new Image("images/WetPlot.png"));
		if (Objects.isNull(individualPlantType))
			return;

		String vegetableName = individualPlantType.getName().toString();
		GrowthStage vegetableStage = individualPlantType.getGrowthStage();
		growthLabel.setText("GROWTH : " + individualPlantType.getGrowth());
		hpLabel.setText("HP : " + individualPlantType.getLifePoint());
		if (farmLand.getContent(row, col).getVegetable().isDead())
			vegetableLayer.setImage(new Image("images/vegetable/DeadBush.png"));
		else
			vegetableLayer.setImage(IDUtil.getSelectedVegetableImage(vegetableName, vegetableStage));
	}

	/**
	 * Checker for showing the warning text if player exceeded capacity of the
	 * animal
	 * 
	 * @return true if the time of the day is exceeded
	 */
	private boolean informationExceededCapacity() {
		if (TimeUtil.currentHour(GameController.getInstance().getDayTime()) >= 20) {
			warningText.setVisible(true);
			return true;
		} else {
			warningText.setVisible(false);
			return false;
		}

	}

	/**
	 * A function to get both row and column from the StackPane in GridPane called
	 * plantGridPane
	 * 
	 * @param stackPane clicked StackPane
	 * @return the pair of integer that are row and column that the StackPane is
	 *         located in plantGridPane
	 */
	private Pair<Integer, Integer> getIndex(StackPane stackPane) {
		int row = GridPane.getRowIndex(stackPane);
		int col = GridPane.getColumnIndex(stackPane);
		return new Pair<Integer, Integer>(row, col);
	}

	/**
	 * A event function for the action of planting, watering, and harvest
	 * 
	 * @param event event from action event
	 * @throws IOException
	 */
	@FXML
	private void farmingAction(MouseEvent event) throws IOException {
		StackPane clickedPlot = (StackPane) event.getSource();
		Pair<Integer, Integer> PII = getIndex(clickedPlot);
		int row = PII.getKey(), col = PII.getValue();
		int currentPlotNumber = GameData.getCurrentFarmLand();
		FarmLand farmLand = GameController.getInstance().getFarmList().get(currentPlotNumber);

		if (plantVegetable(clickedPlot, row, col));
		else if (waterVegetable(clickedPlot, row, col));
		else if (useSickle(clickedPlot, row, col));
		update();
	}

	/**
	 * Checker and planting function for deciding if to plant the vegetable or not
	 * 
	 * @param clickedPlot clickedPlot
	 * @param row         selected row
	 * @param col         selected column
	 * @return true if plant the vegetable
	 */
	private boolean plantVegetable(StackPane clickedPlot, int row, int col) {
		if (selectedTool != null)
			return false;
		ImageView clickedImage = (ImageView) clickedPlot.getChildren().get(1);
		if (farmLand.getContent(row, col).canPlant(IDUtil.getVegetableName(selectedPlant))) {
			clickedImage.setImage(selectedPlantImage);
			((Label) clickedPlot.getChildren().get(2)).setText("HP : " + vegetableToPlant.getLifePoint());
			((Label) clickedPlot.getChildren().get(3)).setText("GROWTH : " + vegetableToPlant.getGrowth());
			vegetableToPlant = ObjectUtil.createVegetable(selectedPlant);
			farmLand.getContent(row, col).plant(vegetableToPlant);
			int vegetableCount = GameController.getInstance().getPlayer()
					.getVegetableCount(IDUtil.getVegetableName(selectedPlant));
			if (vegetableCount == 0) {
				for (Node node : itemGridPane.getChildren()) {
					StackPane stackPane = (StackPane) node;
					((Rectangle) stackPane.getChildren().get(0)).setFill(Color.WHITE);
					((Rectangle) stackPane.getChildren().get(0)).setOpacity(1);
				}
			}
		}

		return true;
	}

	/**
	 * Checker and watering function for deciding if to water the plant or not
	 * 
	 * @param clickedPlot clickedPlot
	 * @param row         selected row
	 * @param col         selected column
	 * @return true if water the vegetable
	 */
	private boolean waterVegetable(StackPane clickedPlot, int row, int col) {
		if (!selectedTool.equals("wateringPot"))
			return false;
		ImageView clickedImage = (ImageView) clickedPlot.getChildren().get(0);
		if (farmLand.getContent(row, col).canWater()) {
			clickedImage.setImage(selectedToolImage);
			farmLand.getContent(row, col).water();
		}
		return true;
	}

	/**
	 * Checker and sickle function for deciding to use the sickle or not
	 * 
	 * @param clickedPlot clickedPlot
	 * @param row         selected row
	 * @param col         selected column
	 * @return true if going to use sickle to harvest or remove dead plant
	 */
	private boolean useSickle(StackPane clickedPlot, int row, int col) {
		if (!selectedTool.equals("sickle"))
			return false;
		ImageView clickedImage = (ImageView) clickedPlot.getChildren().get(1);
		if (farmLand.getContent(row, col).canHarvest()) {
			farmLand.getContent(row, col).harvest();
			clickedImage.setImage(null);
			((Label) clickedPlot.getChildren().get(2)).setText(null);
			((Label) clickedPlot.getChildren().get(3)).setText(null);
		} else if (farmLand.getContent(row, col).getVegetable().isDead()) {
			farmLand.getContent(row, col).setVegetable(null);
			clickedImage.setImage(null);
			((Label) clickedPlot.getChildren().get(2)).setText(null);
			((Label) clickedPlot.getChildren().get(3)).setText(null);
		}
		return true;
	}

	/**
	 * A event function for the action of painting item background
	 * 
	 * @param clickedItemBackground clicked background of item
	 */
	private void paintSelectedItemBackground(Rectangle clickedItemBackground) {
		for (Node node : itemGridPane.getChildren()) {
			StackPane stackPane = (StackPane) node;
			((Rectangle) stackPane.getChildren().get(0)).setFill(Color.WHITE);
			((Rectangle) stackPane.getChildren().get(0)).setOpacity(1);
		}
		clickedItemBackground.setFill(Color.DARKGOLDENROD);
		clickedItemBackground.setOpacity(0.3);
	}

	/**
	 * A event function for the action of select the vegetable that going to plant
	 * 
	 * @param event mouse event from clicked vegetable to plant
	 * @throws IOException
	 */
	@FXML
	private void selectVegetableToPlant(MouseEvent event) throws IOException {
		StackPane clickedItem = (StackPane) event.getSource();
		ImageView clickedImage = (ImageView) clickedItem.getChildren().get(1);
		Rectangle clickedItemBackground = (Rectangle) clickedItem.getChildren().get(0);

		selectedPlant = clickedImage.getId();
		selectedTool = null;
		vegetableToPlant = ObjectUtil.createVegetable(selectedPlant);
		int vegetableCount = GameController.getInstance().getPlayer()
				.getVegetableCount(IDUtil.getVegetableName(selectedPlant));
		if (vegetableCount == 0) {
			return;
		}
		paintSelectedItemBackground(clickedItemBackground);
		selectedPlantImage = IDUtil.getSelectedVegetableImage(selectedPlant, GrowthStage.SEEDLING);
	}

	/**
	 * A event function for the action of select the tool that going to use
	 * 
	 * @param event mouse event from clicked tool to use
	 * @throws IOException
	 */
	@FXML
	private void selectToolToUse(MouseEvent event) throws IOException {
		StackPane clickedItem = (StackPane) event.getSource();
		ImageView clickedImage = (ImageView) clickedItem.getChildren().get(1);
		Rectangle clickedItemBackground = (Rectangle) clickedItem.getChildren().get(0);
		paintSelectedItemBackground(clickedItemBackground);
		selectedTool = clickedImage.getId();
		selectedToolImage = IDUtil.getSelectedToolImage(selectedTool);
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
