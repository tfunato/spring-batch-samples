package jp.canetrash.springbatch.samples.scaling.parallel;

import org.junit.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ParallelStepTest {

	@Test
	public void test() {
		String[] springConfig = { "classpath:parallel-context.xml" };

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				springConfig);
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job) context.getBean("parallelJob");

		try {
			JobParameters params = new JobParameters();
			JobExecution execution = jobLauncher.run(job, params);
			System.out.println("Exit Status : " + execution.getStatus());
			System.out.println("End Time : " + execution.getEndTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		context.close();
	}

}
