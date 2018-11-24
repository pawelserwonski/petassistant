package ski.serwon.petassistant.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ski.serwon.petassistant.model.user.User;
import ski.serwon.petassistant.service.user.UserService;

@Component
public class AuthenticationUtil {
    private UserService userService;

    @Autowired
    public AuthenticationUtil(UserService userService) {
        this.userService = userService;
    }


    public User getCurrentUser() {
        //TODO: implement method
        return userService.getAll().get(0);
    }
}
