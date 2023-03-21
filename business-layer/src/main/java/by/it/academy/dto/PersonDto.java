package by.it.academy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {

    Long id;
    String last_name;
    String first_name;
    String patronymic;
    String email;
}
