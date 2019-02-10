package ski.serwon.petassistant.service.vaccine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ski.serwon.petassistant.dao.vaccine.VaccineDao;
import ski.serwon.petassistant.model.animal.Animal;
import ski.serwon.petassistant.model.vaccine.Vaccine;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class VaccineServiceDefault implements VaccineService {

    private VaccineDao vaccineDao;

    @Autowired
    public VaccineServiceDefault(VaccineDao vaccineDao) {
        this.vaccineDao = vaccineDao;
    }

    @Override
    @Transactional
    public List<Vaccine> getVaccinesOfAnimals(List<Animal> animals) {
        return this.vaccineDao.findAllByVaccinatedAnimalIn(animals);
    }

    @Override
    @Transactional
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
    @Transactional
    public Vaccine addVaccine(Vaccine vaccine) {
        return this.vaccineDao.save(vaccine);
    }

    @Override
    @Transactional
    public void deleteVaccine(Long id) {
        this.vaccineDao.deleteById(id);
    }

    @Override
    @Transactional
    public Vaccine updateVaccine(Vaccine vaccineToUpdate) {
        return vaccineDao.save(vaccineToUpdate);
    }

    @Override
    @Transactional
    public List<Vaccine> getVaccineByDate(LocalDate date) {
        return vaccineDao.findAllByVisitDateBetween(date.atStartOfDay(), date.atTime(23, 59, 59));
    }

}