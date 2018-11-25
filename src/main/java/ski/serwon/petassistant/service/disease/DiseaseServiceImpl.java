package ski.serwon.petassistant.service.disease;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ski.serwon.petassistant.dao.disease.DiseaseDao;
import ski.serwon.petassistant.model.animal.Animal;
import ski.serwon.petassistant.model.disease.Disease;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DiseaseServiceImpl implements DiseaseService {

    private DiseaseDao diseaseDao;

    @Autowired
    public DiseaseServiceImpl(DiseaseDao diseaseDao) {
        this.diseaseDao = diseaseDao;
    }

    @Override
    public List<Disease> getDiseasesOfAnimal(Animal animal) {
        return this.diseaseDao.findAllBySickAnimal(animal);
    }

    @Override
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
    public Disease addDisease(Disease diseaseToAdd) {
        return this.diseaseDao.save(diseaseToAdd);
    }
}
