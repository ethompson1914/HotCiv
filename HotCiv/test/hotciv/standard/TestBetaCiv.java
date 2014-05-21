package hotciv.standard;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.standard.Archer;
import hotciv.standard.GameImpl;
import hotciv.standard.Legion;
import hotciv.standard.beta.BetaCivFactory;

import org.junit.Before;
import org.junit.Test;

public class TestBetaCiv {
	private GameImpl game;
	/** Fixture for betaciv testing. */
	@Before
	public void setUp() {
		
		game = new GameImpl(new BetaCivFactory());
		Position toArcherPosition = new Position(2,1);
		Position blueLegionPosition = new Position(3,0);

		Unit archer2= new Archer(Player.RED);
		Unit legion = new Legion(Player.BLUE);

		game.addUnitToTile(legion, blueLegionPosition);
		game.addUnitToTile(archer2, toArcherPosition);

	}
	
	@Test
	public void shouldBe3000BCAfter10Rounds() {
		for(int i=0; i<20; i++)
		{
			game.endOfTurn();
		}
		assertEquals("Should be 3000BC after 10 Rounds", -3000, game.getAge());
	}
	
	@Test
	public void shouldBe100BCAfter39Rounds() {
		for(int i=0; i<78; i++)
		{
			game.endOfTurn();
		}
		assertEquals("Should be 3000BC after 10 Rounds", -100, game.getAge());
	}

	@Test
	public void shouldBe100BCAfter40Rounds() {
		for(int i=0; i<80; i++)
		{
			game.endOfTurn();
		}
		assertEquals("Should be 3000BC after 10 Rounds", -1, game.getAge());
	}
	
	@Test
	public void shouldBe100BCAfter41Rounds() {
		for(int i=0; i<82; i++)
		{
			game.endOfTurn();
		}
		assertEquals("Should be 3000BC after 10 Rounds", 1, game.getAge());
	}
	
	@Test
	public void shouldBe100BCAfter42Rounds() {
		for(int i=0; i<84; i++)
		{
			game.endOfTurn();
		}
		assertEquals("Should be 3000BC after 10 Rounds", 50, game.getAge());
	}
	
	@Test
	public void shouldBe100BCAfter76Rounds() {
		for(int i=0; i<152; i++)
		{
			game.endOfTurn();
		}
		assertEquals("Should be 3000BC after 10 Rounds", 1750, game.getAge());
	}
	
	@Test
	public void shouldBe100BCAfter82Rounds() {
		for(int i=0; i<164; i++)
		{
			game.endOfTurn();
		}
		assertEquals("Should be 3000BC after 10 Rounds", 1900, game.getAge());
	}
	
	@Test
	public void shouldBe100BCAfter96Rounds() {
		for(int i=0; i<192; i++)
		{
			game.endOfTurn();
		}
		assertEquals("Should be 3000BC after 10 Rounds", 1970, game.getAge());
	}
	
	@Test
	public void shouldBe100BCAfter97Rounds() {
		for(int i=0; i<194; i++)
		{
			game.endOfTurn();
		}
		assertEquals("Should be 3000BC after 10 Rounds", 1971, game.getAge());
	}
	
	@Test
	public void shouldHaveNoWinnerWithDifferentPlayerCitiesOnBoard(){
		assertNull("should not have winner when there are cities with different ownership",game.getWinner());
	}
	
	@Test
	public void RedArcherCaptureCity(){
		Position p = new Position(2,0);
		Position toP = new Position(3,1);
		game.moveUnit(p, toP);
		game.endOfTurn();
		game.endOfTurn();
		Position attack = new Position(4, 1);
		game.attack(toP, attack);
		game.endOfTurn();
		game.endOfTurn();
		
		assertEquals("An red archer at city at 4,1.", GameConstants.ARCHER , game.getUnitAt(attack).getTypeString());
		assertEquals("City at 4,1 is under red control", Player.RED, game.getTileAt(attack).getCity().getOwner());
		//assertEquals("Red player won",Player.RED,game.getWinner());
	}
}
