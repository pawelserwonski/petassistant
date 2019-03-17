package ski.serwon.petassistant.unit.controller;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ski.serwon.petassistant.PetassistantApplication;
import ski.serwon.petassistant.controller.animal.AnimalController;
import ski.serwon.petassistant.dto.animal.AnimalDTO;
import ski.serwon.petassistant.entity.animal.Animal;
import ski.serwon.petassistant.entity.user.User;
import ski.serwon.petassistant.mapper.animal.AnimalMapper;
import ski.serwon.petassistant.mockinitializer.AnimalServiceMockInitializer;
import ski.serwon.petassistant.mockinitializer.LoginDetailsServiceMockInitializer;
import ski.serwon.petassistant.security.LoginDetailsService;
import ski.serwon.petassistant.service.animal.AnimalService;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PetassistantApplication.class)
@WebAppConfiguration
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AnimalControllerTest {
    private MediaType contentType = MediaType.APPLICATION_JSON_UTF8;
    private User mockedUser;
    private Animal mockedAnimal;
    private List<Animal> animalMockList;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Mock
    private AnimalService animalService;
    @Mock
    private LoginDetailsService loginDetailsService;
    @Autowired
    private AnimalMapper animalMapper;

    private AnimalController animalController;

    @BeforeEach
    public void setUpMocks() {
        MockitoAnnotations.initMocks(this);
        animalController = new AnimalController(animalService, animalMapper, loginDetailsService);
        prepareMockedUserAndAnimals();
        AnimalServiceMockInitializer.setUpAnimalServiceMock(animalService, animalMockList);
        LoginDetailsServiceMockInitializer.setUpLoginDetailsServiceMock(loginDetailsService, mockedUser);
    }

    private void prepareMockedUserAndAnimals() {
        animalMockList = new LinkedList<>();
        mockedUser = User.builder().id(1L)
                .firstName("Lorem")
                .lastName("Ipsum")
                .email("foo@bar.com")
                .password("pass")
                .animals(animalMockList)
                .build();
        mockedAnimal = Animal.builder()
                .id(0L)
                .owner(mockedUser)
                .species("Foo")
                .breed("Bar")
                .birthDate(LocalDate.now())
                .name("Lorem")
                .vaccines(new LinkedList<>())
                .walks(new LinkedList<>())
                .feeds(new LinkedList<>())
                .diseasesHistory(new LinkedList<>())
                .vetVisitsHistory(new LinkedList<>())
                .build();
        animalMockList.add(mockedAnimal);
    }

    @Test
    public void shouldNotFound_getAnimalWithNonExistingId() throws Exception{
        long id = 1500_100_900;
        ResponseEntity entity = animalController.getAnimalWithId(id);
        Assertions.assertEquals(ResponseEntity.notFound().build(), entity);
    }

    @Test
    public void shouldFind_getAnimalWithExistingId() throws Exception{
        long id = 0;
        ResponseEntity entity = animalController.getAnimalWithId(id);
        AnimalDTO mappedAnimal = animalMapper.mapModelToDTO(mockedAnimal, AnimalDTO.builder().build());
        Assertions.assertEquals(ResponseEntity.ok(mappedAnimal), entity);
    }

    @Test
    public void shouldNofFound_getAnimalWithNegativeId() throws Exception{
        long id = -1;
        ResponseEntity entity = animalController.getAnimalWithId(id);
        Assertions.assertEquals(ResponseEntity.notFound().build(), entity);
    }

    @Test
    public void shouldAddToListAndReturnAdded_addAnimal() throws Exception {
        AnimalDTO addedAnimalDTO = AnimalDTO.builder()
                .species("FOO")
                .breed("BAR")
                .birthDate(LocalDate.now())
                .name("LOREM")
                .build();
        ResponseEntity entity = animalController.createAnimal(addedAnimalDTO);
        Animal addedAnimal = (Animal) entity.getBody();
        Assertions.assertAll(
                () -> Assertions.assertEquals(ResponseEntity.ok(addedAnimal), entity),
                () -> MatcherAssert.assertThat(animalMockList, Matchers.hasItem(addedAnimal))
        );
    }

    @Test
    public void shouldAddToListWithNewIdAndReturnAdded_addAnimalWithoutPredefinedId() throws Exception {
        AnimalDTO addedAnimalDTO = AnimalDTO.builder()
                .species("FOO")
                .breed("BAR")
                .birthDate(LocalDate.now())
                .name("LOREM")
                .id(10000L)
                .build();
        ResponseEntity entity = animalController.createAnimal(addedAnimalDTO);
        Animal addedAnimal = (Animal) entity.getBody();
        Assertions.assertNotEquals(addedAnimalDTO.getId(), addedAnimal.getId());
    }

    @Test
    public void shouldUpdateInListAndReturnUpdated_updateAnimalWithSpecifiedId() throws Exception {
        AnimalDTO updatedAnimalDTO = AnimalDTO.builder()
                .species("FOO")
                .breed("BAR")
                .birthDate(LocalDate.now())
                .name("LOREM")
                .build();
        ResponseEntity entity = animalController.updateAnimal(updatedAnimalDTO, 0L);
        Animal updatedAnimal = (Animal) entity.getBody();
        Assertions.assertAll(
                () -> Assertions.assertEquals(ResponseEntity.ok(updatedAnimal), entity),
                () -> Assertions.assertEquals(updatedAnimal, animalMockList.get(0))
        );
    }

    @Test
    public void shouldNotUpdateInListAndReturnNull_updateAnimalWithIdNotInList() throws Exception {
        AnimalDTO updatedAnimalDTO = AnimalDTO.builder()
                .species("FOO")
                .breed("BAR")
                .birthDate(LocalDate.now())
                .name("LOREM")
                .build();
        Animal[] beforeUpdate = animalMockList.toArray(new Animal[0]);
        ResponseEntity entity = animalController.updateAnimal(updatedAnimalDTO, 10000L);
        Animal[] afterUpdate = animalMockList.toArray(new Animal[0]);
        Animal updatedAnimal = (Animal) entity.getBody();
        Assertions.assertAll(
                () -> Assertions.assertNull(updatedAnimal),
                () -> Assertions.assertArrayEquals(beforeUpdate, afterUpdate)
        );
    }
}
