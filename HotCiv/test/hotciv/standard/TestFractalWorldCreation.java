package hotciv.standard;

import static org.junit.Assert.assertNotNull;
import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.standard.delta.FractalDeltaCivFactory;

import org.junit.Before;
import org.junit.Test;
public class TestFractalWorldCreation {

	private Game game;
	
	@Before
	public void setUp() {
		game = new GameImpl(new FractalDeltaCivFactory());
	}

	@Test
	public void testThatAllTilesAreCreated() {
		for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
			for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
				assertNotNull("Tile cannot be null.", game.getTileAt(new Position(r, c)));
			}
		}
	}
}