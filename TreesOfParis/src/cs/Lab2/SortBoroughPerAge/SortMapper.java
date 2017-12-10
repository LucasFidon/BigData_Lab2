package cs.Lab2.SortBoroughPerAge;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import cs.Lab2.Tree.Tree;

public class SortMapper extends Mapper<LongWritable, Text, IntWritable, Text>{
	private IntWritable birth_year = new IntWritable();
	private Text borough = new Text();
	
	@Override
	public void map(LongWritable key, Text value, Context context) 
			throws IOException, InterruptedException{
		
		for (String line: value.toString().split("\n")){
			Tree tree = new Tree(line);
			// ignore trees whose birth year is not known
			if (tree.annee_plantation > -1){
				birth_year.set(tree.annee_plantation);
				borough.set(tree.arrondissment);
				context.write(birth_year, borough);
			}
		}
	}
}

