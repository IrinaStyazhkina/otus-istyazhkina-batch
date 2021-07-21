package ru.otus.istyazhkina.library.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.otus.istyazhkina.library.domain.jpa.GenreJpa;
import ru.otus.istyazhkina.library.repository.GenreJpaRepository;

@Service
@RequiredArgsConstructor
public class GenreService {
     private final GenreJpaRepository genreJpaRepository;

     @Cacheable(cacheNames = "genres")
     public GenreJpa getGenreJpa(String name) {
          return genreJpaRepository.getByName(name);
     }

}
