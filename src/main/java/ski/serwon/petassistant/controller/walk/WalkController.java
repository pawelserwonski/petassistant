package ski.serwon.petassistant.controller.walk;

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

    @PostMapping
    public ResponseEntity<Walk> createWalk(@RequestBody WalkDTO walkDTO) {
        Walk walkToAdd = walkMapper.mapDTOtoModel(walkDTO, Walk.builder().build());
        return ResponseEntity.ok(walkService.addWalk(walkToAdd));
    }
}
