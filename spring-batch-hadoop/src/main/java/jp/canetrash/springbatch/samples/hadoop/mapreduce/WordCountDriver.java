package jp.canetrash.springbatch.samples.hadoop.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class WordCountDriver extends Configured implements Tool {

	public static void main(String[] args) throws Exception {
		int ret = ToolRunner.run(new Configuration(), new WordCountDriver(), args);
		System.out.println(ret);
	}

	@Override
	public int run(String[] args) throws Exception {
		Configuration conf = getConf();
		conf.set("fs.defaultFS", "hdfs://cloudera-hadoop:8020");
		conf.set("mapred.job.tracker", "cloudera-hadoop:8021");

		Job job = new Job(conf);
		job.setJobName("WordCountMR");
		job.setJarByClass(WordCountDriver.class);
		job.setMapperClass(WordCountMapper.class);
		job.setReducerClass(WordCountReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		FileInputFormat.addInputPath(job, new Path("/user/cloudera/test.txt"));
		FileOutputFormat.setOutputPath(job, new Path("/user/cloudera/output"));

		boolean success = job.waitForCompletion(true);
		return (success ? 0 : 1);
	}
}
