package ski.serwon.petassistant.service.walk;

import ski.serwon.petassistant.model.animal.Animal;
import ski.serwon.petassistant.model.walk.Walk;

import java.util.List;

public interface WalkService {
    public List<Walk> getWalksOfAnimal(Animal animal);
    public Walk getWalkById(Long id);
    public Walk addWalk(Walk walk);
}
