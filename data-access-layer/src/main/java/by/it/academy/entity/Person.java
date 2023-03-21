package by.it.academy.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Person {
    @Id
    Long id;
    String last_name;
    String first_name;
    String patronymic;
    String email;

    @OneToOne
    Role role;
}
