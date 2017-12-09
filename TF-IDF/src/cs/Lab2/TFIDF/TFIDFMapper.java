package cs.Lab2.TFIDF;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class TFIDFMapper extends Mapper<LongWritable, Text, Text, Text>{
	private Text word = new Text();
	private Text info = new Text();
	
	/*
	 * Input text file with line of the form: "word@docID" "wordcount;wordsperdoc"
	 * Output: ["word", "docID;wordcount;wordsperdoc"]
	 */
	
	@Override
	public void map(LongWritable key, Text value, Context context) 
			throws IOException, InterruptedException{
		
		for (String line: value.toString().split("\n")){
			// pre-processing
			String temp_word = line.split("@")[0];
			String docid = line.split("@")[1].split("\t")[0];
			String wordcount_wordsperdoc = line.split("@")[1].split("\t")[1];
			// return key value pair: ["docId", "word:wordcount"]
			word.set(temp_word);
			info.set(docid+";"+wordcount_wordsperdoc);
			context.write(word, info);
		}
	}
}
