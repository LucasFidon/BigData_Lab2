package cs.Lab2.PageRank;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

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

public class PageRankMapper extends Mapper<LongWritable, Text, IntWritable, Text>{
	private IntWritable nid = new IntWritable();
	private Text p = new Text();
	
	
	
	@Override
	public void map(LongWritable key, Text value, Context context) 
			throws IOException, InterruptedException{
		for (String node_info: value.toString().split("\n")){
			// instantiate the node from its info
			//System.out.println(node_info);
		    Node node = new Node(node_info.split("\t")[1]);
		    //System.out.println(node.toString());
		    if (node.adjencyList.size() == 0){
		    	// if no out neighbors just pass the node unchanged
		    	p.set(node.toString());
				context.write(nid,  p);
		    }
		    else{
			    // mass unit of the page rank of node to pass to its out neighbors
			    float mass_unit = node.pageRank/node.adjencyList.size();
			    
				// pass graph structure (that will be useful for the PageRank reducer)
				node.pageRank = 0;
				nid.set(node.nodeId);
				// node format is: "[nid:<int>, p:<float>, adjacentNodes:<int separated by space>]"
				p.set(node.toString());
				context.write(nid,  p);
				
				// pass PageRank mass to neighbors
				p.set(Float.toString(mass_unit));
				for (int nodeid: node.adjencyList){
					nid.set(nodeid);
					context.write(nid,  p);
				}	
		    }
		}
	}
}
