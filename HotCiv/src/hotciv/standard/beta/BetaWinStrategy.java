package hotciv.standard.beta;

import hotciv.framework.GameState;
import hotciv.framework.Player;
import hotciv.framework.Tile;
import hotciv.framework.WinningStrategy;

import java.util.List;

public class BetaWinStrategy implements WinningStrategy {

	@Override
	public Player getWinner(GameState gs) {
		boolean winnerFound = false;
		Player lastPlayer = gs.getTilesWithCities().get(0).getCity().getOwner();
		for(int i=1; i<gs.getTilesWithCities().size(); i++)
		{
			winnerFound = lastPlayer.equals(gs.getTilesWithCities().get(i).getCity().getOwner());
			lastPlayer= gs.getTilesWithCities().get(i).getCity().getOwner();
		}
		if(winnerFound){
			return lastPlayer;
		}
		return null;
	}

	
}
