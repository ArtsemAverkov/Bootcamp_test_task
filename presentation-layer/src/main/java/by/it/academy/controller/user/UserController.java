package by.it.academy.controller.user;

import by.it.academy.dto.userDto.UserDto;
import by.it.academy.entity.User;
import by.it.academy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The UserApiService class implements the UserService interface and provides methods for creating and reading user entities.
 * These methods use the UserRepository to interact with the database and perform the requested operations.
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    /**
     * Creates a new user entity based on the provided userDto object and saves it to the database.
     *
     * @param userDto the UserDto object containing the user's data.
     * @return the ID of the newly created user entity.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Long createUser(@RequestBody  UserDto userDto) {
        return userService.create(userDto);
    }

    /**
     * Returns a list of all user entities from the database, ordered by email address, and paginated based on the provided pageable object.
     *
     * @param pageable the Pageable object used for pagination and sorting.
     * @return a list of User objects containing the user data.
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> readAllUser(@PageableDefault(page = 0)  @SortDefault(sort = "email") Pageable pageable) {
        return userService.readAll(pageable);
    }

}
