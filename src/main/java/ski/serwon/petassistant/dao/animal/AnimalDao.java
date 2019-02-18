package ski.serwon.petassistant.dao.animal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ski.serwon.petassistant.entity.animal.Animal;
import ski.serwon.petassistant.entity.user.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalDao extends JpaRepository<Animal, Long> {
    List<Animal> findAllByIdIn(List<Long> id);
    Optional<Animal> findById(Long id);
    List<Animal> findAllByOwner(User owner);
    List<Animal> findAll();
}
