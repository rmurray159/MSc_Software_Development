/**
 * 
 */
package challengeSuperhero;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
*@author RachelMurray 15419045
*/
/**
 * @author rache
 *
 */
public class ReadCodePractice2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<TopTrumpCard> mainDeck = readData("toptrumpscards.csv");
		for (TopTrumpCard card : mainDeck) {
			card.printDetails();
		}

	}

	private static List<TopTrumpCard> readData(String fileName) {
		List<TopTrumpCard> listFromFile = new ArrayList<TopTrumpCard>();

		try {
			File file = new File(fileName);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			String line = br.readLine();// discard first line
			line = br.readLine(); // read next line

			while (line != null) {

				try {
					// send to method to process the lines
					TopTrumpCard card = cardFactory(line);
					// add card object to the array list
					listFromFile.add(card);
				} catch (Exception e) {
					System.out.println("Bad data in file skip and move to next line");
					e.printStackTrace();
				}
				line = br.readLine();
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
		System.out.println(listFromFile + "lines read successfully");
		return listFromFile;
	}

	private static TopTrumpCard cardFactory(String line) {
		String[] parts = line.split(",");

		String name = parts[0];
		String realName = parts[1];
		String imageName = parts[2];
		Category cat = Category.valueOf(parts[3].toUpperCase());
		int speed = Integer.parseInt(parts[4]);
		int strength = Integer.parseInt(parts[5]);
		int agility = Integer.parseInt(parts[6]);
		int intelligence = Integer.parseInt(parts[7]);
		String bio = parts[8];

		// further break down of realName
		String[] nameParts = realName.split(" ", 2);
		String firstName = nameParts[0];
		String lastName = nameParts[1];

		System.out.println(firstName);
		System.out.println(lastName);
		// create card object
		TopTrumpCard card = new TopTrumpCard(name, realName, imageName, cat, speed, strength, agility, intelligence,
				bio);
		return card;
	}

}
