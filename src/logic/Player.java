package logic;

import java.util.HashMap;

/**
 * This class represents the player on this game
 */
public class Player {
	/**
	 * Current Player's Money
	 */
	private int money;
	/**
	 * Vegetable seed counter of this player
	 */
	private HashMap<VegetableName, Integer> vegetableCounter;
	/**
	 * Animal in farm counter of this player
	 */
	private HashMap<AnimalName, Integer> animalCounter;

	/**
	 * Create Player with start money 1000 and empty vegetable, animal counter
	 */
	public Player() {
		setMoney(1000);
		setVegetableCounter(new HashMap<VegetableName, Integer>());
		setAnimalCounter(new HashMap<AnimalName, Integer>());
	}

	/**
	 * Set a vegetable count
	 * 
	 * @param name  Vegetable name
	 * @param count Count
	 */
	public void putVegetableCount(VegetableName name, int count) {
		count = Math.max(count, 0);
		getVegetableCounter().put(name, count);
	}

	/**
	 * Get a vegetable seed count
	 * 
	 * @param name Vegetable name
	 * @return if vegetable name in vegetableCounter return it counts otherwise
	 *         return 0
	 */
	public int getVegetableCount(VegetableName name) {
		if (getVegetableCounter().containsKey(name)) {
			return getVegetableCounter().get(name);
		}
		return 0;
	}

	/**
	 * set an Animal count
	 * 
	 * @param name  Animal name
	 * @param count Count
	 */
	public void putAnimalCount(AnimalName name, int count) {
		count = Math.max(count, 0);
		getAnimalCounter().put(name, count);
	}

	/**
	 * Get an Animal count
	 * 
	 * @param name Animal name
	 * @return if Animal name in animalCounter return it counts otherwise return 0
	 */
	public int getAnimalCount(AnimalName name) {
		if (getAnimalCounter().containsKey(name)) {
			return getAnimalCounter().get(name);
		}
		return 0;
	}

	/**
	 * Get All Animals Count that player own
	 * 
	 * @return number of animals that player own
	 */
	public int getBothAnimalCount() {
		return getAnimalCount(AnimalName.CHICKEN) + getAnimalCount(AnimalName.COW);
	}

	/**
	 * getter for money
	 * 
	 * @return money
	 */
	public int getMoney() {
		return money;
	}

	/**
	 * setter for money
	 * 
	 * @param money money that use to set
	 */
	public void setMoney(int money) {
		this.money = money;
	}

	/**
	 * getter for vegetableCounter
	 * 
	 * @return vegetableCounter
	 */
	public HashMap<VegetableName, Integer> getVegetableCounter() {
		return vegetableCounter;
	}

	/**
	 * setter for vegetableCounter
	 * 
	 * @param vegetableCounter vegetableCounter
	 */
	public void setVegetableCounter(HashMap<VegetableName, Integer> vegetableCounter) {
		this.vegetableCounter = vegetableCounter;
	}

	/**
	 * getter for animalCounter
	 * 
	 * @return animalCounter
	 */
	public HashMap<AnimalName, Integer> getAnimalCounter() {
		return animalCounter;
	}

	/**
	 * setter for animalCounter
	 * 
	 * @param animalCounter animalCounter
	 */
	public void setAnimalCounter(HashMap<AnimalName, Integer> animalCounter) {
		this.animalCounter = animalCounter;
	}
}
