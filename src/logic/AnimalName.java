package logic;

/**
 * Stored all Animals name
 */
public enum AnimalName {
	/**
	 * Animal Name Cow
	 */
	COW,
	/**
	 * Animal Name Chicken
	 */
	CHICKEN;

	@Override
	public String toString() {
		return super.toString().substring(0, 1).toUpperCase() + super.toString().substring(1).toLowerCase();
	}

}
