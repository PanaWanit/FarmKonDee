package logic;

/**
 * Stored all Vegetables Growth Stage
 */
public enum GrowthStage {
	/**
	 * Seedling Stage
	 */
	SEEDLING,
	/**
	 * Sapling Stage
	 */
	SAPLING,
	/**
	 * Mature Stage
	 */
	MATURE;

	/**
	 * Get next stage of current Growth
	 * @return go to next Stage SEEDLING -> SAPLING -> MATURE if stage is MATURE do
	 *         nothing
	 */
	GrowthStage nextStage() {
		if (this == GrowthStage.SEEDLING) {
			return GrowthStage.SAPLING;
		}
		return GrowthStage.MATURE;
	}
}
