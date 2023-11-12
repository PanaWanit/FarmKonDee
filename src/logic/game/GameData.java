package logic.game;

/**
 * This class memorizes the current open FarmLand.
 */
public class GameData {
	/**
	 * Stored index of current FarmLand
	 */
	private static int currentFarmLand;

	/**
	 * getter for currentFarmLand
	 * 
	 * @return currentFarmLand
	 */
	public static int getCurrentFarmLand() {
		return currentFarmLand;
	}

	/**
	 * setter for currentFarmLand
	 * 
	 * @param currentFarmLand currentFarmLand
	 */
	public static void setCurrentFarmLand(int currentFarmLand) {
		GameData.currentFarmLand = currentFarmLand;
	}
}
