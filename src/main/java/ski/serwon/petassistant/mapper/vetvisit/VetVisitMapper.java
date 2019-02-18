package ski.serwon.petassistant.mapper.vetvisit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ski.serwon.petassistant.dto.vetvisit.VetVisitDTO;
import ski.serwon.petassistant.entity.vetvisit.VetVisit;
import ski.serwon.petassistant.service.animal.AnimalService;

@Component
public class VetVisitMapper {
    

    private AnimalService animalService;
    
    @Autowired
    public VetVisitMapper(AnimalService animalService) {
        this.animalService = animalService;
    }
    
    public VetVisitDTO mapModelToDTO(VetVisit model, VetVisitDTO dto) {
        dto.setId(model.getId());
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
