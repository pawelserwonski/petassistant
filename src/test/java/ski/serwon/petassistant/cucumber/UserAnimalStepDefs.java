package ski.serwon.petassistant.cucumber;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.mockito.*;
import ski.serwon.petassistant.dao.animal.AnimalDao;
import ski.serwon.petassistant.dao.user.UserDao;
import ski.serwon.petassistant.model.animal.Animal;
import ski.serwon.petassistant.model.user.User;
import ski.serwon.petassistant.service.animal.AnimalService;
import ski.serwon.petassistant.service.animal.DefaultAnimalService;
import ski.serwon.petassistant.service.user.DefaultUserService;
import ski.serwon.petassistant.service.user.UserService;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.*;

public class UserAnimalStepDefs{

    private List<User> userMockList = new LinkedList<>();
    private List<Animal> animalMockList = new LinkedList<>();

    private User userProfile;
    private Animal animal;

    private UserService userService;
    private AnimalService animalService;

    @Mock
    private UserDao userDao;
    @Mock
    private AnimalDao animalDao;


    @Before
    public void setUpMocks() {
        MockitoAnnotations.initMocks(this);
        userService = new DefaultUserService(userDao);
        animalService = new DefaultAnimalService(animalDao);
        setUpUserMock();
        setUpAnimalMock();
    }

    private void setUpUserMock() {
        setUpUserSaveScenario();
        setUpUserFindScenario();
    }

    private void setUpAnimalMock() {
        setUpAnimalSaveScenario();
        setUpAnimalFindScenario();
        setUpAnimalDeleteScenario();
    }

    private void setUpUserSaveScenario() {
        Mockito.when(userDao.save(any(User.class))).then(invocationOnMock1 -> {
            User added = invocationOnMock1.getArgument(0);
            if (added.getId() == null) {
                added.setId(Long.valueOf(userMockList.size()));
                userMockList.add(added);
            } else {
                userMockList.set(added.getId().intValue(), added);
            }
            return added;
        });
    }

    private void setUpUserFindScenario() {
        Mockito.when(userDao.findById(anyLong())).thenAnswer(invocationOnMock ->
                userMockList.get(invocationOnMock.getArgument(0)));
    }

    private void setUpAnimalSaveScenario() {
        Mockito.when(animalDao.save(any())).then(invocationOnMock -> {
            Animal added = invocationOnMock.getArgument(0);
            if (added.getId() == null) {
                added.setId(Long.valueOf(animalMockList.size()));
                animalMockList.add(added);
            } else {
                animalMockList.set(added.getId().intValue(), added);
            }
            return added;
        });
    }

    private void setUpAnimalFindScenario() {
        Mockito.when(animalDao.findAllByOwner(any())).thenAnswer(invocationOnMock -> {
            User owner = invocationOnMock.getArgument(0);
            return animalMockList.stream().filter(c -> c.getOwner() == owner).collect(Collectors.toList());
        });
    }

    private void setUpAnimalDeleteScenario() {
        Mockito.doAnswer(invocationOnMock -> {
            Long id = invocationOnMock.getArgument(0);
            animalMockList.remove(id.intValue());
            return null;
        }).when(animalDao).deleteById(anyLong());
    }

    @Given("^An user profile$")
    public void anUserProfile() {
        userProfile = this.userService.addUser(User.builder().email("test@test.test").password("test").
                firstName("Lorem").lastName("Ipsum").build());
    }

    @When("^I create an animal$")
    public void iCreateAnAnimal() {
        animal = Animal.builder().name("Foo").breed("Bar").species("Spec").birthDate(LocalDate.now())
                .owner(userProfile).build();
    }

    @And("^Add it to user's profile$")
    public void addItToUserSProfile() {
        animal = animalService.addAnimal(animal);
    }

    @And("^an animal in this profile$")
    public void anAnimalInThisProfile() {
        iCreateAnAnimal();
        addItToUserSProfile();
    }

    @Then("^This animal will be in user's animals$")
    public void thisAnimalWillBeInUserSAnimals() {
        Assert.assertTrue(animalService.getAnimalsByOwner(userProfile).contains(animal));
    }

    @When("^I modify any animal's property$")
    public void iModifyAnyAnimalSProperty() {
        animal.setName("Lorem");
        animalService.updateAnimal(animal);
    }

    @Then("^User's animals collection will contain modified animal$")
    public void userSAnimalsCollectionWillContainModifiedAnimal() {
        Assert.assertEquals(animal.getName(), animalService.getAnimalsByOwner(userProfile).get(0).getName());
    }

    @When("^I delete an animal$")
    public void iDeleteAnAnimal() {
        animalService.deleteAnimal(animal.getId());
    }

    @Then("^User's animals collection will not contain this animal$")
    public void userSAnimalsCollectionWillNotContainThisAnimal() {
        Assert.assertFalse(animalService.getAnimalsByOwner(userProfile).contains(animal));
    }
}
