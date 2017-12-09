package cs.Lab2.PageRank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.Text;

public class Graph {
	public int numNodes = 0;
	public HashMap<Integer,Node> nodes = new HashMap<Integer,Node>();

	// builder from original file
	public Graph(Text value){
		// read data
		int line_num = 0;
		System.out.println("Hello Graph");
		System.out.println(value.toString());
		//System.out.println(value.toString().split("\\r?\\n")[0]);
		//System.out.println(value.toString().split("\\r?\\n")[1]);
		for (String line: value.toString().split("\n")){
			//
			System.out.println(line_num);
			if (line_num == 2){
				System.out.println(line);
				// get number of nodes in the graph
				Pattern pattern = Pattern.compile("Nodes: (.*?) Edges");
				Matcher matcher = pattern.matcher(line);
				System.out.println(matcher.group());
				this.numNodes = Integer.parseInt(matcher.group());
				// initialize all nodes
				for (int i=0; i<this.numNodes; i++){
					Node node_i = new Node(i, this.numNodes);
					this.nodes.put(i, node_i);
				}
				line_num+=1;
			}
			//
			else{
				if (line_num > 3){
					String[] edge = line.split("\t");
					int in_node = Integer.parseInt(edge[0]);
					int out_node = Integer.parseInt(edge[1]);
					// add edge from in_node to out_node
					this.nodes.get(in_node).add_adjacent_node(out_node);
					line_num +=1;
				}
				else{
					line_num +=1;
				}
			}	
		}
	}
	
	/////////
//	public void save(String output){
//		
//		try {
//			PrintWriter writer;
//			writer = new PrintWriter(output, "UTF-8");
//			
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		} finally {
//            writer.close();
//        }
//		
//	}
	
	public static void main(String[] args) {
        BufferedWriter writer = null;
        try {
            //create a file
            File logFile = new File("graph_soc-Epinion1");

            // This will output the full path where the file will be written to...
            System.out.println(logFile.getCanonicalPath());

            writer = new BufferedWriter(new FileWriter(logFile));
            writer.write("Hello world!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Close the writer regardless of what happens...
                writer.close();
            } catch (Exception e) {
            }
        }
    }
}
