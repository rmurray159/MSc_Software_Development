package p3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


public class Launcher {

	private static Scanner scanner = new Scanner(System.in);
	private static final int QUIT = 10;

	/**
	 * Entry point of program - no need to modify this method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launchMenu();
		scanner.close();// close scanner once menu system completes
	}

	// TODO modify readData method to populate List appropriately - method partially
	// completed already
	// TODO add static methods to this class as required to achieve tasks outlined
	// in menu
	// TODO modify launchMenu method to add calls to new methods you write etc to
	// accomplish the tasks outlined in the menu

	/**
	 * Launches menu system which in turn calls appropriate methods based on user
	 * choices Partially implemented already. Requires updating to add calls to other
	 * methods written to achieve the tasks described in tasks 3-9
	 */
	public static void launchMenu() {
		List<Accommodation> mainList = readRoomData("Rooms.csv");

		System.out.println();
		int option;
		System.out.println("AccommyNation.com - Wherever you go, you'll need to stay");

		// repeat until quit chosen
		do {
			displayOptions();

			// get input
			option = getMenuChoice("Enter choice ...");
			System.out.println();

			try {
				// process choice - invoke relevant methods etc.
				switch (option) {

				case 1:
					mainList = readRoomData("Rooms.csv");
					break;
				case 2:
					System.out.println("Number of places to stay is " + mainList.size());
					break;
				case 3:
					System.out.println("Print details for all places to stay in list");
					showAllDetails(mainList);
					break;
				case 4:
					System.out.println("Display details of least 3 expensive places in Los Angeles with a 4 star rating, ordered by price low to high");
					List<Accommodation> losAngeles = searchByCityandStar(mainList, "Los Angeles", 4, 3); 
					showAllDetails(losAngeles);
					break;
				case 5:
					System.out.println("Display the number of unique cities in the current list");
					displayUniqueCityNum(mainList); 
					break;
				case 6:
					System.out.println("Display the 4 most expensie BnB in Dublin ordered by price - high to low");
					List<Accommodation> dublinBnB = searchByCityandType(mainList, "Dublin", Type.BNB, 4); 
					showAllDetails(dublinBnB);
					//TODO add required method calls etc
					break;
				case 7:
					System.out.println("Task Not Yet Implemented");
					//TODO add required method calls etc
					break;
				case 8:
					System.out.println("Task Not Yet Implemented");
					//TODO add required method calls etc
					break;
				case 9:
					System.out.println("Write city names and abg cost in a seperate thread");
					callWriterThread(mainList); 
					
					break;
				case QUIT:
					System.out.println("Quitting");
					break;
				default:
					System.out.println("Try options again...");
				}

			} catch (Exception e) {
				System.out.println("Exception caught");
				System.out.println(e.getMessage());
				System.out.println("please try again");
			}

		} while (option != QUIT);
	}
	/**
	 * method to call a writer thread 
	 * @param mainList
	 */
	private static void callWriterThread(List<Accommodation> mainList) {
		WriterThread myWriter = new WriterThread(mainList); 
		Thread t = new Thread(myWriter); 
		t.start();
	}

	/**
	 * method to search by city and type of accommodation 
	 * @param inputList
	 * @param city
	 * @param type 
	 * @param target
	 * @return
	 */
	private static List<Accommodation> searchByCityandType(List<Accommodation> inputList, String city, Type type, int target) {
		if (inputList == null) {
			throw new IllegalArgumentException("Input list cannot be null");
		}
			// search by city 
			List<Accommodation> citySearch = searchByCity(inputList, city); 
			List<Accommodation> bnb = new ArrayList<>(); 
			List<Accommodation> results = new ArrayList<>(); 
			// need a 4 stars only 
			for (Accommodation accom : citySearch) {
				if (accom.getType().equals(type)) {
					bnb.add(accom); 
				}
			}
			// compare by price 
			Collections.sort(bnb, new CompareByPriceHigh());
			// for loop for 3 least expensive checking if there are 3 to print 
			int toLoop = bnb.size()>= target ? target : bnb.size(); 
			for (int i=0; i<toLoop ; i++) {
				results.add(bnb.get(i)); 
			}
				return results;
		}
	/**
	 * method to display the number of unique cities in the list 
	 * @param inputList
	 */
	private static void displayUniqueCityNum(List<Accommodation> inputList) throws IllegalArgumentException{
		if (inputList == null) {
			throw new IllegalArgumentException("Input list cannot be null");
		}
		Set<Accommodation> unique = new HashSet<>(); 
		List<Accommodation> uniqueCities= new ArrayList<Accommodation>(); 
		List<Accommodation> duplicates= new ArrayList<Accommodation>(); 
		for (Accommodation accommodation : inputList) {
			if (uniqueCities.contains(accommodation.getCity())) {
				
			}
		}
		System.out.println("Number of cities: " + uniqueCities.size());
	}

