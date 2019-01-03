package ski.serwon.petassistant.service.vaccine;

import ski.serwon.petassistant.model.animal.Animal;
import ski.serwon.petassistant.model.vaccine.Vaccine;
import ski.serwon.petassistant.model.walk.Walk;

import java.util.List;

public interface VaccineService {
    public List<Vaccine> getVaccinesOfAnimal(Animal animal);
    public List<Vaccine> getVaccinesOfAnimals(List<Animal> animals);
    public Vaccine getVaccineById(Long id);
    public Vaccine addVaccine(Vaccine vaccine);
    public void deleteVaccine(Long id);
    public Vaccine updateVaccine(Vaccine vaccineToUpdate);
}
