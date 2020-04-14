package hw2;

import java.io.FileWriter;
import java.io.IOException;

public class  App {

    public static void main(String[] args) {
	// Parameters
	int frequency = 1;
	int N = 2000;
	double alpha = 2;
	String filename = "data.csv";

        long beforeUsedMem = Runtime.getRuntime().totalMemory() -
	    Runtime.getRuntime().freeMemory();
	
	try (FileWriter f = new FileWriter(filename)) {
	    SimpleHashMap<String, Integer> m = new SimpleHashMap(alpha);
	    for (Integer i = 1; i <= N; i++) {
		f.write(String.format("%d,%d,%.4f\n",
				      i, m.maxChainSize(), m.averageChainSize()));
		m.put(i.toString(), i);
	    }
	} catch (IOException e) {
	    System.out.println(String.format("Failed to create file %s - %s",
					     filename, e.toString()));
	}
	long afterUsedMem = Runtime.getRuntime().totalMemory() -
	    Runtime.getRuntime().freeMemory();
	double usedMemory = (double)(afterUsedMem - beforeUsedMem)/1e6;
	System.out.println(String.format("Total used memory: %.1f MB", usedMemory));
    }
}
