package cs.Lab2.HighestTreePerType;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MaxReducer extends Reducer<Text, FloatWritable, Text, FloatWritable>{
	// as the max operator is commutative and distributive 
	// one can just take the max of all heights at each call to the reducer
	// (and we won't have the same problem as for computing the mean for example)
	@Override
	public void reduce(Text type, Iterable<FloatWritable> heights, Context context)
			throws IOException, InterruptedException{
		Float max_height = (float) -1;
		for(FloatWritable height: heights){
			max_height = Math.max(max_height, height.get());
		}
		context.write(type, new FloatWritable(max_height));
	}
}
