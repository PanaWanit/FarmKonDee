package logic.vegetable;

import logic.VegetableName;
import logic.base.BaseVegetable;
import logic.game.MaxGrowthTime;
import logic.game.OverallValue;
/**
 * This class represents the Potato type vegetable.
 */
public class Potato extends BaseVegetable {
	/**
	 * Constructor for Potato
	 * @param lifePoint
	 * lifePoint
	 */
	public Potato(int lifePoint) {
		super(VegetableName.POTATO, MaxGrowthTime.POTATO_GROWTH, lifePoint, OverallValue.POTATO_VALUE);
	}
	
}
