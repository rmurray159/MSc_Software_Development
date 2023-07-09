package challengeSuperhero;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author RachelMurray 15419045
 */
public class ReadCodePractice {

	public static void main(String[] args) {
		List<TopTrumpCard> mainDeck = new ArrayList<TopTrumpCard>();
		mainDeck = readData("toptrumpscards.csv");
		
		for (TopTrumpCard card : mainDeck) {
			System.out.println(card.toString()); 
			
		}
	}
/**
 * reads in the data from the provided csv and returns a list of the processed top trump cards 
 * @param fileName
 * @return
 */
	private static List<TopTrumpCard> readData(String fileName) {
		List<TopTrumpCard> listFromFile = new ArrayList<TopTrumpCard>();

		try {
			File file = new File(fileName);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			
			String line = br.readLine(); // Discard header 
			line = br.readLine(); 
			
			while( line != null) {
				
				try {
					TopTrumpCard card = cardFactory(line); 
					listFromFile.add(card);
				} catch (Exception e) {
					System.out.println("Bad data in file, skipping and move to next line");
					e.printStackTrace();
				}
				line = br.readLine(); // read next line and loop again 
			}
			// 
			fr.close();
			br.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(listFromFile.size() +" lines read successfully");
		return listFromFile;
	}
private static TopTrumpCard cardFactory(String line) {
	String [] parts = line.split(","); 
	
	// break parts into the component variables 
	String name = parts[0]; 
	String realName = parts[1];
	String imageName = parts[2]; 
	Category cat = Category.valueOf(parts[3].toUpperCase());
	int speed = Integer.parseInt(parts[4]); 
	int strength = Integer.parseInt(parts[5]);
	int agility = Integer.parseInt(parts[6]);
	int intelligence = Integer.parseInt(parts[7]); 
	String bio = parts[8]; 
	
	// using split to further break down a value within a cell 
	String[] nameParts = realName.split(" ", 2); 
	String firstName = nameParts[0];
	String lastName = nameParts[1]; 
	
	// create card object 
	TopTrumpCard card = new TopTrumpCard(name, realName, imageName, cat, speed, strength, agility, intelligence, bio); 
	
	// print fist name and last name to test 
	System.out.println(firstName);
	System.out.println(lastName);

 	return card;
}

}
