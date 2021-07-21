package ru.otus.istyazhkina.library.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;
import ru.otus.istyazhkina.library.domain.jpa.BookJpa;
import ru.otus.istyazhkina.library.domain.mongo.AuthorMongo;
import ru.otus.istyazhkina.library.domain.mongo.BookMongo;
import ru.otus.istyazhkina.library.domain.mongo.GenreMongo;


@Service
@RequiredArgsConstructor
public class BookItemProcessor implements ItemProcessor<BookMongo, BookJpa> {

    private final GenreService genreService;
    private final AuthorService authorService;

    @Override
    public BookJpa process(BookMongo bookMongo) {
        AuthorMongo authorMongo = bookMongo.getAuthorMongo();
        GenreMongo genreMongo = bookMongo.getGenreMongo();
        return new BookJpa(bookMongo.getTitle(),
                authorService.getAuthorJpa(authorMongo.getName(), authorMongo.getSurname()),
                genreService.getGenreJpa(genreMongo.getName()));
    }
}
