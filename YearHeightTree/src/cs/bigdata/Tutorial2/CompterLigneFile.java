package cs.bigdata.Tutorial2;


import java.io.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;



public class CompterLigneFile {

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
				lines_count++;
				// Process of the current line
				System.out.println(line);
				// go to the next line
				line = br.readLine();
			}
		}
		finally{
			//close the file
			in.close();
			fs.close();
			System.out.println("\nNumber of lines: " + lines_count);
		}
 
		
		
	}

}
