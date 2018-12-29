package ski.serwon.petassistant.service.animal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ski.serwon.petassistant.dao.animal.AnimalDao;
import ski.serwon.petassistant.model.animal.Animal;
import ski.serwon.petassistant.model.user.User;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AnimalServiceImpl implements AnimalService {
    private AnimalDao animalDao;

    @Autowired
    public AnimalServiceImpl(AnimalDao animalDao) {
        this.animalDao = animalDao;
    }

    @Override
    public Animal getAnimalById(Long id) {
        Animal animalToReturn;
        try {
            animalToReturn = this.animalDao.findById(id).get();
        } catch (NoSuchElementException e) {
            animalToReturn = null;
        }
        return animalToReturn;
    }

//    @Override
//    public List<Animal> getAnimalsOfUser(User user) {
//        return this.animalDao.findAllByIdIn();
//    }


    @Override
    public Animal addAnimal(Animal animalToAdd) {
        return this.animalDao.save(animalToAdd);
    }

    @Override
    public void deleteAnimal(Long id) {
        this.animalDao.deleteById(id);
    }

    @Override
    public Animal updateAnimal(Animal animalToUpdate) {
        return this.animalDao.save(animalToUpdate);
    }
}
