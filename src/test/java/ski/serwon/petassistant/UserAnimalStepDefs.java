package ski.serwon.petassistant;

import cucumber.api.Scenario;
import cucumber.api.java.After;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

public class UserAnimalStepDefs{

    private List<User> userMockList = new LinkedList<>();
    private List<Animal> animalMockList = new LinkedList<>();

    @Mock
    private UserDao userDao;
    @Mock
    private AnimalDao animalDao;

    private UserService userService;
    private AnimalService animalService;

    @Before
    public void setUpMocks() {
        MockitoAnnotations.initMocks(this);
        userService = new DefaultUserService(userDao);
        animalService = new DefaultAnimalService(animalDao);
        setUpUserMock();
        setUpAnimalMock();
    }

    private void setUpUserMock() {
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
        Mockito.when(userDao.findById(anyLong())).thenAnswer(invocationOnMock ->
                userMockList.get(invocationOnMock.getArgument(0)));
    }

    private void setUpAnimalMock() {
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
        Mockito.doNothing().when(animalDao).deleteById(anyLong());
    }

    private User userProfile;
    private Animal animal;

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
        Assert.assertTrue(userProfile.getAnimals().contains(animal));
    }

    @When("^I modify any animal's property$")
    public void iModifyAnyAnimalSProperty() {
        animal.setName("Lorem");
        animalService.updateAnimal(animal);
    }

    @Then("^User's animals collection will contain modified animal$")
    public void userSAnimalsCollectionWillContainModifiedAnimal() {
        Assert.assertEquals(animal.getName(), userProfile.getAnimals().get(0).getName());
    }

    @When("^I delete an animal$")
    public void iDeleteAnAnimal() {
        animalService.deleteAnimal(animal.getId());
    }

    @Then("^User's animals collection will not contain this animal$")
    public void userSAnimalsCollectionWillNotContainThisAnimal() {
        Assert.assertFalse(userProfile.getAnimals().contains(animal));
    }
}
