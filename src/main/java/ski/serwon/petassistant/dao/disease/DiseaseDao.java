package ski.serwon.petassistant.dao.disease;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ski.serwon.petassistant.entity.animal.Animal;
import ski.serwon.petassistant.entity.disease.Disease;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiseaseDao extends JpaRepository<Disease, Long> {
    Optional<Disease> findById(Long id);
    List<Disease> findAllByIdIn(List<Long> ids);
    List<Disease> findAll();
    List<Disease> findAllBySickAnimal(Animal animal);
}
