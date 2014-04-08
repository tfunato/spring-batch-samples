package jp.canetrash.springbatch.samples.hadoop.mapreduce;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Before;
import org.junit.Test;

public class WordCountMapperTest {

	MapDriver<LongWritable, Text, Text, IntWritable> mapDriver;

	@Before
	public void setUp() throws IOException, InterruptedException {

		WordCountMapper mapper = new WordCountMapper();
		mapDriver = MapDriver.newMapDriver(mapper);
	}

	@Test
	public void testMapper() throws IOException {
		mapDriver.withInput(new LongWritable(), new Text("a b c a"));
		mapDriver.withOutput(new Text("a"), new IntWritable(1));
		mapDriver.withOutput(new Text("b"), new IntWritable(1));
		mapDriver.withOutput(new Text("c"), new IntWritable(1));
		mapDriver.withOutput(new Text("a"), new IntWritable(1));
		mapDriver.runTest();
	}

}
