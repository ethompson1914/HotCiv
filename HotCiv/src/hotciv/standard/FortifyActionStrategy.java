package hotciv.standard;

import hotciv.framework.ActionStrategy;
import hotciv.framework.GameState;
import hotciv.framework.Position;
import hotciv.framework.Unit;

public class FortifyActionStrategy implements ActionStrategy {

	@Override
	public void performAction(GameState gs, Position p) {
		Unit unit = gs.getUnitAt(p);
		if(unit.getActionCount()>0){
			if(unit.isFortified()){
				unit.setDefensiveStrength( unit.getDefensiveStrength()/2);
				unit.fortify();
				unit.resetMoveCount();
				unit.decrementActionCount();
				
			}
			else{
				unit.setDefensiveStrength( unit.getDefensiveStrength()*2);
				unit.fortify();
				unit.zeroMoveCount();
				unit.decrementActionCount();
			}		
		}
		
	}

}
