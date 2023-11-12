package logic.base;

/**
 * This interface defines methods for the Vegetable that can has a chance to
 * grow additional growth
 */
public interface GrowthChance {
	/**
	 * Chance to has additional growth
	 * 
	 * @return true if it has chance to grow up
	 */
	boolean hasChance();

	/**
	 * This method will be called when the Vegetable has grown.
	 */
	void growth();
}
