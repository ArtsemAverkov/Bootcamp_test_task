package by.it.academy.service;

import by.it.academy.dto.PersonDto;
import by.it.academy.entity.Person;
import by.it.academy.entity.Role;
import by.it.academy.repository.person.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonApiService implements PersonService{

    private final PersonRepository personRepository;

    @Override
    public Long create(PersonDto personDto) {
        Person person = buildPerson(personDto);
        return personRepository.save(person).getId();
    }

    @Override
    public List<Person> readAll(Pageable pageable) {
        return personRepository.findAll();
    }
    private Person buildPerson(PersonDto personDto){
        return Person.builder()
                .first_name(personDto.getFirst_name())
                .last_name(personDto.getLast_name())
                .patronymic(personDto.getPatronymic())
                .email(personDto.getEmail())
                .role(Role.USER)
                .build();
    }
}
