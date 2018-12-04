package ski.serwon.petassistant.controller.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ski.serwon.petassistant.dto.security.JwtTokenDTO;
import ski.serwon.petassistant.dto.user.UserDTO;
import ski.serwon.petassistant.security.JwtTokenService;

import static ski.serwon.petassistant.security.JwtConstants.TOKEN_TYPE;

@CrossOrigin
@RestController
@RequestMapping("/signin")
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService tokenService;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager, JwtTokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<JwtTokenDTO> signin(@RequestBody UserDTO user) {
        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final JwtTokenDTO jwtToken = JwtTokenDTO.builder()
                .token(tokenService.createToken(authentication))
                .type(TOKEN_TYPE)
                .build();
        return ResponseEntity.ok(jwtToken);
    }
}
