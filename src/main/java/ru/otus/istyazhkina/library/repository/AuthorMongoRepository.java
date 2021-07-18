package ru.otus.istyazhkina.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.istyazhkina.library.domain.mongo.AuthorMongo;

public interface AuthorMongoRepository extends MongoRepository<AuthorMongo, String> {
}
