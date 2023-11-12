package logic.base;

import logic.AnimalName;
import logic.GameController;
import logic.Player;
import logic.game.ActivityTime;

/**
 * This class is a base class for all the Animal. It contains all common
 * elements of an Animal.
 */
abstract public class BaseAnimal {
	/**
	 * A field represents the name of a name.
	 */
	private final AnimalName name;
	/**
	 * A field represents the produce rate of animal
	 */
	private final int produceRate;
	/**
	 * A day left to make Animal produce a product
	 */
	private int leftProduceDate;
	/**
	 * A sell price of Animal product
	 */
	private final int value;

	/**
	 * set all fields according to the parameters.
	 * 
	 * @param name        name
	 * @param produceRate productRate
	 * @param value       value
	 */
	public BaseAnimal(AnimalName name, int produceRate, int value) {
		this.name = name;
		this.produceRate = produceRate;
		this.leftProduceDate = produceRate;
		this.value = value;
	}

	/**
	 * Checker for Collecting Animal Product
	 * 
	 * @return true if leftProduceDate equal 0 and Player has enough time to get
	 *         product
	 */
	public boolean canGetProduct() {
		return leftProduceDate == 0 && GameController.getInstance().canDoActivity(ActivityTime.GET_PRODUCT);
	}

	/**
	 * Get an Animal Product and increase player money by product value and increase
	 * day time by GET_PRODUCT
	 */
	public void getProduct() {
		GameController.getInstance().doActivity(ActivityTime.GET_PRODUCT);
		Player player = GameController.getInstance().getPlayer();
		player.setMoney(player.getMoney() + value);
		leftProduceDate = produceRate;
	}

	// getter and setter

	/**
	 * getter for Animal name
	 * 
	 * @return Animal name
	 */
	public AnimalName getName() {
		return name;
	}

	/**
	 * getter for Product Value
	 * 
	 * @return product value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * getter for leftProduceDate
	 * 
	 * @return leftProduceDate
	 */
	public int getLeftProduceDate() {
		return leftProduceDate;
	}

	/**
	 * setter for leftProduceDate
	 * 
	 * @param leftProduceDate leftProduceDate
	 */
	public void setLeftProduceDate(int leftProduceDate) {
		this.leftProduceDate = Math.max(0, leftProduceDate);
	}

	/**
	 * getter for produceRate
	 * 
	 * @return produceRate
	 */
	public int getProduceRate() {
		return produceRate;
	}

}
