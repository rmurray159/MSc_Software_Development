package challengeSuperhero;

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

/**
 * Rachel Murray 15419045
 * 
 *
 */
public class StartApp {

	/**
	 * Entry point of program - no need to modify this method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		showMenu();
	}

	// TODO modify readData method to populate List appropriately - method partially
	// completed already
	// TODO add static methods to this class as required to achieve tasks outlined
	// in menu
	// TODO modify showMenu method to add calls to new methods you write to
	// accomplish the tasks outlined in the menu

	/**
	 * Launches menu system which in turn calls appropriate methods based on user
	 * choices Partially implemented already requires updating to add calls to other
	 * methods written to achieve the tasks described in tasks 3-10
	 */
	public static void showMenu() {
		List<TopTrumpCard> mainDeck = readData();

		System.out.println();
		Scanner scanner = new Scanner(System.in);
		int option;
		System.out.println("Hero Card Processing");
		do {
			System.out.println("1. (Re)read Data From File (use to restore removed card for example)");
			System.out.println("2. Play Demo Game with Current Deck");
			System.out.println("3. Display the number of Cards in the Current Deck");
			System.out.println("4. Display full details for all cards in the current Deck");
			System.out.println("5. Display summary details of the top 5 strongest Heroes in the Deck");
			System.out.println(
					"6. Display summary details of Villains in the Deck with an agility rating of 75 or more, sorted alphatically by name");
			System.out.println("7. Display the card name and bio of the card/cards with the longest bio in the Deck");
			System.out.println(
					"8. Find Swapsies: Identify and display summary details of any duplicate cards in the Deck");
			System.out.println("9. Remove any duplicate cards from the Deck.");
			System.out.println(
					"10. Sort the deck from highest to lowest determined by average of the 4 main stats. \n\tDisplay summary details of all cards and include the average as part of the summary.");
			System.out.println("11. Remove any duplicate cards from the Deck.");
			System.out.println("12. Quit");
			System.out.println("Enter option ...");
			option = scanner.nextInt();
			System.out.println();
			switch (option) {

			case 1:
				mainDeck = readData();
				break;
			case 2:
				System.out.println("Option currently disabled...");
				/*
				 * TODO Uncomment method call to enable this option (requires TopTrumpCard class
				 * to match expectations of Game Method to compile successfully) deliberately
				 * commented out initially to allow attempting other tasks
				 */
				CardGame.playDemo(mainDeck);
				break;
			case 3:
				System.out.println("Number of cards in deck: ");
				System.out.println(mainDeck.size());
				// look at adding more info for if deck is empty
				break;
			case 4:
				System.out.println("Print details of all cards in deck");
				showAllDetails(mainDeck);
				break;
			case 5:
				System.out.println("Summary of top 5 strongest heros");
				List<TopTrumpCard> strong = findXStrongest(mainDeck, 5, Category.HERO);
				showSummaryDetails(strong);
				break;
			case 6:
				System.out.println("Summary of Villains with agility rating of 75 or more");
				List<TopTrumpCard> agile = findAgileAbove(mainDeck, 75, Category.VILLAIN);
				showSummaryDetails(agile);

				break;
			case 7:
				System.out.println("Card/s with longest bio");
				List<TopTrumpCard> longBio = findLongestBio(mainDeck);
				showBioDetails(longBio);
				break;
			case 8:
				System.out.println("Find the duplicate cards");
				List<TopTrumpCard> duplicateCards = identifyDuplicates(mainDeck);
				showSummaryDetails(duplicateCards);
				break;
			case 9:
				// Matthews example of using a thread not in the spec
				System.out.println("Write names of all cards with speed over 60 to a file in a seperate thread");
				callWriterThread(mainDeck);
				break;
			case 10:
				System.out.println("Highest to lowest by average of stats");
				List<TopTrumpCard> avgStats = sortByAvgStat(mainDeck);
				showSummaryDetailsAvg(avgStats);
				break;
			case 11:
				System.out.println("Remove duplicate cards");
				removeDuplicates(mainDeck);
				break;
			case 12:
				System.out.println("Quitting");
				break;
			default:
				System.out.println("Try options again...");
			}
		} while (option != 12);
		scanner.close();
	}

	/**
	 * 
	 * @param mainDeck
	 * @return
	 */
	public static List<TopTrumpCard> sortByAvgStat(List<TopTrumpCard> mainDeck) throws IllegalArgumentException {
		if (mainDeck == null) {
			throw new IllegalArgumentException("Input list cannor be null");
		}
		List<TopTrumpCard> localCopy = new ArrayList<TopTrumpCard>(mainDeck);

		// sort by avg stat
		Collections.sort(localCopy, new CompareByAvgStat());

		return localCopy;
	}

