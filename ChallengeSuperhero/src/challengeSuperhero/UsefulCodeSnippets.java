package challengeSuperhero;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;

import countries.Country;
import woof.Dog;

public class UsefulCodeSnippets {

	// split on a space
	String[] tokens = words.split(" ");

	// formatting for maps keys & values
	Set<String> keys = myMap.keySet();System.out.printf("-10s%10s %n","Keys","Values");for(
	String k:keys)
	{
		System.out.printf("-10s%10d  %n", k, myMap.get(k));
	}}

	// arrays as list
	Integer[] numbers = new Integer[] { 4, 8, 23, 1, 55, -34, 100, 3, 22 };
	List<Integer> toSort = new LinkedList<Integer>(Arrays.asList(numbers));

	// formatting of a string from p2 removes spaces and converts to all uppercase
	String status = String.format("SR-%s-&s-NOW:%.1f-%s", args);status=status.toUpperCase();status=status.replace(" ","");

	// show all method for collection of anytype
	public static void showAll(Collection<String> inputList) {
		for (String value : inputList) {
			System.out.println(value);
		}
	}

	// using Collections.max with Comparator
	Employee oldest = Collections.max(staffList, newCompareEmpByAge());System.out.println(oldest);

	// Sorted set - can use a comparator and won't be duplicates TreeSet
	SortedSet<Employee> empTree = new TreeSet<>(new CompareEmpByAge());

	// example of some string functions from catch 22
	if(line.toLowerCase().contains("xxxxx"))
	{
		String[] words = line.toLowerCase().split(" ");
		for (String word : words) {
			if (word.contains("yosssarian")) {
				yosarrian++;
			}
		}

	}

	// check a string for being at least 5 characters, no more that 30, no spaces,
	// no special characters.
	public void setImageFileName(String imageFileName) throws IllegalArgumentException {
			if (ValidateImageFileName(imageFileName) == true) {
				this.imageFileName = imageFileName; 
			} else {
				throw new IllegalArgumentException("invalid image file name");
			}
		}

	private boolean ValidateImageFileName(String imageFileName) {
		if (imageFileName.length() < MIN_IMAGE_FILE__NAME) {
			return false;
		}
		if (imageFileName.length() > MAX_IMAGE_FILE__NAME) {
			return false;
		}
		if (!imageFileName.endsWith(".jpg")) {
			return false;
		}
		// check string is only letters and no spaces
		String validChars = ".abcdefghijjlmnopqrstuvwxyz";
		String lower = imageFileName.toLowerCase();
		for (int i = 0; i < imageFileName.length(); i++) {
			if (validChars.indexOf(lower.charAt(i)) == -1) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * get total population by region using a map
	 * @param countries 
	 */
		private static void getTotalPopByRegion(List<Country> countries) {
			Map<String, Long> regions = new HashMap<String, Long>();
			
			for (Country c: countries) {
				// if new region found add 
				if (!regions.containsKey(c.getRegion())) {
					// new region and add population to the region 
					regions.put(c.getRegion(), (long)c.getPopulation());
					System.out.println("Added " + c.getRegion()+ " " + c.getName());
				} else {
					// have this region and a current total so need to update
					Long currentPop = regions.get(c.getRegion());
					Long updatePop = currentPop += (long)c.getPopulation(); 
					regions.put(c.getRegion(), updatePop); 	
				}
			}
			
			private static Map<String, Integer> countryStats(List<Dog> dogs) {
				System.out.println("Country analysis");
				// ordered map of frequency - key (country) value (frequency of the country) 
				Map<String, Integer> countryFreq = new TreeMap<>();
				
				 for (Dog dog : dogs) {
					 if (countryFreq.containsKey(dog.getCountry())) {
						 int freq = countryFreq.get(dog.getCountry()); 
						 countryFreq.put(dog.getCountry(), freq+1); 
					 } else {
						 countryFreq.put(dog.getCountry(), 1); 
					 }
				 }
				return countryFreq;
			}

			private static void displayCountryStats(Map<String, Integer> countryStats) {
				for (String country : countryStats.keySet()) {
					System.out.println(country + " " + countryStats.get(country));
				}
				
			}
}
