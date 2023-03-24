package by.it.academy.service;

import by.it.academy.dto.userDto.UserDto;
import by.it.academy.entity.User;
import by.it.academy.entity.Role;
import by.it.academy.repository.person.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
/**
 This package contains the implementation of the userService interface for managing user.
 The implementation includes methods for creating  as well as
 getting a list of all user.
 @author Artsem Averkov
 @version 1.0
 */
@Service
@RequiredArgsConstructor
public class UserApiService implements UserService {

    private final UserRepository userRepository;

    /**
     * Creates a new user entity in the repository.
     *
     * @param userDto the DTO object containing the user data to be created
     * @return the ID of the newly created user entity
     */
    @Override
    public Long create(UserDto userDto) {
        User user = buildPerson(userDto);
        return userRepository.save(user).getId();
    }

    /**
     * Retrieves a list of all user entities from the repository.
     *
     * @param pageable the pagination parameters to be applied to the query
     * @return the list of user entities retrieved from the repository
     */
    @Override
    public List<User> readAll(Pageable pageable) {

        return userRepository.findAll(pageable).toList();
    }

    /**
     * Builds a new User object from a UserDto object.
     *
     * @param userDto the DTO object containing the user data to be converted
     * @return the User object built from the DTO data
     */
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
