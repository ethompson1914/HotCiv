package hotciv.standard;

import hotciv.framework.ActionStrategy;
import hotciv.framework.AgingStrategy;
import hotciv.framework.AttackingStrategy;
import hotciv.framework.GameFactory;
import hotciv.framework.WinningStrategy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class BasicFactory implements GameFactory {

	protected String terrainLayout;
	protected String unitLayout;
	protected String cityLayout;
	protected String layout;
	
	protected void buildMap() {
		File f = new File(layout);
		Scanner scan = new Scanner("-");
		StringBuilder sb = new StringBuilder();
		try {
			 scan = new Scanner(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		

		while(!scan.hasNext("-"))
		{
			sb.append(scan.next());
		}
		terrainLayout = sb.toString();
		sb = new StringBuilder();
		scan.next();
		while(!scan.hasNext("-"))
		{
			sb.append(scan.next());
		}
		cityLayout = sb.toString();
		sb = new StringBuilder();
		scan.next();
		while(!scan.hasNext("-"))
		{
			sb.append(scan.next());
		}
		unitLayout = sb.toString();
	}
	
	protected void setLayout(String l)
	{
		layout = "layouts\\" + l;
	}
	
	@Override
	public abstract ActionStrategy createActionStrategy(); 

	@Override
	public abstract WinningStrategy createWinStrategy();

	@Override
	public abstract AgingStrategy createAgeStrategy();

	@Override
	public String getTerrainLayout() {
		return terrainLayout;
	}

	@Override
	public String getCityLayout() {
		return cityLayout;
	}

	@Override
	public String getUnitLayout() {
		return unitLayout;
	}
	
	@Override
	public abstract AttackingStrategy createAttackingStrategy();
}
