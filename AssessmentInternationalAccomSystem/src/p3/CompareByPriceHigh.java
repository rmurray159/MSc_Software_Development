package p3;

import java.util.Comparator;

/**
*@author RachelMurray 15419045
*/
public class CompareByPriceHigh implements Comparator<Accommodation> {

	@Override
	public int compare(Accommodation o1, Accommodation o2) {
		// as double
		if (o1.getPrice() == o2.getPrice()) {
			return 0;
		} else if (o1.getPrice() < o2.getPrice()) {
			return 1;
		} else {
			return -1;
		}
	}

}
