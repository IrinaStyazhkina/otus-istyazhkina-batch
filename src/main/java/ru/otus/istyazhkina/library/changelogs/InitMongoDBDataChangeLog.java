package ru.otus.istyazhkina.library.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.istyazhkina.library.domain.mongo.AuthorMongo;
import ru.otus.istyazhkina.library.domain.mongo.BookMongo;
import ru.otus.istyazhkina.library.domain.mongo.GenreMongo;
import ru.otus.istyazhkina.library.repository.AuthorMongoRepository;
import ru.otus.istyazhkina.library.repository.BookMongoRepository;
import ru.otus.istyazhkina.library.repository.GenreMongoRepository;

import java.util.ArrayList;
import java.util.List;

@ChangeLog(order = "001")
public class InitMongoDBDataChangeLog {
    private final List<AuthorMongo> authors = new ArrayList<>();
    private final List<GenreMongo> genres = new ArrayList<>();
    private final List<BookMongo> books = new ArrayList<>();

    @ChangeSet(order = "000", id = "dropDB", author = "irinastyazhkina", runAlways = true)
    public void dropDB(MongoDatabase mongoDatabase) {
        mongoDatabase.drop();
    }

    @ChangeSet(order = "001", id = "initAuthors", author = "irinastyazhkina", runAlways = true)
    public void initAuthors(AuthorMongoRepository authorRepository) {
        AuthorMongo Tolstoy = authorRepository.save(new AuthorMongo("Lev", "Tolstoy"));
        AuthorMongo Brodskiy = authorRepository.save(new AuthorMongo("Joseph", "Brodskiy"));
        AuthorMongo Tolkien = authorRepository.save(new AuthorMongo("John", "Tolkien"));
        authors.addAll(List.of(Tolstoy, Brodskiy, Tolkien));
    }

    @ChangeSet(order = "002", id = "initGenres", author = "irinastyazhkina", runAlways = true)
    public void initGenres(GenreMongoRepository genreRepository) {
        GenreMongo novel = genreRepository.save(new GenreMongo("novel"));
        GenreMongo poetry = genreRepository.save(new GenreMongo("poetry"));
        GenreMongo fantasy = genreRepository.save(new GenreMongo("fantasy"));
        GenreMongo fiction = genreRepository.save(new GenreMongo("fiction"));
        genres.addAll(List.of(novel, poetry, fantasy, fiction));
    }

    @ChangeSet(order = "003", id = "initBooks", author = "irinastyazhkina", runAlways = true)
    public void initBooks(BookMongoRepository bookRepository) {
        BookMongo warAndPeace = bookRepository.save(new BookMongo("War and Peace", authors.get(0), genres.get(0)));
        BookMongo rozhdestvenskieStikhi = bookRepository.save(new BookMongo("Rozhdestvenskie stikhi", authors.get(1), genres.get(1)));
        BookMongo theHobbit = bookRepository.save(new BookMongo("The Hobbit", authors.get(2), genres.get(2)));
        books.addAll(List.of(warAndPeace, rozhdestvenskieStikhi, theHobbit));
    }
}