	/**
	 * 
	 * @param inputList
	 */
	public static void removeDuplicates(List<TopTrumpCard> inputList) throws IllegalArgumentException  {
		if (inputList == null) {
			throw new IllegalArgumentException("Input list cannor be null");
		}
		// create set from the input list - conversion constructor (no duplicates by
		// definition)
		Set<TopTrumpCard> unique = new HashSet<TopTrumpCard>(inputList);
		// empty the input list
		inputList.clear();
		// add everything from the set into the empty input list
		inputList.addAll(unique);

	}

	/**
	 * 
	 * @param mainDeck
	 * @return
	 */
	public static List<TopTrumpCard> identifyDuplicates(List<TopTrumpCard> mainDeck) throws  IllegalArgumentException {
		if (mainDeck == null) {
			throw new IllegalArgumentException("Input list cannor be null");
		}
		List<TopTrumpCard> duplicates = new ArrayList<TopTrumpCard>();
		Set<TopTrumpCard> set = new HashSet<>();

		for (TopTrumpCard i : mainDeck) {
			if (set.contains(i)) {

				duplicates.add(i);
			} else {
				set.add(i);
			}
		}
		return duplicates;
	}

	/**
	 * Method to Display the name and bio of the card/cards with the longest bio in
	 * the current deck based on the number of characters
	 * 
	 * @param mainDeck
	 */
	public static List<TopTrumpCard> findLongestBio(List<TopTrumpCard> mainDeck) throws IllegalArgumentException {
		if (mainDeck == null) {
			throw new IllegalArgumentException("Input list cannor be null");
		}
		List<TopTrumpCard> localCopy = new ArrayList<TopTrumpCard>(mainDeck);
		List<TopTrumpCard> result = new ArrayList<TopTrumpCard>();
		// use collections max method to get the max value only
		TopTrumpCard max = Collections.max(localCopy, new CompareByBio());

		for (TopTrumpCard i : localCopy) {
			if (i.equals(max)) {
				result.add(i);
			}
		}
		return result;
	}

	/**
	 * 
	 * @param inputList
	 * @param target
	 * @param cat
	 * @return
	 */
	public static List<TopTrumpCard> findAgileAbove(List<TopTrumpCard> inputList, int target, Category cat) throws IllegalArgumentException  {
		if (inputList == null) {
			throw new IllegalArgumentException("Input list cannor be null");
		}
		if (target < 0 || target >100) {
			throw new IllegalArgumentException("Target cannot be outside attribute bounds");
		}
		if (cat == null) {
			throw new IllegalArgumentException("Cannot search for null");
		}
		List<TopTrumpCard> results = new ArrayList<TopTrumpCard>();
		for (TopTrumpCard card : inputList) {
			if (card.getCategory().equals(cat) && (card.getAgility() >= target))
				results.add(card);
		}
		// sort alphabetically by name
		Collections.sort(results, new CompareByName());
		return results;
		
	}

	/**
	 * 
	 * @param mainDeck
	 */
	public static void callWriterThread(List<TopTrumpCard> mainDeck) {
		WriterThread myWriter = new WriterThread(mainDeck, 60);
		Thread t = new Thread(myWriter);
		t.start();
	}

	/**
	 * 
	 * @param inputList
	 * @param target
	 * @param cat
	 * @return
	 */
	public static List<TopTrumpCard> findXStrongest(List<TopTrumpCard> inputList, int target, Category cat) throws IllegalArgumentException {
		if (inputList == null) {
			throw new IllegalArgumentException("Input list cannor be null");
		}
		if (cat == null) {
			throw new IllegalArgumentException("Cannot search for null");
		}
	
		List<TopTrumpCard> heroList = new ArrayList<TopTrumpCard>();
		List<TopTrumpCard> results = new ArrayList<TopTrumpCard>();
		// sort by category HERO/VILLAIN based on method input
		for (TopTrumpCard card : inputList) {
			if (card.getCategory().equals(cat)) {
				heroList.add(card);
			}
		}
		// sort by strength
		Collections.sort(heroList, new CompareByStrength());
		// could also use collections.reverse to change from ascend to descend, would keep the comparator as expected 
		// Collections.sort(heroList, Collections.reverseOrder(new CompareByStrength())); 
		
		// could use collections .sort with a hero/villain comparator 

		// select top 5, add to results list
		
		// check if there are the target number of results else use  list length 
		int toLoop = heroList.size() >= target ? target : heroList.size(); 
		for (int i = 0; i < toLoop ; i++) {
			results.add(heroList.get(i));
		}
		return results;
	}

