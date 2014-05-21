package hotciv.standard.epsilon;

import hotciv.framework.GameState;
import hotciv.framework.Player;
import hotciv.framework.Tile;
import hotciv.framework.WinningStrategy;

import java.util.List;



public class EpsilonWinStrategy implements WinningStrategy{
	
	@Override
	public Player getWinner(GameState gs) {
		if(gs.getPlayerWinCount(Player.RED) == 3)
			return Player.RED;
		else if(gs.getPlayerWinCount(Player.BLUE) == 3)
			return Player.BLUE;
		return null;
	}

}
