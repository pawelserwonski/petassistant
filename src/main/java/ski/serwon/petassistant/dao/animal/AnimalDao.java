package ski.serwon.petassistant.dao.animal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ski.serwon.petassistant.model.animal.Animal;
import ski.serwon.petassistant.model.user.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalDao extends JpaRepository<Animal, Long> {
    List<Animal> findAllByIdIn(List<Long> id);
    Optional<Animal> findById(Long id);
    List<Animal> findAllByOwner(User owner);
    List<Animal> findAll();
}
