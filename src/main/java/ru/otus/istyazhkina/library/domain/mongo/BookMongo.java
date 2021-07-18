package ru.otus.istyazhkina.library.domain.mongo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "book")
public class BookMongo {

    @Id
    private String id;
    @Field(name = "title")
    private String title;

    private AuthorMongo authorMongo;
    private GenreMongo genreMongo;


    public BookMongo(String title, AuthorMongo authorMongo, GenreMongo genreMongo) {
        this.title = title;
        this.authorMongo = authorMongo;
        this.genreMongo = genreMongo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookMongo bookMongo = (BookMongo) o;
        return Objects.equals(id, bookMongo.id) &&
                Objects.equals(title, bookMongo.title) &&
                Objects.equals(authorMongo, bookMongo.authorMongo) &&
                Objects.equals(genreMongo, bookMongo.genreMongo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, authorMongo, genreMongo);
    }

    @Override
    public String toString() {
        return "BookJpa{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", authorJpa=" + authorMongo +
                ", genreJpa=" + genreMongo +
                '}';
    }
}
