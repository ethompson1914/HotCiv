package hotciv.standard.gamma;

import hotciv.framework.ActionStrategy;
import hotciv.framework.AgingStrategy;
import hotciv.framework.AttackingStrategy;
import hotciv.framework.WinningStrategy;
import hotciv.standard.BasicFactory;
import hotciv.standard.FortifyActionStrategy;
import hotciv.standard.SettlingActionStrategy;
import hotciv.standard.StandardAttackingStrategy;
import hotciv.standard.StandardWinStrategy;

public class GammaCivFactory extends BasicFactory{

	public GammaCivFactory() {
		setLayout("ALPHA_LAYOUT.txt");
		buildMap();
	}
	
	@Override
	public ActionStrategy createActionStrategy() {
		return new GammaActionStrategy(new FortifyActionStrategy(), new SettlingActionStrategy());
	}

	@Override
	public WinningStrategy createWinStrategy() {
		return new StandardWinStrategy();
	}

	@Override
	public AgingStrategy createAgeStrategy() {
		return new GammaAgeStrategy();
	}
	
	@Override
	public AttackingStrategy createAttackingStrategy(){
		return new StandardAttackingStrategy();
	}

}
