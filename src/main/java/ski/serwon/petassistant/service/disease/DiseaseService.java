package ski.serwon.petassistant.service.disease;

import ski.serwon.petassistant.model.animal.Animal;
import ski.serwon.petassistant.model.disease.Disease;

import java.util.List;

public interface DiseaseService {
    public List<Disease> getDiseasesOfAnimal(Animal animal);
    public Disease getDiseaseById(Long id);
    public Disease addDisease(Disease diseaseToAdd);
}
