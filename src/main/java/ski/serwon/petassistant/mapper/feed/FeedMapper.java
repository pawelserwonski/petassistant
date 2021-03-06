package ski.serwon.petassistant.mapper.feed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ski.serwon.petassistant.dto.feed.FeedDTO;
import ski.serwon.petassistant.entity.feed.Feed;
import ski.serwon.petassistant.entity.feed.FodderUnit;
import ski.serwon.petassistant.service.animal.AnimalService;

@Component
public class FeedMapper {

    private AnimalService animalService;

    @Autowired
    public FeedMapper(AnimalService animalService) {
        this.animalService = animalService;
    }


    public FeedDTO mapModelToDTO(Feed model, FeedDTO dto) {
        dto.setId(model.getId());
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
