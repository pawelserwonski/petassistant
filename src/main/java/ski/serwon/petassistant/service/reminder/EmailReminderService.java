package ski.serwon.petassistant.service.reminder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ski.serwon.petassistant.model.animal.Animal;
import ski.serwon.petassistant.model.feed.Feed;
import ski.serwon.petassistant.model.vaccine.Vaccine;
import ski.serwon.petassistant.model.vetvisit.VetVisit;
import ski.serwon.petassistant.model.walk.Walk;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Calendar;

@Service
@Slf4j
public class EmailReminderService implements ReminderService {

    private JavaMailSender javaMailSender;

    @Autowired
    public EmailReminderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void remindBirthday(Animal animal) {
        StringBuilder message = new StringBuilder();
        message.append(getMessageHeader(animal.getOwner().getFirstName()));
        message.append("We want to remind you that ");
        message.append(animal.getName());
        message.append(" birthday is today!\nHave you bought the gift already?");
        message.append(getMessageFooter());

        sendMail(animal.getOwner().getEmail(), "It's time to get the party started", message.toString());
    }

    @Override
    public void remindFeed(Feed feed) {
        StringBuilder message = new StringBuilder();
        message.append(getMessageHeader(feed.getFedAnimal().getOwner().getFirstName()));
        message.append(feed.getFedAnimal().getName());
        message.append(" feeding time is coming. It's planned for ");
        message.append(feed.getTime());
        message.append(getMessageFooter());

        sendMail(feed.getFedAnimal().getOwner().getEmail(), "Feeding time!", message.toString());
    }

    @Override
    public void remindWalk(Walk walk) {
        StringBuilder message = new StringBuilder();
        message.append(getMessageHeader(walk.getWalkedOutAnimal().getOwner().getFirstName()));
        message.append("Are you ready for a stroll?\n");
        message.append(walk.getWalkedOutAnimal());
        message.append(" walk is planned for ");
        message.append(walk.getStartTime());
        message.append(getMessageFooter());

        sendMail(walk.getWalkedOutAnimal().getOwner().getEmail(), "Time to grab your shoes", message.toString());
    }

    @Override
    public void remindVetVisit(VetVisit vetVisit) {
        StringBuilder message = new StringBuilder();
        message.append(getMessageHeader(vetVisit.getAnimal().getOwner().getFirstName()));
        message.append("We want to let you know that ");
        message.append(vetVisit.getAnimal().getName());
        message.append(" vet visit is coming.");
        message.append(getMessageFooter());

        sendMail(vetVisit.getAnimal().getOwner().getEmail(), "Vet visit is coming", message.toString());
    }

    @Override
    public void remindVaccine(Vaccine vaccine) {
        StringBuilder message = new StringBuilder();
        message.append(getMessageHeader(vaccine.getVaccinatedAnimal().getOwner().getFirstName()));
        message.append("We want to let you know that ");
        message.append(vaccine.getVaccinatedAnimal().getName());
        message.append(" ");
        message.append(vaccine.getSicknessType());
        message.append(" vaccine is coming.");
        message.append(getMessageFooter());

        sendMail(vaccine.getVaccinatedAnimal().getOwner().getEmail(), "Vaccine is coming", message.toString());
    }

    private boolean sendMail(String to, String subject, String text) {
        try {
            log.info("Sending email to " + to + " with subject " + subject);
            this.javaMailSender.send(createEmailMimeMessage(to, subject, text));
            return true;
        } catch (MessagingException | MailException e) {
            log.error("Email could not be send due to exception " + e);
            return false;
        }
    }

    private MimeMessage createEmailMimeMessage(String to, String subject, String text) throws MessagingException {
        MimeMessage email = this.javaMailSender.createMimeMessage();
        MimeMessageHelper emailHelper = new MimeMessageHelper(email);

        emailHelper.setTo(to);
        emailHelper.setSubject(subject);
        emailHelper.setText(text);
        emailHelper.setFrom("petassistantpracainz@gmail.com");
        emailHelper.setSentDate(Calendar.getInstance().getTime());
        return email;
    }

    private static String getMessageHeader(String name) {
        return "Hello " + name + "!" + System.lineSeparator() + System.lineSeparator();
    }

    private static String getMessageFooter() {
        return "\n\nBest Regards\nPetAssistant Crew";
    }
}
