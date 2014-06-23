package jp.canetrash.springbatch.samples.hadoop;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class WordCountDriverSpringBatchTest {

	@Test
	public void test() throws Exception {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		context.registerShutdownHook();
		context.close();
	}

}
