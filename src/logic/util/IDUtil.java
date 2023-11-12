package logic.util;

import java.net.URL;

import application.FarmPlantPage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.AnimalName;
import logic.GrowthStage;
import logic.VegetableName;
import logic.base.BaseAnimal;
import logic.game.MarketPrices;

/**
 * Tool that use for manage Object from ID
 */
public class IDUtil {
	/**
	 * Get a VegetableName from String
	 * 
	 * @param id vegetable name String
	 * @return VegetableName that name match with id
	 */
	public static VegetableName getVegetableName(String id) {
		if (id.toLowerCase().equals("lime")) {
			return VegetableName.LIME;
		} else if (id.toLowerCase().equals("orange")) {
			return VegetableName.ORANGE;
		} else if (id.toLowerCase().equals("potato")) {
			return VegetableName.POTATO;
		} else if (id.toLowerCase().equals("pumpkin")) {
			return VegetableName.PUMPKIN;
		}
		return null;
	}

	/**
	 * Get a Vegetable price from String
	 * 
	 * @param id vegetable name String
	 * @return Vegetable price that name match with id
	 */
	public static int getVegetablePrice(String id) {
		if (id.toLowerCase().equals("lime")) {
			return MarketPrices.LIME_PRICE;
		} else if (id.toLowerCase().equals("orange")) {
			return MarketPrices.ORANGE_PRICE;
		} else if (id.toLowerCase().equals("potato")) {
			return MarketPrices.POTATO_PRICE;
		} else if (id.toLowerCase().equals("pumpkin")) {
			return MarketPrices.PUMPKIN_PRICE;
		}
		return 0;
	}

	/**
	 * Get a AnimalName from String
	 * 
	 * @param id animal name String
	 * @return AnimalName that name match with id
	 */
	public static AnimalName getAnimalName(String id) {
		if (id.toLowerCase().equals("chicken")) {
			return AnimalName.CHICKEN;
		} else if (id.toLowerCase().equals("cow")) {
			return AnimalName.COW;
		}
		return null;
	}

	/**
	 * Get an Animal Price from String
	 * 
	 * @param id animal name String
	 * @return Animal Price that name match with id
	 */
	public static int getAnimalPrice(String id) {
		if (id.toLowerCase().equals("chicken")) {
			return MarketPrices.CHICKEN_PRICE;
		} else if (id.toLowerCase().equals("cow")) {
			return MarketPrices.COW_PRICE;
		}
		return 0;
	}

	/**
	 * Create a game toolbar Image from String
	 * 
	 * @param id image name
	 * @return javafx Image Object that match with id
	 */
	public static Image getSelectedToolImage(String id) {
		URL imagePath = null;
		String imageName = id.substring(0, 1).toUpperCase() + id.substring(1) + ".png";
		if (imageName.equals("WateringPot.png")) {
			imagePath = ClassLoader.getSystemResource("images/WetPlot.png");
		} else {
			imagePath = ClassLoader.getSystemResource("images/tool/" + imageName);
		}
		return new Image(imagePath.toString());
	}

	/**
	 * Create an Animal Product ImageView from String
	 * 
	 * @param id animal name String
	 * @return javafx ImageView Object that match with animalName
	 */			
	public static ImageView getAnimalProductImage(String id) {
		URL imagePath = null;
		ImageView productImageView = new ImageView();
		if (id.equals(AnimalName.COW.toString())) {
			imagePath = ClassLoader.getSystemResource("images/product/Milk.png");
		} else if (id.equals(AnimalName.CHICKEN.toString())) {
			imagePath = ClassLoader.getSystemResource("images/product/Egg.png");
		}
		Image productImage = new Image(imagePath.toString());
		productImageView.setImage(productImage);
		return productImageView;
	}

	/**
	 * Create a Vegetable Image from String and GrowthStage
	 * 
	 * @param id          vegetable name
	 * @param growthStage growth stage
	 * @return javafx Image Object that match with id and growthStage
	 */
	public static Image getSelectedVegetableImage(String id, GrowthStage growthStage) {
		URL imagePath = null;
		String imageName = id.substring(0, 1).toUpperCase() + id.substring(1).toLowerCase() + ".png";
		if (growthStage.equals(GrowthStage.SEEDLING)) {
			imagePath = ClassLoader.getSystemResource("images/vegetable/Seedling.png");
		} else if (growthStage.equals(GrowthStage.SAPLING)) {
			imagePath = ClassLoader.getSystemResource("images/vegetable/Sapling.png");
		} else {
			imagePath = ClassLoader.getSystemResource("images/vegetable/" + imageName);
		}
		return new Image(imagePath.toString());
	}

	/**
	 * Create a Animal Image from String and status stage
	 * 
	 * @param id    animal name
	 * @param stage animal product stage
	 * @return javafx Image Object that match with id and stage
	 */
	public static Image getSelectedAnimalImage(String id, boolean stage) {
		String status = new String();
		if (stage) {
			status = "Ready.png";
		} else {
			status = Randomizer.random(3) + ".png";
		}
		URL imagePath = ClassLoader.getSystemResource("images/animal/" + id + status);
		return new Image(imagePath.toString());
	}

	/**
	 * Get Index from Farm Plot String
	 * 
	 * @param id selected farm plot
	 * @return String index of current selected farm Plot
	 */
	public static String getSelectedFarmPlotNumber(String id) {
		return id.substring(id.length() - 1, id.length());
	}

	/**
	 * Get selected scene from id
	 * 
	 * @param id scene name id from button fx:id
	 * @return scene name according to the fxml page
	 */
	public static String getSelectedSceneId(String id) {
		return id.substring(0, 1).toUpperCase() + id.substring(1, id.length() - 6);
	}
}
