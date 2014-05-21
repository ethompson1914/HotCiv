package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;

public class Legion extends UnitImpl{

	public Legion(Player owner){
		super(owner);
		attackStrength = GameConstants.LEGION_ATTACK;
		defensiveStrength = GameConstants.LEGION_DEFENSE;
		type = GameConstants.LEGION;
	}
}
