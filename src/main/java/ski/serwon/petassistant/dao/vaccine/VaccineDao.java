package ski.serwon.petassistant.dao.vaccine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ski.serwon.petassistant.model.animal.Animal;
import ski.serwon.petassistant.model.vaccine.Vaccine;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface VaccineDao extends JpaRepository<Vaccine, Long> {
    public Optional<Vaccine> findById(Long id);
    public List<Vaccine> findAllByVaccinatedAnimalIn(List<Animal> animals);
}
