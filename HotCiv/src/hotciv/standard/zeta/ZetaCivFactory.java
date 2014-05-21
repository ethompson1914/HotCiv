package hotciv.standard.zeta;
import hotciv.framework.ActionStrategy;
import hotciv.framework.AgingStrategy;
import hotciv.framework.AttackingStrategy;
import hotciv.framework.WinningStrategy;
import hotciv.standard.BasicFactory;
import hotciv.standard.NoActionStrategy;
import hotciv.standard.StandardAgeStrategy;


public class ZetaCivFactory extends BasicFactory{

	public ZetaCivFactory() {
		setLayout("ALPHA_LAYOUT.txt");
		buildMap();
	}
	
	@Override
	public ActionStrategy createActionStrategy() {
		return new NoActionStrategy();
	}

	@Override
	public WinningStrategy createWinStrategy() {
		return new ZetaWinStrategy();
	}

	@Override
	public AgingStrategy createAgeStrategy() {
		return new StandardAgeStrategy();
	}
	
	@Override
	public AttackingStrategy createAttackingStrategy(){
		return new ZetaAttackingStrategy();
	}
}
