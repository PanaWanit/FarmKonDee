package logic.vegetable;

import logic.VegetableName;
import logic.base.BaseVegetable;
import logic.base.GrowthChance;
import logic.base.Recoverable;
import logic.game.MaxGrowthTime;
import logic.game.OverallValue;
import logic.util.Randomizer;

/**
 * This class represents the Pumpkin type vegetable.
 */
public class Pumpkin extends BaseVegetable implements Recoverable, GrowthChance {

	/**
	 * Constructor for Pumpkin
	 * @param lifePoint
	 * lifePoint
	 */
	public Pumpkin(int lifePoint) {
		super(VegetableName.PUMPKIN, MaxGrowthTime.PUMPKIN_GROWTH, lifePoint, OverallValue.PUMPKIN_VALUE);
	}

	/**
	 * it has 15% chance to has an additional growth
	 * @return true if a random number from 1 to 100 is less than or equal 15
	 */
	@Override
	public boolean hasChance() {
		return Randomizer.random(100) <= 15;
	}

	/**
	 * Increase the current growth by 0.25 of maxGrowth
	 */
	@Override
	public void growth() {
		setGrowth(getGrowth() + getMaxGrowth()/3);
	}

	/**
	 * setLifePoint to max between 1 and current lifePoint
	 */
	@Override
	public void recover() {
		setLifePoint( Math.max(getLifePoint(), 1) );
	}
	/**
	 *
	 * @return true if lifePoint is greater or equal -2 and vegetable is dead
	 */
	@Override
	public boolean isRecoverable() {
		return getLifePoint() >= -2 && isDead();
	}
	
	
}
