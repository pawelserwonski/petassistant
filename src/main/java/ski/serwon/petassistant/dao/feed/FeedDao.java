package ski.serwon.petassistant.dao.feed;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ski.serwon.petassistant.model.feed.Feed;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface FeedDao extends JpaRepository<Feed, Long> {
    public Optional<Feed> findById(Long id);


}
