package logic.util;

import java.util.Random;

import javafx.scene.layout.GridPane;
import javafx.util.Pair;

/**
 * Random Tool
 */
public class Randomizer {
	/**
	 * Randomizer
	 */
	private static Random rnd;

	/**
	 * random Integer number
	 * 
	 * @param number max-number
	 * @return random number that less than or equals <i>number</i>
	 */
	public static int random(int number) {
		if (rnd == null) {
			rnd = new Random();
		}
		return rnd.nextInt(number) + 1;
	}

	/**
	 * Random spawn point for animal in farm
	 * 
	 * @param gridPane Animal farm plane
	 * @param maxRow   max Row of Animal farm plane
	 * @param maxCol   max Column of Animal farm plane
	 * @return a random pair of row and column that empty in animal farm
	 */
	public static Pair<Integer, Integer> getEmptySlot(GridPane gridPane, int maxRow, int maxCol) {
		int row = Randomizer.random(maxRow) - 1, col = Randomizer.random(maxCol) - 1;
		if (gridPane.getChildren()
				.filtered(node -> GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col)
				.isEmpty()) {
			return new Pair<Integer, Integer>(row, col);
		} else {
			return getEmptySlot(gridPane, maxRow, maxCol);
		}
	}

}
