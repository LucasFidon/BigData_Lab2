package cs.Lab2.PageRank;

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

public class PageRankReducer extends Reducer<IntWritable, Text, IntWritable, Text>{
	@Override
	public void reduce(IntWritable key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException{
		float sum = 0;
		Node node = new Node(Integer.parseInt(key.toString()));
		for(Text val: values){
			String p = val.toString();
			if (p.contains("nid:")){
				// instantiate the node from its info
				Node n =  new Node(p.toString());
				for (int adj_n: n.adjencyList){
					node.add_adjacent_node(adj_n);
				}
				//node.adjencyList = n.adjencyList;
				sum += n.pageRank;  // it is 0 except when the current node is a sink
			}
			else{ //p is a contribution to the new value for the pageRank of the current node
				float p_val = Float.parseFloat(p);
				sum += p_val;
			}
		}
		// apply damping factor
		node.pageRank = (float) (0.85*sum + 0.15);
		context.write(key, new Text(node.toString()));
	}
}
