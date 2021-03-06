package ski.serwon.petassistant.service.animal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ski.serwon.petassistant.dao.animal.AnimalDao;
import ski.serwon.petassistant.entity.animal.Animal;
import ski.serwon.petassistant.entity.user.User;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class DefaultAnimalService implements AnimalService {
    private AnimalDao animalDao;

    @Autowired
    public DefaultAnimalService(AnimalDao animalDao) {
        this.animalDao = animalDao;
    }

    @Override
    @Transactional
    public Animal getAnimalById(Long id) {
        Animal animalToReturn;
        try {
            animalToReturn = this.animalDao.findById(id).get();
        } catch (NoSuchElementException e) {
            animalToReturn = null;
        }
        return animalToReturn;
    }

    @Override
    @Transactional
    public Animal addAnimal(Animal animalToAdd) {
        return this.animalDao.save(animalToAdd);
    }

    @Override
    @Transactional
    public void deleteAnimal(Long id) {
        this.animalDao.deleteById(id);
    }

    @Override
    @Transactional
    public Animal updateAnimal(Animal animalToUpdate) {
        return this.animalDao.save(animalToUpdate);
    }

    @Override
    @Transactional
    public List<Animal> getAnimalsByBirthdateDayOfYear(int dayOfYear) {
        return this.animalDao.findAll().stream().filter(animal -> animal.getBirthDate().getDayOfYear() == dayOfYear).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<Animal> getAnimalsByOwner(User owner) {
        return this.animalDao.findAllByOwner(owner);
    }


}
