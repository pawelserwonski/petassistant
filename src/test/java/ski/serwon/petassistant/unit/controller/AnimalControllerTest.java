package ski.serwon.petassistant.unit.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import ski.serwon.petassistant.PetassistantApplication;
import ski.serwon.petassistant.controller.animal.AnimalController;
import ski.serwon.petassistant.dao.animal.AnimalDao;
import ski.serwon.petassistant.dao.user.UserDao;
import ski.serwon.petassistant.entity.animal.Animal;
import ski.serwon.petassistant.entity.user.User;
import ski.serwon.petassistant.mapper.animal.AnimalMapper;
import ski.serwon.petassistant.mockinitializer.AnimalServiceMockInitializer;
import ski.serwon.petassistant.mockinitializer.LoginDetailsServiceMockInitializer;
import ski.serwon.petassistant.security.LoginDetailsService;
import ski.serwon.petassistant.service.animal.AnimalService;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PetassistantApplication.class)
@WebAppConfiguration
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AnimalControllerTest {
    private MediaType contentType = MediaType.APPLICATION_JSON_UTF8;
    private MockMvc mockMvc;
    private User mockedUser;
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

    @BeforeAll
    public void setUpMocks() {
        MockitoAnnotations.initMocks(this);
        animalController = new AnimalController(animalService, animalMapper, loginDetailsService);
        AnimalServiceMockInitializer.setUpAnimalServiceMock(animalService, animalMockList);
        LoginDetailsServiceMockInitializer.setUpLoginDetailsServiceMock();
    }

    @BeforeEach
    public void prepareMockedUserAndAnimals() {
        animalMockList = new LinkedList<>();
        mockedUser = new User(1L, "Lorem", "Ipsum",
                "foo@bar.com", "pass", null, animalMockList);
    }




}
