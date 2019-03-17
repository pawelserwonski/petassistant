package ski.serwon.petassistant.mockinitializer;

import org.mockito.Mockito;
import ski.serwon.petassistant.dao.animal.AnimalDao;
import ski.serwon.petassistant.entity.animal.Animal;
import ski.serwon.petassistant.entity.user.User;

import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

public class AnimalDaoMockInitializer {
    public static void setUpAnimalDaoMock(AnimalDao animalDaoMock, List<Animal> animalMockList) {
        setUpAnimalFindScenario(animalDaoMock, animalMockList);
        setUpAnimalSaveScenario(animalDaoMock, animalMockList);
        setUpAnimalDeleteScenario(animalDaoMock, animalMockList);
    }

    private static void setUpAnimalSaveScenario(AnimalDao animalDaoMock, List<Animal> animalMockList) {
        Mockito.when(animalDaoMock.save(any())).then(invocationOnMock -> {
            Animal added = invocationOnMock.getArgument(0);
            if (added.getId() == null) {
                added.setId(Long.valueOf(animalMockList.size()));
                animalMockList.add(added);
            } else {
                animalMockList.set(added.getId().intValue(), added);
            }
            return added;
        });
    }

    private static void setUpAnimalFindScenario(AnimalDao animalDaoMock, List<Animal> animalMockList) {
        Mockito.when(animalDaoMock.findAllByOwner(any())).thenAnswer(invocationOnMock -> {
            User owner = invocationOnMock.getArgument(0);
            return animalMockList.stream().filter(c -> c.getOwner() == owner).collect(Collectors.toList());
        });
    }

    private static void setUpAnimalDeleteScenario(AnimalDao animalDaoMock, List<Animal> animalMockList) {
        Mockito.doAnswer(invocationOnMock -> {
            Long id = invocationOnMock.getArgument(0);
            animalMockList.remove(id.intValue());
            return null;
        }).when(animalDaoMock).deleteById(anyLong());
    }

}
