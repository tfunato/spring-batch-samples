package jp.canetrash.springbatch.samples.scaling.parallel;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

public class SampleTasklet implements Tasklet, InitializingBean {

	private Long sleep;
	private Integer counter;

	public void setCounter(Integer counter) {
		this.counter = counter;
	}

	public void setSleep(Long sleep) {
		this.sleep = sleep;
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		for (int i = 0; i < counter; i++) {
			System.out.println(chunkContext.getStepContext().getId());
			Thread.sleep(sleep);
		}
		return RepeatStatus.FINISHED;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(sleep);
		Assert.notNull(counter);
	}

}
