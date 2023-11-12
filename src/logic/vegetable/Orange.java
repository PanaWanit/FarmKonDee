package logic.vegetable;

import logic.VegetableName;
import logic.base.BaseVegetable;
import logic.base.Recoverable;
import logic.game.MaxGrowthTime;
import logic.game.OverallValue;

/**
 * This class represents the Orange type vegetable.
 */
public class Orange extends BaseVegetable implements Recoverable {
	/**
	 * Constructor for Orange
	 * @param lifePoint
	 * lifePoint
	 */
	public Orange(int lifePoint) {
		super(VegetableName.ORANGE, MaxGrowthTime.ORANGE_GROWTH, lifePoint, OverallValue.ORANGE_VALUE);
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
	 * @return true if lifePoint is greater or equal -1 and vegetable is dead
	 */
	@Override
	public boolean isRecoverable() {
		return getLifePoint() >= -1 && isDead();
	}
	
	
}
