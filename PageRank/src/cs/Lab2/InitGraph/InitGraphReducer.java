package cs.Lab2.InitGraph;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

import cs.Lab2.PageRank.Node;

public class InitGraphReducer extends Reducer<IntWritable, IntWritable, IntWritable, Text>{

	public void reduce(IntWritable nid, Iterable<IntWritable> out_nodes, Context context)
			throws IOException, InterruptedException{
		/*
		 * get a node id and the list of all its out neighbors as input
		 * Return all necessary information about the node in the graph:
		 * initial page rank set to 1 and the list of its out neighbors.
		 * Those information correspond to a serialization of the class Node from the package cs.Lab2.PageRank
		 */
		// initialise a Node whose nodeId is nid
		Node node = new Node(nid.get());
		// add adjacent nodes
		for(IntWritable adj_node: out_nodes){
			node.add_adjacent_node(adj_node);
			}
		context.write(nid, new Text(node.toString()));
	}

}
