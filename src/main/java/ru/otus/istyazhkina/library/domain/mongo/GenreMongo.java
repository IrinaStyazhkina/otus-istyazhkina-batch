package ru.otus.istyazhkina.library.domain.mongo;

import lombok.AllArgsConstructor;
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
@Document(collection = "genreJpa")
public class GenreMongo {

    @Id
    private String id;
    @Field(name = "name")
    private String name;

    public GenreMongo(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return id + "," + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenreMongo genreMongo = (GenreMongo) o;
        return Objects.equals(id, genreMongo.id) &&
                Objects.equals(name, genreMongo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
