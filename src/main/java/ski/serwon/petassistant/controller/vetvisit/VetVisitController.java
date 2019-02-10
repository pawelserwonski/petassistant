package ski.serwon.petassistant.controller.vetvisit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ski.serwon.petassistant.dto.vetvisit.VetVisitDTO;
import ski.serwon.petassistant.mapper.vetvisit.VetVisitMapper;
import ski.serwon.petassistant.model.vetvisit.VetVisit;
import ski.serwon.petassistant.security.LoginDetailsService;
import ski.serwon.petassistant.service.vetvisit.VetVisitService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vet")
@CrossOrigin
public class VetVisitController {
    private VetVisitMapper vetVisitMapper;
    private VetVisitService vetVisitService;
    private LoginDetailsService loginDetailsService;

    @Autowired
    public VetVisitController(VetVisitMapper vetVisitMapper, VetVisitService vetVisitService, LoginDetailsService loginDetailsService) {
        this.vetVisitMapper = vetVisitMapper;
        this.vetVisitService = vetVisitService;
        this.loginDetailsService = loginDetailsService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<VetVisitDTO> getVetVisit(@PathVariable Long id) {
        return ResponseEntity.ok(vetVisitMapper.mapModelToDTO(vetVisitService.getVetVisitById(id), VetVisitDTO.builder().build()));
    }

    @PostMapping
    public ResponseEntity<VetVisit> createVetVisit(@RequestBody VetVisitDTO vetVisitDTO) {
        VetVisit visitToAdd = vetVisitMapper.mapDTOtoModel(vetVisitDTO, VetVisit.builder().build());
        return ResponseEntity.ok(vetVisitService.addVetVisit(visitToAdd));
    }

    @GetMapping
    public ResponseEntity<List<VetVisitDTO>> getAllVetVisitsOfLoggedUser() {
        try {
            List<VetVisit> vetVisitsToReturn = vetVisitService.getVetVisitsOfAnimals(loginDetailsService.getCurrentUser().getAnimals());
            if (vetVisitsToReturn == null) {
                return ResponseEntity.notFound().build();
            }
            List<VetVisitDTO> vetVisitDTOsToReturn = vetVisitsToReturn.stream().map(vetVisit ->
                    vetVisitMapper.mapModelToDTO(vetVisit, VetVisitDTO.builder().build()))
                    .sorted(Comparator.comparing(VetVisitDTO::getVisitDate).reversed()).collect(Collectors.toList());
            return ResponseEntity.ok(vetVisitDTOsToReturn);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<VetVisit> updateVetVisit(@RequestBody VetVisitDTO vetVisitDTO, @PathVariable("id") Long id) {
        VetVisit visitToUpdate = this.vetVisitService.getVetVisitById(id);
        visitToUpdate.setLocation(vetVisitDTO.getLocation());
        visitToUpdate.setReason(vetVisitDTO.getReason());
        visitToUpdate.setVetOpinion(vetVisitDTO.getVetOpinion());
        visitToUpdate.setVisitDate(vetVisitDTO.getVisitDate());
        return ResponseEntity.ok(this.vetVisitService.updateVetVisit(visitToUpdate));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteVisit(@PathVariable("id") Long id) {
        this.vetVisitService.deleteVisit(id);
        return ResponseEntity.ok().build();
    }
}
