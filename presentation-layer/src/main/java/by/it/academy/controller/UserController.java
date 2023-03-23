package by.it.academy.controller;

import by.it.academy.dto.UserDto;
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


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Long createUser(@RequestBody  UserDto userDto) {
        return userService.create(userDto);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> readAllUser(@PageableDefault(page = 0)  @SortDefault(sort = "email") Pageable pageable) {
        return userService.readAll(pageable);
    }

}
