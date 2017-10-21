package cs.bigdata.Tutorial2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FSDataInputStream;

public class DisplayStation {
	public static String usaf;
	public static String name;
	public static String country;
	public static String elevation;
	
	public static void read_line(String line){
		DisplayStation.name = line.substring(13, 13 + 29);
		DisplayStation.country = line.substring(43, 43 + 2);
		DisplayStation.elevation = line.substring(74, 74 + 7 -1);
	}

	public static void main(String[] args) throws IOException {
		String localSrc = "/home/cloudera/Lab2/BigData_Lab2/Data/isd-history.txt";
		int lines_count = 0;
		//Open the file using Hadoop API
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(conf);
		Path path = new Path(localSrc);
		if (!fs.exists(path)){
			System.out.println("File " + localSrc + " doesn't exist.");
		}
		FSDataInputStream in = fs.open(path);
		
		try{
			InputStreamReader isr = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(isr);
			
			// read line by line
			String line = br.readLine();
			
			while (line !=null){
				// Process of the current line
				if (lines_count > 21){
					DisplayStation.read_line(line);
					System.out.println("Station n" + (21 -lines_count) 
							+ ": name: " + DisplayStation.name 
							+ ", country: " + DisplayStation.country
							+ ", altitude: " + DisplayStation.elevation);
				}	
				// go to the next line
				lines_count++;
				line = br.readLine();
			}
		}
		finally{
			//close the file
			in.close();
			fs.close();
		}

	}
}
