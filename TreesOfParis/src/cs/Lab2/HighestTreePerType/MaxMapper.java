package cs.Lab2.HighestTreePerType;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import cs.Lab2.Tree.Tree;

public class MaxMapper extends Mapper<LongWritable, Text, Text, FloatWritable>{
	private Text type = new Text();
	private FloatWritable height = new FloatWritable();
	
	@Override
	public void map(LongWritable key, Text value, Context context) 
			throws IOException, InterruptedException{
		
		for (String line: value.toString().split("\n")){
			// build tree from line
			Tree tree = new Tree(line);
			// extract information of interest
			// we will assume that what the author of the homework means by "type" is actually "genre"...
			type.set(tree.genre);
			height.set(tree.hauteur);
			context.write(type, height);
		}
	}
}
