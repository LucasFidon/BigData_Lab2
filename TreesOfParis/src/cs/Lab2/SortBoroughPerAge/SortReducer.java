package cs.Lab2.SortBoroughPerAge;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SortReducer extends Reducer<IntWritable, Text, Text, Text>{
	@Override
	public void reduce(IntWritable birth_year, Iterable<Text> boroughs, Context context)
			throws IOException, InterruptedException{
		for(Text borough: boroughs){
			context.write(new Text(borough + "eme Arrondissement"), 
					      new Text("Annee de plantation: " + birth_year.get()));
		}
	}
}
