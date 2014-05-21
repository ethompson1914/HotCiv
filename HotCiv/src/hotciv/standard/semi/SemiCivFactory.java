package hotciv.standard.semi;

import hotciv.framework.ActionStrategy;
import hotciv.framework.AgingStrategy;
import hotciv.framework.AttackingStrategy;
import hotciv.framework.WinningStrategy;
import hotciv.standard.BasicFactory;
import hotciv.standard.NoActionStrategy;
import hotciv.standard.RandomStrengthMultiplierStrategy;
import hotciv.standard.SettlingActionStrategy;
import hotciv.standard.StandardAttackingStrategy;
import hotciv.standard.beta.BetaAgeStrategy;
import hotciv.standard.epsilon.EpsilonAttackingStrategy;
import hotciv.standard.epsilon.EpsilonWinStrategy;
import hotciv.standard.gamma.GammaActionStrategy;

public class SemiCivFactory extends BasicFactory{
	
	public SemiCivFactory(){
		setLayout("DELTA_LAYOUT.TXT");
		buildMap();
	}

	public AgingStrategy createAgeStrategy(){
		return new BetaAgeStrategy();
	}
	
	public WinningStrategy createWinStrategy(){
		return new EpsilonWinStrategy();
	}
	
	public ActionStrategy createActionStrategy(){
		return new GammaActionStrategy(new NoActionStrategy(), new SettlingActionStrategy());
	}

	@Override
	public AttackingStrategy createAttackingStrategy() {
		return new EpsilonAttackingStrategy(new RandomStrengthMultiplierStrategy());
	}
}
