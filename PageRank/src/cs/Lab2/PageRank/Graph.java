package cs.Lab2.PageRank;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.Text;

public class Graph {
	private int numNodes = 0;
	private HashMap<Integer,Node> nodes = new HashMap<Integer,Node>();

	public Graph(Text value){
		// read data
		int line_num = 0;
		for (String line: value.toString().split("\n")){
			if (line_num == 2){
				// get number of nodes in the graph
				Pattern pattern = Pattern.compile("Nodes:=(.*?) Edges");
				Matcher matcher = pattern.matcher(line);
				this.numNodes = Integer.parseInt(matcher.group());
				// initialize all nodes
				for (int i=0; i<this.numNodes; i++){
					Node node_i = new Node(i, this.numNodes);
					this.nodes.put(i, node_i);
				}
				line_num+=1;
			}
			if (line_num > 3){
				String[] edge = line.split("\t");
				int in_node = Integer.parseInt(edge[0]);
				int out_node = Integer.parseInt(edge[1]);
				// add edge from in_node to out_node
				this.nodes.get(in_node).add_adjacent_node(this.nodes.get(out_node));
				line_num +=1;
			}
			else{
				line_num +=1;
			}
			
		}
	}
}
