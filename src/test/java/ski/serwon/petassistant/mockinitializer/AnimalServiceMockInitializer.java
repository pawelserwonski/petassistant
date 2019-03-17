package ski.serwon.petassistant.mockinitializer;

import org.mockito.Mockito;
import ski.serwon.petassistant.entity.animal.Animal;
import ski.serwon.petassistant.service.animal.AnimalService;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

public class AnimalServiceMockInitializer {
    public static void setUpAnimalServiceMock(AnimalService animalServiceMock, List<Animal> animalMockList) {
        setUpGetAnimalByIdScenario(animalServiceMock, animalMockList);
        setUpAddAnimalScenario(animalServiceMock, animalMockList);
        setUpUpdateAnimalScenario(animalServiceMock, animalMockList);
        setUpDeleteAnimalScenario(animalServiceMock, animalMockList);
    }

    private static void setUpGetAnimalByIdScenario(AnimalService animalServiceMock, List<Animal> animalMockList) {
        Mockito.when(animalServiceMock.getAnimalById(anyLong())).thenAnswer(invocationOnMock -> {
            Optional<Animal> animal = animalMockList
                    .stream().filter(c -> c.getId().equals(invocationOnMock.getArgument(0))).findFirst();
            return animal.isPresent() ? animal.get() : null;
        });
    }

    private static void setUpAddAnimalScenario(AnimalService animalServiceMock, List<Animal> animalMockList) {
        Mockito.when(animalServiceMock.addAnimal(any())).then(invocationOnMock -> {
            Animal added = invocationOnMock.getArgument(0);
            added.setId(Long.valueOf(animalMockList.size()));
            animalMockList.add(added);
            return added;
        });
    }

    private static void setUpUpdateAnimalScenario(AnimalService animalServiceMock, List<Animal> animalMockList) {
        Mockito.when(animalServiceMock.updateAnimal(any())).then(invocationOnMock -> {
            Animal edited = invocationOnMock.getArgument(0);
            Optional<Animal> animal = animalMockList
                    .stream().filter(c -> c.getId().equals(edited.getId())).findFirst();
            if (animal.isPresent()) {
                animalMockList.set(animalMockList.indexOf(animal.get()), edited);
                return edited;
            }
            return null;
        });
    }

    private static void setUpDeleteAnimalScenario(AnimalService animalServiceMock, List<Animal> animalMockList) {
        Mockito.doAnswer(invocationOnMock -> {
            Long id = invocationOnMock.getArgument(0);
            animalMockList.remove(id.intValue());
            return null;
        }).when(animalServiceMock).deleteAnimal(anyLong());
    }
}
