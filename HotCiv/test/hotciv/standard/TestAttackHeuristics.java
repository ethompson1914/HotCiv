package hotciv.standard;

import static org.junit.Assert.*;
import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.epsilon.EpsilonFactoryTestStub;

import org.junit.Before;
import org.junit.Test;

public class TestAttackHeuristics {
	private Game game;
	private Position attack, defend;
	
	@Before
	public void setUp() {
		game = new GameImpl(new EpsilonFactoryTestStub());
		attack = new Position(8, 8);
		defend = new Position(9, 9);
	}

	@Test
	public void testEqualStrengths() {
		// attack strength == defense strength
		game.addUnitToTile(new Archer(Player.RED), attack);
		game.addUnitToTile(new Legion(Player.BLUE), defend);
		assertFalse("Archer should not beat a legion.", game.attack(attack, defend));
	}
	
	@Test
	public void testStrongerAttackerWins() {
		// attack strength > defense strength
		game.addUnitToTile(new Legion(Player.RED), attack);
		game.addUnitToTile(new Archer(Player.BLUE), defend);
		assertTrue("Legion should beat an archer.", game.attack(attack, defend));
	}
	
	@Test
	public void testAttackUnitSupport() {
		game.addUnitToTile(new Archer(Player.RED), attack);
		game.addUnitToTile(new Legion(Player.BLUE), defend);
		// add a supporting unit to the attacker
		game.addUnitToTile(new Settler(Player.RED), new Position(8, 9));
		assertTrue("Archer (+1 support) should beat a legion.", game.attack(attack, defend));
	}
	
	@Test
	public void testDefendUnitSupport() {
		game.addUnitToTile(new Archer(Player.RED), attack);
		game.addUnitToTile(new Legion(Player.BLUE), defend);
		// add a supporting unit to the defender
		game.addUnitToTile(new Settler(Player.BLUE), new Position(8, 9));
		assertFalse("Archer should not beat a legion (+1 support).", game.attack(attack, defend));
	}
	
	@Test
	public void testCityBonus() {
		game.addUnitToTile(new Legion(Player.BLUE), new Position(1, 2));
		game.moveUnit(new Position(2, 0), new Position(1, 1));
		game.endOfTurn(); game.endOfTurn();
		// attack from a city to get the +3 bonus
		assertTrue("Archer on a city (+3) should beat a legion.", game.attack(new Position(1, 1), new Position(1, 2)));
	}
}