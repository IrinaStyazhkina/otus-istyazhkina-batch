package ru.otus.istyazhkina.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.istyazhkina.library.domain.mongo.BookMongo;

public interface BookMongoRepository extends MongoRepository<BookMongo, String> {
}
