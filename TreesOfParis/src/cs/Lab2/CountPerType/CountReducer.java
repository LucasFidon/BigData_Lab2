package cs.Lab2.CountPerType;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class CountReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
	@Override
	public void reduce(Text type, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException{
		int sum = 0;
		for(IntWritable val: values){
			sum += val.get();
		}
		context.write(type, new IntWritable(sum));
	}
}
