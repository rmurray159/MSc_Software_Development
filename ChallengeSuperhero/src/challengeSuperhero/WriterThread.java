package challengeSuperhero;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriterThread implements Runnable {
// instance variables 
	private List<TopTrumpCard> mainList;
	private int searchTarget;

	// constructor that will be seen in run 
	/**
	 * @param mainList
	 * @param searchTarget
	 */
	public WriterThread(List<TopTrumpCard> mainList, int searchTarget) {
		super();
		this.mainList = mainList;
		this.searchTarget = searchTarget;
	}

	@Override
	public void run() {
		System.out.println("Thread starting");

		List<TopTrumpCard> results = StartApp.findXStrongest(mainList, searchTarget, Category.VILLAIN);

		try {
			File file = new File("SearchResults.csv");
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			
			// this is the actual solution for the search requirement 
			for (TopTrumpCard card : results) {
				// access each object, prepare line to write
				String line = String.format("%s, %d", card.getName(), card.getStrength());
				// write line
				bw.write(line);
				// write new line
				bw.newLine();
			}

			bw.flush();

			bw.close();
			fw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Thread closing");

	};
}
