package ski.serwon.petassistant.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import ski.serwon.petassistant.sheduler.reminder.*;

@Configuration
public class JobsBeanConfiguration {
    @Bean(name = "vetVisitJobDetail")
    public JobDetailFactoryBean vetVisitJob() {
        return QuartzConfiguration.createJobDetail(VetVisitJob.class);
    }

    @Bean(name = "walkJobDetail")
    public JobDetailFactoryBean walkJob() {
        return QuartzConfiguration.createJobDetail(WalkJob.class);
    }

    @Bean(name = "birthdayJobDetail")
    public JobDetailFactoryBean birthdayJob() {
        return QuartzConfiguration.createJobDetail(BirthdayJob.class);
    }

    @Bean(name = "feedJobDetail")
    public JobDetailFactoryBean feedJob() {
        return QuartzConfiguration.createJobDetail(FeedJob.class);
    }

    @Bean(name = "vaccineJobDetail")
    public JobDetailFactoryBean vaccineJob() {
        return QuartzConfiguration.createJobDetail(VaccineJob.class);
    }
}
