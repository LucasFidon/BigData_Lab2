package cs.Lab2.CountPerType;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import cs.Lab2.Tree.Tree;

public class CountMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	private Text type = new Text();
	private IntWritable ONE = new IntWritable(1);
	
	@Override
	public void map(LongWritable key, Text value, Context context) 
			throws IOException, InterruptedException{
		
		for (String line: value.toString().split("\n")){
			// build tree from line
			Tree tree = new Tree(line);
			// extract information of interest
			// we will assume that what the author of the homework means by "type" is actually "genre"...
			type.set(tree.genre);
			context.write(type, ONE);
		}
	}
}
