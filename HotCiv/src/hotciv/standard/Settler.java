package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;

public class Settler extends UnitImpl{

	public Settler(Player owner){
		super(owner);
		attackStrength = GameConstants.SETTLER_ATTACK;
		defensiveStrength = GameConstants.SETTLER_DEFENSE;
		type = GameConstants.SETTLER;
	}
}
