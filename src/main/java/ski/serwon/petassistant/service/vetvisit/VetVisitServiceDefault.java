package ski.serwon.petassistant.service.vetvisit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ski.serwon.petassistant.dao.vetvisit.VetVisitDao;
import ski.serwon.petassistant.entity.animal.Animal;
import ski.serwon.petassistant.entity.vetvisit.VetVisit;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class VetVisitServiceDefault implements VetVisitService {

    private VetVisitDao vetVisitDao;

    @Autowired
    public VetVisitServiceDefault(VetVisitDao vetVisitDao) {
        this.vetVisitDao = vetVisitDao;
    }

    @Override
    @Transactional
    public List<VetVisit> getVetVisitsOfAnimals(List<Animal> animals) {
        return this.vetVisitDao.findAllByAnimalIn(animals);
    }

    @Override
    @Transactional
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
    @Transactional
    public VetVisit addVetVisit(VetVisit vetVisitToAdd) {
        return this.vetVisitDao.save(vetVisitToAdd);
    }

    @Override
    @Transactional
    public void deleteVisit(Long id) {
        vetVisitDao.deleteById(id);
    }

    @Override
    @Transactional
    public VetVisit updateVetVisit(VetVisit vetVisit) {
        return this.vetVisitDao.save(vetVisit);
    }

    @Override
    @Transactional
    public List<VetVisit> getVetVisitByDate(LocalDate localDate) {
        return vetVisitDao.findAllByVisitDateBetween(localDate.atStartOfDay(), localDate.atTime(23,59,59));
    }
}
