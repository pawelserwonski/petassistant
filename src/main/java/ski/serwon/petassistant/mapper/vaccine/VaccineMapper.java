package ski.serwon.petassistant.mapper.vaccine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ski.serwon.petassistant.dto.animal.AnimalDTO;
import ski.serwon.petassistant.dto.vaccine.VaccineDTO;
import ski.serwon.petassistant.mapper.animal.AnimalMapper;
import ski.serwon.petassistant.model.animal.Animal;
import ski.serwon.petassistant.model.vaccine.Vaccine;
import ski.serwon.petassistant.service.animal.AnimalService;

@Component
public class VaccineMapper {

    private AnimalMapper animalMapper;
    private AnimalService animalService;

    @Autowired
    public VaccineMapper(AnimalMapper animalMapper, AnimalService animalService) {
        this.animalMapper = animalMapper;
        this.animalService = animalService;
    }

    public VaccineDTO mapModelToDTO(Vaccine model, VaccineDTO dto) {
        dto.setId(model.getId());
        dto.setVaccinatedAnimal(animalMapper.mapModelToDTO(model.getVaccinatedAnimal(), AnimalDTO.builder().build()));
        dto.setVisitDate(model.getVisitDate());
        dto.setSicknessType(model.getSicknessType());
        dto.setLocation(model.getLocation());
        return dto;
    }

    public Vaccine mapDTOtoModel(VaccineDTO dto, Vaccine model) {
        dto.setId(model.getId());
        model.setVaccinatedAnimal(animalService.getAnimalById(dto.getVaccinatedAnimal().getId()));
        dto.setVisitDate(model.getVisitDate());
        dto.setSicknessType(model.getSicknessType());
        dto.setLocation(model.getLocation());

        return model;
    }
}
