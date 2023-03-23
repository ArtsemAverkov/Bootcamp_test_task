package by.it.academy.common.controller;


import by.it.academy.Application;
import by.it.academy.common.extension.ValidParameterResolverUser;
import by.it.academy.controller.UserController;
import by.it.academy.dto.UserDto;
import by.it.academy.entity.Role;
import by.it.academy.entity.User;
import by.it.academy.service.UserService;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@DisplayName("User Controller Test")
@WebMvcTest(UserController.class)
@RunWith(SpringRunner.class)
@ExtendWith(ValidParameterResolverUser.class)
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @Autowired
    private Gson gson;



    @Test
    void createUser(UserDto userDto) throws Exception {
        final String jsonUserDto= "{\n" +
                "  \"id\": "+userDto.getId()+",\n" +
                "  \"last_name\": "+userDto.getLast_name()+",\n" +
                "  \"first_name\":"+userDto.getFirst_name()+",\n" +
                "  \"patronymic\": "+userDto.getPatronymic()+",\n" +
                "  \"email\": "+userDto.getEmail()+"\n" +
                "}";
        User user = buildUser(userDto);
        Mockito.when(userService.create(userDto)).thenReturn(user.getId());
        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUserDto))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(user.getId().toString()))
                .andDo(MockMvcResultHandlers.print());

    }
    @Test
    void readAllUser(UserDto userDto) throws Exception {
        final String jsonUserDto = "{\n" +
                "  \"id\": " + userDto.getId() + ",\n" +
                "  \"last_name\": " + userDto.getLast_name() + ",\n" +
                "  \"first_name\":" + userDto.getFirst_name() + ",\n" +
                "  \"patronymic\": " + userDto.getPatronymic() + ",\n" +
                "  \"email\": " + userDto.getEmail() + "\n" +
                "}";
        User user = buildUser(userDto);
        List<User> productList = new ArrayList<>();
        productList.add(user);
        Mockito.when(userService.readAll(Pageable.ofSize(10).withPage(0))).thenReturn(productList);
        mockMvc.perform(MockMvcRequestBuilders.get("/user")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(jsonUserDto))
                .andDo(MockMvcResultHandlers.print());
    }


    private User buildUser(UserDto userDto){
        return User.builder()
                .first_name(userDto.getFirst_name())
                .last_name(userDto.getLast_name())
                .patronymic(userDto.getPatronymic())
                .email(userDto.getEmail())
                .role(Role.USER)
                .build();
    }
}
