package co.com.genius.fixtures.etl.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguraton {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job(){
        return this.jobBuilderFactory.get("job").start(step1()).build();
    }
    @Bean
    public Step step1(){
        return this.stepBuilderFactory.get("step1").tasklet(((stepContribution, chunkContext) -> {
            System.out.println("Step1 ran");
            return RepeatStatus.FINISHED;
        })).build();
    }
}
