package hotciv.standard;

import hotciv.framework.LoggingGame;
import hotciv.framework.LoggingState;

public class NoLoggingState implements LoggingState {

	@Override
	public void log(String message) {}

	@Override
	public void switchState(LoggingGame game) {
		game.setLoggingState(new PrintLogState());
	}

}
