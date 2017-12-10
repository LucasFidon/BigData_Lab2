package cs.Lab2.SortperTFIDF;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SortReducer extends Reducer<FloatWritable, Text, Text, FloatWritable>{
	@Override
	public void reduce(FloatWritable score, Iterable<Text> words, Context context)
			throws IOException, InterruptedException{
		// the reducer is treating the element by ordering score
		// so one can just return the original couple word_docid tf-idf
		for(Text word_doc: words){
			Float tfidf = 1/score.get() - 1;
			context.write(word_doc, new FloatWritable(tfidf));
		}
	}
}
