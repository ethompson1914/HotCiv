package hotciv.standard.zeta;

import hotciv.framework.ActionStrategy;
import hotciv.framework.AgingStrategy;
import hotciv.framework.AttackingStrategy;
import hotciv.framework.WinningStrategy;
import hotciv.standard.BasicFactory;
import hotciv.standard.NoActionStrategy;
import hotciv.standard.StandardAgeStrategy;

public class ZetaCiv20RoundsFactoryTestStub extends BasicFactory {

	public ZetaCiv20RoundsFactoryTestStub() {
		setLayout("ALPHA_LAYOUT.txt");
		buildMap();
	}
	
	@Override
	public ActionStrategy createActionStrategy() {
		return new NoActionStrategy();
	}

	@Override
	public WinningStrategy createWinStrategy() {
		return new ZetaCiv20RoundsWinStrategyTestStub();
	}

	@Override
	public AgingStrategy createAgeStrategy() {
		return new StandardAgeStrategy();
	}
	
	@Override
	public AttackingStrategy createAttackingStrategy(){
		return new ZetaAttackingStrategy20RoundTestStub();
	}
}
