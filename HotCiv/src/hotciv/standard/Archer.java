package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;

public class Archer extends UnitImpl {

	public Archer(Player owner){
		super(owner);
		attackStrength = GameConstants.ARCHER_ATTACK;
		defensiveStrength = GameConstants.ARCHER_DEFENSE;
		type = GameConstants.ARCHER;
		moveCount= DISTANCE;
	}


}
