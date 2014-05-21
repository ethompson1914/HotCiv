package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.GameObserver;
import hotciv.framework.LoggingGame;
import hotciv.framework.LoggingState;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.framework.Unit;

import java.util.List;

public class LoggingGameImpl implements LoggingGame {
	private Game game;
	private String lastMessage;
	private LoggingState currentState;

	public LoggingGameImpl (Game game){
		this.game = game;
		currentState = new PrintLogState();
	}

	@Override
	public Tile getTileAt(Position p) {
		return game.getTileAt(p);
	}

	@Override
	public Unit getUnitAt(Position p) {
		return game.getUnitAt(p);
	}

	@Override
	public City getCityAt(Position p) {
		return game.getCityAt(p);
	}

	@Override
	public Player getPlayerInTurn() {
		return game.getPlayerInTurn();
	}

	@Override
	public Player getWinner() {
		return game.getWinner();
	}

	@Override
	public int getAge() {
		return game.getAge();
	}

	@Override
	public List<Tile> getTilesWithCities() {
		return game.getTilesWithCities();
	}

	@Override
	public List<Tile> getTilesWithUnits() {
		return game.getTilesWithUnits();
	}

	@Override
	public void createCity(Position p) {
		lastMessage = getUnitAt(p).getOwner().toString()+" has built a city at "+p.toString()+".";
		currentState.log(lastMessage);		
		game.createCity(p);

	}

	@Override
	public void incrementPlayerWinCount(Player player) {
		game.incrementPlayerWinCount(player);

	}

	@Override
	public int getPlayerWinCount(Player player) {
		return getPlayerWinCount(player);
	}

	@Override
	public boolean moveUnit(Position from, Position to) {
		lastMessage = game.getPlayerInTurn().toString()+" moves "+game.getUnitAt(from).getTypeString()+" from "+ from.toString()+" to "+to.toString() + ".";
		currentState.log(lastMessage);
		return game.moveUnit(from, to);
	}

	@Override
	public void endOfTurn() {
		lastMessage = "Turn has ended for player "+game.getPlayerInTurn().toString()+".";
		currentState.log(lastMessage);
		game.endOfTurn();
	}

	@Override
	public void changeWorkForceFocusInCityAt(Position p, String balance) {
		lastMessage = getCityAt(p).getOwner().toString() + " changes work focus in city at " + p.toString()+" to "+balance+".";
		currentState.log(lastMessage);
		game.changeWorkForceFocusInCityAt(p, balance);

	}

	@Override
	public void changeProductionInCityAt(Position p, String unitType) {
		lastMessage = getCityAt(p).getOwner().toString() + " changes production in city at " + p.toString()+" to "+unitType+".";
		currentState.log(lastMessage);
		game.changeProductionInCityAt(p, unitType);
	}

	@Override
	public void performUnitActionAt(Position p) {
		lastMessage = game.getPlayerInTurn().toString() + " has performed unit action at "+ p.toString()+".";
		currentState.log(lastMessage);
		game.performUnitActionAt(p);
	}

	@Override
	public boolean attack(Position attacker, Position defender) {
		lastMessage = game.getPlayerInTurn().toString() + " "+ game.getUnitAt(attacker).getTypeString()+ " at "+ attacker.toString()+ " attacks "+ 
				game.getUnitAt(defender).getOwner().toString() + " "+ game.getUnitAt(defender).getTypeString()+ " at "+defender.toString()+".";

		currentState.log(lastMessage);	
		return game.attack(attacker, defender);
	}

	@Override
	public void addUnitToTile(Unit unit, Position p) {
		game.addUnitToTile(unit, p);
	}

	@Override
	public void switchLoggingState() {
		currentState.switchState(this);
	}

	@Override
	public void setLoggingState(LoggingState ls) {
		currentState = ls;
	}

	@Override
	public LoggingState getLoggingState() {
		return currentState;
	}
	
	@Override
	public String getLastMessage(){
		return lastMessage;
	}

	@Override
	public void addObserver(GameObserver observer) {
		game.addObserver(observer);
	}

	@Override
	public Tile selectTile(Position p) {
		return game.selectTile(p);
	}
}
