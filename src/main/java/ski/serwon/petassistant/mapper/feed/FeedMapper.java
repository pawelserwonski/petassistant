package ski.serwon.petassistant.mapper.feed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ski.serwon.petassistant.dto.animal.AnimalDTO;
import ski.serwon.petassistant.dto.feed.FeedDTO;
import ski.serwon.petassistant.mapper.animal.AnimalMapper;
import ski.serwon.petassistant.model.animal.Animal;
import ski.serwon.petassistant.model.feed.Feed;
import ski.serwon.petassistant.model.feed.FodderUnit;
import ski.serwon.petassistant.service.animal.AnimalService;

@Component
public class FeedMapper {

    private AnimalMapper animalMapper;
    private AnimalService animalService;

    @Autowired
    public FeedMapper(AnimalMapper animalMapper, AnimalService animalService) {
        this.animalMapper = animalMapper;
        this.animalService = animalService;
    }


    public FeedDTO mapModelToDTO(Feed model, FeedDTO dto) {
        dto.setId(model.getId());
//        dto.setFedAnimal(animalMapper.mapModelToDTO(model.getFedAnimal(), AnimalDTO.builder().build()));
        dto.setTime(model.getTime());
        dto.setFodderType(model.getFodderType());
        dto.setPortionSize(model.getPortionSize());
        dto.setPortionSizeUnit(model.getPortionSizeUnit().getName());
        return dto;
    }
    
    public Feed mapDTOtoModel(FeedDTO dto, Feed model) {
        model.setId(dto.getId());
        model.setFedAnimal(animalService.getAnimalById(dto.getFedAnimal().getId()));
        model.setTime(dto.getTime());
        model.setFodderType(dto.getFodderType());
        model.setPortionSize(dto.getPortionSize());
        model.setPortionSizeUnit(FodderUnit.valueOf(dto.getPortionSizeUnit()));
        return model;
    }
}