	/**
	 * 
	 * @param inputList
	 */
	public static void showAllDetails(List<TopTrumpCard> inputList) {
		int count = 1; 
		if (inputList == null) {
			System.out.println("Can't be null");
		}
		if (inputList.size() == 0) {
			System.out.println("No values in list");
		} else {
			for (TopTrumpCard card : inputList) {
				System.out.println(count++);
				card.printDetails();
				//count++; 
			}
		}
	}

	/**
	 * 
	 * @param inputList
	 */
	public static void showSummaryDetails(List<TopTrumpCard> inputList) {
		if (inputList == null) {
			System.out.println("Can't be null");
		}
		if (inputList.size() == 0) {
			System.out.println("No values in list");
		} else {
			for (TopTrumpCard card : inputList) {
				card.summaryDetails();
			}
		}
	}

	/**
	 * 
	 * @param inputList
	 */
	public static void showBioDetails(List<TopTrumpCard> inputList) {
		if (inputList == null) {
			System.out.println("Can't be null");
		}
		if (inputList.size() == 0) {
			System.out.println("No values in list");
		} else {
			for (TopTrumpCard card : inputList) {
				card.bioDetails();
			}
		}
	}

	/**
	 * 
	 * @param inputList
	 */
	public static void showSummaryDetailsAvg(List<TopTrumpCard> inputList) {
		if (inputList == null) {
			System.out.println("Can't be null");
		}
		if (inputList.size() == 0) {
			System.out.println("No values in list");
		} else {
			for (TopTrumpCard card : inputList) {
				card.summaryDetailsAvgStats();
			}
		}

	}

	/**
	 * Reads in the data from the provided csv and returns a list of TopTrumpCard
	 * objects for further processing - requires updating for full functionality
	 */
	public static List<TopTrumpCard> readData() {

		List<TopTrumpCard> listFromFile = new ArrayList<TopTrumpCard>();

		File file = new File("toptrumpscards.csv"); // hard coded to specific file

		// try with resources - auto closes reader resources when finished/exception
		// occurs
		try (FileReader fr = new FileReader(file); BufferedReader reader = new BufferedReader(fr);) {

			String line = reader.readLine(); // discard header
			line = reader.readLine(); // read first line

			while (line != null) {
				try {

					TopTrumpCard card = cardFactory(line);

					listFromFile.add(card);

				} catch (Exception ex) {
					System.out.println("Bad data in file, exception caught, skipping line");
					ex.printStackTrace();
				}

				line = reader.readLine();// attempt to read next line and loop again
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found error");
		} catch (IOException e) {
			System.out.println("IO Exception");
		} catch (Exception e) {
			System.out.println("Exception occured");
			System.out.println(listFromFile.size() + " lines read successfully");
			System.out.println(e.getMessage());
		}
		System.out.println(listFromFile.size() + " lines read successfully");
		return listFromFile;
	}

	/**
	 * @param line
	 * @return
	 */
	private static TopTrumpCard cardFactory(String line) {
		// TODO Code to process current line
		String[] parts = line.split(",");

		// TODO instantiate TopTrumpCard object
		String name = parts[0];
		String realname = parts[1];
		String imageName = parts[2];
		Category cat = Category.valueOf(parts[3].toUpperCase());
		int speed = Integer.parseInt(parts[4]);
		int strength = Integer.parseInt(parts[5]);
		int agility = Integer.parseInt(parts[6]);
		int intelligence = Integer.parseInt(parts[7]);
		String bio = parts[8];
		
//		// using string to further break down a value within a cell 
//		String[] nameParts = realname.split(" ", 2); 
//		String firstName = nameParts[0]; 
//		String lastName = nameParts[1]; 

		TopTrumpCard card = new TopTrumpCard(name, realname, imageName, cat, speed, strength, agility,
				intelligence, bio);
		return card;
	}
	
	// dummy data if can't read in the info from file 
	public static List<TopTrumpCard> dummyData(){
		List<TopTrumpCard> data = new ArrayList<TopTrumpCard>();
		data.add(new TopTrumpCard("name1", "real name 1", "a.jpg", Category.HERO, 50, 40, 30, 10, "biowords")); 
		data.add(new TopTrumpCard("name2", "real name 2", "a.jpg", Category.HERO, 50, 40, 30, 10, "biowords")); 
		data.add(new TopTrumpCard("name3", "real name 3", "a.jpg", Category.HERO, 50, 40, 30, 10, "biowords")); 
		data.add(new TopTrumpCard("name4", "real name 4", "a.jpg", Category.HERO, 50, 40, 30, 10, "biowords")); 
		data.add(new TopTrumpCard("name5", "real name 5", "a.jpg", Category.HERO, 50, 40, 30, 10, "biowords")); 
		
		return data; 
	}

}
