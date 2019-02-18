package ski.serwon.petassistant.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ski.serwon.petassistant.dao.user.UserDao;
import ski.serwon.petassistant.entity.user.User;

import java.util.Optional;

@Service
public class LoginDetailsService implements UserDetailsService {
    private final UserDao userDao;

    @Autowired
    public LoginDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.findByEmail(s).orElseThrow(() -> new UsernameNotFoundException(s));
        return LoginDetails.builder().username(user.getEmail()).password(user.getPassword()).build();
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
