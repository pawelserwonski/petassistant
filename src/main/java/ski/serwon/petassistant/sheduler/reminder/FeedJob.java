package ski.serwon.petassistant.sheduler.reminder;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.stereotype.Component;
import ski.serwon.petassistant.configuration.QuartzConfiguration;
import ski.serwon.petassistant.service.feed.FeedService;
import ski.serwon.petassistant.service.reminder.ReminderService;


import java.time.LocalTime;

@Component
@Slf4j
public class FeedJob implements Job {

    @Autowired
    private ReminderService reminderService;
    @Autowired
    private FeedService feedService;


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("Executing FeedJob");
        feedService.getAllWithTimeBetween(LocalTime.now(), LocalTime.now().plusHours(1)).forEach(feed -> reminderService.remindFeed(feed));
    }



    @Bean(name = "feedJobDetailTrigger")
    public SimpleTriggerFactoryBean feedJobTrigger(@Qualifier("feedJobDetail") JobDetail jobDetail) {
        return QuartzConfiguration.createTrigger(jobDetail, QuartzConfiguration.hoursToMilis(1L), QuartzConfiguration.localTimeToDate(LocalTime.NOON));
    }
}
