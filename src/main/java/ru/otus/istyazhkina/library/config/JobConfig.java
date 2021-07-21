package ru.otus.istyazhkina.library.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.data.builder.MongoItemReaderBuilder;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.istyazhkina.library.domain.jpa.AuthorJpa;
import ru.otus.istyazhkina.library.domain.jpa.BookJpa;
import ru.otus.istyazhkina.library.domain.jpa.GenreJpa;
import ru.otus.istyazhkina.library.domain.mongo.AuthorMongo;
import ru.otus.istyazhkina.library.domain.mongo.BookMongo;
import ru.otus.istyazhkina.library.domain.mongo.GenreMongo;
import ru.otus.istyazhkina.library.service.impl.AuthorItemProcessor;
import ru.otus.istyazhkina.library.service.impl.BookItemProcessor;
import ru.otus.istyazhkina.library.service.impl.GenreItemProcessor;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;

@Configuration
@RequiredArgsConstructor
public class JobConfig {

    private static final int CHUNK_SIZE = 5;
    public static final String IMPORT_DATA_JOB_NAME = "importDataJob";

    private final JobBuilderFactory jobBuilderFactory;
    private final MongoTemplate mongoTemplate;
    private final EntityManagerFactory entityManagerFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job importDataJob(Step importGenres, Step importAuthors, Step importBooks) {
        return jobBuilderFactory.get(IMPORT_DATA_JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .flow(importGenres)
                .next(importAuthors)
                .next(importBooks)
                .end()
                .build();
    }

    @Bean
    public Step importGenres(MongoItemReader<GenreMongo> reader, JpaItemWriter<GenreJpa> writer,
                             GenreItemProcessor itemProcessor) {
        return stepBuilderFactory.get("importGenres")
                .<GenreMongo, GenreJpa>chunk(CHUNK_SIZE)
                .reader(reader)
                .processor(itemProcessor)
                .writer(writer)
                .build();
    }

    @Bean
    public Step importAuthors(MongoItemReader<AuthorMongo> reader, JpaItemWriter<AuthorJpa> writer,
                              AuthorItemProcessor itemProcessor) {
        return stepBuilderFactory.get("importAuthors")
                .<AuthorMongo, AuthorJpa>chunk(CHUNK_SIZE)
                .reader(reader)
                .processor(itemProcessor)
                .writer(writer)
                .build();
    }

    @Bean
    public Step importBooks(MongoItemReader<BookMongo> reader, JpaItemWriter<BookJpa> writer,
                            BookItemProcessor itemProcessor) {
        return stepBuilderFactory.get("importBooks")
                .<BookMongo, BookJpa>chunk(CHUNK_SIZE)
                .reader(reader)
                .processor(itemProcessor)
                .writer(writer)
                .build();
    }

    @StepScope
    @Bean
    public MongoItemReader<GenreMongo> genreReader() {
        return new MongoItemReaderBuilder<GenreMongo>()
                .name("genreReader")
                .template(mongoTemplate)
                .jsonQuery("{}")
                .targetType(GenreMongo.class)
                .sorts(new HashMap<>())
                .build();
    }

    @StepScope
    @Bean
    public MongoItemReader<AuthorMongo> authorReader() {
        return new MongoItemReaderBuilder<AuthorMongo>()
                .name("authorReader")
                .template(mongoTemplate)
                .jsonQuery("{}")
                .targetType(AuthorMongo.class)
                .sorts(new HashMap<>())
                .build();
    }

    @StepScope
    @Bean
    public MongoItemReader<BookMongo> bookReader() {
        return new MongoItemReaderBuilder<BookMongo>()
                .name("bookReader")
                .template(mongoTemplate)
                .jsonQuery("{}")
                .targetType(BookMongo.class)
                .sorts(new HashMap<>())
                .build();
    }

    @StepScope
    @Bean
    public JpaItemWriter<GenreJpa> genreWriter() {
        return new JpaItemWriterBuilder<GenreJpa>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }

    @StepScope
    @Bean
    public JpaItemWriter<AuthorJpa> authorWriter() {
        return new JpaItemWriterBuilder<AuthorJpa>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }

    @StepScope
    @Bean
    public JpaItemWriter<BookJpa> bookWriter() {
        return new JpaItemWriterBuilder<BookJpa>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }
}
