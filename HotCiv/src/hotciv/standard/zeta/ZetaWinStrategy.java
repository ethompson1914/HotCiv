package hotciv.standard.zeta;

import hotciv.framework.GameState;
import hotciv.framework.Player;
import hotciv.framework.WinningStrategy;
import hotciv.standard.beta.BetaWinStrategy;
import hotciv.standard.epsilon.EpsilonWinStrategy;

public class ZetaWinStrategy implements WinningStrategy {

	WinningStrategy wsState ;
	BetaWinStrategy betaWinState;
	EpsilonWinStrategy epsilonWinState;
	int roundNumber;
	
	public ZetaWinStrategy(){
		betaWinState = new BetaWinStrategy();
		epsilonWinState = new EpsilonWinStrategy();
		wsState = betaWinState;
		roundNumber = 0;
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
