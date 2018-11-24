package ski.serwon.petassistant.service.feed;

import ski.serwon.petassistant.model.animal.Animal;
import ski.serwon.petassistant.model.feed.Feed;

import java.util.List;

public interface FeedService {
    public List<Feed> getFeedsOfAnimal(Animal animal);
    public Feed getFeedById(Long id);
    public Feed addFeed(Feed feedToAdd);
}
