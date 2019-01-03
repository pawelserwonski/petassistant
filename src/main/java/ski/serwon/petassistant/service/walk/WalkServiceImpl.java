package ski.serwon.petassistant.service.walk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ski.serwon.petassistant.dao.walk.WalkDao;
import ski.serwon.petassistant.model.animal.Animal;
import ski.serwon.petassistant.model.walk.Walk;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class WalkServiceImpl implements WalkService {

    private WalkDao walkDao;

    @Autowired
    public WalkServiceImpl(WalkDao walkDao) {
        this.walkDao = walkDao;
    }

    @Override
    public List<Walk> getWalksOfAnimal(Animal animal) {
        throw new NotImplementedException();
    }

    @Override
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
    public Walk addWalk(Walk walk) {
        return this.walkDao.save(walk);
    }

    @Override
    public void deleteWalk(Long id) {
        walkDao.deleteById(id);
    }

    @Override
    public Walk updateWalk(Walk walkToUpdate) {
        return this.walkDao.save(walkToUpdate);
    }
}
