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
@Document(collection = "authorJpa")
public class AuthorMongo {

    @Id
    private String id;
    @Field(name = "name")
    private String name;
    @Field(name = "surname")
    private String surname;

    public AuthorMongo(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorMongo authorMongo = (AuthorMongo) o;
        return Objects.equals(id, authorMongo.id) &&
                Objects.equals(name, authorMongo.name) &&
                Objects.equals(surname, authorMongo.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname);
    }
}
