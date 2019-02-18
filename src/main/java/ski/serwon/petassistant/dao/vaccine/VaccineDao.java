package ski.serwon.petassistant.dao.vaccine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ski.serwon.petassistant.entity.animal.Animal;
import ski.serwon.petassistant.entity.vaccine.Vaccine;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface VaccineDao extends JpaRepository<Vaccine, Long> {
    Optional<Vaccine> findById(Long id);
    List<Vaccine> findAllByVaccinatedAnimalIn(List<Animal> animals);
    List<Vaccine> findAllByVisitDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
