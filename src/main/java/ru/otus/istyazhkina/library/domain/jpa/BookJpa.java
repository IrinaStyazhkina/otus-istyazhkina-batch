package ru.otus.istyazhkina.library.domain.jpa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class BookJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;

    @ManyToOne(targetEntity = AuthorJpa.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private AuthorJpa authorJpa;

    @ManyToOne(targetEntity = GenreJpa.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private GenreJpa genreJpa;

    public BookJpa(String title, AuthorJpa authorJpa, GenreJpa genreJpa) {
        this.title = title;
        this.authorJpa = authorJpa;
        this.genreJpa = genreJpa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookJpa bookJpa = (BookJpa) o;
        return Objects.equals(id, bookJpa.id) &&
                Objects.equals(title, bookJpa.title) &&
                Objects.equals(authorJpa, bookJpa.authorJpa) &&
                Objects.equals(genreJpa, bookJpa.genreJpa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, authorJpa, genreJpa);
    }

    @Override
    public String toString() {
        return String.format("%s\t|\t%s\t|\t%s\t|\t%s", id, title, authorJpa.getName() + " " + authorJpa.getSurname(), genreJpa.getName());
    }
}
