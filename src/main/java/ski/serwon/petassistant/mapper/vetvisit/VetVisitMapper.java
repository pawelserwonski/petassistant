package ski.serwon.petassistant.mapper.vetvisit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ski.serwon.petassistant.dto.animal.AnimalDTO;
import ski.serwon.petassistant.dto.vetvisit.VetVisitDTO;
import ski.serwon.petassistant.mapper.animal.AnimalMapper;
import ski.serwon.petassistant.model.vetvisit.VetVisit;
import ski.serwon.petassistant.service.animal.AnimalService;

@Component
public class VetVisitMapper {
    
    private AnimalMapper animalMapper;
    private AnimalService animalService;
    
    @Autowired
    public VetVisitMapper(AnimalMapper animalMapper, AnimalService animalService) {
        this.animalMapper = animalMapper;
        this.animalService = animalService;
    }
    
    public VetVisitDTO mapModelToDTO(VetVisit model, VetVisitDTO dto) {
        dto.setId(model.getId());
//        dto.setAnimal(animalMapper.mapModelToDTO(model.getAnimal(), AnimalDTO.builder().build()));
        dto.setVisitDate(model.getVisitDate());
        dto.setReason(model.getReason());
        dto.setLocation(model.getLocation());
        dto.setVetOpinion(model.getVetOpinion());
        return dto;
    }
    
    public VetVisit mapDTOtoModel(VetVisitDTO dto, VetVisit model) {
        model.setId(dto.getId());
        model.setAnimal(animalService.getAnimalById(dto.getAnimal().getId()));
        model.setVisitDate(dto.getVisitDate());
        model.setReason(dto.getReason());
        model.setLocation(dto.getLocation());
        model.setVetOpinion(dto.getVetOpinion());
        return model;
    }
}