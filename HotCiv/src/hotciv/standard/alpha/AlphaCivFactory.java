package hotciv.standard.alpha;

import hotciv.framework.ActionStrategy;
import hotciv.framework.AgingStrategy;
import hotciv.framework.AttackingStrategy;
import hotciv.framework.WinningStrategy;
import hotciv.standard.BasicFactory;
import hotciv.standard.NoActionStrategy;
import hotciv.standard.StandardAgeStrategy;
import hotciv.standard.StandardAttackingStrategy;
import hotciv.standard.StandardWinStrategy;

public class AlphaCivFactory extends BasicFactory{
		
	public AlphaCivFactory() {
		setLayout("ALPHA_LAYOUT.txt");
		buildMap();
	}
	
	@Override
	public ActionStrategy createActionStrategy() {
		return new NoActionStrategy();
	}

	@Override
	public WinningStrategy createWinStrategy() {
		return new StandardWinStrategy();
	}

	@Override
	public AgingStrategy createAgeStrategy() {
		return new StandardAgeStrategy();
	}
	
	@Override
	public AttackingStrategy createAttackingStrategy(){
		return new StandardAttackingStrategy();
	}
}
