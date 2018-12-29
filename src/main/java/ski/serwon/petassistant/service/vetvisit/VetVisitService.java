package ski.serwon.petassistant.service.vetvisit;

import ski.serwon.petassistant.model.animal.Animal;
import ski.serwon.petassistant.model.vetvisit.VetVisit;

import java.util.List;

public interface VetVisitService {
    public List<VetVisit> getVetVisitsOfAnimal(Animal animal);
    public List<VetVisit> getVetVisitsOfAnimals(List<Animal> animals);
    public VetVisit getVetVisitById(Long id);
    public VetVisit addVetVisit(VetVisit vetVisitToAdd);
    public void deleteVisit(Long id);
    public VetVisit updateVetVisit(VetVisit vetVisit);
}
