package ski.serwon.petassistant.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ski.serwon.petassistant.dao.user.UserDao;
import ski.serwon.petassistant.model.user.User;

@Component
public class AuthenticationUtil {
    private UserDao userDao;

    @Autowired
    public AuthenticationUtil(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getCurrentUser() {
        //TODO: implement method
        return userDao.findAll().get(0);
    }
}
