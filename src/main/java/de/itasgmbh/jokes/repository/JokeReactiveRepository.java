package de.itasgmbh.jokes.repository;

import de.itasgmbh.jokes.model.Joke;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface JokeReactiveRepository extends ReactiveMongoRepository<Joke, String>, JokeReactiveRepositoryCustom{

}
