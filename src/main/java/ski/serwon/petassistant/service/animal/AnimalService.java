package ski.serwon.petassistant.service.animal;

import ski.serwon.petassistant.model.animal.Animal;
import ski.serwon.petassistant.model.user.User;

import java.util.List;

public interface AnimalService {
    public Animal getAnimalById(Long id);
//    public List<Animal> getAnimalsOfUser(User user);
    public Animal addAnimal(Animal animalToAdd);
    public void deleteAnimal(Long id);
    public Animal updateAnimal(Animal animalToUpdate);
}
