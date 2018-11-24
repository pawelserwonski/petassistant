package ski.serwon.petassistant.service.animal;

import ski.serwon.petassistant.model.animal.Animal;

import java.util.List;

public interface AnimalService {
    public Animal getAnimalById(Long id);
    public List<Animal> getAnimalsOfCurrentUser();
    public Animal addAnimal(Animal animalToAdd);
}
