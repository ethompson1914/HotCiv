package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.framework.Unit;

public class TileImpl implements Tile {
	private Position pos;
	private String type; //terrain
	private Unit unit; 
	private City city;

	public TileImpl(Position pos, String type){
		this.pos= pos;
		this.type= type;
		this.city = null;
		this.unit = null;
	}
	
	@Override 
	public boolean setCity(City newCity){
		if(city == null){
			city = newCity;
			return true;
		}
		else{ 
			return false;
		}
	}

	@Override
	public City getCity(){
		return city;
	}

	@Override
	public Unit getUnit(){
		return unit;
	}

	@Override
	public void setUnit(Unit newUnit){
			unit = newUnit;
	}

	@Override
	public void clearUnit(){
		unit = null;
	}

	@Override
	public Position getPosition() {
		return pos;
	}

	@Override
	public String getTypeString() {
		return type;
	}

}
