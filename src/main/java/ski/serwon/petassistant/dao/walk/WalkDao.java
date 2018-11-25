package ski.serwon.petassistant.dao.walk;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ski.serwon.petassistant.model.walk.Walk;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface WalkDao extends JpaRepository<Walk, Long> {
    public Optional<Walk> findById(Long id);
}