package ski.serwon.petassistant.mockinitializer;

import org.mockito.Mockito;
import ski.serwon.petassistant.entity.animal.Animal;
import ski.serwon.petassistant.service.animal.AnimalService;

import java.util.List;

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
        Mockito.when(animalServiceMock.getAnimalById(anyLong())).thenAnswer(invocationOnMock ->
                animalMockList.get(invocationOnMock.getArgument(0)));
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
            animalMockList.set(edited.getId().intValue(), edited);
            return edited;
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
