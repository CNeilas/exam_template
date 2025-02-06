package vidunas.techin.exam_template.rest;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import vidunas.techin.exam_template.exception.DuplicatedUserInfoException;
import vidunas.techin.exam_template.model.User;
import vidunas.techin.exam_template.rest.dto.AuthResponse;
import vidunas.techin.exam_template.rest.dto.LoginRequest;
import vidunas.techin.exam_template.rest.dto.SignupRequest;
import vidunas.techin.exam_template.security.SecurityConfig;
import vidunas.techin.exam_template.security.TokenProvider;
import vidunas.techin.exam_template.service.UserService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        String token = authenticateAndGetToken(loginRequest.getUsername(), loginRequest.getPassword());
        return new AuthResponse(token);
    }

    private String authenticateAndGetToken(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return tokenProvider.generateToken(authentication);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public AuthResponse signUp(@Valid @RequestBody SignupRequest signupRequest) {
        if(userService.hasUserWithUsername(signupRequest.getUsername())) {
            throw new DuplicatedUserInfoException("Username already exists");
        }
        if (userService.hasUserWithEmail(signupRequest.getEmail())) {
            throw new DuplicatedUserInfoException("Email already exists");
        }

        userService.saveUser(mapSignupRequestToUser(signupRequest));
        String token = authenticateAndGetToken(signupRequest.getUsername(), signupRequest.getPassword());
        return new AuthResponse(token);
    }

    private User mapSignupRequestToUser(SignupRequest signupRequest) {
        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setEmail(signupRequest.getEmail());
        user.setRole(SecurityConfig.USER);
        return user;
    }
}
