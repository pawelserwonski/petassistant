package ski.serwon.petassistant.mapper.disease;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ski.serwon.petassistant.dto.animal.AnimalDTO;
import ski.serwon.petassistant.dto.disease.DiseaseDTO;
import ski.serwon.petassistant.mapper.animal.AnimalMapper;
import ski.serwon.petassistant.model.animal.Animal;
import ski.serwon.petassistant.model.disease.Disease;
import ski.serwon.petassistant.service.animal.AnimalService;

@Component
public class DiseaseMapper {

    private AnimalService animalService;

    @Autowired
    public DiseaseMapper(AnimalService animalService) {
        this.animalService = animalService;
    }

    public DiseaseDTO mapModelToDTO(Disease model, DiseaseDTO dto) {
        dto.setId(model.getId());
//        dto.setSickAnimal(animalMapper.mapModelToDTO(model.getSickAnimal(), AnimalDTO.builder().build()));
        dto.setStartDate(model.getStartDate());
        dto.setEndDate(model.getEndDate());
        dto.setType(model.getType());
        dto.setDescription(model.getDescription());

        return dto;
    }

    public Disease mapDTOtoModel(DiseaseDTO dto, Disease model) {
        model.setId(dto.getId());
        model.setSickAnimal(animalService.getAnimalById(dto.getSickAnimal().getId()));
        model.setStartDate(dto.getStartDate());
        model.setEndDate(dto.getEndDate());
        model.setType(dto.getType());
        model.setDescription(dto.getDescription());
        return model;
    }
}
