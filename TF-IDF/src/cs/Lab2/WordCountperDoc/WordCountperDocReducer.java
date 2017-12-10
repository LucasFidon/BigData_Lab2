package cs.Lab2.WordCountperDoc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WordCountperDocReducer extends Reducer<Text, Text, Text, Text>{
	@Override
	public void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException{
		// count words per doc
		int wordsPerDoc = 0;
		Map<String, Integer> tempWordWordcount = new HashMap<String, Integer>();
		for(Text word_wordcount: values){
			String word = word_wordcount.toString().split(":")[0];
			int wordcount = Integer.parseInt(word_wordcount.toString().split(":")[1]);
			tempWordWordcount.put(word, wordcount);
			wordsPerDoc += wordcount;
		}
		// reduce
		for(String word: tempWordWordcount.keySet()){
			context.write(new Text(word +"@"+ key.toString()), 
					new Text(tempWordWordcount.get(word)+";"+wordsPerDoc));
		}
	}
}
