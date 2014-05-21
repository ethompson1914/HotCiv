package hotciv.standard;

import hotciv.framework.AttackingStrategy;
import hotciv.framework.GameState;
import hotciv.framework.Unit;

public class StandardAttackingStrategy implements AttackingStrategy {

	@Override
	public boolean attackSuccessful(GameState gs, Unit a, Unit d, int attackingSupport,
			int attackingTerrainFactor, int defendingSupport,
			int defendingTerrainFactor) {
		return true;
	}


}
