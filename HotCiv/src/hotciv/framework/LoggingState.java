package hotciv.framework;

public interface LoggingState {
	
	/**
	 * Takes a message and prints it to the appropriate place i.e. file and/or console
	 * @param message The message to log
	 */
	public void log(String message);
	
	/**
	 * Switches the current game state
	 * @param game The game to switch state
	 */
	public void switchState(LoggingGame game);
}
