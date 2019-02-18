package ski.serwon.petassistant.service.reminder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ski.serwon.petassistant.entity.animal.Animal;
import ski.serwon.petassistant.entity.feed.Feed;
import ski.serwon.petassistant.entity.vaccine.Vaccine;
import ski.serwon.petassistant.entity.vetvisit.VetVisit;
import ski.serwon.petassistant.entity.walk.Walk;

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
        String message = createMessage(animal.getOwner().getFirstName(),
                "We want to remind you that " + animal.getName() + " birthday is today!",
                "Have you bought the gift already?");
        sendMail(animal.getOwner().getEmail(), "It's time to get the party started", message);
    }

    @Override
    public void remindFeed(Feed feed) {
        String message = createMessage(feed.getFedAnimal().getOwner().getFirstName(),
                feed.getFedAnimal().getName() + " feeding time is coming. It's planned for " + feed.getTime());
        sendMail(feed.getFedAnimal().getOwner().getEmail(), "Feeding time!", message);
    }

    @Override
    public void remindWalk(Walk walk) {
        String message = createMessage(walk.getWalkedOutAnimal().getOwner().getFirstName(),
                "Are you ready for a stroll?",
                walk.getWalkedOutAnimal() + " walk is planned for " + walk.getStartTime());
        sendMail(walk.getWalkedOutAnimal().getOwner().getEmail(), "Time to grab your shoes", message);
    }

    @Override
    public void remindVetVisit(VetVisit vetVisit) {
        String message = createMessage(vetVisit.getAnimal().getOwner().getFirstName(),
                "We want to let you know that " + vetVisit.getAnimal().getName() + " vet visit is coming.");
        sendMail(vetVisit.getAnimal().getOwner().getEmail(), "Vet visit is coming", message);
    }

    @Override
    public void remindVaccine(Vaccine vaccine) {
        String message = createMessage(vaccine.getVaccinatedAnimal().getOwner().getFirstName(),
                "We want to let you know that " + vaccine.getVaccinatedAnimal().getName() + " " + vaccine.getSicknessType() + " vaccine is coming.");
        sendMail(vaccine.getVaccinatedAnimal().getOwner().getEmail(), "Vaccine is coming", message);
    }


    private static String createMessage(String firstName, String... contentRecords) {
        return getMessageHeader(firstName) + getMessageContent(contentRecords) + getMessageFooter();
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

    private static String getMessageHeader(String name) {
        return "Hello " + name + "!" + System.lineSeparator();
    }

    private static String getMessageContent(String... contentRecords) {
        StringBuilder messageContent = new StringBuilder();
        for(String record : contentRecords) {
            messageContent.append(record);
            messageContent.append(System.lineSeparator());
        }
        return messageContent.toString();
    }

    private static String getMessageFooter() {
        return System.lineSeparator() + "Best Regards" + System.lineSeparator() + "PetAssistant Crew";
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
}
