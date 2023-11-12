package logic.animal;

import logic.AnimalName;
import logic.base.BaseAnimal;
import logic.game.MarketPrices;
import logic.game.OverallValue;

/**
 * This class represents the Chicken type Animal.
 */
public class Chicken extends BaseAnimal {
	/**
	 * Constructor for chicken
	 */
	public Chicken() {
		super(AnimalName.CHICKEN, 2, OverallValue.CHICKEN_VALUE);
	}

}
