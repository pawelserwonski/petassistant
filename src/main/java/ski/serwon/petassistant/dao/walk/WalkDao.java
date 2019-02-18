package ski.serwon.petassistant.dao.walk;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ski.serwon.petassistant.entity.walk.Walk;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface WalkDao extends JpaRepository<Walk, Long> {
    Optional<Walk> findById(Long id);
    List<Walk> findAllByDaysOfWeekAndStartTimeBetween(DayOfWeek dayOfWeek, LocalTime start, LocalTime end);
}
