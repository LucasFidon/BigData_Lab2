package cs.Lab2.PageRank;

import java.util.HashSet;

public class Node {
	
	private int nodeId;
	private float pageRank = -1;
	//total number of nodes in the graph whose the node belongs to
	private int numNodes= -1; //used for PageRank
	// Set of nodes adjacent to the node
	private HashSet<Node> adjencyList;
	
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
	
    ///////////////////////////////////////////
	// Setters
	public void add_adjacent_node(Node adj_node){
		this.adjencyList.add(adj_node);
	}

}
