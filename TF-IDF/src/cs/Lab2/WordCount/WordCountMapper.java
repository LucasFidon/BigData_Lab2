package cs.Lab2.WordCount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	private IntWritable ONE = new IntWritable(1);
	private Text word = new Text();
	
	/*
	 * Output: ["word@docId", 1]
	 */
	
	@Override
	public void map(LongWritable key, Text value, Context context) 
			throws IOException, InterruptedException{
		
		// Get the name of the file from the inputsplit in the context
        String fileName = ((FileSplit) context.getInputSplit()).getPath().getName();

		for (String token: value.toString().split("\\s+")){
			// pre-processing
			token = token.toLowerCase();
			token = token.replace("'", "");
			token = token.replace("(", "");
			token = token.replace(")", "");
			token = token.replace(".", "");
			token = token.replace(",", "");
			token = token.replace(";", "");
			token = token.replace("\"", "");
			token = token.replace("?", "");
			token = token.replace("!", "");
			token = token.replace("&", "");
			token = token.replace(":", "");
			token = token.replace("-", "");
			if (token.length() > 0){
				// return key value pair: ["word@docId", 1]
				word.set(token + "@" + fileName);
				context.write(word,  ONE);
			}
		}
	}
}
