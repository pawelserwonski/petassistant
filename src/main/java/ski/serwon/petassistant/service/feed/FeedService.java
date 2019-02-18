package ski.serwon.petassistant.service.feed;

import ski.serwon.petassistant.entity.feed.Feed;

import java.time.LocalTime;
import java.util.List;

public interface FeedService {
    public Feed getFeedById(Long id);
    public Feed addFeed(Feed feedToAdd);
    public void deleteFeed(Long id);
    public Feed updateFeed(Feed feedToUpdate);
    public List<Feed> getAllWithTimeBetween(LocalTime start, LocalTime end);
}
