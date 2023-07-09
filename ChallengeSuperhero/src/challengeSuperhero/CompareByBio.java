package challengeSuperhero;

import java.util.Comparator;

public class CompareByBio implements Comparator<TopTrumpCard> {

	@Override
	public int compare(TopTrumpCard o1, TopTrumpCard o2) {
		int s1 = o1.getBio().length();
		int s2 = o2.getBio().length();
		return s1 - s2;
		
		//return o2.getBio().length() - o1.getBio().length();
	}

}
