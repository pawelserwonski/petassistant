package ski.serwon.petassistant.dao.disease;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ski.serwon.petassistant.model.animal.Animal;
import ski.serwon.petassistant.model.disease.Disease;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface DiseaseDao extends JpaRepository<Disease, Long> {
    public Optional<Disease> findById(Long id);

    public List<Disease> findAllByIdIn(List<Long> ids);

    public List<Disease> findAll();

    public List<Disease> findAllBySickAnimal(Animal animal);
}
