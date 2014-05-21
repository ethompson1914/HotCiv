package hotciv.standard.beta;

import hotciv.framework.ActionStrategy;
import hotciv.framework.AgingStrategy;
import hotciv.framework.AttackingStrategy;
import hotciv.framework.WinningStrategy;
import hotciv.standard.BasicFactory;
import hotciv.standard.NoActionStrategy;
import hotciv.standard.StandardAttackingStrategy;

public class BetaCivFactory extends BasicFactory {

	public BetaCivFactory() {
		setLayout("ALPHA_LAYOUT.txt");
		buildMap();
	}

	@Override
	public ActionStrategy createActionStrategy() {
		return new NoActionStrategy();
	}

	@Override
	public WinningStrategy createWinStrategy() {
		return new BetaWinStrategy();
	}

	@Override
	public AgingStrategy createAgeStrategy() {
		return new BetaAgeStrategy() ;
	}

	@Override
	public AttackingStrategy createAttackingStrategy(){
		return new StandardAttackingStrategy();
	}
}
