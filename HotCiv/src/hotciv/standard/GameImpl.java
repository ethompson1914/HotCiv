package hotciv.standard;
import hotciv.framework.ActionStrategy;
import hotciv.framework.AgingStrategy;
import hotciv.framework.AttackingStrategy;
import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.GameFactory;
import hotciv.framework.GameObserver;
import hotciv.framework.LoggingState;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.framework.Unit;
import hotciv.framework.Utility;
import hotciv.framework.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

import sun.security.acl.WorldGroupImpl;


/** Skeleton implementation of HotCiv.

   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Computer Science Department
     Aarhus University

   This source code is provided WITHOUT ANY WARRANTY either 
   expressed or implied. You may study, use, modify, and 
   distribute it for non-commercial purposes. For any 
   commercial use, see http://www.baerbak.com/
 */

public class GameImpl implements Game {
	private Tile[][] board = new TileImpl[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];
	private Player currentPlayer;
	private Player lastPlayerOfGame=Player.BLUE;
	private ArrayList<Tile> tilesWithCities;
	private ArrayList<Tile> tilesWithUnits;
	private int age;
	private Player winner;
	private GameFactory gameFactory;
	private AgingStrategy agingStrategy;
	private WinningStrategy winningStrategy;
	private ActionStrategy actionStrategy;	
	private AttackingStrategy attackingStrategy;
	private int redWinCount = 0 ;
	private int blueWinCount = 0 ;
	
	private List<GameObserver> observers;

	public GameImpl(GameFactory gameFactory){
		this.gameFactory = gameFactory;
		tilesWithCities = new ArrayList<Tile>();
		tilesWithUnits = new ArrayList<Tile>();
		age= -4000;
		winner = null;
		init();

		observers = new ArrayList<GameObserver>();
	}

	private void init(){

		createLayoutTerrain();
		createLayoutCities();
		createLayoutUnits();

		currentPlayer = Player.RED;

		agingStrategy = gameFactory.createAgeStrategy();
		actionStrategy = gameFactory.createActionStrategy();
		winningStrategy = gameFactory.createWinStrategy();
		attackingStrategy = gameFactory.createAttackingStrategy();

	}

