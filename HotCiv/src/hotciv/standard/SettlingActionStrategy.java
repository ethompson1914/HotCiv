package hotciv.standard;

import hotciv.framework.ActionStrategy;
import hotciv.framework.GameConstants;
import hotciv.framework.GameState;
import hotciv.framework.Position;

public class SettlingActionStrategy implements ActionStrategy {

	@Override
	public void performAction(GameState gs, Position p) {	
			gs.createCity(p);
	}

}
