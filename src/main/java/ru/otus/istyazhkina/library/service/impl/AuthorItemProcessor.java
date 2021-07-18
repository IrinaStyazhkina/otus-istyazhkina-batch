package ru.otus.istyazhkina.library.service.impl;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;
import ru.otus.istyazhkina.library.domain.jpa.AuthorJpa;
import ru.otus.istyazhkina.library.domain.mongo.AuthorMongo;

@Service
public class AuthorItemProcessor implements ItemProcessor<AuthorMongo, AuthorJpa> {

    @Override
    public AuthorJpa process(AuthorMongo authorMongo) {
        return new AuthorJpa(authorMongo.getName(), authorMongo.getSurname());
    }
}
