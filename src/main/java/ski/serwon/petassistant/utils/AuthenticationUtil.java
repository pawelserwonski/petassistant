package ski.serwon.petassistant.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ski.serwon.petassistant.dao.user.UserDao;
import ski.serwon.petassistant.model.user.User;

import java.util.Optional;

@Component
public class AuthenticationUtil {
    private UserDao userDao;

    @Autowired
    public AuthenticationUtil(UserDao userDao) {
        this.userDao = userDao;
    }


    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> currentUser = userDao.findByEmail(authentication.getName());
        if (currentUser.isPresent()) {
            return currentUser.get();
        }

        throw new SecurityException("Current profile was not found in database");
    }

    public boolean isCurrentProfileId(Long id) {
        return id.equals(getCurrentUser().getId());
    }
}
