package by.it.academy.service;

import by.it.academy.dto.PersonDto;
import by.it.academy.entity.Person;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface PersonService {
    Long create(PersonDto personDto);
    List<Person> readAll(Pageable pageable);
}
