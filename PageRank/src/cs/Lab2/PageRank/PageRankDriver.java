package cs.Lab2.PageRank;

import java.util.Arrays;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.Tool;
//import org.apache.hadoop.mapreduce.Mapper;
//import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.util.ToolRunner;

import cs.Lab2.InitGraph.InitGraphMapper;
import cs.Lab2.InitGraph.InitGraphReducer;

public class PageRankDriver extends Configured implements Tool{
	
	public static void main(String[] args) throws Exception {
	      System.out.println(Arrays.toString(args));
	      int res = 0;
	      int max_iter = 200;
	      for (int i = 0; i<max_iter; i++){
	    	  if (i > 0){
	    		  String[] args_temp = {"./" + args[1] + (i - 1) + "/part-r-00000", args[1] + i};
	    		  res = ToolRunner.run(new Configuration(), new PageRankDriver(), args_temp);
	    	  }
	    	  else{
	    		  String[] args_temp = {args[0], args[1] + i};
	    		  res = ToolRunner.run(new Configuration(), new PageRankDriver(), args_temp);
	    	  }
	      }
	   }

	   @Override
	   public int run(String[] args) throws Exception {
	      // Apply PageRang algo
	      Job job = new Job(getConf(), "PageRank");
	      job.setJarByClass(PageRankDriver.class);
	      job.setMapOutputKeyClass(IntWritable.class);
	      job.setMapOutputValueClass(Text.class);
	      
	      job.setOutputKeyClass(IntWritable.class);
	      job.setOutputValueClass(Text.class);

	      job.setMapperClass(PageRankMapper.class);
	      job.setReducerClass(PageRankReducer.class);

	      job.setInputFormatClass(TextInputFormat.class);
	      job.setOutputFormatClass(TextOutputFormat.class);
	      
	      FileInputFormat.addInputPath(job, new Path(args[0]));
	      FileOutputFormat.setOutputPath(job, new Path(args[1]));

	      job.waitForCompletion(true);
	      
	      return 0;
	   }
}
