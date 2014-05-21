package hotciv.standard.zeta;

import hotciv.framework.AttackingStrategy;
import hotciv.framework.GameState;
import hotciv.framework.Unit;

public class ZetaAttackingStrategy implements AttackingStrategy {

	@Override
	public boolean attackSuccessful(GameState gs, Unit a, Unit d, int attackingSupport,
			int attackingTerrainFactor, int defendingSupport,
			int defendingTerrainFactor) {
		gs.incrementPlayerWinCount(a.getOwner());
		return true;
	}
	

}
