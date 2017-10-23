package cs.Lab2.ComputeMean;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

public class ComputeMeanReducer extends Reducer<Text, FloatWritable, Text, FloatWritable>{
	@Override
	public void reduce(Text key, Iterable<FloatWritable> values, Context context)
			throws IOException, InterruptedException{
		int sum = 0;
		int cnt = 0;
		for(FloatWritable val: values){
			sum += val.get();
			cnt += 1;
		}
		float mean = sum/cnt;
		context.write(key, new FloatWritable(mean));
	}
}
