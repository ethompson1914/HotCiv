package hotciv.framework;

/**
 * A sub-interface for supplying methods directly related to logging game interatctions and transcribing for tournament play
 * @author andrew.mccubbin
 *
 */
public interface LoggingGame extends Game {
	
	/**
	 * Alternates between logging states
	 */
	public void switchLoggingState();
	
	/**
	 * A setter to change the current logging state
	 * @param ls The new logging state
	 */
	public void setLoggingState(LoggingState ls);
	
	/**
	 * A getter for the current logging state of the game
	 * @return the current logging state of the game
	 */
	public LoggingState getLoggingState();
	
	/**
	 * Getter to retrieve the last recorded message of the game play
	 * @return A string with the last message of game play
	 */
	public String getLastMessage();
}
