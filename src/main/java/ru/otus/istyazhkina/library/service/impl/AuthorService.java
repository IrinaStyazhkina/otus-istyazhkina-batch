package ru.otus.istyazhkina.library.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.otus.istyazhkina.library.domain.jpa.AuthorJpa;
import ru.otus.istyazhkina.library.repository.AuthorJpaRepository;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorJpaRepository authorJpaRepository;

    @Cacheable(cacheNames = "authors")
    public AuthorJpa getAuthorJpa(String name, String surname) {
        return authorJpaRepository.getByNameAndSurname(name, surname);
    }

}
