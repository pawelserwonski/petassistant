package ski.serwon.petassistant.mapper.walk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ski.serwon.petassistant.dto.animal.AnimalDTO;
import ski.serwon.petassistant.dto.walk.WalkDTO;
import ski.serwon.petassistant.mapper.animal.AnimalMapper;
import ski.serwon.petassistant.model.walk.Walk;
import ski.serwon.petassistant.model.walk.WalkUnit;
import ski.serwon.petassistant.service.animal.AnimalService;

import java.time.DayOfWeek;
import java.util.stream.Collectors;

@Component
public class WalkMapper {

    private AnimalService animalService;

    @Autowired
    public WalkMapper(AnimalService animalService) {
        this.animalService = animalService;
    }

    public WalkDTO mapModelToDTO(Walk model, WalkDTO dto) {
        dto.setId(model.getId());
//        dto.setWalkedOutAnimal(animalMapper.mapModelToDTO(model.getWalkedOutAnimal(), AnimalDTO.builder().build()));
        dto.setStartDate(model.getStartDate());
        dto.setEndDate(model.getEndDate());
        dto.setStartTime(model.getStartTime());
        dto.setDaysOfWeek(model.getDaysOfWeek().stream().map(DayOfWeek::toString).collect(Collectors.toList()));
        dto.setWalkLength(model.getWalkLength());
        dto.setWalkLengthUnit(model.getWalkLengthUnit().getName());
        return dto;
    }


    public Walk mapDTOtoModel(WalkDTO dto, Walk model) {
        model.setId(dto.getId());
        model.setWalkedOutAnimal(animalService.getAnimalById(dto.getWalkedOutAnimal().getId()));
        model.setStartDate(dto.getStartDate());
        model.setEndDate(dto.getEndDate());
        model.setStartTime(dto.getStartTime());
        model.setDaysOfWeek(dto.getDaysOfWeek().stream().map(DayOfWeek::valueOf).collect(Collectors.toList()));
        model.setWalkLength(dto.getWalkLength());
        model.setWalkLengthUnit(WalkUnit.valueOf(dto.getWalkLengthUnit()));
        return model;
    }
}
