package ski.serwon.petassistant.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ski.serwon.petassistant.dto.user.UserDTO;
import ski.serwon.petassistant.mapper.user.UserMapper;
import ski.serwon.petassistant.entity.user.User;
import ski.serwon.petassistant.security.LoginDetailsService;
import ski.serwon.petassistant.service.user.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    private LoginDetailsService loginDetailsService;
    private UserMapper userMapper;
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(LoginDetailsService loginDetailsService, UserMapper userMapper, UserService userService, PasswordEncoder passwordEncoder) {
        this.loginDetailsService = loginDetailsService;
        this.userMapper = userMapper;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User userToAdd = userMapper.mapDTOtoModel(userDTO, User.builder().build());
        return ResponseEntity.ok(this.userService.addUser(userToAdd));
    }

    @GetMapping
    public ResponseEntity<UserDTO> getLoggedUser() {
        try {
            return ResponseEntity.ok(userMapper.mapModelToDTO(loginDetailsService.getCurrentUser(), UserDTO.builder().build()));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
