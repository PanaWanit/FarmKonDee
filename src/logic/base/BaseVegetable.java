package logic.base;

import logic.GrowthStage;
import logic.VegetableName;

/**
 * This class is a base class for all the Vegetables. It contains all common
 * elements of a vegetable.
 */
abstract public class BaseVegetable {
	/**
	 * A field represents the name of a vegetable.
	 */
	private final VegetableName name;
	/**
	 * A field represents the max growth of a vegetable.
	 */
	private final int maxGrowth;
	/**
	 * A field represents the growth of a vegetable. Growth cannot be less than 0.
	 */
	private int growth;
	/**
	 * A field represents the life point of a vegetable. lifePoint cannot be less
	 * than 0.
	 */
	private int lifePoint;
	/**
	 * A field represents the sale value of a vegetable when the vegetable fully
	 * grows.
	 */
	private final int value;
	/**
	 * A field represents the growth stage of a vegetable.
	 */
	private GrowthStage growthStage;

	/**
	 * set all fields according to the parameters.
	 * 
	 * @param name      Vegetable name
	 * @param maxGrowth maxGrowth
	 * @param lifePoint start lifePoint
	 * @param value     harvesting value
	 */
	public BaseVegetable(VegetableName name, int maxGrowth, int lifePoint, int value) {
		this.name = name;
		this.maxGrowth = maxGrowth;
		setGrowth(1);
		setLifePoint(lifePoint);
		this.value = Math.max(1, value);
		setGrowthStage(GrowthStage.SEEDLING);
	}

	/**
	 * checker for dead status
	 * 
	 * @return return true if the vegetable’s lifePoint is lower or equal 0, return
	 *         false otherwise.
	 */
	public boolean isDead() {
		return getLifePoint() <= 0;
	}

	/**
	 * getter for Vegetable name
	 * 
	 * @return name
	 */
	public VegetableName getName() {
		return name;
	}

	/**
	 * getter for maxGrowth
	 * 
	 * @return maxGrowth
	 */
	public int getMaxGrowth() {
		return maxGrowth;
	}

	/**
	 * getter for growth
	 * 
	 * @return growth
	 */
	public int getGrowth() {
		return growth;
	}

	/**
	 *
	 * growth cannot be higher than maxGrowth <br>
	 * if growth is in field that can go to next stage change growthStage to next
	 * stage <br>
	 * SEEDLING: 1 -> maxGrowth/3 <br>
	 * SAPLING: maxGrowth/3+1 -> maxGrowth-1 <br>
	 * MATURE: maxGrowth
	 * 
	 * @param growth growth
	 */
	public void setGrowth(int growth) {
		this.growth = Math.max(Math.min(maxGrowth, growth), 1);
		if (this.growth < maxGrowth / 3) {
			setGrowthStage(GrowthStage.SEEDLING);
		}
		else if (this.growth < maxGrowth - 1) {
			setGrowthStage(GrowthStage.SAPLING);
		}
		else {
			setGrowthStage(GrowthStage.MATURE);
		}
	}

	/**
	 * getter for lifePoint
	 * 
	 * @return lifePoint
	 */
	public int getLifePoint() {
		return lifePoint;
	}

	/**
	 * setter for lifePoint
	 * 
	 * @param lifePoint lifePoint
	 */
	public void setLifePoint(int lifePoint) {
		// Change from 1 to 0
		this.lifePoint = Math.max(0, lifePoint);
	}

	/**
	 * getter for value
	 * 
	 * @return value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * getter for growthStage
	 * 
	 * @return growthStage
	 */
	public GrowthStage getGrowthStage() {
		return growthStage;
	}

	/**
	 * setter for growthStage
	 * 
	 * @param growthStage growthStage
	 */
	public void setGrowthStage(GrowthStage growthStage) {
		this.growthStage = growthStage;
	}
}