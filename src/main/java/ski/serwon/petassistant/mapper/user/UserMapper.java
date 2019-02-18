package ski.serwon.petassistant.mapper.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ski.serwon.petassistant.dto.animal.AnimalDTO;
import ski.serwon.petassistant.dto.user.UserDTO;
import ski.serwon.petassistant.mapper.animal.AnimalMapper;
import ski.serwon.petassistant.entity.user.User;

import java.util.stream.Collectors;

@Component
public class UserMapper {

    private AnimalMapper animalMapper;

    @Autowired
    public UserMapper(AnimalMapper animalMapper) {
        this.animalMapper = animalMapper;
    }

    public UserDTO mapModelToDTO(User model, UserDTO dto) {
        dto.setId(model.getId());
        dto.setFirstName(model.getFirstName());
        dto.setLastName(model.getLastName());
        dto.setPhoto(model.getPhoto());
        dto.setAnimals(model.getAnimals().stream().map(animal ->
                animalMapper.mapModelToDTO(animal, AnimalDTO.builder().build())).collect(Collectors.toList()));
        return dto;
    }
    
    public User mapDTOtoModel(UserDTO dto, User model) {
        model.setId(dto.getId());
        model.setFirstName(dto.getFirstName());
        model.setLastName(dto.getLastName());
        model.setPhoto(dto.getPhoto());
        model.setEmail(dto.getEmail());
        model.setPassword(dto.getPassword());
        return model;
    }
}
