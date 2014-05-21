package hotciv.standard.epsilon;

import hotciv.framework.AttackingStrategy;
import hotciv.framework.GameState;
import hotciv.framework.StrengthMultiplierStrategy;
import hotciv.framework.Unit;

import java.util.Random;

public class EpsilonAttackingStrategy implements AttackingStrategy {

	private StrengthMultiplierStrategy strengthMultiplierStrategy;
	
	public EpsilonAttackingStrategy(StrengthMultiplierStrategy strengthMultiplierStrategy){
		this.strengthMultiplierStrategy = strengthMultiplierStrategy;
	}
	
	
	@Override
	public boolean attackSuccessful(GameState gs, Unit a, Unit d, int attackingSupport, int attackingTerrainFactor, 
			int defendingSupport, int defendingTerrainFactor) {	
		
		int aTotalAttackStrength = (a.getAttackingStrength()+attackingSupport)*attackingTerrainFactor;
		int dTotalDefensiveStrength = (d.getDefensiveStrength()+defendingSupport)*defendingTerrainFactor;
		boolean attackIsSuccessful = aTotalAttackStrength*strengthMultiplierStrategy.getStrengthMultiplier()
				> dTotalDefensiveStrength*strengthMultiplierStrategy.getStrengthMultiplier();
		
		if(attackIsSuccessful){
			gs.incrementPlayerWinCount(a.getOwner());
		}
		return attackIsSuccessful;
	}
}
