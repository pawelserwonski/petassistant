package ski.serwon.petassistant.service.walk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ski.serwon.petassistant.dao.walk.WalkDao;
import ski.serwon.petassistant.model.animal.Animal;
import ski.serwon.petassistant.model.walk.Walk;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DefaultWalkService implements WalkService {

    private WalkDao walkDao;

    @Autowired
    public DefaultWalkService(WalkDao walkDao) {
        this.walkDao = walkDao;
    }

    @Override
    @Transactional
    public List<Walk> getWalksOfAnimal(Animal animal) {
        throw new NotImplementedException();
    }

    @Override
    @Transactional
    public Walk getWalkById(Long id) {
        Walk walkToReturn;
        try {
            walkToReturn = this.walkDao.findById(id).get();
        } catch (NoSuchElementException e) {
            walkToReturn = null;
        }
        return walkToReturn;
    }

    @Override
    @Transactional
    public Walk addWalk(Walk walk) {
        return this.walkDao.save(walk);
    }

    @Override
    @Transactional
    public void deleteWalk(Long id) {
        walkDao.deleteById(id);
    }

    @Override
    @Transactional
    public Walk updateWalk(Walk walkToUpdate) {
        return this.walkDao.save(walkToUpdate);
    }

    @Override
    @Transactional
    public List<Walk> getAllByDaysOfWeekAndStartTimeBetween(DayOfWeek dayOfWeek, LocalTime start, LocalTime end) {
        return walkDao.findAllByDaysOfWeekAndStartTimeBetween(dayOfWeek, start, end);
    }
}
