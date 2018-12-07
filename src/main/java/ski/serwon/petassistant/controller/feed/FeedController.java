package ski.serwon.petassistant.controller.feed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ski.serwon.petassistant.dto.feed.FeedDTO;
import ski.serwon.petassistant.mapper.feed.FeedMapper;
import ski.serwon.petassistant.model.feed.Feed;
import ski.serwon.petassistant.service.feed.FeedService;

@RestController
@RequestMapping("/feed")
@CrossOrigin
public class FeedController {
    private FeedMapper feedMapper;
    private FeedService feedService;

    @Autowired
    public FeedController(FeedMapper feedMapper, FeedService feedService) {
        this.feedMapper = feedMapper;
        this.feedService = feedService;
    }

    @PostMapping
    public ResponseEntity<Feed> createFeed(@RequestBody FeedDTO dto) {
        try {
            Feed feedToAdd = feedMapper.mapDTOtoModel(dto, Feed.builder().build());
            return ResponseEntity.ok(feedService.addFeed(feedToAdd));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteFeed(@PathVariable("id") Long id) {
        this.feedService.deleteFeed(id);
        return ResponseEntity.ok().build();
    }
}
