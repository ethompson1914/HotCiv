package hotciv.standard;

import hotciv.framework.AgingStrategy;

public class StandardAgeStrategy implements AgingStrategy {
	
	@Override
	public int calculateAge(int age) {		
		return age+100;
	}
	

}