	private void createLayoutTerrain(){
		String line;
		String[] layout;
		layout = gameFactory.getTerrainLayout().split(",");
		for ( int r = 0; r < GameConstants.WORLDSIZE; r++ ) {		      
			line = layout[r];
			for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
				char tileChar = line.charAt(c);
				String type = "error";
				if ( tileChar == '.' ) { type = GameConstants.OCEANS; }
				if ( tileChar == 'o' ) { type = GameConstants.PLAINS; }
				if ( tileChar == 'M' ) { type = GameConstants.MOUNTAINS; }
				if ( tileChar == 'f' ) { type = GameConstants.FOREST; }
				if ( tileChar == 'h' ) { type = GameConstants.HILLS; }
				Position p = new Position(r,c);
				board[r][c]= new TileImpl(p, type);
			}
		}		    		
	}

	private void createLayoutCities(){
		String line;
		String[] layout;
		layout = gameFactory.getCityLayout().split(",");
		for ( int r = 0; r < GameConstants.WORLDSIZE; r++ ) {		      
			line = layout[r];
			for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
				char tileChar = line.charAt(c);
				Player owner = null;
				if ( tileChar == 'c' ) { owner = Player.RED; }
				if ( tileChar == 'C' ) { owner = Player.BLUE; }
				if ( tileChar == '.' ) { owner = null; }
				Position p = new Position(r,c);
				if(owner != null){
					addCityToTile(new CityImpl(owner), p);
				}
			}
		}		    		
	}

	private void createLayoutUnits(){
		String line;
		String[] layout;
		layout = gameFactory.getUnitLayout().split(",");
		for ( int r = 0; r < GameConstants.WORLDSIZE; r++ ) {		      
			line = layout[r];
			for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
				char tileChar = line.charAt(c);
				Unit unit = null;	
				if ( tileChar == 'a' ){
					unit = new Archer(Player.RED);
				}
				if ( tileChar == 'A' ) {
					unit = new Archer(Player.BLUE);
				}
				if ( tileChar == 'l' ) { 
					unit = new Legion(Player.RED);
				}
				if ( tileChar == 'L' ) { 
					unit = new Legion(Player.BLUE);
				}
				if ( tileChar == 's' ) {
					unit = new Settler(Player.RED);
				}
				if ( tileChar == 'S' ) {
					unit = new Settler(Player.BLUE);		    		}
				Position p = new Position(r,c);
				if(unit != null)
				{
					addUnitToTile(unit, p);
				}
			}
		}	
	}

	//adds a city to a tile
	public void addCityToTile(CityImpl city, Position pos){
		Tile t = getTileAt(pos);
		t.setCity(city);
		tilesWithCities.add(getTileAt(pos));

	}

	//adds a unit to a tile and adds the tile into the arrayList that keeps track of units
	public void addUnitToTile(Unit unit, Position pos){
		Tile t =  getTileAt(pos);
		t.setUnit(unit);
		tilesWithUnits.add(getTileAt(pos));
	}

	//gets the tile at a location
	public Tile getTileAt(Position p ) {
		return board[p.getRow()][p.getColumn()]; 
	}

	//gets a unit at a location
	public Unit getUnitAt(Position p ) { 
		return getTileAt(p).getUnit();
	}

	//gets a city at a location
	public City getCityAt(Position p ) { 
		return board[p.getRow()][p.getColumn()].getCity(); 
	}

	//gets whose turn it is
	public Player getPlayerInTurn() { 
		return currentPlayer; }

	public Player getWinner() { return winner; }

	public int getAge() {
		return age; 
	}

	public boolean moveUnit(Position from, Position to ) {
		if(isValidMove(from, to)){
			Unit unit = getTileAt(from).getUnit();
			addUnitToTile(unit, to);
			getTileAt(from).clearUnit();
			getTileAt(to).getUnit().decrementMoveCount();
			tilesWithUnits.remove(getTileAt(from));
			if(getTileAt(to).getCity() != null){
				getTileAt(to).getCity().setOwner(getUnitAt(to).getOwner());
			}
			
			worldChangedAt(to);
			tileFocusChangedAt(to);
			return true;
		}
		else{
			worldChangedAt(to);
			return false;
		}
	}

	private boolean isValidMove(Position from, Position to){

		int rfrom = from.getRow();
		int cfrom = from.getColumn();
		int rto = to.getRow();
		int cto = to.getColumn();

		if(getTileAt(from).getUnit().getMoveCount() == 0 ){ //cannot move an unit that has 0 move counts
			return false;
		}
		else if(rto<0 || rto>GameConstants.WORLDSIZE-1 ||
				cto<0||cto>GameConstants.WORLDSIZE-1) { //cannot move unit off the board
			return false;
		}
		else if(getTileAt(to).getTypeString().equals(GameConstants.MOUNTAINS) ||
				getTileAt(to).getTypeString().equals(GameConstants.OCEANS)){
			return false; //cannot move units to Mountains or Oceans
		}
		else if(!(getTileAt(to).getUnit()==null) && 
				getTileAt(to).getUnit().getOwner()==currentPlayer){
			return false; //cannot move on top of your own units
		}
		else if(!(getTileAt(from).getUnit().getOwner()==currentPlayer)){
			return false;  //cannot control other player's units
		}
		else if((Math.abs(rfrom-rto)==1&&Math.abs(cfrom-cto)==1) ||
				(Math.abs(rfrom-rto)==1&&Math.abs(cfrom-cto)==0) ||
				(Math.abs(rfrom-rto)==0&&Math.abs(cfrom-cto)==1)) 
		{
			return true; //can only move one space horizontally, vertically or diagonally.
		}
		else{
			return false;
		}
	}

	//ends the turn of the current player
	public void endOfTurn() {

		if(currentPlayer == lastPlayerOfGame){
			endOfRound(); 
		}		
		switch (currentPlayer){
		case RED: 
			currentPlayer = Player.BLUE;
			break;
		case BLUE:
			currentPlayer = Player.RED;
			break;
		}

		for (GameObserver o : observers) {
			o.turnEnds(currentPlayer, getAge());
		}
	}

	private void endOfRound(){
		for (Tile t : tilesWithCities ){
			City city = t.getCity();
			city.increaseTotalProduction();
			String unitType = t.getCity().getProduction();
			if(t.getCity().canProduceUnit())
			{
				Position p;
				if(unitType.equals(GameConstants.ARCHER))
				{
					p = getNextNonOccupiedTile(t).getPosition();
					addUnitToTile(new Archer(city.getOwner()),p);
				}
				if(unitType.equals(GameConstants.LEGION))
				{
					p = getNextNonOccupiedTile(t).getPosition();
					addUnitToTile(new Legion(city.getOwner()),p);
				}
				if(unitType.equals(GameConstants.SETTLER))
				{
					p = getNextNonOccupiedTile(t).getPosition();
					addUnitToTile(new Settler(city.getOwner()),p);
				}
			}
		}
		for (Tile t : tilesWithUnits){
			Unit unit = t.getUnit();
			if (unit != null) {
				unit.resetMoveCount();
				unit.resetActionCount();
			}
		}
		age = agingStrategy.calculateAge(age);
		winner = winningStrategy.getWinner(this);
	}

	private ArrayList<Tile> getAdjacentTiles(Tile t2){
		ArrayList<Tile> adjacentTiles = new ArrayList<Tile>();
		int r = t2.getPosition().getRow();
		int c = t2.getPosition().getColumn();
		adjacentTiles.add(board[r][c]);
		adjacentTiles.add(board[r-1][c]);
		adjacentTiles.add(board[r-1][c+1]);
		adjacentTiles.add(board[r][c+1]);
		adjacentTiles.add(board[r+1][c+1]);
		adjacentTiles.add(board[r+1][c]);
		adjacentTiles.add(board[r+1][c-1]);
		adjacentTiles.add(board[r][c-1]);
		adjacentTiles.add(board[r-1][c-1]);
		return adjacentTiles;
	}

	private Tile getNextNonOccupiedTile(Tile t2){
		ArrayList<Tile> tiles = getAdjacentTiles(t2);		
		int count=0;
		for(Tile t: tiles){
			if((t.getTypeString() != GameConstants.MOUNTAINS 
					&& t.getTypeString() != GameConstants.OCEANS && (t.getUnit()) == null)){
				return t;
			}

		}
		return tiles.get(count);
	}

	public void changeWorkForceFocusInCityAt( Position p, String balance ) {
		if(!(getTileAt(p).getCity().equals(null))&&currentPlayer.equals(getTileAt(p).getCity().getOwner())){
			getTileAt(p).getCity().changeFocus(balance);
		}

	}

	public void changeProductionInCityAt( Position p, String unitType ) {
		if(!(getTileAt(p).getCity().equals(null))&&currentPlayer.equals(getTileAt(p).getCity().getOwner())){
			getTileAt(p).getCity().changeProduction(unitType);
			tileFocusChangedAt(p);
		}
	}
	
	private void worldChangedAt(Position p) {
		for (GameObserver o : observers) {
			o.worldChangedAt(p);
		}
	}
	
	private void tileFocusChangedAt(Position p) {
		for (GameObserver o : observers) {
			o.tileFocusChangedAt(p);
		}
	}

	//TODO for future
	public void performUnitActionAt( Position p ) {
		actionStrategy.performAction(this, p);
		worldChangedAt(p);
	}

	public boolean attack(Position attacker, Position defender) {
		boolean attacking = attackingStrategy.attackSuccessful(this, getUnit(attacker), getUnit(defender), 
				Utility.getFriendlySupport(this, attacker, getUnitOwner(attacker)), 
				Utility.getTerrainFactor(this, attacker),
				Utility.getFriendlySupport(this, defender, getUnitOwner(defender)), 
				Utility.getTerrainFactor(this, defender));

		if(attacking){
			moveUnit(attacker, defender);
		} else {
			// getUnitAt(attacker).decrementMoveCount();
		}
		worldChangedAt(defender);
		return attacking;
	}

	private Player getUnitOwner(Position p){
		if(getUnit(p)!=null){
			return getUnit(p).getOwner();
		}
		else{ 
			return null;
		}
	}

	private Unit getUnit(Position p){
		return getTileAt(p).getUnit();
	}

	@Override
	public List<Tile> getTilesWithCities() {
		return tilesWithCities;
	}

	@Override
	public List<Tile> getTilesWithUnits() {
		return tilesWithUnits;
	}

	@Override
	public void createCity(Position p) {
		getTileAt(p).setCity(new CityImpl(getTileAt(p).getUnit().getOwner()));
		getTilesWithCities().add(getTileAt(p));
		getTileAt(p).clearUnit();
		getTilesWithUnits().remove(getTileAt(p));
	}
	
	@Override
	public void incrementPlayerWinCount(Player player) {
		if(player == Player.BLUE)
			this.blueWinCount++;
		else if(player == Player.RED)
			this.redWinCount++;
	}

	@Override
	public int getPlayerWinCount(Player player) {
		if(player == Player.BLUE){
			return blueWinCount;
		}
		else{
			return redWinCount;
		}
	}

	@Override
	public void addObserver(GameObserver observer) {
		observers.add(observer);
	}

	@Override
	public Tile selectTile(Position p) {
		for (GameObserver o : observers) {
			o.tileFocusChangedAt(p);
		}
		return getTileAt(p);
	}

}
