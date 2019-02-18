package ski.serwon.petassistant.controller.animal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ski.serwon.petassistant.dto.animal.AnimalDTO;
import ski.serwon.petassistant.dto.user.UserDTO;
import ski.serwon.petassistant.mapper.animal.AnimalMapper;
import ski.serwon.petassistant.entity.animal.Animal;
import ski.serwon.petassistant.security.LoginDetailsService;
import ski.serwon.petassistant.service.animal.AnimalService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/animal")
@CrossOrigin
public class AnimalController {

    private AnimalService animalService;
    private AnimalMapper animalMapper;
    private LoginDetailsService loginDetailsService;

    @Autowired
    public AnimalController(AnimalService animalService, AnimalMapper animalMapper, LoginDetailsService loginDetailsService) {
        this.animalService = animalService;
        this.animalMapper = animalMapper;
        this.loginDetailsService = loginDetailsService;
    }

    @GetMapping
    public ResponseEntity<List<AnimalDTO>> getAnimalsOfLoggedUser() {
        try {
            List<Animal> animalsToReturn = loginDetailsService.getCurrentUser().getAnimals();
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
            animalDTO.setOwner(UserDTO.builder().id(loginDetailsService.getCurrentUser().getId()).build());
            Animal animalToAdd = animalMapper.mapDTOtoModel(animalDTO, Animal.builder().build());
            return ResponseEntity.ok(animalService.addAnimal(animalToAdd));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Animal> updateAnimal(@RequestBody AnimalDTO animalDTO, @PathVariable("id") Long id) {
        Animal updatedAnimal = animalService.getAnimalById(id);
        updatedAnimal.setName(animalDTO.getName());
        updatedAnimal.setBreed(animalDTO.getBreed());
        updatedAnimal.setBirthDate(animalDTO.getBirthDate());
        updatedAnimal.setSpecies(animalDTO.getSpecies());
        updatedAnimal.setPhoto(animalDTO.getPhoto());

        return ResponseEntity.ok(animalService.updateAnimal(updatedAnimal));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteAnimal(@PathVariable("id") Long id) {
        this.animalService.deleteAnimal(id);
        return ResponseEntity.ok().build();
    }
}
