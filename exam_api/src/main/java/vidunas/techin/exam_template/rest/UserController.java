package vidunas.techin.exam_template.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import vidunas.techin.exam_template.mapper.UserMapper;
import vidunas.techin.exam_template.model.User;
import vidunas.techin.exam_template.rest.dto.UserDto;
import vidunas.techin.exam_template.security.CustomUserDetails;
import vidunas.techin.exam_template.service.UserService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAll().stream()
                .map(userMapper::toUserDto)
                .toList();
    }

    @GetMapping("/me")
    public UserDto getCurrentUsername(@AuthenticationPrincipal CustomUserDetails currentUser) {
        return userMapper.toUserDto(userService.validateAndGetUser(currentUser.getUsername()));
    }

    @GetMapping("/{username}")
    public UserDto getUser(@PathVariable String username) {
        return userMapper.toUserDto(userService.validateAndGetUser(username));
    }

    @DeleteMapping("/{username}")
    public UserDto deleteUser(@PathVariable String username) {
        User user = userService.validateAndGetUser(username);
        userService.deleteUser(user);
        return userMapper.toUserDto(user);
    }
}
