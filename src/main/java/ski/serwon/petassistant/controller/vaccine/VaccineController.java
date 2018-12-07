package ski.serwon.petassistant.controller.vaccine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ski.serwon.petassistant.dto.vaccine.VaccineDTO;
import ski.serwon.petassistant.mapper.vaccine.VaccineMapper;
import ski.serwon.petassistant.model.vaccine.Vaccine;
import ski.serwon.petassistant.service.vaccine.VaccineService;
import ski.serwon.petassistant.utils.AuthenticationUtil;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vaccine")
@CrossOrigin
public class VaccineController {
    private VaccineMapper vaccineMapper;
    private VaccineService vaccineService;
    private AuthenticationUtil authenticationUtil;

    @Autowired
    public VaccineController(VaccineMapper vaccineMapper, VaccineService vaccineService, AuthenticationUtil authenticationUtil) {
        this.vaccineMapper = vaccineMapper;
        this.vaccineService = vaccineService;
        this.authenticationUtil = authenticationUtil;
    }


    @PostMapping
    public ResponseEntity<Vaccine> createVaccine(@RequestBody VaccineDTO vaccineDTO) {
        try {
            Vaccine vaccineToAdd = vaccineMapper.mapDTOtoModel(vaccineDTO, Vaccine.builder().build());
            return ResponseEntity.ok(vaccineService.addVaccine(vaccineToAdd));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<VaccineDTO>> getAllVaccinesOfLoggedUser() {
        try {
            List<Vaccine> vaccinesToReturn = vaccineService.getVaccinesOfAnimals(authenticationUtil.getCurrentUser().getAnimals());
            if (vaccinesToReturn == null) {
                return ResponseEntity.notFound().build();
            }
            List<VaccineDTO> vaccineDTOsToReturn = vaccinesToReturn.stream().map(vaccine ->
                    vaccineMapper.mapModelToDTO(vaccine, VaccineDTO.builder().build()))
                    .sorted(Comparator.comparing(VaccineDTO::getVisitDate).reversed())
                    .collect(Collectors.toList());
            return ResponseEntity.ok(vaccineDTOsToReturn);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteVaccine(@PathVariable("id") Long id) {
        this.vaccineService.deleteVaccine(id);
        return ResponseEntity.ok().build();
    }
}
