package cs.Lab2.SortBoroughPerAge;

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


public class SortDriver extends Configured implements Tool{
	public static void main(String[] args) throws Exception {
	      System.out.println(Arrays.toString(args));
	      int res = ToolRunner.run(new Configuration(), new SortDriver(), args);
	      System.exit(res);
	   }

	   @Override
	   public int run(String[] args) throws Exception {
	      Job job = new Job(getConf(), "SortBoroughperHeight");
	      job.setJarByClass(SortDriver.class);
	      job.setMapOutputKeyClass(IntWritable.class);
	      job.setMapOutputValueClass(Text.class);
	      job.setOutputKeyClass(Text.class);
	      job.setOutputValueClass(Text.class);

	      job.setMapperClass(SortMapper.class);
	      job.setReducerClass(SortReducer.class);

	      job.setInputFormatClass(TextInputFormat.class);
	      job.setOutputFormatClass(TextOutputFormat.class);

	      for (int i=0; i<args.length-1; i++){
	    	  FileInputFormat.addInputPath(job, new Path(args[i]));
	      }
	      FileOutputFormat.setOutputPath(job, new Path(args[args.length - 1]));

	      job.waitForCompletion(true);
	      
	      return 0;
	   }
}
