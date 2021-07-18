package ru.otus.istyazhkina.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.istyazhkina.library.domain.mongo.GenreMongo;

public interface GenreMongoRepository extends MongoRepository<GenreMongo, String> {
}
