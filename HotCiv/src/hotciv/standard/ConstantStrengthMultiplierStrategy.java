package hotciv.standard;

import hotciv.framework.StrengthMultiplierStrategy;

public class ConstantStrengthMultiplierStrategy implements
		StrengthMultiplierStrategy {

	@Override
	public int getStrengthMultiplier() {
		return 1;
	}

}
