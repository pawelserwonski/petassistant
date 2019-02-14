package ski.serwon.petassistant;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ski.serwon.petassistant.model.animal.Animal;
import ski.serwon.petassistant.model.user.User;
import ski.serwon.petassistant.service.animal.AnimalService;
import ski.serwon.petassistant.service.user.UserService;

import java.time.LocalDate;

@ContextConfiguration(classes = PetassistantApplication.class, loader = SpringBootContextLoader.class)
@WebAppConfiguration
public class UserAnimalStepDefs{

    @Autowired
    private UserService userService;

    @Autowired
    private AnimalService animalService;

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

    @After
    public void deleteUserProfile(Scenario scenario) {
        this.userService.deleteUser(this.userProfile);
    }
}
