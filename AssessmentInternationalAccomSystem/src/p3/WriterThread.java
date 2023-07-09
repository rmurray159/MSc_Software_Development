package p3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

/**
*@author RachelMurray 15419045
*/
public class WriterThread implements Runnable {
	
	// instance variables 
	List<Accommodation> mainList; 

	public WriterThread(List<Accommodation> mainList) {
		this.mainList=mainList; 
	}

	@Override
	public void run() {
		// create map with city as key and average price as value 
		// calcualte the average by city 
		// sort by city name alpehticall - TreeMap will do this automatically 
		
		// write to file 
		File file = new file ("averageCosts.csv");
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw); 
		
		String line = 
		bw.write(line);
	}

}
