package ski.serwon.petassistant.sheduler.reminder;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.stereotype.Component;
import ski.serwon.petassistant.configuration.QuartzConfiguration;
import ski.serwon.petassistant.service.reminder.ReminderService;
import ski.serwon.petassistant.service.vaccine.VaccineService;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
@Slf4j
public class VaccineJob implements Job {

    @Autowired
    private ReminderService reminderService;
    @Autowired
    private VaccineService vaccineService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("Executing VaccineJob");
        this.vaccineService.getVaccineByDate(LocalDate.now().plusDays(7)).forEach(vaccine
                -> reminderService.remindVaccine(vaccine));
    }



    @Bean(name = "vaccineJobDetailTrigger")
    public SimpleTriggerFactoryBean vaccineJobTrigger(@Qualifier("vaccineJobDetail")JobDetail jobDetail) {
        return QuartzConfiguration.createTrigger(jobDetail, QuartzConfiguration.hoursToMilis(24L), QuartzConfiguration.localTimeToDate(LocalTime.NOON));
    }
}
