package ru.otus.istyazhkina.library.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;
import ru.otus.istyazhkina.library.domain.jpa.BookJpa;
import ru.otus.istyazhkina.library.domain.mongo.AuthorMongo;
import ru.otus.istyazhkina.library.domain.mongo.BookMongo;
import ru.otus.istyazhkina.library.domain.mongo.GenreMongo;
import ru.otus.istyazhkina.library.repository.AuthorJpaRepository;
import ru.otus.istyazhkina.library.repository.GenreJpaRepository;


@Service
@RequiredArgsConstructor
public class BookItemProcessor implements ItemProcessor<BookMongo, BookJpa> {

    private final GenreJpaRepository genreJpaRepository;
    private final AuthorJpaRepository authorJpaRepository;

    @Override
    public BookJpa process(BookMongo bookMongo) {
        AuthorMongo authorMongo = bookMongo.getAuthorMongo();
        GenreMongo genreMongo = bookMongo.getGenreMongo();
        return new BookJpa(bookMongo.getTitle(),
                authorJpaRepository.getByNameAndSurname(authorMongo.getName(), authorMongo.getSurname()),
                genreJpaRepository.getByName(genreMongo.getName()));
    }
}
