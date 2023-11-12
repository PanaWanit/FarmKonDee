package logic.animal;

import logic.AnimalName;
import logic.base.BaseAnimal;
import logic.game.MarketPrices;
import logic.game.OverallValue;

/**
 * This class represents the Cow type Animal.
 */
public class Cow extends BaseAnimal {
	/**
	 * Constructor for Cow
	 */
	public Cow() {
		super(AnimalName.COW, 3, OverallValue.COW_VALUE);
	}

}
