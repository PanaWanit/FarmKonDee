package logic;

/**
 * Stored all Vegetables name
 */
public enum VegetableName {
	/**
	 * Vegetable name orange
	 */
	ORANGE,
	/**
	 * Vegetable name Potato
	 */
	POTATO,
	/**
	 * Vegetable name Pumpkin
	 */
	PUMPKIN,
	/**
	 * Vegetable name Lime
	 */
	LIME;

	@Override
	public String toString() {
		return super.toString().substring(0, 1).toUpperCase() + super.toString().substring(1).toLowerCase();
	}
}
