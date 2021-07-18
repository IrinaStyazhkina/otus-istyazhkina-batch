package ru.otus.istyazhkina.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.istyazhkina.library.domain.jpa.GenreJpa;

public interface GenreJpaRepository extends JpaRepository<GenreJpa, Long> {

    GenreJpa getByName(String name);
}
