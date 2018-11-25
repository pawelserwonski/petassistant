package ski.serwon.petassistant.service.vetvisit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ski.serwon.petassistant.dao.vetvisit.VetVisitDao;
import ski.serwon.petassistant.model.animal.Animal;
import ski.serwon.petassistant.model.vetvisit.VetVisit;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class VetVisitServiceImpl implements VetVisitService {

    private VetVisitDao vetVisitDao;

    @Autowired
    public VetVisitServiceImpl(VetVisitDao vetVisitDao) {
        this.vetVisitDao = vetVisitDao;
    }

    @Override
    public List<VetVisit> getVetVisitsOfAnimal(Animal animal) {
        throw new NotImplementedException();
    }

    @Override
    public List<VetVisit> getVetVisitsOfAnimals(List<Animal> animals) {
        return this.vetVisitDao.findAllByAnimalIn(animals);
    }

    @Override
    public VetVisit getVetVisitById(Long id) {
        VetVisit vetVisit;
        try {
            vetVisit = this.vetVisitDao.findById(id).get();
        } catch (NoSuchElementException e) {
            vetVisit = null;
        }
        return vetVisit;
    }

    @Override
    public VetVisit addVetVisit(VetVisit vetVisitToAdd) {
        return this.vetVisitDao.save(vetVisitToAdd);
    }
}