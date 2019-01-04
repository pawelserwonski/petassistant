package ski.serwon.petassistant.mapper.vaccine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ski.serwon.petassistant.dto.vaccine.VaccineDTO;
import ski.serwon.petassistant.mapper.animal.AnimalMapper;
import ski.serwon.petassistant.model.vaccine.Vaccine;
import ski.serwon.petassistant.service.animal.AnimalService;

@Component
public class VaccineMapper {

    private AnimalService animalService;

    @Autowired
    public VaccineMapper(AnimalService animalService) {
        this.animalService = animalService;
    }

    public VaccineDTO mapModelToDTO(Vaccine model, VaccineDTO dto) {
        dto.setId(model.getId());
        dto.setVisitDate(model.getVisitDate());
        dto.setSicknessType(model.getSicknessType());
        dto.setLocation(model.getLocation());
        return dto;
    }

    public Vaccine mapDTOtoModel(VaccineDTO dto, Vaccine model) {
        model.setId(dto.getId());
        model.setVaccinatedAnimal(animalService.getAnimalById(dto.getVaccinatedAnimal().getId()));
        model.setVisitDate(dto.getVisitDate());
        model.setSicknessType(dto.getSicknessType());
        model.setLocation(dto.getLocation());

        return model;
    }
}
