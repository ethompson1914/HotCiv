package hotciv.standard;

import hotciv.framework.StrengthMultiplierStrategy;

import java.util.Random;

public class RandomStrengthMultiplierStrategy implements
		StrengthMultiplierStrategy {

	Random r = new Random();
	
	@Override
	public int getStrengthMultiplier() {
		return r.nextInt(6) + 1;
	}
}