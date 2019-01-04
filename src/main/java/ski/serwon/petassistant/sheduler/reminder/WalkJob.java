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
import ski.serwon.petassistant.service.reminder.EventReminderService;
import ski.serwon.petassistant.service.walk.WalkService;


import java.time.LocalDate;
import java.time.LocalTime;

@Component
@Slf4j
public class WalkJob implements Job {

    @Autowired
    private EventReminderService reminderService;
    @Autowired
    private WalkService walkService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("Executing WalkJob");
        walkService.getAllByDaysOfWeekAndStartTimeBetween(LocalDate.now().getDayOfWeek(), LocalTime.now(),
                LocalTime.now().plusHours(1)).forEach(walk -> reminderService.remindWalk(walk));
    }



    @Bean(name = "walkJobDetailTrigger")
    public SimpleTriggerFactoryBean walkJobTrigger(@Qualifier("walkJobDetail") JobDetail jobDetail) {
        return QuartzConfiguration.createTrigger(jobDetail, QuartzConfiguration.hoursToMilis(1L), QuartzConfiguration.localTimeToDate(LocalTime.NOON));
    }
}
