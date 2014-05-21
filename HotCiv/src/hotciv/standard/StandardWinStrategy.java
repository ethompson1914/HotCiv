package hotciv.standard;

import hotciv.framework.GameState;
import hotciv.framework.Player;
import hotciv.framework.WinningStrategy;

public class StandardWinStrategy implements WinningStrategy {

	@Override
	public Player getWinner(GameState gs) {
		if(gs.getAge() == -3000){
			return Player.RED;
		}
		return null;
	}

}
