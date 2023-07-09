package challengeSuperhero;

import java.util.Comparator;

public class CompareByStrength implements Comparator<TopTrumpCard> {
	/**
	 * sort by strength from high to low 
	 */
	@Override
	public int compare(TopTrumpCard o1, TopTrumpCard o2) {
		return o2.getStrength() - o1.getStrength();
	}
}
