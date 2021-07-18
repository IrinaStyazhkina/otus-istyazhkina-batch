package ru.otus.istyazhkina.library.service.impl;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;
import ru.otus.istyazhkina.library.domain.jpa.GenreJpa;
import ru.otus.istyazhkina.library.domain.mongo.GenreMongo;

@Service
public class GenreItemProcessor implements ItemProcessor<GenreMongo, GenreJpa> {

    @Override
    public GenreJpa process(GenreMongo genreMongo) {
        return new GenreJpa(genreMongo.getName());
    }
}
