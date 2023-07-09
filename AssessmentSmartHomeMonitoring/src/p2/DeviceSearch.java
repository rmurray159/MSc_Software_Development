/**
 * 
 */
package p2;

import java.util.ArrayList;

/**
 * @author rache note use of polymorphism that can search by room for any device
 *         type, not just smart radiator
 */
public class DeviceSearch {
	/**
	 * search for devices in input list whose room matched search term
	 * 
	 * @param inputList
	 * @param searchTerm
	 * @return
	 */
	public static ArrayList<Device> searchByRoom(ArrayList<Device> inputList, Room searchTerm)
			throws IllegalArgumentException {
		if (inputList == null) {
			throw new IllegalArgumentException("Input list cannor be null");
		}
		if (searchTerm == null) {
			throw new IllegalArgumentException("Cannot search for null");
		}
		ArrayList<Device> results = new ArrayList<Device>();

		for (Device dev : inputList) {
			if (dev.getRoom() == searchTerm) {
				results.add(dev);
			}
		}
		return results;

	}

	/**
	 * search for radiators in input list whose current temp is between low and high
	 * inclusive
	 * 
	 * @param inputList
	 * @param low
	 * @param high
	 * @return
	 */
	public static ArrayList<Smartradiator> searchByTemp(ArrayList<Smartradiator> inputList, double low, double high)
			throws IllegalArgumentException {
		if (inputList == null) {
			throw new IllegalArgumentException("Input list cannor be null");
		}
		if (low >= high) {
			throw new IllegalArgumentException("Low target must be smaller than high or search");
		}
		ArrayList<Smartradiator> results = new ArrayList<Smartradiator>();

		for (Smartradiator rad : inputList) {
			if (rad.getTempNow() >= low && rad.getTempNow() <= high) {
				results.add(rad);
			}
		}
		return results;

	}
}
