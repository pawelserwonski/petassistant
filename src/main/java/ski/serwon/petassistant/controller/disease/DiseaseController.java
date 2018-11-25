package ski.serwon.petassistant.controller.disease;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ski.serwon.petassistant.dto.disease.DiseaseDTO;
import ski.serwon.petassistant.mapper.disease.DiseaseMapper;
import ski.serwon.petassistant.model.disease.Disease;
import ski.serwon.petassistant.service.disease.DiseaseService;

@RestController
@RequestMapping("/disease")
@CrossOrigin
public class DiseaseController {
    private DiseaseService diseaseService;
    private DiseaseMapper diseaseMapper;

    @Autowired
    public DiseaseController(DiseaseService diseaseService, DiseaseMapper diseaseMapper) {
        this.diseaseService = diseaseService;
        this.diseaseMapper = diseaseMapper;
    }


    @PostMapping
    public ResponseEntity<Disease> createDisease(@RequestBody DiseaseDTO diseaseDTO) {
        try {
            Disease diseaseToAdd = diseaseMapper.mapDTOtoModel(diseaseDTO, Disease.builder().build());
            return ResponseEntity.ok(diseaseService.addDisease(diseaseToAdd));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
