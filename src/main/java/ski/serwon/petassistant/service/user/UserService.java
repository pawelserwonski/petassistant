package ski.serwon.petassistant.service.user;

import ski.serwon.petassistant.model.user.User;

import java.util.List;

public interface UserService {
    public User getUserById(Long id);
    public List<User> getAll();
    public User addUser(User userToAdd);
    public void deleteUser(User userToDelete);
}
