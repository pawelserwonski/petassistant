package ski.serwon.petassistant.service.feed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ski.serwon.petassistant.dao.feed.FeedDao;
import ski.serwon.petassistant.model.animal.Animal;
import ski.serwon.petassistant.model.feed.Feed;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DefaultFeedService implements FeedService {

    private FeedDao feedDao;

    @Autowired
    public DefaultFeedService(FeedDao feedDao) {
        this.feedDao = feedDao;
    }

    @Override
    public List<Feed> getFeedsOfAnimal(Animal animal) {
        throw new NotImplementedException();
    }

    @Override
    public Feed getFeedById(Long id) {
        Feed feedToReturn;
        try {
            feedToReturn = this.feedDao.findById(id).get();
        } catch (NoSuchElementException e) {
            feedToReturn = null;
        }
        return feedToReturn;
    }

    @Override
    public Feed addFeed(Feed feedToAdd) {
        return this.feedDao.save(feedToAdd);
    }

    @Override
    public void deleteFeed(Long id) {
        this.feedDao.deleteById(id);
    }

    @Override
    public Feed updateFeed(Feed feedToUpdate) {
        return this.feedDao.save(feedToUpdate);
    }

    @Override
    public List<Feed> getAllWithTimeBetween(LocalTime start, LocalTime end) {
        return feedDao.findAllByTimeIsBetween(start, end);
    }
}
