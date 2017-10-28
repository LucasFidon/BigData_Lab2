package cs.Lab2.TFIDF;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;

public class ParserNCDC {
	public static String month;
	public static float temperature;
	
	public static void read_line(String line){
		ParserNCDC.month = line.substring(16+3, 16+5);
		//System.out.println(line.substring(87, 92));
		ParserNCDC.temperature = Float.parseFloat(line.substring(87, 92));
	}
	
	public static ArrayList<NCDCData> parseFile(Text value){
		ArrayList<NCDCData> itr = new ArrayList<NCDCData>();
		String[] text = value.toString().split("\n");
		//System.out.println("coucou " + text[0]);
		for(String line:text){
			ParserNCDC.read_line(line);
			NCDCData line_data = new NCDCData(ParserNCDC.month, ParserNCDC.temperature);
			System.out.println("coucou: " + line_data.month + ", " + line_data.temperature);
			itr.add(line_data);		
		}
		return itr;
	}

	public static void main(String[] args) throws IOException {
		String localSrc = "/home/cloudera/Lab2/BigData_Lab2/Data/NCDC/008414-99999-2015";
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
				ParserNCDC.read_line(line);
				System.out.println("month: " + ParserNCDC.month 
							+ ", temperature: " + ParserNCDC.temperature);	
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
