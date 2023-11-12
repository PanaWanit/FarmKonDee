package logic.vegetable;

import logic.VegetableName;
import logic.base.BaseVegetable;
import logic.base.GrowthChance;
import logic.game.MaxGrowthTime;
import logic.game.OverallValue;
import logic.util.Randomizer;

/**
 * This class represents the Lime type vegetable.
 */
public class Lime extends BaseVegetable implements GrowthChance {
	/**
	 * Constructor for Lime
	 * @param lifePoint
	 * lifePoint
	 */
	public Lime(int lifePoint) {
		super(VegetableName.LIME, MaxGrowthTime.LIME_GROWTH, lifePoint, OverallValue.LIME_VALUE);
		
	}

	/**
	 * it has 25% chance to has an additional growth
	 * @return true if a random number from 1 to 100 is less than or equal 25
	 */
	@Override
	public boolean hasChance() {
		return Randomizer.random(100) <= 25;
	}

	/**
	 * Increase the current growth by 0.25 of maxGrowth
	 */
	@Override
	public void growth() {
		setGrowth(getGrowth() + getMaxGrowth()/4);
	}
	
}