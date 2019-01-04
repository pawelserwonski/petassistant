package ski.serwon.petassistant.controller.walk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ski.serwon.petassistant.dto.walk.WalkDTO;
import ski.serwon.petassistant.mapper.walk.WalkMapper;
import ski.serwon.petassistant.model.walk.Walk;
import ski.serwon.petassistant.model.walk.WalkUnit;
import ski.serwon.petassistant.service.walk.WalkService;

import java.time.DayOfWeek;
import java.util.stream.Collectors;

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
        if (walkDTO.getStartDate().isAfter(walkDTO.getEndDate())) {
            return ResponseEntity.badRequest().build();
        }
        Walk walkToAdd = walkMapper.mapDTOtoModel(walkDTO, Walk.builder().build());
        return ResponseEntity.ok(walkService.addWalk(walkToAdd));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteWalk(@PathVariable("id") Long id) {
        walkService.deleteWalk(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<WalkDTO> getWalk(@PathVariable("id") Long id) {
        return ResponseEntity.ok(walkMapper.mapModelToDTO(walkService.getWalkById(id), WalkDTO.builder().build()));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Walk> updateWalk(@RequestBody WalkDTO walkDTO, @PathVariable("id") Long id) {
        Walk walkToUpdate = walkService.getWalkById(id);
        walkToUpdate.setDaysOfWeek(walkDTO.getDaysOfWeek().stream().map(DayOfWeek::valueOf).collect(Collectors.toList()));
        walkToUpdate.setEndDate(walkDTO.getEndDate());
        walkToUpdate.setStartDate(walkDTO.getStartDate());
        walkToUpdate.setStartTime(walkDTO.getStartTime());
        walkToUpdate.setWalkLength(walkDTO.getWalkLength());
        walkToUpdate.setWalkLengthUnit(WalkUnit.valueOf(walkDTO.getWalkLengthUnit()));
        return ResponseEntity.ok(walkService.updateWalk(walkToUpdate));
    }
}
