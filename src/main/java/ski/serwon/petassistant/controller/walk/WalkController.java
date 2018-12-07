package ski.serwon.petassistant.controller.walk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ski.serwon.petassistant.dto.walk.WalkDTO;
import ski.serwon.petassistant.mapper.walk.WalkMapper;
import ski.serwon.petassistant.model.walk.Walk;
import ski.serwon.petassistant.service.walk.WalkService;

@RestController
@RequestMapping("/walk")
@CrossOrigin
public class WalkController {
    private WalkMapper walkMapper;
    private WalkService walkService;

    @Autowired
    public WalkController(WalkMapper walkMapper, WalkService walkService) {
        this.walkMapper = walkMapper;
        this.walkService = walkService;
    }

    @PostMapping
    public ResponseEntity<Walk> createWalk(@RequestBody WalkDTO walkDTO) {
        Walk walkToAdd = walkMapper.mapDTOtoModel(walkDTO, Walk.builder().build());
        return ResponseEntity.ok(walkService.addWalk(walkToAdd));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteWalk(@PathVariable("id") Long id) {
        walkService.deleteWalk(id);
        return ResponseEntity.ok().build();
    }
}
