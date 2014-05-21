package hotciv.framework;


public interface WinningStrategy {
	
	/**
	 * Determines the winner of the game
	 * @param gs The current game
	 * @return a player who has one the game if the winning conditions are met, null otherwise
	 */
	 public Player getWinner(GameState gs);
	 

	 
}