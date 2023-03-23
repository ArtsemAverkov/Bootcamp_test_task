package by.it.academy.service;

import by.it.academy.dto.UserDto;
import by.it.academy.entity.User;
import by.it.academy.entity.Role;
import by.it.academy.repository.person.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class UserApiService implements UserService {

    private final UserRepository userRepository;

    @Override
    public Long create(UserDto userDto) {
        User user = buildPerson(userDto);
        return userRepository.save(user).getId();
    }

    @Override
    public List<User> readAll(Pageable pageable) {

        return userRepository.findAll(pageable).toList();
    }
    private User buildPerson(UserDto userDto){
        return User.builder()
                .first_name(userDto.getFirst_name())
                .last_name(userDto.getLast_name())
                .patronymic(userDto.getPatronymic())
                .email(userDto.getEmail())
                .role(Role.USER)
                .build();
    }
}
