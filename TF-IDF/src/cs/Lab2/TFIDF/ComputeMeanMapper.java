package cs.Lab2.TFIDF;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

public class ComputeMeanMapper extends Mapper<LongWritable, Text, Text, FloatWritable>{
	private FloatWritable temperature = new FloatWritable();
	private Text month = new Text();
	
	@Override
	public void map(LongWritable key, Text value, Context context) 
			throws IOException, InterruptedException{
		ArrayList<NCDCData> itr = ParserNCDC.parseFile(value);
		for (NCDCData data: itr){
			month.set(data.month);
			temperature.set(data.temperature);
			context.write(month,  temperature);
		}
	}
}
