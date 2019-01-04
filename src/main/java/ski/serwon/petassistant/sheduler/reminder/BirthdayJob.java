package ski.serwon.petassistant.sheduler.reminder;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.stereotype.Component;
import ski.serwon.petassistant.configuration.QuartzConfiguration;
import ski.serwon.petassistant.service.animal.AnimalService;
import ski.serwon.petassistant.service.reminder.EventReminderService;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
@Slf4j
public class BirthdayJob implements Job {

    @Autowired
    private AnimalService animalService;
    @Autowired
    private EventReminderService reminderService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("Executing BirthdayJob");
        animalService.getAnimalsByBirthdateDayOfYear(LocalDate.now().getDayOfYear())
                .forEach(animal -> reminderService.remindBirthday(animal));
    }



    @Bean(name = "birthdayJobDetailTrigger")
    public SimpleTriggerFactoryBean birthdayJobTrigger(@Qualifier("birthdayJobDetail") JobDetail jobDetail) {
        return QuartzConfiguration.createTrigger(jobDetail, QuartzConfiguration.hoursToMilis(24L), QuartzConfiguration.localTimeToDate(LocalTime.NOON));
    }
}
