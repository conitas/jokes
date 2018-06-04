package de.itasgmbh.jokes.repository;

import de.itasgmbh.jokes.model.Joke;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JokeRepository extends MongoRepository<Joke,Long>, JokeRepositoryCustom {
}
