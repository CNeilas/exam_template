package sunklodas.techin.exam_template.runner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import sunklodas.techin.exam_template.service.UserService;
import sunklodas.techin.exam_template.model.User;
import sunklodas.techin.exam_template.security.SecurityConfig;


import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserInitializer implements CommandLineRunner {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (!userService.findAll().isEmpty()) {
            return;
        }
        USERS.forEach(user -> {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.saveUser(user);
        });
        log.info("Database initialized");

    }

    private static final List<User> USERS = Arrays.asList(
            new User("admin", "admin@gmail.com", "admin", SecurityConfig.ADMIN),
            new User("user", "user@gmail.com", "user", SecurityConfig.USER)
    );
}
