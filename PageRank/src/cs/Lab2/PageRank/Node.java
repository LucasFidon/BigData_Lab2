package cs.Lab2.PageRank;

import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.IntWritable;

public class Node {
	
	public int nodeId;
	public float pageRank = 1;
	//total number of nodes in the graph whose the node belongs to
	private int numNodes= -1; //used for PageRank
	// Set of ids of nodes adjacent to the node
	public HashSet<Integer> adjencyList = new HashSet<Integer>();
	
	///////////////////////////////////////////
	// Constructors
	public Node(int nodeId) {
		this.nodeId = nodeId;
	}
	
	public Node(int nodeId, float pageRank) {
		this.nodeId = nodeId;
		this.pageRank = pageRank;
	}
	
	public Node(int nodeId, int numNodes) {
		this.nodeId = nodeId;
		// random surfer init PageRank
		this.pageRank = 1/numNodes;
		this.numNodes = numNodes;
	}
	
	public Node(String node_str){
		// get node from string
		//Pattern pattern = Pattern.compile("[nid:(*), p:(*), adjacentNodes:(*)]");
		//Matcher matcher = pattern.matcher(node_str);
		//boolean b =matcher.matches();
		//System.out.println(b);
		String node_info = node_str;
		// preprocessing
		node_info = node_info.replace("[", "");
		node_info = node_info.replace("]", "");
		node_info = node_info.replace(":", "");
		node_info = node_info.replace("nid", "");
		node_info = node_info.replace("p", "");
		node_info = node_info.replace("adjacentNodes", "");
		String[] info = node_info.split(",");
		this.nodeId = Integer.parseInt(info[0]);
		this.pageRank = Float.parseFloat(info[1]);
		if (info.length > 2){ // the adjacent nodes set can be empty (ex: sink node)
			String[] adj = (info[2]).split(" ");
			for (String node_id: adj){
				this.adjencyList.add(Integer.parseInt(node_id));
		    }
		}
	}
	
    ///////////////////////////////////////////
	// Setters
	public void add_adjacent_node(int adj_node){
		this.adjencyList.add(adj_node);
	}
	
	public void add_adjacent_node(IntWritable adj_node) {
		add_adjacent_node(adj_node.get());
	}
	
	///////////////////////////////////////////
	// Serializer
	@Override
	public String toString(){
		String node_str = "[nid:" + this.nodeId + ",p:" + this.pageRank + ",adjacentNodes:";
		for (int nid: this.adjencyList){
			node_str += nid + " ";
		}
		node_str = node_str.substring(0, node_str.length() - 1);
		node_str += "]";
		return node_str;
	}


	///////////////////////////////////////////
	// Test
	public static void main(String[] args){
		Node node = new Node(12);
		node.add_adjacent_node(1);
		node.add_adjacent_node(27);
		System.out.println(node.toString());
		Node node2 = new Node(node.toString());
		System.out.println(node2.toString());
	}
	

}
