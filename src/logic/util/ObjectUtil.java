package logic.util;

import logic.base.BaseAnimal;
import logic.base.BaseVegetable;
import logic.vegetable.Lime;
import logic.vegetable.Orange;
import logic.vegetable.Potato;
import logic.vegetable.Pumpkin;
import logic.animal.Chicken;
import logic.animal.Cow;

/**
 * Tool that use to create an Animal or Vegetable Object from it name
 */
public class ObjectUtil {
	/**
	 * Create a Vegetable Object from String
	 * 
	 * @param name Vegetable name
	 * @return BaseVegetable Object that match it name
	 */
	public static BaseVegetable createVegetable(String name) {
		if (name.toLowerCase().equals("lime")) {
			return new Lime(2);
		} else if (name.toLowerCase().equals("orange")) {
			return new Orange(2);
		} else if (name.toLowerCase().equals("potato")) {
			return new Potato(1);
		} else if (name.toLowerCase().equals("pumpkin")) {
			return new Pumpkin(3);
		}
		return null;
	}

	/**
	 * Create an Animal Object from String
	 * 
	 * @param name Animal name
	 * @return BaseAnimal Object that match it name
	 */
	public static BaseAnimal createAnimal(String name) {
		if (name.toLowerCase().equals("cow")) {
			return new Cow();
		} else if (name.toLowerCase().equals("chicken")) {
			return new Chicken();
		}
		return null;
	}
}
