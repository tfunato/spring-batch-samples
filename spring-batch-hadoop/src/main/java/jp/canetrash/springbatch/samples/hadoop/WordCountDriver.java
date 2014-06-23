package jp.canetrash.springbatch.samples.hadoop;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class WordCountDriver extends Configured implements Tool {
	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			System.out.println("need input and output path.");
			return;
		}

		Configuration conf = new Configuration();
		conf.set("mapreduce.framework.name", "local");
		conf.set("mapreduce.jobtracker.address", "local");
		int res = ToolRunner.run(conf, new WordCountDriver(), args);
		System.out.println(res);
	}

	@Override
	public int run(String[] args) throws Exception {
		Job job = Job.getInstance(getConf());

		job.setJarByClass(WordCountDriver.class);
		job.setJobName("Stub Driver");
		job.setMapperClass(MapperSample.class);
		job.setReducerClass(ReducerSample.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		boolean success = job.waitForCompletion(true);

		return (success ? 0 : 1);
	}
}

class MapperSample extends Mapper<LongWritable, Text, Text, IntWritable> {
	private final static IntWritable one = new IntWritable(1);
	private Text word = new Text();

	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

		StringTokenizer itr = new StringTokenizer(value.toString());
		while (itr.hasMoreTokens()) {
			String val = itr.nextToken();
			word.set(val);
			context.write(word, one);
		}
	}
}

class ReducerSample extends Reducer<Text, IntWritable, Text, IntWritable> {
	private IntWritable result = new IntWritable();

	@Override
	public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException,
			InterruptedException {
		int sum = 0;
		for (IntWritable val : values) {
			sum += val.get();
		}
		result.set(sum);
		context.write(key, result);
	}
}
