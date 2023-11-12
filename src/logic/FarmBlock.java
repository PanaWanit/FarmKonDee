package logic;

import logic.base.BaseVegetable;
import logic.base.GrowthChance;
import logic.base.Recoverable;
import logic.game.ActivityTime;
import logic.util.IDUtil;

/**
 * This class represent each block of farm land property
 */
public class FarmBlock {
	/**
	 * Vegetable in this Block
	 */
	private BaseVegetable vegetable;
	/**
	 * Watered status
	 */
	private boolean isWatered;

	/**
	 * Create new FarmBlock
	 * 
	 * @param vegetable Vegetable that plant on this block
	 * @param isWatered Watered status on this block
	 */
	public FarmBlock(BaseVegetable vegetable, boolean isWatered) {
		setVegetable(vegetable);
		setWatered(isWatered);
	}

	/**
	 * Update the block status at the end of the day
	 */
	public void update() {
		if (getVegetable() == null) {
			setWatered(false);
			return;
		}
		if (isWatered() == false) {
			getVegetable().setLifePoint(getVegetable().getLifePoint() - 1);
			return;
		}

		// has Vegetable and Watered
		if (getVegetable() instanceof Recoverable && ((Recoverable) getVegetable()).isRecoverable()) {
			((Recoverable) getVegetable()).recover();
		} else if (getVegetable() instanceof GrowthChance && ((GrowthChance) getVegetable()).hasChance()) {
			((GrowthChance) getVegetable()).growth();
		} else {
			getVegetable().setGrowth(getVegetable().getGrowth() + 1);
		}
		setWatered(false);
	}

	/**
	 * Checker for this block can if it can water
	 * 
	 * @return true if this block is not watered and has enough time to do this
	 *         activity
	 */
	public boolean canWater() {
		return !isWatered() && GameController.getInstance().canDoActivity(ActivityTime.WATERING_TIME);
	}

	/**
	 * Water this block and increase day time by WATERING_TIME
	 */
	public void water() {
		setWatered(true);
		GameController.getInstance().doActivity(ActivityTime.WATERING_TIME);
	}

	/**
	 * Checker for this block if it can plant
	 * 
	 * @param selectedPlant vegetable that player want to plant
	 * @return true if there is no plant on this block and has enough time to do
	 *         this activity and player has this vegetable seed
	 */
	public boolean canPlant(VegetableName selectedPlant) {
		return getVegetable() == null && GameController.getInstance().canDoActivity(ActivityTime.PLANTING_TIME)
				&& GameController.getInstance().getPlayer().getVegetableCount(selectedPlant) != 0;
	}

	/**
	 * Plant vegetable in this block and increase day time by PLANTING_TIME
	 * 
	 * @param vegetable vegetable to plant
	 */
	public void plant(BaseVegetable vegetable) {
		Player player = GameController.getInstance().getPlayer();
		setVegetable(vegetable);
		GameController.getInstance().doActivity(ActivityTime.PLANTING_TIME);
		player.putVegetableCount(vegetable.getName(), player.getVegetableCount(vegetable.getName()) - 1);
	}

	/**
	 * Checker for this block if it can harvest
	 * 
	 * @return true if there is a plant in this block and plant stage is MATURE and
	 *         has enough time to do this activity
	 */
	public boolean canHarvest() {
		return getVegetable() != null && getVegetable().getGrowthStage() == GrowthStage.MATURE
				&& GameController.getInstance().canDoActivity(ActivityTime.HARVESTING_TIME);
	}

	/**
	 * Harvest vegetable in this block increase player money by plant value and
	 * remove plant on this block and increase day time by HARVESTING_TIME
	 */
	public void harvest() {
		Player player = GameController.getInstance().getPlayer();
		player.setMoney(player.getMoney() + getVegetable().getValue());
		setVegetable(null);
		GameController.getInstance().doActivity(ActivityTime.HARVESTING_TIME);
	}

	/**
	 * getter for vegetable
	 * 
	 * @return Vegetable that plant on this block
	 */
	public BaseVegetable getVegetable() {
		return vegetable;
	}

	/**
	 * setter for vegetable
	 * 
	 * @param vegetable Vegetable that set on this block
	 */
	public void setVegetable(BaseVegetable vegetable) {
		this.vegetable = vegetable;
	}

	/**
	 * getter for isWatered
	 * 
	 * @return isWatered
	 */
	public boolean isWatered() {
		return isWatered;
	}

	/**
	 * setter for isWatered
	 * 
	 * @param isWatered boolean of watered status
	 */
	public void setWatered(boolean isWatered) {
		this.isWatered = isWatered;
	}

}
