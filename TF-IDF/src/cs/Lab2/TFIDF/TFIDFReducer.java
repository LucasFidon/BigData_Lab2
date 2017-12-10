package cs.Lab2.TFIDF;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class TFIDFReducer extends Reducer<Text, Text, Text, DoubleWritable>{
	@Override
	public void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException{
		// count docs per word
		Map<String, String> tempDocID = new HashMap<String, String>();
		float docsPerWord = 0;
		for(Text info: values){
			String docID = info.toString().split(";")[0];
			String counts = info.toString().split(";")[1] +";"+ info.toString().split(";")[2];
			tempDocID.put(docID, counts);
			docsPerWord += 1; 
		}
		// reduce
		for(String docID: tempDocID.keySet()){
			float wordCount = Float.parseFloat(tempDocID.get(docID).split(";")[0]);
			float wordsPerDoc = Float.parseFloat(tempDocID.get(docID).split(";")[1]);
			double tfidf = wordCount/wordsPerDoc*Math.log(TFIDFDriver.totalDocs/docsPerWord);
			context.write(new Text(key.toString() +"@"+ docID), 
					new DoubleWritable(tfidf));
		}
	}
}
