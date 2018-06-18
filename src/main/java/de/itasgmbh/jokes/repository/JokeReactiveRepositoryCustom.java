package de.itasgmbh.jokes.repository;

import de.itasgmbh.jokes.model.Joke;
import org.springframework.data.mongodb.repository.Query;
import reactor.core.publisher.Flux;

import java.util.List;

public interface JokeReactiveRepositoryCustom {

    Flux<Joke> findRandom(int quantity, String language);

    Flux<Joke> findRandom(int quantity);
}
