package ski.serwon.petassistant.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ski.serwon.petassistant.model.animal.Animal;
import ski.serwon.petassistant.model.disease.Disease;
import ski.serwon.petassistant.model.feed.Feed;
import ski.serwon.petassistant.model.feed.FodderUnit;
import ski.serwon.petassistant.model.user.User;
import ski.serwon.petassistant.model.vaccine.Vaccine;
import ski.serwon.petassistant.model.vetvisit.VetVisit;
import ski.serwon.petassistant.model.walk.Walk;
import ski.serwon.petassistant.model.walk.WalkUnit;
import ski.serwon.petassistant.service.animal.AnimalService;
import ski.serwon.petassistant.service.disease.DiseaseService;
import ski.serwon.petassistant.service.feed.FeedService;
import ski.serwon.petassistant.service.user.UserService;
import ski.serwon.petassistant.service.vaccine.VaccineService;
import ski.serwon.petassistant.service.vetvisit.VetVisitService;
import ski.serwon.petassistant.service.walk.WalkService;

import javax.annotation.PostConstruct;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.LinkedList;

@Component
public class DBInitializer {
    private UserService userService;
    private AnimalService animalService;
    private DiseaseService diseaseService;
    private FeedService feedService;
    private VaccineService vaccineService;
    private VetVisitService vetVisitService;
    private WalkService walkService;

    @Autowired
    public DBInitializer(UserService userService, AnimalService animalService, DiseaseService diseaseService, FeedService feedService, VaccineService vaccineService, VetVisitService vetVisitService, WalkService walkService) {
        this.userService = userService;
        this.animalService = animalService;
        this.diseaseService = diseaseService;
        this.feedService = feedService;
        this.vaccineService = vaccineService;
        this.vetVisitService = vetVisitService;
        this.walkService = walkService;
    }

    @PostConstruct
    public void initializeDb() {
        User johnDoe = User.builder().firstName("John").lastName("Doe").email("john@doe.com").password("xD").build();
        this.userService.addUser(johnDoe);
        Animal lorem = Animal.builder().name("Lorem").birthDate(LocalDate.now()).owner(johnDoe).breed("Ipsum").species("Foo Bar").build();
        this.animalService.addAnimal(lorem);
        Disease flu = Disease.builder().sickAnimal(lorem).type("flu").description("bad flu ;__;").startDate(LocalDate.now().minusDays(2)).endDate(LocalDate.now()).build();
        diseaseService.addDisease(flu);
        Feed czapi = Feed.builder().fedAnimal(lorem).fodderType("chappi").time(LocalTime.NOON).portionSize(500.0).portionSizeUnit(FodderUnit.GRAM).build();
        feedService.addFeed(czapi);
        Vaccine vaccine = Vaccine.builder().location("vet").sicknessType("flu").vaccinatedAnimal(lorem).visitDate(LocalDateTime.now()).build();
        vaccineService.addVaccine(vaccine);
        VetVisit vetVisit = VetVisit.builder().animal(lorem).location("gliwice").reason("bo chory i nie je czapi ;__;").vetOpinion("chory").visitDate(LocalDateTime.now()).build();
        vetVisitService.addVetVisit(vetVisit);
        Walk walk = Walk.builder().startDate(LocalDate.now().minusMonths(1)).endDate(LocalDate.now().plusMonths(1)).startTime(LocalTime.of(17, 0)).walkedOutAnimal(lorem)
                .daysOfWeek(new LinkedList<DayOfWeek>(Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.SATURDAY))).walkLength(2.0).walkLengthUnit(WalkUnit.KILOMETER).build();
        walkService.addWalk(walk);
    }
}
