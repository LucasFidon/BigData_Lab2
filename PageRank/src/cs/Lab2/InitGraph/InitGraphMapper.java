package cs.Lab2.InitGraph;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class InitGraphMapper extends Mapper<LongWritable, Text, IntWritable, IntWritable>{
	private IntWritable nid = new IntWritable();  // Id number of the current node
	private IntWritable out_node = new IntWritable(); // out node of the current node
	
	@Override
	public void map(LongWritable key, Text value, Context context) 
			throws IOException, InterruptedException{
		for (String line: value.toString().split("\n")){
			// Format of the line: FromNodeId	ToNodeId
			//System.out.println(line);
			String[] edge = line.split("\t");
			int in_node = Integer.parseInt(edge[0]);
			int out_n = Integer.parseInt(edge[1]);
			nid.set(in_node);
			out_node.set(out_n);
			context.write(nid, out_node);
		}
	}

}
