package hotciv.standard;

import hotciv.framework.LoggingGame;
import hotciv.framework.LoggingState;

public class PrintLogState implements LoggingState {

	@Override
	public void log(String message) {
		System.out.println(message);
	}

	@Override
	public void switchState(LoggingGame game) {
		game.setLoggingState(new NoLoggingState());
	}

}
