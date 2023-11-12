package logic;

import java.util.ArrayList;

import logic.base.BaseAnimal;
import logic.game.ActivityTime;
import logic.util.IDUtil;
import logic.util.ObjectUtil;

/**
 * Stored Game Database and System
 */
public class GameController {
	/**
	 * Player
	 */
	private final Player player;
	/**
	 * All farm Land
	 */
	private final ArrayList<FarmLand> farmList;
	/**
	 * Animal Farm
	 */
	private final ArrayList<BaseAnimal> animalList;
	/**
	 * Day Time
	 */
	private int dayTime;
	/**
	 * Current Day
	 */
	private int currentDay;
	/**
	 * Minimum money that use to complete a game objective
	 */
	private int endMoneyGoal = 3000;

	/**
	 * game instance
	 */
	private static GameController instance;

	/**
	 * Construct default Game Setup
	 */
	public GameController() {
		this.player = new Player();
		this.farmList = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			this.farmList.add(new FarmLand(3));
		}
		this.animalList = new ArrayList<>();
		setDayTime(0);
		setCurrentDay(1);
	}

	/**
	 * Getter for GameController Instance
	 * 
	 * @return Game Instance
	 */
	public static GameController getInstance() {
		if (instance == null) {
			instance = new GameController();
		}
		return instance;
	}

	/**
	 * Buy Item from Market
	 * 
	 * @param name name item that want to buy
	 */
	public void buy(Object name) {
		int price = 0;
		if (name instanceof VegetableName) {
			price = IDUtil.getVegetablePrice(name.toString());
			player.putVegetableCount((VegetableName) name, player.getVegetableCount((VegetableName) name) + 1);
		} else if (name instanceof AnimalName) {
			price = IDUtil.getAnimalPrice(name.toString());
			this.animalList.add(ObjectUtil.createAnimal(name.toString()));
			player.putAnimalCount((AnimalName) name, player.getAnimalCount((AnimalName) name) + 1);
		}
		player.setMoney(player.getMoney() - price);
	}

	/**
	 * Sell Item (Only Animal)
	 * 
	 * @param name (Animal) name that player want to sell
	 */
	public void sell(Object name) {
		int price = IDUtil.getAnimalPrice(name.toString());
		if (name instanceof AnimalName) {
			AnimalName animalName = (AnimalName) name;
			for (int i = 0; i < this.animalList.size(); i++) {
				if (this.animalList.get(i).getName().toString().equals(animalName.toString())) {
					this.animalList.remove(i);
					break;
				}
			}
			player.setMoney(player.getMoney() + price);
			player.putAnimalCount((AnimalName) name, player.getAnimalCount((AnimalName) name) - 1);
		}
	}

	/**
	 * Checker that player can buy item
	 * 
	 * @param price price of item
	 * @return true if player's money is enough to buy item and has enough time to
	 *         buy
	 */
	public boolean canBuy(int price) {
		return player.getMoney() >= price && canDoActivity(ActivityTime.BUYING_TIME);
	}

	/**
	 * Checker for time for doing activity
	 * 
	 * @param activityTime time use to do activity
	 * @return true if it has enough time to do activity
	 */
	public boolean canDoActivity(int activityTime) {
		return getDayTime() + activityTime <= ActivityTime.DAY_TIME_LIMIT;
	}

	/**
	 * increase dayTime by activityTime
	 * 
	 * @param activityTime time use to do activity
	 */
	public void doActivity(int activityTime) {
		setDayTime(getDayTime() + activityTime);
	}

	/**
	 * Check if Money is enough for Game Objective
	 * 
	 * @return true if player money greater or equal endMoneyGoal
	 */
	public boolean isMoneyPassEndGame() {
		return player.getMoney() >= endMoneyGoal;
	}
	// getter and setter

	/**
	 * getter for player
	 * 
	 * @return player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * getter for dayTime
	 * 
	 * @return dayTime
	 */
	public int getDayTime() {
		return dayTime;
	}

	/**
	 * setter for dayTime
	 * 
	 * @param dayTime dayTime
	 */
	public void setDayTime(int dayTime) {
		this.dayTime = dayTime;
	}

	/**
	 * getter for currentDay
	 * 
	 * @return currentDay
	 */
	public int getCurrentDay() {
		return currentDay;
	}

	/**
	 * setter for currentDay
	 * 
	 * @param currentDay currentDay
	 */
	public void setCurrentDay(int currentDay) {
		this.currentDay = currentDay;
	}

	/**
	 * getter for farmList
	 * 
	 * @return farmList
	 */
	public ArrayList<FarmLand> getFarmList() {
		return farmList;
	}

	/**
	 * getter for animalList
	 * 
	 * @return animalList
	 */
	public ArrayList<BaseAnimal> getAnimalList() {
		return animalList;
	}

	/**
	 * setter for instance
	 * 
	 * @param instance instance
	 */
	public static void setInstance(GameController instance) {
		GameController.instance = instance;
	}
}
