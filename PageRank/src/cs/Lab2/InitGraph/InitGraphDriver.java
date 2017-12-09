package cs.Lab2.InitGraph;

import java.util.Arrays;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import cs.Lab2.PageRank.PageRankDriver;

public class InitGraphDriver extends Configured implements Tool{
		public static void main(String[] args) throws Exception {
		      System.out.println(Arrays.toString(args));
		      int res = ToolRunner.run(new Configuration(), new InitGraphDriver(), args);
		      System.exit(res);
		   }

		   @Override
		   public int run(String[] args) throws Exception {
			   //////////////////////////////////////
			   // Initialization of the Graph from the initial file containing the edges of the graph
			   // In the end of the initialization we get one line per node with the format:
			   // nid	[nid:(*), p:(*), adjacentNodes:(*)]
		      Job job_init = new Job(getConf(), "InitGraph");
		      job_init.setJarByClass(PageRankDriver.class);
		      job_init.setMapOutputKeyClass(IntWritable.class);
		      job_init.setMapOutputValueClass(IntWritable.class);
		      
		      job_init.setOutputKeyClass(IntWritable.class);
		      job_init.setOutputValueClass(Text.class);

		      job_init.setMapperClass(InitGraphMapper.class);
		      job_init.setReducerClass(InitGraphReducer.class);

		      job_init.setInputFormatClass(TextInputFormat.class);
		      job_init.setOutputFormatClass(TextOutputFormat.class);
		      
		      FileInputFormat.addInputPath(job_init, new Path(args[0]));
		      FileOutputFormat.setOutputPath(job_init, new Path(args[1]));

		      job_init.waitForCompletion(true);
		      
		      return 0;
		   }

}
