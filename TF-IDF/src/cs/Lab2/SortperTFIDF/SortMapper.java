package cs.Lab2.SortperTFIDF;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SortMapper extends Mapper<LongWritable, Text, FloatWritable, Text>{
	//a 1-to-1 non-increasing function is applied to the tf-idf score to sort them by descending order in the end
	private FloatWritable score = new FloatWritable();
	private Text word_doc = new Text();
	
	/*
	 * Input text file with line of the form: "word@docID" "TF-IDF"
	 * Output: ["word@docID" "TF-IDF"] sorted per value (descending order)
	 */
	
	@Override
	public void map(LongWritable key, Text value, Context context) 
			throws IOException, InterruptedException{
		
		for (String line: value.toString().split("\n")){
			// pre-processing
			String word_docid = line.split("\t")[0];
			Float tfidf = Float.parseFloat(line.split("\t")[1]);
			// return key value pair: [score, "word_doc"]
			score.set(1/(1+tfidf));
			word_doc.set(word_docid);
			context.write(score, word_doc);
		}
	}
}

