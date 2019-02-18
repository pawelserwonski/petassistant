package ski.serwon.petassistant.service.disease;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ski.serwon.petassistant.dao.disease.DiseaseDao;
import ski.serwon.petassistant.entity.animal.Animal;
import ski.serwon.petassistant.entity.disease.Disease;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DefaultDiseaseService implements DiseaseService {

    private DiseaseDao diseaseDao;

    @Autowired
    public DefaultDiseaseService(DiseaseDao diseaseDao) {
        this.diseaseDao = diseaseDao;
    }

    @Override
    @Transactional
    public List<Disease> getDiseasesOfAnimal(Animal animal) {
        return this.diseaseDao.findAllBySickAnimal(animal);
    }

    @Override
    @Transactional
    public Disease getDiseaseById(Long id) {
        Disease toReturn;
        try {
            toReturn = this.diseaseDao.findById(id).get();
        } catch (NoSuchElementException e) {
            toReturn = null;
        }
        return toReturn;
    }

    @Override
    @Transactional
    public Disease addDisease(Disease diseaseToAdd) {
        return this.diseaseDao.save(diseaseToAdd);
    }
}
