package sunklodas.techin.exam_template.mapper;

import org.springframework.stereotype.Service;
import sunklodas.techin.exam_template.model.User;
import sunklodas.techin.exam_template.rest.dto.UserDto;

@Service
public class UserMapper {

    public UserDto toUserDto(User user) {
        if (user == null) {
            return null;
        }
        return new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getRole());
    }
}
