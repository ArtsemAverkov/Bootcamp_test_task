package by.it.academy.common.service;

import by.it.academy.common.extension.InvalidParameterResolverUser;
import by.it.academy.common.extension.ValidParameterResolverUser;
import by.it.academy.dto.UserDto;
import by.it.academy.entity.Role;
import by.it.academy.entity.User;
import by.it.academy.repository.person.UserRepository;
import by.it.academy.service.UserApiService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@DisplayName("Testing User Service for Valid and Invalid")
public class UserServiceImplTest {
    @Nested
    @ExtendWith(MockitoExtension.class)
    @ExtendWith(ValidParameterResolverUser.class)
    public class ValidData {
        @InjectMocks
        private UserApiService userApiService;
        @Mock
        private UserRepository userRepository;

        @Test
        void shouldCreateUserWhenUserIsValid(UserDto userDto) {
            User user = buildUser(userDto);
            Mockito.when(userRepository.save(user)).thenReturn(user);
            Assertions.assertEquals(user.getId(), userApiService.create(userDto));
            Mockito.verify(userRepository, Mockito.times(1)).save(user);
        }

        @Test
        void shouldReadAllProductWhenProductIsValid(UserDto userDto) {
            User user = buildUser(userDto);
            List<User> userList = new ArrayList<>();
            userList.add(user);
            Mockito.when(userRepository.findAll()).thenReturn(userList);
            Assertions.assertEquals(userList, userApiService.readAll(Pageable.ofSize(10).withPage(0)));
            Mockito.verify(userRepository, Mockito.times(1)).findAll();
        }

        private User buildUser(UserDto userDto) {
            return User.builder()
                    .first_name(userDto.getFirst_name())
                    .last_name(userDto.getLast_name())
                    .patronymic(userDto.getPatronymic())
                    .email(userDto.getEmail())
                    .role(Role.USER)
                    .build();
        }

    }

    @Nested
    @ExtendWith(MockitoExtension.class)
    @ExtendWith(InvalidParameterResolverUser.class)
    public class InvalidData {
        @InjectMocks
        private UserApiService userApiService;

        @Test
        void shouldCreateUserWheUserIsInvalid() {
            Assertions.assertThrows(NullPointerException.class,
                    () -> userApiService.create(null));
        }

        @Test
        void shouldReadAllUserWhenUserIsInvalid() {
            Assertions.assertThrows(IllegalArgumentException.class,
                    () -> userApiService.readAll(Pageable.ofSize(0).withPage(0)));
        }
      }
    }
