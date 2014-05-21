package hotciv.standard;

import java.util.HashMap;
import java.util.Map;

import hotciv.framework.City;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Tile;

public class CityImpl implements City {
	private Player player;
	private String currProduction = GameConstants.ARCHER; //default production for now
	private String currFocus = GameConstants.productionFocus;  //default focus for now
	private int totalProduction;
	private int productionRate =6;
	private int population = 1;
	//Unit Map Cost
	private Map<String, Integer> unitCostMap = new HashMap<String, Integer>();
		
	

	public CityImpl(Player player ){
		this.player = player;
		unitCostMap.put(GameConstants.ARCHER, GameConstants.ARCHER_COST);
		unitCostMap.put(GameConstants.LEGION, GameConstants.LEGION_COST);
		unitCostMap.put(GameConstants.SETTLER, GameConstants.SETTLER_COST);
	}

	@Override
	public Player getOwner() {
		return player;
	}

	@Override
	public int getSize() {
		return population; 
	}

	@Override
	public String getProduction() {
		return currProduction;
	}

	@Override
	public String getWorkforceFocus() {
		return currFocus;
	}

	@Override
	public int getTotalProduction(){
		return totalProduction;
	}
	
	@Override
	public void increaseTotalProduction(){
		totalProduction = totalProduction + productionRate;
	}

	@Override
	public boolean canProduceUnit(){
		int unitCost=unitCostMap.get(currProduction); 

		if(totalProduction>=unitCost){
			totalProduction -= unitCost;
			return true;
		}
		else{
			return false;

		}
	}
	
	@Override
	public void changeProduction(String production){
		currProduction = production;
	}

	@Override
	public void changeFocus(String focus){
		currFocus = focus;
	}

	@Override
	public void setOwner(Player player) {
		this.player = player;
		
	}
}
