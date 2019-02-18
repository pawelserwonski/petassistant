package ski.serwon.petassistant.dao.vetvisit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ski.serwon.petassistant.entity.animal.Animal;
import ski.serwon.petassistant.entity.vetvisit.VetVisit;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface VetVisitDao extends JpaRepository<VetVisit, Long> {
    public Optional<VetVisit> findById(Long id);
    public List<VetVisit> findAllByAnimalIn(List<Animal> animals);
    public List<VetVisit> findAllByVisitDateBetween(LocalDateTime start, LocalDateTime end);
}
