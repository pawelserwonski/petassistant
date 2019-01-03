package ski.serwon.petassistant.controller.feed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ski.serwon.petassistant.dto.feed.FeedDTO;
import ski.serwon.petassistant.mapper.feed.FeedMapper;
import ski.serwon.petassistant.model.feed.Feed;
import ski.serwon.petassistant.model.feed.FodderUnit;
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

    @GetMapping(path = "/{id}")
    public ResponseEntity<FeedDTO> getFeed(@PathVariable("id") Long id) {
        return ResponseEntity.ok(feedMapper.mapModelToDTO(feedService.getFeedById(id), FeedDTO.builder().build()));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Feed> updateFeed(@RequestBody FeedDTO feedDTO, @PathVariable("id") Long id) {
        Feed feedToUpdate = feedService.getFeedById(id);
        feedToUpdate.setFodderType(feedDTO.getFodderType());
        feedToUpdate.setPortionSize(feedDTO.getPortionSize());
        feedToUpdate.setTime(feedDTO.getTime());
        feedToUpdate.setPortionSizeUnit(FodderUnit.valueOf(feedDTO.getPortionSizeUnit()));
        return ResponseEntity.ok(feedService.updateFeed(feedToUpdate));
    }
}
