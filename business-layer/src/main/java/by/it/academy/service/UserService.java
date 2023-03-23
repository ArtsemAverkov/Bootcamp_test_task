package by.it.academy.service;

import by.it.academy.dto.UserDto;
import by.it.academy.entity.User;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface UserService {
    Long create(UserDto userDto);
    List<User> readAll(Pageable pageable);
}
