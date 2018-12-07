package ski.serwon.petassistant.controller.animal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ski.serwon.petassistant.dto.animal.AnimalDTO;
import ski.serwon.petassistant.dto.user.UserDTO;
import ski.serwon.petassistant.mapper.animal.AnimalMapper;
import ski.serwon.petassistant.model.animal.Animal;
import ski.serwon.petassistant.service.animal.AnimalService;
import ski.serwon.petassistant.utils.AuthenticationUtil;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/animal")
@CrossOrigin
public class AnimalController {

    private AnimalService animalService;
    private AnimalMapper animalMapper;
    private AuthenticationUtil authenticationUtil;

    @Autowired
    public AnimalController(AnimalService animalService, AnimalMapper animalMapper, AuthenticationUtil authenticationUtil) {
        this.animalService = animalService;
        this.animalMapper = animalMapper;
        this.authenticationUtil = authenticationUtil;
    }

    @GetMapping
    public ResponseEntity<List<AnimalDTO>> getAnimalsOfLoggedUser() {
        try {
            List<Animal> animalsToReturn = authenticationUtil.getCurrentUser().getAnimals();
            if (animalsToReturn == null) {
                return ResponseEntity.notFound().build();
            }
            List<AnimalDTO> animalDTOsToReturn = animalsToReturn.stream().map(animal ->
                    animalMapper.mapModelToDTO(animal, AnimalDTO.builder().build())).collect(Collectors.toList());
            return ResponseEntity.ok(animalDTOsToReturn);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AnimalDTO> getAnimalWithId(@PathVariable("id") Long id) {
        try {
            Animal animalToReturn = animalService.getAnimalById(id);
            if (animalToReturn == null) {
                return ResponseEntity.notFound().build();
            }
            AnimalDTO dtoToReturn = animalMapper.mapModelToDTO(animalToReturn, AnimalDTO.builder().build());
            return ResponseEntity.ok(dtoToReturn);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Animal> createAnimal(@RequestBody AnimalDTO animalDTO) {
        try {
            animalDTO.setOwner(UserDTO.builder().id(authenticationUtil.getCurrentUser().getId()).build());
            Animal animalToAdd = animalMapper.mapDTOtoModel(animalDTO, Animal.builder().build());
            return ResponseEntity.ok(animalService.addAnimal(animalToAdd));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //TODO: add update

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteAnimal(@PathVariable("id") Long id) {
        this.animalService.deleteAnimal(id);
        return ResponseEntity.ok().build();
    }
}
