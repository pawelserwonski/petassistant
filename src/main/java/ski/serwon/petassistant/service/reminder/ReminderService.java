package ski.serwon.petassistant.service.reminder;

import ski.serwon.petassistant.entity.animal.Animal;
import ski.serwon.petassistant.entity.feed.Feed;
import ski.serwon.petassistant.entity.vaccine.Vaccine;
import ski.serwon.petassistant.entity.vetvisit.VetVisit;
import ski.serwon.petassistant.entity.walk.Walk;

public interface ReminderService {
    public void remindBirthday(Animal animal);
    public void remindFeed(Feed feed);
    public void remindWalk(Walk walk);
    public void remindVetVisit(VetVisit vetVisit);
    public void remindVaccine(Vaccine vaccine);
}
