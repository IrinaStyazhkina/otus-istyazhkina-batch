package ru.otus.istyazhkina.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.istyazhkina.library.domain.jpa.AuthorJpa;

public interface AuthorJpaRepository extends JpaRepository<AuthorJpa, Long> {

    AuthorJpa getByNameAndSurname(String name, String surname);
}
