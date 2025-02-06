package vidunas.techin.exam_template.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vidunas.techin.exam_template.exception.UserNotFoundException;
import vidunas.techin.exam_template.model.User;
import vidunas.techin.exam_template.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll() { return userRepository.findAll(); }

    public boolean hasUserWithUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean hasUserWithEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public User validateAndGetUser(String username) {
        return getUserByUsername(username).orElseThrow(() -> new UserNotFoundException("User with username " + username + " not found"));
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
