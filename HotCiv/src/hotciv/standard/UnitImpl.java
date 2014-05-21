package hotciv.standard;

import hotciv.framework.Player;
import hotciv.framework.Unit;

public class UnitImpl implements Unit {

	protected String type;
	protected Player owner;
	protected final int DISTANCE = 1;
	protected final int ACTION_COUNT_MAX = 1;
	protected int moveCount = DISTANCE;
	protected int defensiveStrength;
	protected boolean isFortified = false;
	protected int actionCount = 1;
	protected int attackStrength;

	public UnitImpl(Player owner) {
		this.owner = owner;
	}
	
	@Override
	public String getTypeString() {
		return type;
	}

	@Override
	public Player getOwner() {
		return owner;
	}

	@Override
	public int getMoveCount() {
		return Math.max(0, moveCount);
	}

	@Override
	public int getDefensiveStrength() {
		return defensiveStrength;
	}

	@Override
	public int getAttackingStrength() {
		return attackStrength;
	}

	@Override
	public void decrementMoveCount(){
		moveCount--;
	}

	@Override
	public void resetMoveCount(){
		moveCount = DISTANCE;
	}


	@Override
	public int getActionCount() {
		return actionCount;
	}

	@Override
	public void resetActionCount() {
		actionCount = ACTION_COUNT_MAX;
	}

	@Override
	public boolean isFortified() {		
		return isFortified;
	}

	@Override
	public void setDefensiveStrength(int newDefensiveStrength) {
		defensiveStrength = newDefensiveStrength;
		
	}

	@Override
	public void fortify() {
		isFortified = !isFortified;		
	}

	@Override
	public void decrementActionCount() {
		actionCount--;
		
	}

	@Override
	public void zeroMoveCount() {
		moveCount = 0;
		
	}


}
