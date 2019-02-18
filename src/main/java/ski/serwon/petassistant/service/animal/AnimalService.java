package ski.serwon.petassistant.service.animal;

import ski.serwon.petassistant.model.animal.Animal;
import ski.serwon.petassistant.model.user.User;

import java.util.List;

public interface AnimalService {
    public Animal getAnimalById(Long id);
    public Animal addAnimal(Animal animalToAdd);
    public void deleteAnimal(Long id);
    public Animal updateAnimal(Animal animalToUpdate);
    public List<Animal> getAnimalsByBirthdateDayOfYear(int dayOfYear);
    public List<Animal> getAnimalsByOwner(User owner);
}
