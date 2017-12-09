package cs.Lab2.PageRank;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import java.util.Map.Entry;


public class Run_ranking {

	public static void main(String[] args) throws FileNotFoundException, IOException{
		Map<Integer, Float> pageRank = new HashMap<Integer, Float>();
		//String file = args[0];
		int iter_used = 199;
		String file = "./output_pageRank"+ iter_used +"/part-r-00000";
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       // process the line.
		    	Node node = new Node(line.split("\t")[1]);
		    	// pageRank values are transformed by a one-to-one non-increasing function
		    	pageRank.put(node.nodeId, 1/(1+node.pageRank));
		    }
		}
		HashMap ranking = (HashMap) MapUtil.sortByValue(pageRank);
		Iterator itr = ranking.entrySet().iterator();
		PrintStream out = new PrintStream(new FileOutputStream("top10ranking_output.txt"));
		System.setOut(out);
		System.out.println("Top 10 ranking according to the pageRank for the file /Data/soc-Epinions1.txt");
		System.out.println("(damping factor used: 0,85, number of iterations: "+ iter_used +")");
		for (int i = 0; i<10; i++){
			Map.Entry<Integer,Float> entry = (Entry<Integer, Float>) itr.next();
			int nid = entry.getKey();
			// convert the value back to the corresponding pageRank value
			float p = 1/entry.getValue() - 1;
			System.out.println("Rank " + (i+1) + ": nodeId=" + nid + ", PageRank=" + p);
		}
	}
}
