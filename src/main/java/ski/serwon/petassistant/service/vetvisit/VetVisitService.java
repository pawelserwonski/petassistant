package ski.serwon.petassistant.service.vetvisit;

import ski.serwon.petassistant.entity.animal.Animal;
import ski.serwon.petassistant.entity.vetvisit.VetVisit;

import java.time.LocalDate;
import java.util.List;

public interface VetVisitService {
    public List<VetVisit> getVetVisitsOfAnimals(List<Animal> animals);
    public VetVisit getVetVisitById(Long id);
    public VetVisit addVetVisit(VetVisit vetVisitToAdd);
    public void deleteVisit(Long id);
    public VetVisit updateVetVisit(VetVisit vetVisit);
    public List<VetVisit> getVetVisitByDate(LocalDate localDate);
}
