package ski.serwon.petassistant.service.vaccine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ski.serwon.petassistant.dao.vaccine.VaccineDao;
import ski.serwon.petassistant.model.animal.Animal;
import ski.serwon.petassistant.model.vaccine.Vaccine;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class VaccineServiceImpl implements VaccineService {

    private VaccineDao vaccineDao;

    @Autowired
    public VaccineServiceImpl(VaccineDao vaccineDao) {
        this.vaccineDao = vaccineDao;
    }

    @Override
    public List<Vaccine> getVaccinesOfAnimal(Animal animal) {
        throw new NotImplementedException();
    }

    @Override
    public List<Vaccine> getVaccinesOfAnimals(List<Animal> animals) {
        return this.vaccineDao.findAllByVaccinatedAnimalIn(animals);
    }

    @Override
    public Vaccine getVaccineById(Long id) {
        Vaccine vaccineToReturn;
        try {
            vaccineToReturn = this.vaccineDao.findById(id).get();
        } catch (NoSuchElementException e) {
            vaccineToReturn = null;
        }
        return vaccineToReturn;
    }

    @Override
    public Vaccine addVaccine(Vaccine vaccine) {
        return this.vaccineDao.save(vaccine);
    }
}
