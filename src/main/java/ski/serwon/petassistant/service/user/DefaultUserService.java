package ski.serwon.petassistant.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ski.serwon.petassistant.dao.user.UserDao;
import ski.serwon.petassistant.model.user.User;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DefaultUserService implements UserService {

    private UserDao userDao;

    @Autowired
    public DefaultUserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public User getUserById(Long id) {
        User userToReturn;
        try {
            userToReturn = this.userDao.findById(id).get();
        } catch (NoSuchElementException e) {
            userToReturn = null;
        }
        return userToReturn;
    }

    @Override
    @Transactional
    public List<User> getAll() {
        return this.userDao.findAll();
    }

    @Override
    @Transactional
    public User addUser(User userToAdd) {
        return this.userDao.save(userToAdd);
    }
}
