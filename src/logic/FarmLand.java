package logic;

import java.util.ArrayList;

import logic.base.BaseVegetable;

/**
 * This class represents a square farmBlock. One block displays the vegetables
 * that have been planted as well as information about those vegetables.
 */
public class FarmLand {

	/**
	 * size of farm land that is the side of square
	 */
	private int farmSize;
	/**
	 * content of farm land
	 */
	private ArrayList<ArrayList<FarmBlock>> content;

	/**
	 * Create farm land
	 * 
	 * @param farmSize Farm Size
	 */
	public FarmLand(int farmSize) {
		this.content = new ArrayList<>();
		setFarmSize(farmSize);
	}

	/**
	 * Update all farm block for all content in this farm land
	 */
	public void updateAll() {
		for (ArrayList<FarmBlock> row : content) {
			for (FarmBlock farmBlock : row) {
				farmBlock.update();
			}
		}
	}

	/**
	 * getter for farmSize
	 * 
	 * @return farmSize
	 */
	public int getFarmSize() {
		return farmSize;
	}

	/**
	 * setter for Farm Size
	 * 
	 * @param farmSize Farm Size setter for farmSize and change content size to
	 *                 farmSize x farmSize new farmSize cannot less than old
	 *                 farmSize
	 */
	public void setFarmSize(int farmSize) {
		if (farmSize < this.farmSize) {
			return;
		}
		int different = farmSize - this.farmSize;

		// adding new rows
		for (int i = 0; i < different; i++) {
			ArrayList<FarmBlock> row = new ArrayList<>();
			for (int j = 0; j < farmSize; j++) {
				row.add(new FarmBlock(null, false));
			}
			this.content.add(row);
		}

		// adding additional column in old rows
		for (int i = 0; i < this.farmSize; i++) {
			for (int j = 0; j < different; j++) {
				this.content.get(i).add(new FarmBlock(null, false));
			}
		}

		this.farmSize = farmSize;
	}

	/**
	 * Getter for FarmBlock in FarmLand
	 * 
	 * @param row row that want to get from content
	 * @param col column that want to get from content
	 * @return FarmBlock that stored in content at (row, col)
	 */
	public FarmBlock getContent(int row, int col) {
		return this.content.get(row).get(col);
	}
}