package hotciv.standard.zeta;

import java.util.List;

import hotciv.framework.GameState;
import hotciv.framework.Player;
import hotciv.framework.Tile;
import hotciv.framework.WinningStrategy;
import hotciv.standard.beta.BetaWinStrategy;
import hotciv.standard.epsilon.EpsilonWinStrategy;

public class ZetaCiv20RoundsWinStrategyTestStub implements WinningStrategy {

	WinningStrategy wsState ;
	BetaWinStrategy betaWinState;
	EpsilonWinStrategy epsilonWinState;
	int roundNumber;

	public ZetaCiv20RoundsWinStrategyTestStub(){
		betaWinState = new BetaWinStrategy();
		epsilonWinState = new EpsilonWinStrategy();
		wsState = betaWinState;
		roundNumber = 20;
	}
	@Override
	public Player getWinner(GameState gs) {
		if(roundNumber == 20){
			wsState = epsilonWinState;
		}
		roundNumber++;
		return wsState.getWinner(gs);
	}
	
	
}
