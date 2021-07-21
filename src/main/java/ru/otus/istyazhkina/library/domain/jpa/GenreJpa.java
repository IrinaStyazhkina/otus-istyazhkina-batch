package ru.otus.istyazhkina.library.domain.jpa;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "genres")
public class GenreJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;

    public GenreJpa(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public GenreJpa(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%s\t|\t%s", id, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenreJpa genreJpa = (GenreJpa) o;
        return Objects.equals(id, genreJpa.id) &&
                Objects.equals(name, genreJpa.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