	/**
	 * Method to search carry out a search by required city and number of stars and provide details for the least expensive with a
	 *  target value of options displayed
	 * @param inputList
	 * @param city
	 * @param noOfStars
	 * @param target
	 * @return an arraylist of the results
	 */
private static List<Accommodation> searchByCityandStar(List<Accommodation> inputList, String city, int noOfStars, int target) throws IllegalArgumentException {
	if (inputList == null) {
		throw new IllegalArgumentException("Input list cannot be null");
	}
	
	// search by city 
	List<Accommodation> citySearch = searchByCity(inputList, city); 
	List<Accommodation> stars = new ArrayList<>(); 
	List<Accommodation> results = new ArrayList<>(); 
	// need a 4 stars only 
	for (Accommodation accom : citySearch) {
		if (accom.getStars() == noOfStars) {
			stars.add(accom); 
		}
	}
	// compare by price 
	Collections.sort(stars, new CompareByPriceLow());
	// for loop for 3 least expensive checking if there are 3 to print 
	int toLoop = stars.size()>= target ? target : stars.size(); 
	for (int i=0; i<toLoop ; i++) {
		results.add(stars.get(i)); 
	}
		return results;
	}
/**
 * method to carry out a search by city on a provided input list 
 * @param inputList
 * @param city
 * @return
 */
private static List<Accommodation> searchByCity(List<Accommodation> inputList, String city) {
	List<Accommodation> results = new ArrayList<Accommodation>(); 
	
	for (Accommodation accommodation : inputList) {
		if (accommodation.getCity().equals(city)); {
			results.add(accommodation);
		}
	}
	return results;
}

/**
 * method to display all details of the accommodation from the inputed list 
 * @param inputList
 */
	private static void showAllDetails(List<Accommodation> inputList) {
		int count = 1; 
		if (inputList == null ) {
			System.out.println("Can't be null");
		}
		if (inputList.size() == 0) {
			System.out.println("No values in list");
		} else {
			for (Accommodation accommodation : inputList) {
				System.out.println(count++ +")");
				accommodation.printDetails(); 
			}
		}
	}

	/**
	 * Read data from a given filename for a formatted csv file of accommodation
	 * data
	 * 
	 * @param filename
	 * @return list of accommodation objects for each row of the file containing
	 *         valid data
	 */
	public static List<Accommodation> readRoomData(String filename) {
		List<Accommodation> rooms = new ArrayList<Accommodation>();
		
		try {
			File file = new File(filename); 
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			
			String line = br.readLine(); // discard header
			line = br.readLine(); 
			
			while(line != null) {
				
				try {
					Accommodation accom = accommodationFactory(line); 
					rooms.add(accom);
				} catch (Exception e) {
					System.out.println("Bad data in file, skipping line");
					e.printStackTrace();
				} 
				line = br.readLine(); // read next line 
			}
			fr.close();
			br.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		System.out.println(rooms.size() + " entries read successfully");
		return rooms;
	}
/**
 * method to process the data from the csv file into an accommodation object 
 * @param line
 * @return
 */
	private static Accommodation accommodationFactory(String line) {
		 // process the line 
		String[] parts = line.split(","); 
		
		// apply the parts to the accommodation variables 
		String name = parts[0]; 
		Type type = Type.valueOf(parts[1].toUpperCase()); 
		int stars = Integer.parseInt(parts[2]); 
		String city = parts[3];
		double price =Double.parseDouble(parts[4]); 
		int rooms = Integer.parseInt(parts[5]); 
		
		// create a card object 
		Accommodation accom = new Accommodation(name, type, stars, city, price, rooms); 
		
		return accom;
	}

	/**
	 * Display prompt and get int user input via static scanner repeat if invalid
	 * input type given - no need to modify this method
	 * 
	 * @return int value entered via scanner
	 */
	private static int getMenuChoice(String prompt) {
		try {
			System.out.println(prompt);
			int choice = scanner.nextInt();
			return choice;
		} catch (Exception e) {
			System.out.println("Invalid input try again");
			// clear buffer if required
			if (scanner.hasNext()) {
				scanner.next();// read and discard line break
			}
			return getMenuChoice(prompt);// return recursive call to method to recover
		}
	}

	/**
	 * Writes menu options to console - no need to modify this method
	 */
	private static void displayOptions() {
		System.out.println();
		System.out.println("Menu Options:");
		System.out.println("1. (Re)read Data From Original File (use to restore removed data for example)");
		System.out.println("2. Display the number of places to stay in the current list");
		System.out.println("3. Display details for all places to stay in the current list");
		System.out.println(
				"4. Display details of the 3 least expensive 4 Star places to stay in Los Angeles (low to high)");
		System.out.println("5. Display the number of cities in the current list");
		System.out.println("6. Display details of the 4 most expensive BnBs in Dublin (high to low)");
		System.out.println("7. Display the average price of a hotel in New York");
		System.out.println("8. Remove all entries with fewer than 10 rooms available for Paris from the current list");
		System.out.println("9. (Separate Thread) Write out to a formatted csv, "
				+ "\nthe name of each city and the average price of a hotel there (2 decimal places, alphabetcically by City name)");
		System.out.println("10. Quit");
	}

}
