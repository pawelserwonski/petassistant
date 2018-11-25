package ski.serwon.petassistant.mapper.animal;

import org.springframework.stereotype.Component;
import ski.serwon.petassistant.dto.animal.AnimalDTO;
import ski.serwon.petassistant.dto.disease.DiseaseDTO;
import ski.serwon.petassistant.dto.feed.FeedDTO;
import ski.serwon.petassistant.dto.user.UserDTO;
import ski.serwon.petassistant.dto.vaccine.VaccineDTO;
import ski.serwon.petassistant.dto.vetvisit.VetVisitDTO;
import ski.serwon.petassistant.dto.walk.WalkDTO;
import ski.serwon.petassistant.mapper.disease.DiseaseMapper;
import ski.serwon.petassistant.mapper.feed.FeedMapper;
import ski.serwon.petassistant.mapper.user.UserMapper;
import ski.serwon.petassistant.mapper.vaccine.VaccineMapper;
import ski.serwon.petassistant.mapper.vetvisit.VetVisitMapper;
import ski.serwon.petassistant.mapper.walk.WalkMapper;
import ski.serwon.petassistant.model.animal.Animal;
import ski.serwon.petassistant.service.user.UserService;

import java.util.stream.Collectors;

@Component
public class AnimalMapper {
    
    private UserMapper userMapper;
    private UserService userService;
    private VaccineMapper vaccineMapper;
    private WalkMapper walkMapper;
    private FeedMapper feedMapper;
    private DiseaseMapper diseaseMapper;
    private VetVisitMapper vetVisitMapper;

    public AnimalDTO mapModelToDTO(Animal model, AnimalDTO dto) {
        dto.setId(model.getId());
        dto.setOwner(userMapper.mapModelToDTO(model.getOwner(), UserDTO.builder().build()));
        dto.setSpecies(model.getSpecies());
        dto.setBreed(model.getBreed());
        dto.setBirthDate(model.getBirthDate());
        dto.setName(model.getName());
        dto.setPhoto(model.getPhoto());
        
        dto.setVaccines(model.getVaccines().stream().map(vaccine -> 
                vaccineMapper.mapModelToDTO(vaccine, VaccineDTO.builder().build())).collect(Collectors.toList()));
        
        dto.setWalks(model.getWalks().stream().map(walk -> 
                walkMapper.mapModelToDTO(walk, WalkDTO.builder().build())).collect(Collectors.toList()));
        
        dto.setFeeds(model.getFeeds().stream().map(feed -> 
                feedMapper.mapModelToDTO(feed, FeedDTO.builder().build())).collect(Collectors.toList()));
        
        dto.setDiseasesHistory(model.getDiseasesHistory().stream().map(disease -> 
                diseaseMapper.mapModelToDTO(disease, DiseaseDTO.builder().build())).collect(Collectors.toList()));
        
        dto.setVetVisitsHistory(model.getVetVisitsHistory().stream().map(vetVisit -> 
                vetVisitMapper.mapModelToDTO(vetVisit, VetVisitDTO.builder().build())).collect(Collectors.toList()));
        
        return dto;
    }

    public Animal mapDTOtoModel(AnimalDTO dto, Animal model) {
        model.setId(dto.getId());
        model.setOwner(userService.getUserById(dto.getOwner().getId()));
        model.setSpecies(dto.getSpecies());
        model.setBreed(dto.getBreed());
        model.setBirthDate(dto.getBirthDate());
        model.setName(dto.getName());
        model.setPhoto(dto.getPhoto());
        return model;
    }

}
