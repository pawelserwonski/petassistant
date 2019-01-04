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
import ski.serwon.petassistant.service.vetvisit.VetVisitService;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
@Slf4j
public class VetVisitJob implements Job {

    @Autowired
    private EventReminderService reminderService;
    @Autowired
    private VetVisitService vetVisitService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("Executing VetVisitJob");
        vetVisitService.getVetVisitByDate(LocalDate.now().plusDays(7)).forEach(vetVisit -> reminderService.remindVetVisit(vetVisit));
    }



    @Bean(name = "vetVisitJobDetailTrigger")
    public SimpleTriggerFactoryBean vetVisitJobTrigger(@Qualifier("vetVisitJobDetail") JobDetail jobDetail) {
        return QuartzConfiguration.createTrigger(jobDetail, QuartzConfiguration.hoursToMilis(24L), QuartzConfiguration.localTimeToDate(LocalTime.NOON));
    }
}
