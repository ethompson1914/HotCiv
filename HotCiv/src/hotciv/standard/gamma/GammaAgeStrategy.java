package hotciv.standard.gamma;

import hotciv.framework.AgingStrategy;

public class GammaAgeStrategy implements AgingStrategy {

	@Override
	public int calculateAge(int age) {		
		return age+100;
	}
	

}
