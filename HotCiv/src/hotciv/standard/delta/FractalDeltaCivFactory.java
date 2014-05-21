package hotciv.standard.delta;

import hotciv.framework.GameConstants;
import thirdparty.ThirdPartyFractalGenerator;

public class FractalDeltaCivFactory extends DeltaCivFactory {
	public FractalDeltaCivFactory() {
		super();
		
		ThirdPartyFractalGenerator g = new ThirdPartyFractalGenerator();
		StringBuilder s = new StringBuilder();
		for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
			for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
				s.append(g.getLandscapeAt(r,  c));
			}
			s.append(",");
		}
		terrainLayout = s.toString();
	}
}
