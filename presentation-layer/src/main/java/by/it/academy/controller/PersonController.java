package by.it.academy.controller;

import by.it.academy.dto.PersonDto;
import by.it.academy.entity.Person;
import by.it.academy.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Long createDiscount(@RequestBody @Valid PersonDto personDto) {
        return personService.create(personDto);
    }

    @GetMapping(produces = MediaType.APPLICATION_ATOM_XML_VALUE)
    public List<Person> readDiscount(@PageableDefault(page = 10) Pageable pageable) {
        return personService.readAll(pageable);
    }

}
