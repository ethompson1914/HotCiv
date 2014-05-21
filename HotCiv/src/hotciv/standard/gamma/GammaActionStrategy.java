package hotciv.standard.gamma;

import hotciv.framework.ActionStrategy;
import hotciv.framework.GameConstants;
import hotciv.framework.GameState;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.standard.FortifyActionStrategy;
import hotciv.standard.SettlingActionStrategy;

public class GammaActionStrategy implements ActionStrategy{
	
	private ActionStrategy fortify;
	private ActionStrategy settling;
	
	public GammaActionStrategy(ActionStrategy newFortify, ActionStrategy newSettling){
		fortify = newFortify;
		settling = newSettling;		
	}
			
			
	@Override
	public void performAction(GameState gs, Position p) {
		
		
		if(gs.getTileAt(p).getUnit().getActionCount()!=0){
			if(gs.getTileAt(p).getUnit().getTypeString() == GameConstants.ARCHER){
				fortify.performAction(gs, p);
			}
			else if(gs.getTileAt(p).getUnit().getTypeString() == GameConstants.SETTLER){
				settling.performAction(gs, p);
			}
			//For legion
			else {}
		}
	}
}
