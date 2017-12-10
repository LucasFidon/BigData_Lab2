package cs.Lab2.WordCountperDoc;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCountperDocMapper extends Mapper<LongWritable, Text, Text, Text>{
	private Text docID = new Text();
	private Text word_wordcount = new Text();
	
	/*
	 * Input text file with line on the form: "word@docID wordcount"
	 * Output: ["docId", "word:wordcount"]
	 */
	
	@Override
	public void map(LongWritable key, Text value, Context context) 
			throws IOException, InterruptedException{
		
		for (String line: value.toString().split("\n")){
			// pre-processing
			String word = line.split("@")[0];
			String docid = line.split("@")[1].split("\t")[0];
			String wordcount = line.split("@")[1].split("\t")[1];
			// return key value pair: ["docId", "word:wordcount"]
			docID.set(docid);
			word_wordcount.set(word+":"+wordcount);
			context.write(docID, word_wordcount);
		}
	}
}
