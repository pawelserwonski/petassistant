package ski.serwon.petassistant.service.reminder;

import ski.serwon.petassistant.model.animal.Animal;
import ski.serwon.petassistant.model.feed.Feed;
import ski.serwon.petassistant.model.vaccine.Vaccine;
import ski.serwon.petassistant.model.vetvisit.VetVisit;
import ski.serwon.petassistant.model.walk.Walk;

public interface ReminderService {
    public void remindBirthday(Animal animal);
    public void remindFeed(Feed feed);
    public void remindWalk(Walk walk);
    public void remindVetVisit(VetVisit vetVisit);
    public void remindVaccine(Vaccine vaccine);
}
