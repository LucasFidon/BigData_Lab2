package cs.bigdata.Tutorial2;

import java.io.*;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.fs.FileSystem;

public class Tree {
	public static String height;
	public static String year;
	
	public static void read_line(String line){
		String[] fields = line.split(";");
		Tree.year = fields[5];
		if (Tree.year.length() < 4){
			Tree.year = "unknown";
		}
		Tree.height = fields[6];
	}

	public static void main(String[] args) throws IOException {
		String localSrc = "/home/cloudera/Lab2/Data/arbres.csv";
		int lines_count = 0;
		//Open the file
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(conf);
		InputStream in = new BufferedInputStream(new FileInputStream(localSrc));
		
		try{
			InputStreamReader isr = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(isr);
			
			// read line by line
			String line = br.readLine();
			
			while (line !=null){
				// Process of the current line
				Tree.read_line(line);
				if (lines_count > 0){
					System.out.println("Tree n" + lines_count +": year: " + Tree.year + ", height: " + Tree.height);
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
